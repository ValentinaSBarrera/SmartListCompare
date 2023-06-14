package com.valentinasbarrera.smartlistcompare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Tipo

class TipoAdapter( val context: Context, val layout: Int)
 : RecyclerView.Adapter<TipoAdapter.ViewHolder>() {

    private var dataList: List<Tipo> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setTipos(tipos: List<Tipo>) {
        this.dataList = tipos
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Tipo) {
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem

            val tvtiporow = itemView.findViewById(R.id.tvtiporow) as TextView

            tvtiporow.text = dataItem.nombre
            itemView.tag = dataItem

        }
    }
}