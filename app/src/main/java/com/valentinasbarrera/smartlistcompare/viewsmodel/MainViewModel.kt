package com.valentinasbarrera.smartlistcompare.viewsmodel


import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.squareup.picasso.Picasso
import com.valentinasbarrera.smartlistcompare.api.MainRepository

import com.valentinasbarrera.smartlistcompare.model.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var repository: MainRepository = MainRepository()

    fun getAllCategorias(): MutableLiveData<List<Categoria>> {
        val categorias = MutableLiveData<List<Categoria>>()
        GlobalScope.launch(Main) {
            categorias.value = repository.getAllCategorias()
        }
        return categorias
    }

    // lista de subcategorias
    fun getSubcategorias(id_categoria: Int): MutableLiveData<List<Subcategoria>> {
        val subcategorias = MutableLiveData<List<Subcategoria>>()
        GlobalScope.launch(Main){
            subcategorias.value = repository.getSubcategorias(id_categoria)
        }
        return subcategorias
    }

    // lista de tipos
    fun getTiposBySubcategoria(id_subcategoria: Int): MutableLiveData<List<Tipo>> {
        val tipos = MutableLiveData<List<Tipo>>()
        GlobalScope.launch(Main){
            tipos.value = repository.getTiposBySubcategoria(id_subcategoria)
        }
        return tipos
    }

    // lista de productos por tipo
    fun getProductosByTipo(id_tipo: Int): MutableLiveData<List<Producto>> {
        val productos = MutableLiveData<List<Producto>>()
        GlobalScope.launch(Main){
            productos.value = repository.getProductosByTipo(id_tipo)
        }
        return productos
    }

    // lista de productos por supermercado
    fun getProductosBySupermercado(id_supermercado: Int): MutableLiveData<List<Producto>> {
        val productos = MutableLiveData<List<Producto>>()
        GlobalScope.launch(Main){
            productos.value = repository.getProductosBySupermercado(id_supermercado)
        }
        return productos
    }

    // usuario por nombre_usuario y contrasena
    fun getUserByEmailAndPass(email: String, contrasena: String): MutableLiveData<Usuario?> {
        val usuario = MutableLiveData<Usuario?>()
        GlobalScope.launch(Main){
            usuario.value = repository.getUserByEmailAndPass(email, contrasena)
        }
        return usuario
    }

    // lista de supermercados
    fun getSupermercados(): MutableLiveData<List<Supermercado>> {
        val supermercados = MutableLiveData<List<Supermercado>>()
        GlobalScope.launch(Main){
            supermercados.value = repository.getSupermercados()
        }
        return supermercados
    }

    // lista de marcas
    fun getMarcas(): MutableLiveData<List<Marca>> {
        val marcas = MutableLiveData<List<Marca>>()
        GlobalScope.launch(Main){
            marcas.value = repository.getMarcas()
        }
        return marcas
    }

    // lista de marcas por supermercado
    fun getMarcasBySupermercado(id_supermercado: Int): MutableLiveData<List<Marca>> {
        val marcas = MutableLiveData<List<Marca>>()
        GlobalScope.launch(Main){
            marcas.value = repository.getMarcasBySupermercado(id_supermercado)
        }
        return marcas
    }

    // lista de productos de una lista de compra por usuario
    fun getProductosByListaCompraByUser(id_usuario: Int): MutableLiveData<List<Listadecompra>> {
        val listasCompra = MutableLiveData<List<Listadecompra>>()
        GlobalScope.launch(Main){
            listasCompra.value = repository.getProductosByListaCompraByUser(id_usuario)
        }
        return listasCompra
    }

    // save usuario
    fun saveUsuario(usuario: Usuario):MutableLiveData<Usuario> {
        val usuarioresponse= MutableLiveData<Usuario>()
        GlobalScope.launch(Main) {
            usuarioresponse.value = repository.saveUsuario(usuario)
        }
        return usuarioresponse
    }

    // save producto lista de compra
    fun saveProductoListaCompra(id_usuario: Int,listadecompra: Listadecompra): MutableLiveData<Listadecompra?> {
        val productoListaCompraresponse = MutableLiveData<Listadecompra?>()
        GlobalScope.launch(Main){
            productoListaCompraresponse.value = repository.saveProductoListaCompra(id_usuario,listadecompra)
        }
        return productoListaCompraresponse
    }

    fun updateUser(email: String): MutableLiveData<Usuario?> {
        val usuarioresponse = MutableLiveData<Usuario?>()
        GlobalScope.launch(Main){
            usuarioresponse.value = repository.updateUsuario(email)
        }
        return usuarioresponse
    }
}