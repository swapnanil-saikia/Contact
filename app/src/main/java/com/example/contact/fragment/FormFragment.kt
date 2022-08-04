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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
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
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class FormFragment(var context: Activity) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding : FragmentFormBinding
    var FILE = 2
    lateinit var user:User
    var image:String=""
    lateinit var name: EditText; lateinit var age: EditText
    lateinit var des: EditText //lateinit var but: button
    lateinit var button:Button
    lateinit var phoneNo: EditText; lateinit var btnFile: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_form, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_form,container,false)

        name = binding.name
        age =  binding.age
        des = binding.desc
        button = binding.insertButton
        phoneNo = binding.phone
        btnFile = binding.btOpenGallery

        val action = this.arguments?.getString("action").toString()

        if(action=="Edit"){

            user =  this.arguments?.getSerializable("user") as User
            image = user.img!!
            name.setText( user.name)
            age.setText( user.age)
            des.setText(  user.des)
            phoneNo.setText(user.phone)
            button.text = "Update"
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
                if(valid){
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
                } }

            else {
                if (valid) {
                    val updatedUser = User(
                        name.text.toString(),
                        age.text.toString(),
                        image,
                        des.text.toString(),
                        phoneNo.text.toString()
                    )
                    if(user.id==null){
                        updatedUser.id=-1
                    }
                    else{
                        updatedUser.id=user.id
                    }
                    println(updatedUser.name+"    "+user.name)
                    viewModal.updateUser(
                        updatedUser
                    )
//                    Toast.makeText(this, name.text.toString(), Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this.context, DashBoard::class.java))
//                    this.context.finish()
                }
            }
        }
    return binding.root
    }

    companion object {
       //d number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            FormFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }

    private fun openGallery(){
        Intent(Intent.ACTION_GET_CONTENT).also {intent->
            intent.type = "image/*"
            intent.resolveActivity(this.context.packageManager)?.also {
                startActivityForResult(intent,FILE)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == AppCompatActivity.RESULT_OK && data !=null) {
                val bitmap: Bitmap? =
                    MediaStore.Images.Media.getBitmap(this.context.contentResolver, data.data)
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


    private fun checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(this.context, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this.context,
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
