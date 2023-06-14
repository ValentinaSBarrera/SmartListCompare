package com.valentinasbarrera.smartlistcompare.views.body

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.adapters.TipoAdapter
import com.valentinasbarrera.smartlistcompare.model.Subcategoria
import com.valentinasbarrera.smartlistcompare.model.Tipo
import com.valentinasbarrera.smartlistcompare.views.nav.BaseActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel


class TipoActivity :  BaseActivity() {

    private lateinit var rvTipos: RecyclerView
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: TipoAdapter
    private lateinit var subcategoria: Subcategoria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inflateTipoActivityLayout(R.layout.activity_tipo)
        subcategoria = intent.getSerializableExtra("subcategoria") as Subcategoria
        rvTipos = findViewById(R.id.rvTipo)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        initRV()
        getTipos(subcategoria.id)

    }

    private fun getTipos(id_subcategoria: Int) {
        viewModel.getTiposBySubcategoria(id_subcategoria).observe(this, Observer { it ->
            it?.let{
                adapter.setTipos(it)
            }
        })
    }

    private fun initRV() {
        adapter = TipoAdapter(this, R.layout.item_row_tipo)
        rvTipos.adapter = adapter
        rvTipos.layoutManager = LinearLayoutManager(this)
    }

   fun onClickTipo(v : View){
        val tipo = v.tag as Tipo
        val intent = Intent(this, ProductoActivity::class.java)
        intent.putExtra("tipo", tipo)
        startActivity(intent)

    }
}