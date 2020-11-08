package com.example.myapplication

import java.util.*

data class Student (val id: UUID = UUID.randomUUID(), var name :String="", var number:String="",
                    var pass:Boolean=false){


}