package com.example.nlp_expense_tracker

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import com.example.nlp_expense_tracker.Database.Receipts
import com.example.nlp_expense_tracker.fragments.findFloat
import com.example.nlp_expense_tracker.fragments.getBetrag
import com.example.nlp_expense_tracker.fragments.getStore
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizerOptions
import java.io.File
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject


class ReceiptsViewModel () {


    lateinit var imageURI: Uri
    var textDetector = TextRecognition.getClient(TextRecognizerOptions.DEFAULT_OPTIONS)


    fun uploadIntent(): Intent {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        return intent
    }

    fun cameraIntent(context: Context): Intent {
        val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        val imageFileName = "IMG_" + timeStamp + "_"
        val storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val filephoto = File.createTempFile(
            imageFileName, /* prefix */
            ".jpg", /* suffix */
            storageDir      /* directory */
        )
        imageURI = FileProvider.getUriForFile(context, "com.example.nlp_expense_tracker.fileprovider", filephoto)
        val pictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageURI)
        return pictureIntent
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getReceipts(text: String): Receipts {
        val originalResult = text.findFloat()
        if (originalResult.isEmpty()) return Receipts("test","test","test")
        else {
            val receipts = Receipts("test","test","test")
            val currentDateTime = LocalDateTime.now()
            receipts.total = text.getBetrag()
            receipts.store = text.getStore()
            receipts.date = currentDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
            return receipts
        }
    }



}