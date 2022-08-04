package com.example.contact.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.DataBindingUtil
import com.example.contact.R
import com.example.contact.databinding.ActivityDetailsBinding

class Details : AppCompatActivity() {

    private lateinit var binding : ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_details)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_details)
//

//        var imageView: ImageView = findViewById<ImageView>(R.id.ivBanner)
//        val nameView: TextView = findViewById<TextView>(R.id.name)
//        val ageView: TextView = findViewById<TextView>(R.id.age)
//        val detailsView = findViewById<TextView>(R.id.des)
//        val phView = findViewById<TextView>(R.id.phone)

//        val id = intent.getStringExtra("ima")?.toUri()
//        println(id)
//        intent.get
//        var name = intent.getSerializableExtra("name")
//        val age = intent.getSerializableExtra("age")
//        val details = intent.getSerializableExtra("des")
//        val ph = intent.getSerializableExtra("phone")

        var userName = intent.getSerializableExtra("name")
        val userAge = intent.getSerializableExtra("age")
        val userDetails = intent.getSerializableExtra("des")
        val userPh = intent.getSerializableExtra("phone")
        val id=intent.getIntExtra("id",-1)

        binding.apply {
            name.text = userName.toString()
            age.text = "Age: " + userAge.toString()
            des.text = userDetails.toString()
            phone.text ="Phone: " + userPh.toString()

        }
        binding.edit.setOnClickListener {
            startActivity(
                Intent(this, FormActivity::class.java)
                    .putExtra("name",userName.toString())
                    .putExtra("age",userAge.toString())
                    .putExtra("des",userDetails.toString())
                    .putExtra("phone",userPh.toString())
                    .putExtra("id",id)
                    .putExtra("action","Edit")
            )
        }
//        viewModal.loadImage(id as Int)

//            var img = viewModal.image
//            println(img)
//
//            var decodedByte: ByteArray? = null
//            var bmp: Bitmap? = null
//            if (img != "") {
//                decodedByte = Base64.decode(img, Base64.DEFAULT)
//                bmp = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
//                imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp!!, 120, 120, false));
////
//            }
//        viewModal.image.observe(this) { img ->
//            img?.let {
//                println("qqqqqqqqqq"+img)
//                var decodedByte: ByteArray? = null
//                var bmp: Bitmap? = null
//                if (img != "") {
//                    decodedByte = Base64.decode(img, Base64.DEFAULT)
//                    bmp = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
//        val bitmap: Bitmap? =
//            MediaStore.Images.Media.getBitmap(this.contentResolver,id)
//        println("ajkjkjkaj"+bitmap)
//                    imageView.setImageURI(id);
//                    imageView.setImageBitmap(bitmap)
//
//                }
//
//            }
//        }
//
//        var decodedByte: ByteArray? =null
//        var bmp: Bitmap? =null
//        if (image!="") {
//            decodedByte = Base64.decode(image, Base64.DEFAULT)
//            bmp = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
//            imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp!!, 120, 120, false));
//
//        }


//        imageView.setImageBitmap(Bitmap.createScaledBitmap(image, 120, 120, false));

//        imageView.setImageResource(R.drawable.download);


//        nameView.text = name.toString()
//        ageView.text = "Age: "+age.toString()
//        detailsView.text = details.toString()
//        phView.text = "Ph: "+ph.toString()

    }

   }
