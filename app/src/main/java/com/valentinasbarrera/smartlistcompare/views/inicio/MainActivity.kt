package com.valentinasbarrera.smartlistcompare.views.inicio

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.google.android.material.tabs.TabLayout
import androidx.appcompat.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Usuario
import com.valentinasbarrera.smartlistcompare.views.nav.BaseActivity
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel

open class MainActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var frameContainer: FrameLayout
    private lateinit var viewModel: MainViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private var usuario: Usuario? = null
    private val PREF_LOGGED_IN = "logged_in"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tabLayout = findViewById(R.id.tab_layout)
        frameContainer = findViewById(R.id.frame)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        setupTabs()
        setInitialActivity()
    }

    private fun setupTabs() {
        val tabOne = tabLayout.newTab().setText("Login")
        val tabTwo = tabLayout.newTab().setText("Register")

        tabLayout.addTab(tabOne)
        tabLayout.addTab(tabTwo)

        val layoutResIds = arrayListOf(
            R.layout.activity_login,
            R.layout.activity_register
        )

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val layoutResId = layoutResIds[tab.position]
                inflateActivityLayout(layoutResId)
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {
                // No se necesita ninguna acción adicional cuando se deselecciona una pestaña
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
                // No se necesita ninguna acción adicional cuando se vuelve a seleccionar una pestaña
            }
        })
    }

    private fun inflateActivityLayout(layoutResId: Int) {
        frameContainer.removeAllViews()
        layoutInflater.inflate(layoutResId, frameContainer, true)
    }

    private fun setInitialActivity() {
        inflateActivityLayout(R.layout.activity_login)
        sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE)
    }

    private fun getUserSH() {
        val id = sharedPreferences.getInt("id", 0)
        val nombre_usuario = sharedPreferences.getString("nombre_usuario", null)
        val email = sharedPreferences.getString("email", null)
        val contrasena = sharedPreferences.getString("contrasena", null)
        val isLoggedIn = sharedPreferences.getBoolean(PREF_LOGGED_IN, false)
        if (isLoggedIn && id != 0 && nombre_usuario != null && email != null && contrasena != null) {
            usuario = Usuario(id, nombre_usuario, email, contrasena)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun saveUserToSharedPreferences() {
        val editor = sharedPreferences.edit()
        editor.putInt("id", usuario!!.id)
        editor.putString("nombre_usuario", usuario!!.nombre_usuario)
        editor.putString("email", usuario!!.email)
        editor.putString("contrasena", usuario!!.contrasena)
        editor.putBoolean(PREF_LOGGED_IN, true)
        editor.commit()

    }
    private fun saveUserToSharedPreferencesRegister(usuario: Usuario) {
        val editor = sharedPreferences.edit()
        editor.putInt("id", usuario!!.id)
        editor.putString("nombre_usuario", usuario!!.nombre_usuario)
        editor.putString("email", usuario!!.email)
        editor.putString("contrasena", usuario!!.contrasena)
        editor.putBoolean(PREF_LOGGED_IN, true)
        editor.commit()

    }

    private fun registerUser(email: String, nombre_usuario: String, contrasena: String) {
        if (email.isEmpty() || nombre_usuario.isEmpty() || contrasena.isEmpty()) {
            showToast("Por favor, completa todos los campos")
            return
        } else {
            viewModel.saveUsuario(Usuario(0, nombre_usuario, email, contrasena)).observe(this, Observer { it ->
                it?.let { usuarioRegistrado ->
                    saveUserToSharedPreferencesRegister(usuarioRegistrado)

                    showToast("Te has registrado correctamente")

                    val intent = Intent(this, BaseActivity::class.java)
                    intent.putExtra("usuario", usuarioRegistrado)
                    startActivity(intent)
                }
            })


        }
    }
    fun onClickLogin(view: View) {
        val emailEditText = findViewById<EditText>(R.id.useremail)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val email = emailEditText.text.toString()
        val contrasena = passwordEditText.text.toString()

        if (email.isEmpty() || contrasena.isEmpty()) {
            showToast("Por favor, completa todos los campos")
        } else {
            loginuser(email, contrasena)
        }
    }
    private fun loginuser(email: String, contrasena: String) {
        viewModel.getUserByEmailAndPass(email,contrasena).observe(this, Observer { it ->
            if (it == null) {
                showToast("Usuario no encontrado, debe registrarse")
                inflateActivityLayout(R.layout.activity_register)
                tabLayout.getTabAt(1)?.select()
            }
            else {
                usuario = it
                saveUserToSharedPreferences()
                showToast("Bienvenido ${usuario!!.nombre_usuario}")
                val intent = Intent(this, BaseActivity::class.java)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
                getUserSH()
            }
        })
    }
    fun onClickRegister(view: View) {
        val emailEditText = findViewById<EditText>(R.id.useremail)
        val usernameEditText = findViewById<EditText>(R.id.username)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val email = emailEditText.text.toString()
        val nombre_usuario = usernameEditText.text.toString()
        val contrasena = passwordEditText.text.toString()

        if (email.isEmpty() || nombre_usuario.isEmpty() || contrasena.isEmpty()) {
            showToast("Por favor, completa todos los campos")
        } else {
            registerUser(email, nombre_usuario, contrasena)
        }
    }
}
