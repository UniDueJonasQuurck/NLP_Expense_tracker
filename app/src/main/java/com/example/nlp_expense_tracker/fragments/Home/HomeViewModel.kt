package com.example.nlp_expense_tracker.fragments.Home

import android.graphics.Color
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
class HomeViewModel @Inject constructor(
    private val receiptDao: ReceiptDao
): ViewModel() {

    val latestEntryStore = receiptDao.getLatestEntryStore().asLiveData()
    val latestEntryTotal = receiptDao.getLatestEntryTotal().asLiveData()
    val secondLatestEntryStore = receiptDao.getSecondLatestEntryStore().asLiveData()
    val secondLatestEntryTotal = receiptDao.getSecondLatestEntryTotal().asLiveData()
    val thirdLatestEntryStore = receiptDao.getThirdLatestEntryStore().asLiveData()
    val thirdLatestEntryTotal = receiptDao.getThirdLatestEntryTotal().asLiveData()
    val fourthLatestEntryStore = receiptDao.getFourthLatestEntryStore().asLiveData()
    val fourthLatestEntryTotal = receiptDao.getFourthLatestEntryTotal().asLiveData()



}