package com.example.nlp_expense_tracker.fragments.Camera

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager
import com.example.nlp_expense_tracker.R
import com.example.nlp_expense_tracker.ReceiptsViewModel
import com.google.android.material.snackbar.Snackbar
import com.google.mlkit.vision.common.InputImage
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import java.io.FileNotFoundException

@AndroidEntryPoint
class Scanner : Fragment() {

    private lateinit var imageView: ImageView
    private lateinit var photoImage: Bitmap
    private lateinit var mlkitImage: InputImage
    private lateinit var editStore: EditText
    private lateinit var editDate: EditText
    private lateinit var editTotal: EditText
    private lateinit var pb: ProgressBar
    private lateinit var checkBox: CheckBox
    private val REQUEST_CODE_KAMERA = 42
    private val REQUEST_CODE_GALLERY = 69
    private val CAMERA_PERMISSION_CODE = 100
    private lateinit var  receiptsViewModel: ReceiptsViewModel
    private val viewModel: ScannerViewModel by viewModels()
    private lateinit var viewPager: ViewPager
    lateinit var imageURI: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?):
            View {val view: View = inflater.inflate(R.layout.fragment_scan, container, false)

        val cameraBtn: Button = view.findViewById(R.id.cameraBtn) /// Initiate all our buttons and layout ID's
        val galleryBtn: Button = view.findViewById(R.id.galleryBtn)
        val confirmButton: Button = view.findViewById(R.id.confirmbutton)
        imageView = view.findViewById(R.id.imageView)
        editStore = view.findViewById(R.id.editStore)
        editTotal =view.findViewById(R.id.editTotal)
        editDate = view.findViewById(R.id.editDate)
        receiptsViewModel = ReceiptsViewModel()
        pb = view.findViewById(R.id.progressBar)
        viewPager = requireActivity().findViewById(R.id.viewPager)

        
        //Button Picture
        cameraBtn.setOnClickListener {
            showDialogCamera()

        }
        //Button Gallery
        galleryBtn.setOnClickListener{
         showDialogGallery()
        }
        //Confirm Button, mit dem der Text aus den EditText's übermittel wird.
        confirmButton.setOnClickListener{
            viewModel.onSaveClick()
            viewPager.setCurrentItem(2)
        }

        editDate.addTextChangedListener {
            viewModel.date = it.toString() //Updates viewmodel with the new value
        }
        editStore.addTextChangedListener {
            viewModel.store = it.toString()
        }
        editTotal.addTextChangedListener {
            viewModel.total = it.toString()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.tasksEvent.collect { event->
                when (event) {
                    is ScannerViewModel.TasksEvent.ShowInvalidInputMessage->{
                        Snackbar.make(requireView(),event.msg,Snackbar.LENGTH_LONG).show()
                    }
                    is ScannerViewModel.TasksEvent.NavigateBackWithResult ->{
                        setFragmentResult("add_receipt_request",
                            bundleOf("add_receipt_request" to event.result)
                        )
                    }
                }
            }
        }


        return view
    }



    override fun onResume() { //clears textfields when switching between tabs
        super.onResume()
        editStore.setText("")
        editDate.setText("")
        editTotal.setText("")
    }


    private fun uploadAction(data: Intent) {
        try {
            val resolver = requireActivity().contentResolver
            val stream = resolver!!.openInputStream(data.data!!)
            if (::photoImage.isInitialized) photoImage.recycle()
            photoImage = BitmapFactory.decodeStream(stream)
            mlkitImage = InputImage.fromBitmap(photoImage,0)
            imageView.setImageBitmap(photoImage)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }
    private fun cameraAction() {
        try {
            Picasso.get().load(receiptsViewModel.imageURI).into(imageView)
            mlkitImage = InputImage.fromFilePath(requireActivity(), receiptsViewModel.imageURI)
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        }
    }


    private fun textRecognitionAction() {

        var text = ""
        receiptsViewModel.textDetector.process(mlkitImage)
            .addOnSuccessListener {
                pb.visibility = View.GONE
                for (block in it.textBlocks) text += block.text + "\n"
                val receipts = receiptsViewModel.getReceipts(text)
                editTotal.setText(receipts.total.replace("EUR"," €"), TextView.BufferType.EDITABLE)
                editStore.setText(receipts.store.lowercase().replaceFirstChar { it.uppercase() }, TextView.BufferType.EDITABLE)
                editDate.setText(receipts.date, TextView.BufferType.EDITABLE)
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_GALLERY -> uploadAction(data!!)
                REQUEST_CODE_KAMERA -> cameraAction()
            }
            textRecognitionAction()
        }
    }

    private fun checkPermission(permission: String, requestCode: Int) {
        if (ContextCompat.checkSelfPermission(requireActivity(), permission) == PackageManager.PERMISSION_DENIED) {

            // Requesting the permission
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(permission), requestCode)
        } else {
            Toast.makeText(requireActivity(), "Permission already granted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startCamera() {
        pb.visibility = View.VISIBLE
        checkPermission(Manifest.permission.CAMERA,CAMERA_PERMISSION_CODE)
        startActivityForResult(
            receiptsViewModel.cameraIntent(requireActivity()),
            REQUEST_CODE_KAMERA
        )
    }
    private fun startGallery(){
        pb.visibility = View.VISIBLE
        checkPermission(Manifest.permission.CAMERA,
            CAMERA_PERMISSION_CODE)
        startActivityForResult(receiptsViewModel.uploadIntent(),REQUEST_CODE_GALLERY)
    }

    private fun showDialogCamera() {
        if (dialogStatus) {
            startCamera()
            return
        }
        val mainView = requireActivity().layoutInflater.inflate(R.layout.alert, null)
        checkBox = mainView.findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            storeDialogStatus(compoundButton.isChecked)
        }
        AlertDialog.Builder(context)
            .setView(mainView)
            .setMessage("Please make sure to put the total on the bottom right corner and the receipts header at the top. \nAn example is shown below.")
            .setPositiveButton("Alright, got it!") { _, _ -> startCamera() }
            .create()
            .show()
    }

    fun showDialogGallery(){
        if (dialogStatus) {
            startGallery()
            return
        }
        val mainView = requireActivity().layoutInflater.inflate(R.layout.alert, null)
        checkBox = mainView.findViewById<CheckBox>(R.id.checkBox)
        checkBox.setOnCheckedChangeListener { compoundButton, b ->
            storeDialogStatus(compoundButton.isChecked)
        }
        AlertDialog.Builder(context)
            .setView(mainView)
            .setMessage("Please make sure put the total on the bottom right corner and the receipts header at the top. \nAn example is shown below.")
            .setPositiveButton("Alright, got it!") { _, _ -> startGallery() }
            .create()
            .show()
    }

    private fun storeDialogStatus(isChecked: Boolean) {
        val mSharedPreferences = requireActivity().getSharedPreferences("CheckItem", AppCompatActivity.MODE_PRIVATE)
        val mEditor = mSharedPreferences.edit()
        mEditor.putBoolean("item", isChecked)
        mEditor.apply()
    }

    private val dialogStatus: Boolean
        private get() {
            val mSharedPreferences = requireActivity().getSharedPreferences("CheckItem",
                AppCompatActivity.MODE_PRIVATE
            )
            return mSharedPreferences.getBoolean("item", false)
        }

}

