package com.valentinasbarrera.smartlistcompare.views.nav

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.valentinasbarrera.smartlistcompare.databinding.ActivityBaseBinding
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.adapters.*
import com.valentinasbarrera.smartlistcompare.model.*
import com.valentinasbarrera.smartlistcompare.views.body.ProductosSuperActivity
import com.valentinasbarrera.smartlistcompare.views.body.SubcategoriaActivity
import com.valentinasbarrera.smartlistcompare.views.inicio.MainActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel



open class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
    BottomNavigationView.OnNavigationItemSelectedListener {


    private lateinit var binding: ActivityBaseBinding
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView
    private lateinit var bottomNavView: BottomNavigationView
    private lateinit var frameLayout: FrameLayout
    private lateinit var categoriaAdapter: CategoriaAdapter
    private lateinit var categorias: List<Categoria>
    private lateinit var viewModel: MainViewModel
    private lateinit var gvcategorias: GridView
    private lateinit var sh: SharedPreferences
    private lateinit var navHeader: View
    private var usuario : Usuario ? =null
    private lateinit var adapterSuper: SupermercadoAdapter
    private lateinit var supermercados: List<Supermercado>
    private lateinit var gvsuper: GridView
    private lateinit var adapterProducto: ProductoListaAdapter
    private lateinit var productos: List<Listadecompra>
    private lateinit var gvProductosLista: GridView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.appBarDrawer.toolbar)
        // Ocultar el título del Toolbar
        supportActionBar?.setDisplayShowTitleEnabled(false)
        drawerLayout = binding.drawerLayout
        navView = binding.navView
        // Después de inflar el layout
        navHeader = navView.getHeaderView(0)
        usuario = intent.getSerializableExtra("usuario") as Usuario?
        frameLayout = findViewById(R.id.frame)
        val tollbar: Toolbar = binding.appBarDrawer.toolbar
        bottomNavView = binding.btNav.navButtonview
        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, binding.appBarDrawer.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        toggle.syncState()
        sh = getSharedPreferences("usuario", MODE_PRIVATE)
        // Obtener los datos de SharedPreferences
        val username = sh.getString("nombre_usuario", null)
        val email = sh.getString("email", null)
        // Actualizar la información en el HeaderView con los datos de SharedPreferences
        val usernameTextView = navHeader.findViewById<TextView>(R.id.tvusername)
        val emailTextView = navHeader.findViewById<TextView>(R.id.tvuseremail)
        usernameTextView.text = username
        emailTextView.text = email

        navView.setNavigationItemSelectedListener(this)
        bottomNavView.setOnNavigationItemSelectedListener(this)
        tollbar.setNavigationIcon(R.drawable.ic_menu)

        setInitialActivity()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            finish()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                inflateActivityLayout(R.layout.activity_home)
                gvcategorias = findViewById(R.id.gvCategorias)
                initRV()
                getCategorias()
                bottomNavView.menu.getItem(0).isChecked = true
            }
            R.id.nav_perfil -> {
                val intent = Intent(this, PerfilActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.nav_feedback -> {
                val url = "https://forms.gle/8zjg8mcgYoF2arXr7"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                startActivity(intent)
                return true
            }
            R.id.nav_logout -> {

                sh = getSharedPreferences("usuario", Context.MODE_PRIVATE)
                sh.edit().clear().apply()

                val intent = Intent(this, MainActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
            R.id.navigation_home -> {
                inflateActivityLayout(R.layout.activity_home)
                gvcategorias = findViewById(R.id.gvCategorias)
                initRV()
                getCategorias()
                return true
            }
            R.id.navigation_supermercado -> {
                inflateActivityLayout(R.layout.activity_supermercado)
                gvsuper = findViewById(R.id.gvSupermercados)
                initRVSuper()
                getSupermercados()
                return true
            }
            R.id.navigation_tulista -> {
                Toast.makeText(this, "Funcionalidad en Proceso de Construcción", Toast.LENGTH_SHORT).show()
                inflateActivityLayout(R.layout.activity_home)
                gvcategorias = findViewById(R.id.gvCategorias)
                initRV()
                getCategorias()
                bottomNavView.menu.getItem(0).isChecked = true
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun getCategorias() {
        viewModel.getAllCategorias().observe(this, Observer { it ->
            it?.let{
                categorias=it
                categoriaAdapter.setCategorias(categorias)
                for (categoria in categorias) {
                    Log.d("paco", "${categoria.nombre}")
                }
            }
        })
    }
    private fun setInitialActivity() {
        inflateActivityLayout(R.layout.activity_home)
        gvcategorias = findViewById(R.id.gvCategorias)
        initRV()
        getCategorias()

    }
    private fun initRVTuLista() {
        adapterProducto = ProductoListaAdapter(this, R.layout.item_grid_producto)
        gvProductosLista.adapter = adapterProducto
        gvProductosLista.numColumns = 2
    }
    private fun initRV() {
        categoriaAdapter = CategoriaAdapter(this, R.layout.item_grid_categoria)
        gvcategorias.adapter = categoriaAdapter
        gvcategorias.numColumns = 2
    }

    fun onClickCategoria(view: View) {
        val viewHolder = view.tag as CategoriaAdapter.ViewHolder
        val categoria = viewHolder.getCategoria()
        val intent = Intent(this, SubcategoriaActivity::class.java)
        intent.putExtra("categoria", categoria)
        startActivity(intent)
    }
    fun onClickSupermercado(view: View) {
        val viewHolder = view.tag as SupermercadoAdapter.ViewHolder
        val supermercado = viewHolder.getSupermercado()
        val intent = Intent(this, ProductosSuperActivity::class.java)
        intent.putExtra("supermercado", supermercado)
        startActivity(intent)
        bottomNavView.menu.getItem(1).isChecked = true
    }
    private fun getSupermercados() {
        viewModel.getSupermercados().observe(this) { it ->
            it?.let {
                supermercados = it
                adapterSuper.setSupermercados(supermercados)
            }
        }
    }

    private fun initRVSuper() {
        adapterSuper = SupermercadoAdapter(this, R.layout.item_grid_supermercado)
        gvsuper.adapter = adapterSuper
        gvsuper.numColumns = 2
    }
    private fun inflateActivityLayout(layoutResId: Int) {
        frameLayout.removeAllViews()
        layoutInflater.inflate(layoutResId, frameLayout)
        bottomNavView.menu.getItem(0).isChecked = true
    }
    fun inflateSubcategoriaActivityLayout(layoutResId: Int) {
        frameLayout.removeAllViews()
        layoutInflater.inflate(layoutResId, frameLayout)
    }

    fun inflateTipoActivityLayout(layoutResId: Int) {
        frameLayout.removeAllViews()
        layoutInflater.inflate(layoutResId, frameLayout)
    }

    fun inflateProductoActivityLayout(layoutResId: Int) {
        frameLayout.removeAllViews()
        layoutInflater.inflate(layoutResId, frameLayout)
    }

    fun inflateSupermercadoProductoActivityLayout(layoutResId: Int) {
        frameLayout.removeAllViews()
        layoutInflater.inflate(layoutResId, frameLayout)
        bottomNavView.menu.getItem(1).isChecked = true
    }
}


