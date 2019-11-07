package com.mobile.azrinurvani.footballclub_api.presenter

import com.google.gson.Gson
import com.mobile.azrinurvani.footballclub_api.api.ApiRepository
import com.mobile.azrinurvani.footballclub_api.api.TheSportDBApi
import com.mobile.azrinurvani.footballclub_api.model.TeamResponse
import com.mobile.azrinurvani.footballclub_api.view.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view:MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson){

    fun getTeamList(league:String?){
        view.showLoading()
        doAsync {
            val data = gson.fromJson(
                apiRepository.
                    doRequest(TheSportDBApi.getTeams(league)),
                    TeamResponse::class.java
            )


            uiThread {
                view.hideLoading()
                view.showTeamListData(data.teams)
            }
        }
    }
}