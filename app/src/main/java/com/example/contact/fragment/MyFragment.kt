package com.example.contact.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.contact.R
import com.example.contact.databinding.FragmentMyBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MyFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyFragment : Fragment() {

    private lateinit var binding : FragmentMyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        var view =  inflater.inflate(layout.fragment_my, container, false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false)

//        view.<TextView>(R.id.phone).text = getString(R.string.myPhon)

        binding.apply {
            age.text = getString(R.string.myage)
            name.text = getString(R.string.myName)
            des.text = getString(R.string.myDes)
            phone.text = getString(R.string.myPhon)
        }
//        return view
            return binding.root

    }

    }