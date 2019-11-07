package com.mobile.azrinurvani.footballclub_api.api

import java.net.URL

class ApiRepository {

//    Function dengan extensions readText() untuk membaca URL
    fun doRequest(url : String):String{
        return URL(url).readText()
    }
}