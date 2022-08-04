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
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        var view =  inflater.inflate(layout.fragment_my, container, false)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_my,container,false)

//        view.findViewById<TextView>(R.id.age).text = getString(R.string.myage)
//        view.findViewById<TextView>(R.id.des).text = getString(R.string.myDes)
//        view.findViewById<TextView>(R.id.phone).text = getString(R.string.myPhon)

        binding.apply {
            age.text = getString(R.string.myage)
            name.text = getString(R.string.myName)
            des.text = getString(R.string.myDes)
            phone.text = getString(R.string.myPhon)
        }
//        return view
            return binding.root

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MyFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}