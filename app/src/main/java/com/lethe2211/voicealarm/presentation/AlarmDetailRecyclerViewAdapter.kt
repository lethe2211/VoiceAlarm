package com.lethe2211.voicealarm.presentation

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lethe2211.voicealarm.R


/**
 * Created by shuhei.shogen on 2018/02/17.
 */
class AlarmDetailRecyclerViewAdapter(dataset: Array<String>) : RecyclerView.Adapter<AlarmDetailRecyclerViewAdapter.ViewHolder>() {

    val mDataset = dataset

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textView = itemView.findViewById<TextView>(R.id.configured_alarm_time) as TextView

    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view: View  = LayoutInflater.from(parent?.context)
                .inflate(R.layout.alarm_detail_recycler_view_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mDataset.size

    override fun onBindViewHolder(holder: ViewHolder?, position: Int) {
        holder?.textView?.text = mDataset[position]
    }


}