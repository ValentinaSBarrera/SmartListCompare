package com.valentinasbarrera.smartlistcompare.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Supermercado


class SupermercadoAdapter(val context: Context,
                          val layout: Int
) : BaseAdapter() {

    private var dataList: List<Supermercado> = emptyList()


    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        val holder: ViewHolder

        if (itemView == null) {
            val layoutInflater = LayoutInflater.from(context)
            itemView = layoutInflater.inflate(layout, null)
            holder = ViewHolder(itemView)
            itemView.tag = holder
        } else {
            holder = itemView.tag as ViewHolder
        }

        val item = dataList[position]
        holder.bind(item)

        return itemView!!
    }

    internal fun setSupermercados(supermercados: List<Supermercado>) {
        this.dataList = supermercados
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) {
        private val ivsupergrid: ImageView = view.findViewById(R.id.ivsupergrid)
        private val tvsupergrid: TextView = view.findViewById(R.id.tvsupergrid)
        private lateinit var dataItem: Supermercado

        fun bind(dataItem: Supermercado) {
            this.dataItem = dataItem
            tvsupergrid.text = dataItem.nombre_super
            Picasso.get().load(dataItem.logo_super).into(ivsupergrid)


        }
        fun getSupermercado(): Supermercado {
            return dataItem
        }
    }
}
