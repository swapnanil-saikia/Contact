package com.example.contact.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.example.contact.R
import com.example.contact.activity.DashBoard
import com.example.contact.activity.FormActivity
import com.example.contact.databinding.FragmentDetailViewBinding
import com.example.contact.model.User


/**
 * A simple [Fragment] subclass.
 * Use the [DetailViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailViewFragment (var context:AppCompatActivity): Fragment() {

    private lateinit var binding : FragmentDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_detail_view, container, false)

//        var bundle = arguments?.getBundle("user")


//        val user = arguments?.getBundle( "user")
        val user = this.arguments?.getSerializable("user") as User
//        val userPh = intent.getSerializableExtra("phone")
//        val id=intent.getIntExtra("id",-1)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_view,container,false)

        binding.apply {
            name.text = user.name.toString()
            age.text = "Age: " + user.age.toString()
            des.text = user.des.toString()
            phone.text ="Phone: " + user.phone.toString()

        }
        binding.edit.setOnClickListener {
//            startActivity(
//                Intent(context, FormActivity::class.java)
//                    .putExtra("name",user.name.toString())
//                    .putExtra("age",user.age.toString())
//                    .putExtra("des",user.des.toString())
//                    .putExtra("phone",user.phone.toString())
//                    .putExtra("id",user.id)
//                    .putExtra("action","Edit")
//            )

            val formFragment = FormFragment(this.context)
            val bundle = Bundle()
            bundle.putSerializable("user",user)
            bundle.putString("action","Edit")
            formFragment.arguments = bundle
            val fm : FragmentManager = ( this.context as AppCompatActivity).supportFragmentManager

            val ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_content_main,formFragment)
            ft.commit()
        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailViewFragment.
         */
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            DetailViewFragment().apply {
//                arguments = Bundle().apply {
//                }
//            }
//    }
    }

//     fun onBackPressed(){
//        super.onBackPressed()
//    };
}