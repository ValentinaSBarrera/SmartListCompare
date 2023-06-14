package com.valentinasbarrera.smartlistcompare.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Categoria


class CategoriaAdapter(val context: Context,
                       val layout: Int
) : BaseAdapter() {

    private var dataList: List<Categoria> = emptyList()


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

    internal fun setCategorias(Categorias: List<Categoria>) {
        this.dataList = Categorias
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) {
        private val context: Context = view.context
        private val ivcategoriagrid: ImageView = view.findViewById(R.id.ivcategoriagrid)
        private val tvcategoriagrid: TextView = view.findViewById(R.id.tvcategoriagrid)
        private lateinit var dataItem: Categoria

        fun bind(dataItem: Categoria) {
            this.dataItem = dataItem
            tvcategoriagrid.text = dataItem.nombre
            val resourceId = context.resources.getIdentifier(dataItem.icono, "drawable", context.packageName)
            ivcategoriagrid.setImageResource(resourceId)


        }
        fun getCategoria(): Categoria {
            return dataItem
        }

    }
}
