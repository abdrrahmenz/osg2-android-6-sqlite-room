package com.educa62.simpleroom.adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.educa62.simpleroom.R
import com.educa62.simpleroom.db.Constant
import com.educa62.simpleroom.entity.Players
import com.educa62.simpleroom.ui.AddDetailPlayerActivity
import kotlinx.android.synthetic.main.item_main.view.*

class PlayerAdapter(val context: Context, val playerList: List<Players>): RecyclerView.Adapter<PlayerViewHolder>() {

    var bundle: Bundle? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(LayoutInflater.from(context).inflate(R.layout.item_main,parent,false))
    }

    override fun getItemCount(): Int = playerList.size

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.tvName.text = playerList[position].player_
        holder.itemView.setOnClickListener {
            bundle = Bundle()
            bundle?.putInt(Constant.TAG_ID, playerList[position].id!!)
            bundle?.putString(Constant.TAG_PLAYER, playerList[position].player_!!)
            bundle?.putString(Constant.TAG_POSITION, playerList[position].position_!!)
            bundle?.putString(Constant.TAG_AGE, playerList[position].age_!!)
            bundle?.putString(Constant.TAG_GENDER, playerList[position].gender_!!)
            bundle?.putString(Constant.TAG_COUNTRY, playerList[position].country_!!)
            context.startActivity(Intent(context, AddDetailPlayerActivity::class.java).putExtras(bundle))
        }
    }

}

class PlayerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    val tvName = itemView.tvItemMain
}
