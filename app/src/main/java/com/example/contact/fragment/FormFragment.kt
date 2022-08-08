package com.example.contact.fragment

import android.Manifest
import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.contact.R
import com.example.contact.activity.DashBoard
import com.example.contact.databinding.FragmentFormBinding
import com.example.contact.model.User
import com.example.contact.viewmodel.ModelViewModal
import java.io.ByteArrayOutputStream
import java.util.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

class FormFragment() : Fragment() {
    // TODO: Rename and change types of parameter

    private lateinit var binding : FragmentFormBinding
    var FILE = 2
    lateinit var user:Array<Any>
    var image:String=""
    lateinit var name: EditText; lateinit var age: EditText
    lateinit var des: EditText //lateinit var but: button
    lateinit var button:Button
    lateinit var phoneNo: EditText; lateinit var btnFile: Button
     var iD:Int =1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form,container,false)

        name = binding.name
        age =  binding.age
        des = binding.desc
        button = binding.insertButton
        phoneNo = binding.phone
        btnFile = binding.btOpenGallery

        val action = this.arguments?.getString("action").toString()
//        this.arguments = null

        if(action=="Edit"){
            user =  this.arguments?.getSerializable("user") as Array<Any>
//            image = user.img!!
           iD = (user)[4] as Int

            name.setText(user[0].toString())
            age.setText(user[2].toString())
            des.setText(user[1].toString())
            phoneNo.setText(user[3].toString())
            button.text = getString(R.string.update)
        }
        btnFile.setOnClickListener {
            openGallery()
        }
        val viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
        ).get(ModelViewModal::class.java)

        button.setOnClickListener {
            val valid: Boolean = check()

            if (action != "Edit") {
                    if (valid) {
                        viewModal.addUser(
                            User(
                                name.text.toString(),
                                age.text.toString(),
                                image,
                                des.text.toString(),
                                phoneNo.text.toString()
                            )
                        )
//                    Toast.makeText(this, name.text.toString(), Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this.context, DashBoard::class.java))
//                    this.context.finish()
                    }
            }

            else {
                if (valid) {
                    var updatedUser:User
                        viewModal.loadImage(iD).invokeOnCompletion {
                            if(image==""){
                                image=viewModal.image
                            }
                            updatedUser = User(
                                name.text.toString(),
                                age.text.toString(),
                                image,
                                des.text.toString(),
                                phoneNo.text.toString()
                            )

                            if (iD == null) {
                                updatedUser.id = -1
                            } else {
                                updatedUser.id = iD
                            }
                            viewModal.updateUser(
                                updatedUser
                            )
                        }

//                     Toast.makeText(this, name.text.toString(), Toast.LENGTH_SHORT).show
                        startActivity(Intent(this.context, DashBoard::class.java))
                    }
                }
            }
    return binding.root
    }

    private fun openGallery(){
        Intent(Intent.ACTION_GET_CONTENT).also {intent->
            intent.type = "image/*"
            intent.resolveActivity((this.context as Context).packageManager)?.also {
                startActivityForResult(intent,FILE)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data !=null) {
                val bitmap: Bitmap? =
                    MediaStore.Images.Media.getBitmap((this.context as Context).contentResolver, data.data)
                val stream = ByteArrayOutputStream()
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    bitmap!!.compress(Bitmap.CompressFormat.PNG, 100, stream)
                }
                val byteArray = stream.toByteArray()
                val encoder = Base64.getEncoder()
                image = encoder.encodeToString(byteArray)
        }
    }

    override fun onPause() {
        super.onPause()
        checkCameraPermission()
    }

//    override fun onSaveInstanceState(outState: Bundle) {}


    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this.context as Context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.context as Activity,
                arrayOf(Manifest.permission.CAMERA),
                100)
        }
    }

    private fun check():Boolean{
        if(name.text.toString().isEmpty()){
            name.error = "This Field is Required"
            return false
        }
        return true
    }



}
