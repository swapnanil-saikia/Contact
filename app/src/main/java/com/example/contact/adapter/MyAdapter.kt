//package com.example.contact.adapter
//
//import com.example.contact.fragment.DashFragment
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.adapter.FragmentStateAdapter
//import com.example.contact.fragment.MyFragment
//import com.example.contact.viewmodel.ModelViewModal
//
//@Suppress("DEPRECATION")
//class MyAdapter(fragment: Fragment, var totalTabs: Int, var viewModal: ModelViewModal) :
//    FragmentStateAdapter(fragment) {
//    // this is for fragment tabs
//    override fun getItemCount(): Int {
//        return totalTabs
//    }
//
//    override fun createFragment(position: Int): Fragment {
//        return when (position) {
//            0 -> {
//                DashFragment(this.viewModal)
//            }
//            1 -> {
//                MyFragment()
//            }
//            2 -> {
//                MyFragment()
//            }
//            else -> MyFragment()
//        }
//    }
//}
//
//





package com.example.contact.adapter


import com.example.contact.fragment.DashFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.contact.fragment.MyFragment
import com.example.contact.activity.DashBoard
import com.example.contact.viewmodel.ModelViewModal

@Suppress("DEPRECATION")
class MyAdapter(var fm: FragmentManager?, var totalTabs: Int, var viewModal: ModelViewModal) :
    FragmentPagerAdapter(fm!!) {
    // this is for fragment tabs
    override fun getItem(position: Int): Fragment {
        return when (position) {
//            0 -> {
//                DashFragment()
//            }
//            1 -> {
//                MyFragment()
//            }
//            2 -> {
//                MyFragment()
//            }
            else -> MyFragment()
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }

}