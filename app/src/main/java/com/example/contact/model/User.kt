package com.example.contact.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.io.Serializable
//
@Entity(tableName = "user")
class User : Serializable {

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null

    @ColumnInfo(name="name")
    var name=""

    @ColumnInfo(name="age")
    var age = ""

    @ColumnInfo(name="Img")
    var img : String?=null
//
    @ColumnInfo(name="des")
    var des = ""

    @ColumnInfo(name="pho")
    var phone = ""

         constructor(name:String, age: String, img: String?, des:String, phone:String){
         this.name = name
         this.age = age
         this.img = img
         this.des = des
         this.phone=phone
     }

    @Ignore
    constructor(id: Int?) {
        this.id = id
    }

//    public fun setId(Id:Int){
//        this.id = Id
//    }

//

 }
//
//
//@Entity(tableName = "model")
//data class model :Serializable (val name: String,
//                  val age: Int,
//                  val Img: Int,
//                  val des:String,
//                  @PrimaryKey(autoGenerate = false) val id: Int? = null)

