package com.valentinasbarrera.smartlistcompare.views.body

import SubcategoriaAdapter
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.GridView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Categoria
import com.valentinasbarrera.smartlistcompare.views.nav.BaseActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel


class SubcategoriaActivity : BaseActivity() {

    private lateinit var gvSubcategorias: GridView
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: SubcategoriaAdapter
    private lateinit var categoria: Categoria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateSubcategoriaActivityLayout(R.layout.activity_subcategoria)
        categoria = intent.getSerializableExtra("categoria") as Categoria
        gvSubcategorias = findViewById(R.id.gvProductos)
        
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        
        initGV()
        getSubcategorias(categoria.id)

    }

    private fun getSubcategorias(id_categoria: Int) {
        viewModel.getSubcategorias(id_categoria).observe(this, Observer { it ->
            it?.let{
                adapter.setSubcategorias(it)
            }
        })
    }

    private fun initGV() {
        adapter = SubcategoriaAdapter(this, R.layout.item_grid_subcategoria)
        gvSubcategorias.adapter = adapter
        gvSubcategorias.numColumns = 2
    }

    fun onClickSubcategoria (v : View){
        val viewHolder = v.tag as SubcategoriaAdapter.ViewHolder
        val subcategoria = viewHolder.getSubCategoria()
        val intent = Intent(this, TipoActivity::class.java)
        intent.putExtra("subcategoria", subcategoria)
        startActivity(intent)

    }


}