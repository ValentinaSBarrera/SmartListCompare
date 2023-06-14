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

class ProductoAdapter(val context: Context, val layout: Int) : BaseAdapter() {

    private var dataList: List<Producto> = emptyList()
    private var productoMasBarato: Producto? = null
    private var productoMasCaro: Producto? = null

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

        val precio = item.precio
        val colorFondo = establecerColorFondoProducto(precio)
        val cardView = itemView!!.findViewById<CardView>(R.id.cardviewgrid)
        cardView.setCardBackgroundColor(colorFondo)


        return itemView
    }

    internal fun setProducto(productos: List<Producto>) {
        this.dataList = productos.sortedBy { it.precio }
        this.productoMasBarato = productos.minByOrNull { it.precio }
        this.productoMasCaro = productos.maxByOrNull { it.precio }
        notifyDataSetChanged()
    }

    private fun establecerColorFondoProducto(precio: Double): Int {
        if (productoMasBarato != null && productoMasCaro != null) {
            if (precio == productoMasBarato!!.precio) {
                return Color.rgb(102, 255, 102)
            } else if (precio == productoMasCaro!!.precio) {
                return Color.rgb(255, 102, 102)
            }
        }
        return Color.WHITE
    }

    class ViewHolder(view: View) {
        private val context: Context = view.context
        private val ivImagenProductoGrid: ImageView = view.findViewById(R.id.ivproductogrid)
        private val tvNombreProductoGrid: TextView = view.findViewById(R.id.tvnombreproductogrid)
        private val tvPrecioProductoGrid: TextView = view.findViewById(R.id.tvprecioproductogrid)
        private val ivLogoSupermercadoGrid: ImageView = view.findViewById(R.id.ivLogoSupermercado)
        private lateinit var dataItem: Producto

        fun bind(dataItem: Producto) {
            this.dataItem = dataItem
            tvNombreProductoGrid.text = dataItem.nombre
            tvPrecioProductoGrid.text = "${dataItem.precio} €"

            Picasso.get().load(dataItem.supermercado.logo_super).into(ivLogoSupermercadoGrid)
            Picasso.get().load(dataItem.imagen).into(ivImagenProductoGrid)
        }

        fun getProducto(): Producto {
            return dataItem
        }
    }
}
