package com.example.myapplication.fragment

interface ClickFragment1 {
    fun onClickGotoFragment2()
    fun getUserDetails(sports:String,date:String)
    fun getTodo()
    fun getAlbum()
    fun set()
    fun get()
    fun getPhoto()
}
interface CLickFragment2 {
    fun onClickGotoFragment1()
}
