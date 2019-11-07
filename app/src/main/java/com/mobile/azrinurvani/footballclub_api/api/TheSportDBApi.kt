package com.mobile.azrinurvani.footballclub_api.api

import android.net.Uri
import com.mobile.azrinurvani.footballclub_api.BuildConfig

object TheSportDBApi {
//    fun getTeams(league:String?):String{
//        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}\" + \"/search_all_teams.php?l="+league
//    }

//    atau dengan sederhana dengan memanfaatkan Uri.parse

    fun getTeams(league:String?):String{
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath("search_all_teams.php")
            .appendQueryParameter("1",league)
            .build()
            .toString()
    }

}