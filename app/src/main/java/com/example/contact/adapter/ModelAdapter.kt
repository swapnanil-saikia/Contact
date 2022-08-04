package com.example.contact.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.util.ULocale
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.example.contact.fragment.DetailViewFragment
import com.example.contact.R
import com.example.contact.model.User
import java.util.*
import kotlin.collections.ArrayList


//import kotlinx.android.synthetic.main.info_list_row.view.*

class ModelAdapter(
    val context: DashBoard,
    val noteClickInterface: NoteClickInterface
): RecyclerView.Adapter<ModelAdapter.ViewHolder>() {


    var userList = ArrayList<User>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // on below line we are creating an initializing all our
        // variables which we have added in layout file.
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val name: TextView = itemView.findViewById(R.id.name)
        val age: TextView = itemView.findViewById(R.id.age)
        val pop_up:Button = itemView.findViewById((R.id.item_popup_menu))
//        val phone: ImageView = itemView.findViewById(R.id.phone)
//        val delete : ImageView= itemView.findViewById(R.id.idIVDelete)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.info_list_row, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var decodedByte: ByteArray? =null
        var bmp: Bitmap? =null
        if (userList[position].img!="") {
             decodedByte = Base64.decode(userList[position].img, Base64.DEFAULT)
             bmp = BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.size)
            holder.imageView.setImageBitmap(Bitmap.createScaledBitmap(bmp!!, 120, 120, false))
        }
            holder.name.text = "Name " + userList[position].name
            holder.age.text = "Age " + userList[position].age
            holder.pop_up.setOnClickListener {
                val popupMenu = PopupMenu(holder.itemView.context, holder.pop_up)
                popupMenu.menuInflater.inflate(R.menu.list_notes_item_menu, popupMenu.menu)
                popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                    when (item.itemId) {
                        R.id.idIVDelete ->
                            noteClickInterface.onDeleteIconClick(userList[position])
                        R.id.share -> {
                            val sendIntent: Intent = Intent().apply {
                                action = Intent.ACTION_SEND
                                putExtra(
                                    Intent.EXTRA_TEXT,
                                    userList[position].name + "\n" + userList[position].phone
                                )
                                type = "text/plain"
                            }

                            val shareIntent = Intent.createChooser(sendIntent, null)
                            holder.itemView.context.startActivity(shareIntent)
                        }
                        R.id.phone -> {
                            val intent = Intent(
                                Intent.ACTION_DIAL,
                                Uri.parse("tel:+" + userList[position].phone)
                            )
                            startActivity(it.context, intent, null)
                        }
                        R.id.mes -> {
                            val intent = Intent(
                                Intent.ACTION_VIEW,
                            )
                            intent.data = Uri.parse("sms:+" + userList[position].phone)
                            startActivity(it.context, intent, null)
                        }
                    }
                    true
                })
                popupMenu.show()
            }

//            holder.phone.setImageResource(R.drawable.download2)

//        holder.phone.setOnClickListener{
//            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:+"+userList[position].phone))
//                startActivity(it.context,intent,null)
//        }
//
//        holder.delete.setOnClickListener{
//            noteClickInterface.onDeleteIconClick(userList[position])
//        }
//        var s:Uri="s".toUri()
//        val stream = ByteArrayOutputStream()
//        if (bmp != null) {
//            bmp.compress(Bitmap.CompressFormat.PNG, 1, stream)
//            s = MediaStore.Images.Media.getContentUri(bmp.toString())
////            MediaStore.Images.Media.insertImage(,bmp, "Title", null);
//            println("kkkkkkkkkkkkkkkkkk"+stream.toByteArray())
//        }
//        val b = Bundle()
//        b.putSerializable("Image", s)
//        mIntent.putExtras(b)
//        startActivity(mIntent)


//        var myFragment = MyFragment()
//
//       val activity =   DashBoard() as AppCompatActivity
//        val viewPager = findViewById<ViewPager>(R.id.viewPager);

//        holder.itemView.setOnClickListener {
//
//            val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
//            fragmentTransaction.add(R.id.viewPager, myFragment)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()
//        }

//

//        val f:Fragment = DetailViewFragment()
//        var fm :FragmentManager =
//        fm.beginTransaction()

//        (context as FragmentActivity).supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment_content_main,DetailViewFragment())

        holder.itemView.setOnClickListener {
//            startActivity(  it.context,
//            Intent(it.context, Details::class.java)
//                .putExtra("name",userList[position].name)
//                .putExtra("age",userList[position].age)
//                .putExtra("des",userList[position].des)
//                .putExtra("phone",userList[position].phone)
//                .putExtra("id",userList[position].id)
//            ,
//            null
//        )

//                .beginTransaction().replace(R.id.nav_host_fragment_content_main,DetailViewFragment()).commit()

            val detailViewFragment = DetailViewFragment(this.context)
            val bundle = Bundle()
            bundle.putSerializable("user",userList[position])
            detailViewFragment.arguments = bundle
            val fm : FragmentManager = ( holder.itemView.context as FragmentActivity).supportFragmentManager

            val ft = fm.beginTransaction();
            ft.replace(R.id.nav_host_fragment_content_main,detailViewFragment)
            ft.commit()

        }
    }

    override fun getItemCount(): Int {

        return userList.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newList: List<User>) {// on below line we are clearing
        userList.clear()
        // on below line we are adding a
        // new list to our all notes list.
        userList.addAll(newList)
        // on below line we are calling notify data

        notifyDataSetChanged()
    }
}

interface NoteClickInterface {

    fun onDeleteIconClick(model: User)
}


