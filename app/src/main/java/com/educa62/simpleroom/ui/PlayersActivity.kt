package com.educa62.simpleroom.ui

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.educa62.simpleroom.R
import com.educa62.simpleroom.adapter.PlayerAdapter
import com.educa62.simpleroom.db.Constant
import com.educa62.simpleroom.db.PlayerDatabase
import com.educa62.simpleroom.entity.Players
import java.util.ArrayList

class PlayersActivity : AppCompatActivity() {

    private var rvMain: RecyclerView? = null
    private var playerDatabase: PlayerDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players)
        title = "Player List"
        rvMain = findViewById(R.id.rvMain)
        findViewById<FloatingActionButton>(R.id.fabAddPlayer).setOnClickListener {
            startActivityForResult(Intent(this,AddDetailPlayerActivity::class.java),2)
        }
        playerDatabase = PlayerDatabase.createDatabase(this)
    }

    override fun onResume() {
        super.onResume()

        rvMain?.apply {
            layoutManager = LinearLayoutManager(this@PlayersActivity)
            adapter = PlayerAdapter(this@PlayersActivity, playerDatabase?.playersDao()!!.select())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode == Activity.RESULT_OK && data != null) {

            val playersList = ArrayList<Players>()

            val players = Players()

            players.player_ = data.getStringExtra(Constant.TAG_PLAYER)
            players.position_ = data.getStringExtra(Constant.TAG_POSITION)
            players.age_ = data.getStringExtra(Constant.TAG_AGE)
            players.gender_ = data.getStringExtra(Constant.TAG_GENDER)
            players.country_ = data.getStringExtra(Constant.TAG_COUNTRY)

            playersList.add(players)

            playerDatabase?.playersDao()!!.insert(playersList)
        }
    }
}
