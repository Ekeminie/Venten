package com.example.venten.helper

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.venten.R
import com.example.venten.model.Filter
import com.example.venten.model.FilterItem

class FilterAdapter(private val filterList: Filter) :RecyclerView.Adapter<FilterAdapter.ViewHolder>() {

    var onItemClick: ((FilterItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view  = LayoutInflater.from(parent.context).inflate(R.layout.single_filter_item,parent,false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int {

        return filterList.size
    }


    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Response", "List Count :${filterList.size} ")


        return holder.bind(filterList, position)

    }
    inner class ViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClick?.invoke(filterList[adapterPosition])
            }
        }

    val textUtils = TextHelper()

        var dateRange = itemView.findViewById<TextView>(R.id.date_range)
        var gender = itemView.findViewById<TextView>(R.id.gender)
        var countries = itemView.findViewById<TextView>(R.id.countries)
        var colors = itemView.findViewById<TextView>(R.id.colors)


        fun bind(filter: Filter, index: Int) {




            val date_range ="${filter[index].start_year.toString()} - ${filter[index].end_year.toString()}"
            dateRange.text = date_range
            gender.text = textUtils.checkIfStringValueisEmpty(filter[index].gender)
            countries.text = filter[index].countries.toString()
            colors.text = filter[index].colors.toString()
            }

    }
}