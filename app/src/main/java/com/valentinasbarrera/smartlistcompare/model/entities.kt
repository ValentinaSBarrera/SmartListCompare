package com.valentinasbarrera.smartlistcompare.model


import java.io.Serializable


data class Categoria(
    var id: Int,
    var nombre: String,
    var icono: String
): Serializable
data class Listadecompra(
    var id: Int,
    var id_usuario:Int,
    var id_producto :Int,
    var productos: List<Producto>): Serializable
data class Marca(
    var id: Int,
    var nombre: String,
    var id_supermercado :Int): Serializable
data class ListadeCompraView(
    var id_usuario:Int,
    var id_producto :Int,
    var nombre:String,
    var marca: Int,
    var precio:Double,
    var imagen:String): Serializable
data class Producto(
    var id: Int,
    var nombre: String,
    var marca :Int,
    var precio :Double,
    var tamano:String,
    var imagen:String,
    var tipo:Int,
    var favorito:Int,
    var supermercado: Supermercado
): Serializable
data class ProductoView(
    var id: Int,
    var nombre: String,
    var marca :Int,
    var precio :Double,
    var tamano:String,
    var imagen:String,
    var tipo:Int,
    var favorito:Int,
    var isListaCompra :Boolean): Serializable
data class Subcategoria(
    var id: Int,
    var nombre: String,
    var icono: String,
    var id_categoria :Int): Serializable
data class Supermercado(
    var id: Int,
    var nombre_super: String,
    var logo_super: String): Serializable
data class Tipo(
    var id: Int,
    var nombre: String,
    var id_subcategoria: Int): Serializable
data class Usuario(
    var id: Int,
    var nombre_usuario: String,
    var email: String,
    var contrasena: String,
   ): Serializable
data class UsuarioView(
    var id: Int,
    var nombre_usuario: String,
    var email: String?,
    var contrasena: String,
    var genero: String="",
): Serializable
data class ProductoConLogo(
    val producto: Producto,
    val marca: Int,
    val logo_super: String
): Serializable

    data class Categorias(var Categorias: List<Categoria>)
    data class Subcategorias(var Subcategorias: List<Subcategoria>)
    data class Tipos(var Tipos: List<Tipo>)
    data class Productos(var productos: List<Producto>)
    data class Marcas(var Marcas: List<Marca>)
    data class Supermercados(var Supermercados: List<Supermercado>)
    data class Listasdecompra(var ListaCompra : List<Listadecompra>)
    data class Usuarios(var Usuario: List<Usuario>)
    data class ProductoListaCompra(var ListaCompra: Listadecompra)
    data class Respuesta(
        var usuario: Usuario
    ):Serializable



