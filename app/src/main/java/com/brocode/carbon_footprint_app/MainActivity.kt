package com.brocode.carbon_footprint_app

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Toast
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

private const val REQUEST_CODE=42
private lateinit var photofile: File
private const val FILE_NAME="photo.jpg"
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        capture.setOnClickListener {
            handleImageCapture()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode== REQUEST_CODE && resultCode== Activity.RESULT_OK){
            val takenImage= data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(takenImage)
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    private fun handleImageCapture(){
        val takePictureIntent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        photofile=getPhotoFile()

        val fileProvider=FileProvider.getUriForFile(this, "com.brocode.carbon_footprint_app.fileprovider", photofile)
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileProvider)

        if(takePictureIntent.resolveActivity(this.packageManager)!=null){
            startActivityForResult(takePictureIntent, REQUEST_CODE)
        }else{
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show()
        }
    }

    private fun getPhotoFile():File{
        val storageDirectory=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(FILE_NAME, ".jpg", storageDirectory)
    }
}