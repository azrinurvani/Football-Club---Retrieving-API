package com.mobile.azrinurvani.footballclub_api.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("idTeam")
    var itemId:String?=null,

    @SerializedName("strTeam")
    var teamName:String?=null,

    @SerializedName("strTeamBadge")
    var teamBadge:String?=null
)