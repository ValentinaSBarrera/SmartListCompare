package com.valentinasbarrera.smartlistcompare.views.body


import android.os.Bundle
import android.widget.GridView
import android.widget.ImageView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.adapters.ProductoAdapter
import com.valentinasbarrera.smartlistcompare.model.Tipo
import com.valentinasbarrera.smartlistcompare.views.nav.BaseActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel

class ProductoActivity : BaseActivity() {

    private lateinit var gvProductos: GridView
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ProductoAdapter
    private lateinit var tipo: Tipo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateProductoActivityLayout(R.layout.activity_producto)
        tipo = intent.getSerializableExtra("tipo") as Tipo
        gvProductos = findViewById(R.id.gvProductos)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initGV()
        getProductos(tipo.id)

    }

    private fun getProductos(id_tipo: Int) {
        viewModel.getProductosByTipo(id_tipo).observe(this, Observer { it ->
            it?.let {
                adapter.setProducto(it)
            }
        })
    }
    private fun initGV() {
        adapter = ProductoAdapter(this, R.layout.item_grid_producto)
        gvProductos.adapter = adapter
        gvProductos.numColumns = 2
    }

}
