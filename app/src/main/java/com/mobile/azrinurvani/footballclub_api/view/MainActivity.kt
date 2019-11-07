package com.mobile.azrinurvani.footballclub_api.view

import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.*
import com.google.gson.Gson
import com.mobile.azrinurvani.footballclub_api.R
import com.mobile.azrinurvani.footballclub_api.adapter.MainAdapter
import com.mobile.azrinurvani.footballclub_api.api.ApiRepository
import com.mobile.azrinurvani.footballclub_api.model.Team
import com.mobile.azrinurvani.footballclub_api.presenter.MainPresenter
import com.mobile.azrinurvani.footballclub_api.util.invisible
import com.mobile.azrinurvani.footballclub_api.util.visble
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.swipeRefreshLayout

class MainActivity : AppCompatActivity(), MainView {

//  lateinit berfungsi untuk menginisialisasi varibel sebelum diakses

//    1
    private lateinit var listItem : RecyclerView
    private lateinit var progressBar : ProgressBar
    private lateinit var swipeRefresh : SwipeRefreshLayout
    private lateinit var spinner : Spinner

//    2
    private var teams : MutableList<Team> = mutableListOf()
    private lateinit var presenter : MainPresenter
    private lateinit var adapter : MainAdapter

    private lateinit var leagueName:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        Desain layout menggunakan anko layout
        linearLayout {
            lparams(width= matchParent, height = wrapContent)
            orientation = LinearLayout.VERTICAL
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)

            spinner = spinner()

            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    R.color.colorAccent,
                                        android.R.color.holo_green_light,
                                        android.R.color.holo_orange_light,
                                        android.R.color.holo_red_light)
            }

            relativeLayout{
                lparams(width= matchParent,height = wrapContent)
                listItem = recyclerView{
                    lparams(width= matchParent,height=wrapContent)
                    layoutManager = LinearLayoutManager(context)
                }
                progressBar = progressBar{}.lparams{centerHorizontally()}
            }
        }

        val spinnerItems = resources.getStringArray(R.array.league)
        val spinnerAdapter = ArrayAdapter(applicationContext,android.R.layout.simple_dropdown_item_1line,spinnerItems)
        spinner.adapter = spinnerAdapter

        adapter = MainAdapter(teams)
        listItem.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()

        presenter = MainPresenter(this,request,gson)

        spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                leagueName = spinner.selectedItem.toString()
                presenter.getTeamList(leagueName)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
//                No actions
            }
        }


    }

    override fun showLoading() {
        progressBar.visble()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showTeamListData(data: List<Team>) {
        swipeRefresh.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

}
