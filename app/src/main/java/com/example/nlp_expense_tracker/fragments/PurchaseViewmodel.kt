package com.example.nlp_expense_tracker.fragments

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.nlp_expense_tracker.ADD_RECEIPT_RESULT_OK
import com.example.nlp_expense_tracker.Database.ReceiptDao
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

    fun onAddResult(result: Int){
        when (result){
            ADD_RECEIPT_RESULT_OK ->showReceiptSavedConfirmation("Receipt is saved")
        }
    }
    private fun showReceiptSavedConfirmation (text: String) = viewModelScope.launch {
        tasksEventChannel.send(TasksEvent.ShowReceiptSavedConfirmation(text))
    }

    sealed class TasksEvent {
        data class ShowReceiptSavedConfirmation(val msg: String) : TasksEvent()
    }
}