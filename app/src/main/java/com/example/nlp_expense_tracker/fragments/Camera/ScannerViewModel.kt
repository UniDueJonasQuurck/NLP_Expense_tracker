package com.example.nlp_expense_tracker.fragments.Camera

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.ADD_RECEIPT_RESULT_OK
import com.example.nlp_expense_tracker.Database.ReceiptDao
import com.example.nlp_expense_tracker.Database.Receipts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val receiptDao: ReceiptDao,
    private val state: SavedStateHandle):
    ViewModel(){

    private val tasksEventChannel = Channel<TasksEvent>()

    val tasksEvent = tasksEventChannel.receiveAsFlow()

    val receipt = state.get<Receipts>("receipt")

    var store = state.get<String>("store")?: receipt?.store ?:""
    set(value){
        field = value
        state.set("store",value)
    }
    var total = state.get<String>("amount")?: receipt?.total ?:""
        set(value){
            field = value
            state.set("amount",value)
        }
    var date = state.get<String>("date")?: receipt?.date ?:""
        set(value){
            field = value
            state.set("date",value)
        }

    fun onSaveClick() {
        if (store.isBlank()){
            showInvalidInputMessage("Store cannot be empty")
        }
        if (date.isBlank()){
            showInvalidInputMessage("Date cannot be empty")
        }
        if (total.isBlank()){
            showInvalidInputMessage("Total cannot be empty")
        }
        else{
            val newReceipt = Receipts(store = store,total = total,date = date)
            createReceipt(newReceipt)
        }
    }
    private fun createReceipt(receipts: Receipts) = viewModelScope.launch {
            receiptDao.insert(receipts)

        tasksEventChannel.send(TasksEvent.NavigateBackWithResult(ADD_RECEIPT_RESULT_OK))
        showReceiptSavedConfirmation("Receipt has been saved")
    }


    private fun showInvalidInputMessage(text: String) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.ShowInvalidInputMessage(text))
    }
    private fun showReceiptSavedConfirmation(text: String) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.ShowInvalidInputMessage(text))
    }


    sealed class TasksEvent{
        data class ShowInvalidInputMessage(val msg: String) : TasksEvent()
        data class NavigateBackWithResult (val result: Int): TasksEvent()
        data class ShowReceiptSavedConfirmation(val msg: String) : ScannerViewModel.TasksEvent()
    }



}