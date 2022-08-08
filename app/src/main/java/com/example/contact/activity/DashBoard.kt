package com.example.contact.activity

import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.contact.R
import com.example.contact.databinding.ActivityDashBoardBinding
import com.example.contact.fragment.AboutFragment
import com.example.contact.fragment.DashFragment
import com.example.contact.fragment.FormFragment
import com.example.contact.fragment.MyFragment
import com.example.contact.viewmodel.ModelViewModal
import kotlinx.android.synthetic.main.app_bar_main.view.*
import kotlinx.android.synthetic.main.drawable.*


class DashBoard : AppCompatActivity(){

    private lateinit var binding : ActivityDashBoardBinding
    private lateinit var viewModal: ModelViewModal
    private lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dash_board)


        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(ModelViewModal::class.java)

        drawerLayout = binding.myDrawerLayout
        val navigationView = binding.navigationView
        val toolbar = findViewById<Toolbar>(R.id.toolbar)

        setSupportActionBar(toolbar)


        val toggle: ActionBarDrawerToggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        drawerLayout.addDrawerListener(toggle)

        toggle.syncState()

        loadFragment(DashFragment(viewModal))

//        onBackPressed()

        navigationView.setNavigationItemSelectedListener {
            val id = it.itemId
            if (id == R.id.nav_home) {
                checkAndLoadFragment(DashFragment(viewModal))
            } else if (id == R.id.nav_profile) {
                checkAndLoadFragment(MyFragment())
            } else {
                checkAndLoadFragment(AboutFragment())
            }
            drawerLayout.closeDrawer(GravityCompat.START)

            return@setNavigationItemSelectedListener true
        }

    }

    override fun onBackPressed(){
    if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
    }
        when(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)){
            is DashFragment -> {
                finish()
            }
            is FormFragment->{
                AlertDialog.Builder(this)
                    .setTitle("Exit Alert")
                    .setMessage("Do You Want To Exit Petals App?")
                    .setPositiveButton(android.R.string.ok) { dialog, whichButton ->
                        loadFragmentUtil()
                    }
                    .setNegativeButton(android.R.string.cancel) { dialog, whichButton ->

                    }
                    .show()
            }
            else -> {
                loadFragmentUtil()
            }
        }

//       }
    }
    private fun checkAndLoadFragment(fragment:Fragment) {
        when(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)) {
            is FormFragment -> {
                AlertDialog.Builder(this)
                    .setTitle("Exit Alert")
                    .setMessage("Do You Want To Exit?")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        loadFragment(fragment)
                    }
                    .setNegativeButton(android.R.string.cancel) { _, _ ->

                    }
                    .show()
            }
            else ->{
                loadFragment(fragment)
            }
        }
    }
    private fun loadFragmentUtil(){
        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_content_main, DashFragment(viewModal))
            .commit()
    }

    private fun loadFragment(fragment: Fragment){
        print("eeeeeeeeeeeeeeeee")
        val backStateName: String = fragment.javaClass.name
        val fm: FragmentManager = supportFragmentManager
        val fragmentPopped = fm.popBackStackImmediate(backStateName, 0)
        if (!fragmentPopped) {
            val ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_content_main, fragment)
            ft.addToBackStack(backStateName)
            ft.commit()
        }
        else {
            println("eeeeeee")
        }
    }
}














//package com.example.contact.activity
//
//import android.content.Intent
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import androidx.lifecycle.Observer
//import androidx.lifecycle.ViewModelProvider
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.viewpager.widget.ViewPager
//import com.example.contact.R
//import com.example.contact.adapter.MyAdapter
//import com.example.contact.model.Model
//import com.example.contact.viewmodel.ModelViewModal
//import com.google.android.material.floatingactionbutton.FloatingActionButton
//import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
//import com.google.android.material.tabs.TabLayout.TabLayoutOnPageChangeListener
//
//
//class DashBoard : AppCompatActivity(),NoteClickInterface{
//
//
//    lateinit var viewModal: ModelViewModal
//    lateinit var notesRV: RecyclerView
//    lateinit var addFAB: FloatingActionButton
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dash_board)
//
//        val recycle = findViewById<RecyclerView>(R.id.recyclecon);
//        val button = findViewById<FloatingActionButton>(R.id.button)
//        val tabLayout = findViewById<TabLayout>(R.id.tabLayout);
//        val viewPager = findViewById<ViewPager>(R.id.viewPager);
//
//        val myAdapter = MyAdapter(this, supportFragmentManager, tabLayout.tabCount)
//        viewPager.adapter = myAdapter
//        viewPager.addOnPageChangeListener(TabLayoutOnPageChangeListener(tabLayout))
//
//        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab) {
//                viewPager.currentItem = tab.position
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab) {}
//            override fun onTabReselected(tab: TabLayout.Tab) {}
//        })
//
//
//        button.setOnClickListener {
//            startActivity(Intent(this, FormActivity::class.java))
////            this.finish()
//
//        }
//
//        recycle.setLayoutManager(LinearLayoutManager(this));
//
//        val data = arrayListOf(
//            Model(
//                "Per1",
//                "l",
//                R.drawable.download,
//                "He is a sofyware developoer. from Us, dcsdsdssdsdsdsdsdsdsdssd",
//                ""
//            ),
////            Model(
////                "Per2",
////                50,
////                R.drawable.download,
////                "He is a sofyware developoer. from Us, dcsdsdssdsdsdsdsdsdsdssd"
////            ),
////            Model(
////                "Per3",
////                35,
////                R.drawable.download,
////                "He is a sofyware developoer. from Us, dcsdsdssdsdsdsdsdsdsdssd"
////            ),
////            Model(
////                "p4",
////                20,
////                R.drawable.download,
////                "He is a sofyware developoer. from Us, dcsdsdssdsdsdsdsdsdsdssd"
////            ),
////            Model(
////                "p5",
////                15,
////                R.drawable.download,
////                "He is a sofyware developoer. from Us, dcsdsdssdsdsdsdsdsdsdssd"
////            ),
////            Model(
////                "p6",
////                10,
////                R.drawable.download,
////                "He is a sofyware developoer. from Us, dcsdsdssdsdsdsdsdsdsdssd"
////            ),
////
//        )
//
//        val adapter = ModelAdapter(this,this);
//
//
//
//        recycle.adapter = adapter
//
//
////        adapter.updateList(data)
////        adapter.notifyDataSetChanged()
//
//        viewModal = ViewModelProvider(
//            this,
//            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
//        ).get(ModelViewModal::class.java)
//
//
//
//        viewModal.allModel.observe(this, Observer { List ->
//            List?.let {
//                // on below line we are updating our list.
//
//                adapter.updateList(List)
//
//                adapter.notifyDataSetChanged()
//
//            }
//        })
//    }
//
//        override fun onDeleteIconClick(model: Model) {
//            // in on note click method we are calling delete
//            // method from our view modal to delete our not.
//            viewModal.delModel(model)
//
//        }
//
////    override fun onNoteClick(model: Model) {
////
////    }
//}
