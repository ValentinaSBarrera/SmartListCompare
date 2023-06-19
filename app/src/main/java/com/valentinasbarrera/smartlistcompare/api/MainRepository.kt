package com.valentinasbarrera.smartlistcompare.api

import com.valentinasbarrera.smartlistcompare.model.*

import com.valentinasbarrera.smartlistcompare.api.WebAccess


class MainRepository() {
    val service = WebAccess.smartListService

    // listado de categorias
    suspend fun getAllCategorias(): List<Categoria> {
        val webResponse = service.getAllCategorias().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.Categorias
        }
        return emptyList()
    }

    //listado de subcategorias por categoria
    suspend fun getSubcategorias(id_categoria: Int): List<Subcategoria> {
        val webResponse = service.getSubcategoriasByCategoria(id_categoria).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.Subcategorias
        }
        return emptyList()
    }

    // listado de tipos por subcategoria
    suspend fun getTiposBySubcategoria(id_subcategoria: Int): List<Tipo> {
        val webResponse = service.getTiposBySubcategoria(id_subcategoria).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.Tipos
        }
        return emptyList()
    }

    // listado de productos por tipo
    suspend fun getProductosByTipo(id_tipo: Int): List<Producto> {
        val webResponse = service.getProductosByTipo(id_tipo).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.productos
        }
        return emptyList()
    }

    // listado de productos por supermercado
    suspend fun getProductosBySupermercado(id_supermercado: Int): List<Producto> {
        val webResponse = service.getProductosBySupermercado(id_supermercado).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.productos
        }
        return emptyList()
    }

    // usuario por email y contrasena
    suspend fun getUserByEmailAndPass(email: String, contrasena: String): Usuario? {
        val webResponse = service.getUserByEmailAndPass(email, contrasena).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    // listado de supermercados
    suspend fun getSupermercados(): List<Supermercado> {
        val webResponse = service.getSupermercados().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.Supermercados
        }
        return emptyList()
    }

    // listado de marcas
    suspend fun getMarcas(): List<Marca> {
        val webResponse = service.getMarcas().await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.Marcas
        }
        return emptyList()
    }

    // listado de marcas por supermercado
    suspend fun getMarcasBySupermercado(id_supermercado: Int): List<Marca> {
        val webResponse = service.getMarcasBySupermercado(id_supermercado).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.Marcas
        }
        return emptyList()
    }

    // listas de la compra por id_usuario

    //save usuario
    suspend fun saveUsuario(usuario: Usuario): Usuario? {
        var usuarioresponse:Usuario? = null
        val webResponse = service.saveUsuario(usuario).await()
        if (webResponse.isSuccessful) {
            usuarioresponse = webResponse.body()!!.usuario
        }
        return usuarioresponse
    }


    // update usuario
    suspend fun updateUsuario(email:String): Usuario? {
        val webResponse = service.updateUsuario(email).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    // delete usuario
    suspend fun deleteUsuario(email:String): Usuario? {
        val webResponse = service.deleteUsuario(email).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.usuario
        }
        return null
    }

    // listas de la compra por id_usuario
    suspend fun getProductosByListaCompraByUser(id_usuario: Int): List<Listadecompra> {
        val webResponse = service.getProductosByListaCompraByUser(id_usuario).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.ListaCompra
        }
        return emptyList()
    }

    //save producto a la lista de la compra
    suspend fun saveProductoListaCompra(id_usuario: Int,listadecompra: Listadecompra): Listadecompra? {
        var listadecompraresponse: Listadecompra? = null
        val webResponse = service.saveProductoListaCompra(id_usuario, listadecompra).await()
        if (webResponse.isSuccessful) {
            listadecompraresponse = webResponse.body()!!.ListaCompra
        }
        return listadecompraresponse
    }

    //save usuario

    // delete producto de la lista de la compra
    suspend fun deleteProductoListaCompra(id_usuario: Int, id_producto: Int): Listadecompra? {
        val webResponse = service.deleteProductoListaCompra(id_usuario, id_producto).await()
        if (webResponse.isSuccessful) {
            return webResponse.body()!!.ListaCompra
        }
        return null
    }



}
