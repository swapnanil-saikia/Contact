package com.example.contact.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
//import android.util.Log\
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contact.R
import com.example.contact.activity.DashBoard
import com.example.contact.activity.FormActivity
import com.example.contact.activity.ModelAdapter
import com.example.contact.activity.NoteClickInterface
import com.example.contact.databinding.FragmentDashBinding
import com.example.contact.model.User
import com.example.contact.viewmodel.ModelViewModal
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


class DashFragment(var viewModel: ModelViewModal, var context: DashBoard) : Fragment(), NoteClickInterface,
    OnQueryTextListener {
    // TODO: Rename and change types of parameters
//    lateinit var viewModal: ModelViewModal

    lateinit var filtered : List<User>
    lateinit var adapter: ModelAdapter
    private lateinit var binding : FragmentDashBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dash, container, false)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dash,container,false)

//        val search = view.findViewById<SearchView>(R.id.search)
//        val recycle = view.findViewById<RecyclerView>(R.id.recyclecon)
//        val button = view.findViewById<FloatingActionButton>(R.id.button)

        val search = binding.search
        val recycle = binding.recyclecon
        val button = binding.button

        search.setOnQueryTextListener(this)

        recycle.setLayoutManager(LinearLayoutManager(this.context))

        button.setOnClickListener {
//            startActivity(Intent(this.context, FormActivity::class.java))
//            this.finish()

            val formFragment = FormFragment(this.context)
            val bundle = Bundle()
            bundle.putString("action","Add")
            formFragment.arguments = bundle
            val fm : FragmentManager = ( this.context as FragmentActivity).supportFragmentManager

            val ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_content_main,formFragment)
            ft.commit()

        }

        adapter = ModelAdapter(this.context,this)
        recycle.adapter = adapter

        this.viewModel.allUser.observe(this, Observer { list ->
            list?.let {

                // on below line we are updating our list.

                filtered = list
                adapter.updateList(list)

//                adapter.notifyDataSetChanged()

            }
        })
        search.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filteredList(newText)
                return true
            }
        })



        return binding.root
    }

    fun filteredList(newText: String) {
        var filteredList = ArrayList<User>()
        for(item in this.filtered){
            if(item.name.toLowerCase(Locale.ROOT).contains(newText.toLowerCase(Locale.ROOT))){
                filteredList.add(item)
            }
        }

        adapter.updateList(filteredList as List<User>)
//        adapter.notifyDataSetChanged()
    }
    companion object {
        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String)
//        =
//            DashFragment().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
    }

    override fun onDeleteIconClick(model: User) {
//            // in on note click method we are calling delete
//            // method from our view modal to delete our not.
            this.viewModel.delUser(model)
//
        }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        TODO("Not yet implemented")
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        TODO("Not yet implemented")
    }
}