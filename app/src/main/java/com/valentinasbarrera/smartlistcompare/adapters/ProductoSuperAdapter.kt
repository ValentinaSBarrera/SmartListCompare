package com.valentinasbarrera.smartlistcompare.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.cardview.widget.CardView
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Producto
import com.valentinasbarrera.smartlistcompare.model.Supermercado
import com.valentinasbarrera.smartlistcompare.views.body.ProductoActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel

class ProductoSuperAdapter(val context: Context, val layout: Int) : BaseAdapter() {

    private var dataList: List<Producto> = emptyList()

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(position: Int): Any {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
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


        return itemView
    }

    internal fun setProducto(productos: List<Producto>) {
        this.dataList = productos
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) {
        private val context: Context = view.context
        private val ivImagenProductoGrid: ImageView = view.findViewById(R.id.ivproductogrid)
        private val tvNombreProductoGrid: TextView = view.findViewById(R.id.tvnombreproductogrid)
        private val tvPrecioProductoGrid: TextView = view.findViewById(R.id.tvprecioproductogrid)

        private lateinit var dataItem: Producto

        fun bind(dataItem: Producto) {
            this.dataItem = dataItem
            tvNombreProductoGrid.text = dataItem.nombre
            Picasso.get().load(dataItem.imagen).into(ivImagenProductoGrid)
            tvPrecioProductoGrid.text = "${dataItem.precio} €"

        }

        fun getProducto(): Producto {
            return dataItem
        }
    }
}
