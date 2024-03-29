package com.mobile.azrinurvani.footballclub_api.adapter

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mobile.azrinurvani.footballclub_api.R
import com.mobile.azrinurvani.footballclub_api.model.Team
import com.squareup.picasso.Picasso
import org.jetbrains.anko.*

class MainAdapter(private val teams:List<Team>):
    RecyclerView.Adapter<MainAdapter.TeamViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(TeamUI().createView(AnkoContext.Companion.create(parent.context,parent)))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: TeamViewHolder, i: Int) {
       holder.bindItem(teams[i])
    }


    class TeamUI:AnkoComponent<ViewGroup>{
        override fun createView(ui: AnkoContext<ViewGroup>): View {
            return with(ui){
                linearLayout{
                    lparams(width= matchParent, height = wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.HORIZONTAL

                    imageView {
                        id = R.id.team_badge
                    }.lparams{
                        height = dip(50)
                        width = dip(50)
                    }

                    textView {
                        id = R.id.team_name
                        textSize = 16f
                    }.lparams{
                        margin = dip(15)
                    }
                }
            }
        }

    }

    class TeamViewHolder(view:View):RecyclerView.ViewHolder(view) {
        private val teamBadge:ImageView = view.find(R.id.team_badge)
        private val teamName:TextView = view.find(R.id.team_name)

        fun bindItem(teams:Team){
            Picasso.get().load(teams.teamBadge).fit().into(teamBadge)
            teamName.text = teams.teamName
        }
    }
}