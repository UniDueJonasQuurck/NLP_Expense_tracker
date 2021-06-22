package com.example.nlp_expense_tracker.fragments.History

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nlp_expense_tracker.ADD_RECEIPT_RESULT_OK
import com.example.nlp_expense_tracker.Database.ReceiptDao
import com.example.nlp_expense_tracker.Database.Receipts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PurchaseViewmodel @Inject constructor(
    private val receiptDao: ReceiptDao
        ): ViewModel() {
    private val tasksEventChannel = Channel<TasksEvent>()
    val addTaskEvent = tasksEventChannel.receiveAsFlow()
    val receipts = receiptDao.getAllReceipts().asLiveData()
    val totalSum = receiptDao.getSum().asLiveData()

    fun onAddResult(result: Int){
        when (result){
            ADD_RECEIPT_RESULT_OK ->showReceiptSavedConfirmation("Receipt has been saved")
        }
    }
    private fun showReceiptSavedConfirmation (text: String) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.ShowReceiptSavedConfirmation(text))
    }

    fun onSwipe (receipts: Receipts) = viewModelScope.launch {
        receiptDao.delete(receipts)
        tasksEventChannel.send(TasksEvent.ShowUndoDelete(receipts))
    }
    fun unDoDeleteClick (receipts: Receipts) = viewModelScope.launch {
        receiptDao.insert(receipts)
    }

    sealed class TasksEvent {
        data class ShowReceiptSavedConfirmation(val msg: String) : TasksEvent()
        data class ShowUndoDelete(val receipts: Receipts) : TasksEvent()
    }
}