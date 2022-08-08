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
class DetailViewFragment (): Fragment() {

    private lateinit var binding : FragmentDetailViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val user = this.arguments?.getSerializable("user") as User
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail_view,container,false)

        binding.apply {
            name.text = user.name.toString()
            age.text = "Age: " + user.age.toString()
            des.text = user.des.toString()
            phone.text ="Phone: " + user.phone.toString()

        }
        binding.edit.setOnClickListener {
            val formFragment = FormFragment()
            val bundle = Bundle()
            bundle.putString("action","Edit")
            bundle.putSerializable("user", arrayOf(user.name,user.des,user.age,user.phone,user.id))
            formFragment.arguments = bundle
            val fm : FragmentManager = ( context as AppCompatActivity).supportFragmentManager

            val ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_content_main,formFragment)
            ft.commit()
        }

        return binding.root
    }


}