package com.example.contact.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.contact.R
import com.example.contact.databinding.ActivityFormBinding
import com.example.contact.model.User
import com.example.contact.viewmodel.ModelViewModal
import java.io.ByteArrayOutputStream
import java.util.*


class FormActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFormBinding
    lateinit var viewModal: ModelViewModal
    var FILE = 2
    var image:String=""
    lateinit var name: EditText; lateinit var age:EditText
    lateinit var des:EditText; lateinit var But:Button
    lateinit var Phone:EditText; lateinit var btnFile:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_form)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_form)
//
//        val name = findViewById<EditText>(R.id.name)
//        val age = findViewById<EditText>(R.id.age)
//        val des = findViewById<EditText>(R.id.desc)
//        val But = findViewById<Button>(R.id.insertButton)
//        val Phone = findViewById<EditText>(R.id.phone)



        name = binding.name
        age =  binding.age
        des = binding.desc
         But = binding.insertButton
         Phone = binding.phone
         btnFile = binding.btOpenGallery

        val action = intent.getStringExtra("action")

        if(action=="Edit"){
            name.setText( intent.getStringExtra("name").toString())
            age.setText( intent.getStringExtra("age").toString())
            des.setText( intent.getStringExtra("des").toString())
            Phone.setText(intent.getStringExtra("phone").toString())
            But.text = "Update"
        }

//        val btnFile = findViewById<Button>(R.id.btOpenGallery)

        btnFile.setOnClickListener {
            openGallery()
        }

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ModelViewModal::class.java)

        But.setOnClickListener {
            var valid: Boolean = check()

                if (action != "Edit") {
                    if(valid){
                    viewModal.addUser(
                         User(
                            name.text.toString(),
                             age.text.toString(),
                             image,
                             des.text.toString(),
                          Phone.text.toString()
                    ))
                    Toast.makeText(this, name.text.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(applicationContext, DashBoard::class.java))
                    this.finish()
                } }

                else {
                    if (valid) {
                        var user = User(
                            name.text.toString(),
                            age.text.toString(),
                            image,
                            des.text.toString(),
                            Phone.text.toString()
                        )
                        user.id = intent.getIntExtra("id", -1)
                        viewModal.updateUser(
                            user
                        )
                        Toast.makeText(this, name.text.toString(), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(applicationContext, DashBoard::class.java))
                        this.finish()
                    }
                }
        }
    }

    private fun openGallery(){
        Intent(Intent.ACTION_GET_CONTENT).also {intent->
            intent.type = "image/*"
            intent.resolveActivity(packageManager)?.also {
                startActivityForResult(intent,FILE)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
                if (data!=null) {
                    val bitmap: Bitmap? =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, data.getData())
                    val stream = ByteArrayOutputStream()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                    }
                    val byteArray = stream.toByteArray()
                    val encoder = Base64.getEncoder()
                    image = encoder.encodeToString(byteArray)
                }
        }
    }

    override fun onPause() {
        super.onPause()
        checkCameraPermission()
    }


    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CAMERA),
                100)
        }
    }

    fun check():Boolean{
        if(name.text.toString().isEmpty()){
            name.error = "This Field is Required"
            return false
        }
        return true
    }

}
