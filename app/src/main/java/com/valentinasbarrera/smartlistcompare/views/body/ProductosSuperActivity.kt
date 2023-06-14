package com.valentinasbarrera.smartlistcompare.views.body

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.adapters.ProductoAdapter
import com.valentinasbarrera.smartlistcompare.adapters.ProductoSuperAdapter
import com.valentinasbarrera.smartlistcompare.model.Supermercado
import com.valentinasbarrera.smartlistcompare.model.Tipo
import com.valentinasbarrera.smartlistcompare.views.nav.BaseActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel

class ProductosSuperActivity : BaseActivity() {

        private lateinit var gvProductos: GridView
        private lateinit var viewModel: MainViewModel
        private lateinit var adapter: ProductoSuperAdapter
        private lateinit var supermercado: Supermercado

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            inflateSupermercadoProductoActivityLayout(R.layout.activity_productos_super)
            supermercado = intent.getSerializableExtra("supermercado") as Supermercado
            gvProductos = findViewById(R.id.gvProductos)
            viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

            initGV()
            getProductos(supermercado.id)

        }

        private fun getProductos(id_super: Int) {
            viewModel.getProductosBySupermercado(id_super).observe(this, Observer { it ->
                it?.let {
                    adapter.setProducto(it)
                }
            })
        }
        private fun initGV() {
            adapter = ProductoSuperAdapter(this, R.layout.item_grid_producto_super)
            gvProductos.adapter = adapter
            gvProductos.numColumns = 2
        }
}