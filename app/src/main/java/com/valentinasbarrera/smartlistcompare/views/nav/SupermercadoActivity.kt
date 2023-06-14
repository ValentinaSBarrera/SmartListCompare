package com.valentinasbarrera.smartlistcompare.views.nav

import android.os.Bundle
import android.widget.GridView
import androidx.lifecycle.ViewModelProvider

import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.adapters.SupermercadoAdapter
import com.valentinasbarrera.smartlistcompare.model.Supermercado
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel


class SupermercadoActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_supermercado)


    }
}