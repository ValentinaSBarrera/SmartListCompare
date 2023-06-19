package com.valentinasbarrera.smartlistcompare.api

import com.valentinasbarrera.smartlistcompare.model.*
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.*

interface SmartListService {

    // Metodos GET
    // Get all categorias
    @GET("categorias")
    fun getAllCategorias(): Deferred<Response<Categorias>>

    // Get all subcategorias
    // Get all subcategorias by categoria
    @GET("categorias/{id_categoria}/subcategorias")
    fun getSubcategoriasByCategoria(
        @Path("id_categoria") id_categoria: Int): Deferred<Response<Subcategorias>>

    // Get all tipos by subcategoria
    @GET("subcategorias/{id_subcategoria}/tipos")
    fun getTiposBySubcategoria(
        @Path("id_subcategoria") id_subcategoria: Int): Deferred<Response<Tipos>>

    // Get all productos by tipo
    @GET("producto/{id_tipo}")
    fun getProductosByTipo(
        @Path("id_tipo") id_tipo: Int): Deferred<Response<Productos>>

    // Get usuario by nick and pass
    @GET("usuarios/{email}/{contrasena}/usuario") // nombre_usuario y contraseña como variables en la url?email=valentina@azarquiel.es&contrasena=1234
    fun getUserByEmailAndPass(
        @Path("email") email: String,
        @Path("contrasena") contrasena: String
    ): Deferred<Response<Respuesta>>

    // Get all productos by supermercado
    @GET("supermercado/{id_supermercado}/productos")
    fun getProductosBySupermercado(
        @Path("id_supermercado") id_supermercado: Int): Deferred<Response<Productos>>

    // Get all supermercados
    @GET("supermercados")
    fun getSupermercados(): Deferred<Response<Supermercados>>

    // Get all marcas
    @GET("marcas")
    fun getMarcas(): Deferred<Response<Marcas>>

    // Get all marcas by supermercado
    @GET("marcas/{id_supermercado}")
    fun getMarcasBySupermercado(
        @Path("id_supermercado") id_supermercado: Int): Deferred<Response<Marcas>>

    // Get all productos by lista de la compra
    @GET("listacompra/{id_usuario}/productos")
    fun getProductosByListaCompraByUser(
        @Path("id_usuario") id_usuario: Int): Deferred<Response<Listasdecompra>>

    // Metodos POST
// post con objeto en json usuario
    @POST("usuario")
    fun saveUsuario(@Body usuario: Usuario): Deferred<Response<Respuesta>>

    // post con objeto en json lista de la compra
    @POST("listacompra/{id_usuario}/producto")
    fun saveProductoListaCompra(
        @Path("id_usuario") id_usuario: Int,
        @Body listadecompra:Listadecompra): Deferred<Response<ProductoListaCompra>>

    // Metodo PUT
// actualizar usuario
    @PUT("usuario/{email}")
    fun updateUsuario(
        @Path("email") email: String): Deferred<Response<Respuesta>>

    //Metodos DELETE
    // borrar usuario
    @DELETE("usuario/{email}")
    fun deleteUsuario(
        @Path("email") email: String): Deferred<Response<Respuesta>>

    // borrar producto de la lista de la compra
    @DELETE("listacompra/{id_usuario}/producto/{id_producto}")
    fun deleteProductoListaCompra(
        @Path("id_usuario") id_usuario: Int,
        @Path("id_producto") id_producto: Int): Deferred<Response<ProductoListaCompra>>

}