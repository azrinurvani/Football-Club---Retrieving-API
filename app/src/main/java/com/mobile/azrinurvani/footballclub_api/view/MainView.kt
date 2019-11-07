package com.mobile.azrinurvani.footballclub_api.view

import com.mobile.azrinurvani.footballclub_api.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamListData(data: List<Team>)
}