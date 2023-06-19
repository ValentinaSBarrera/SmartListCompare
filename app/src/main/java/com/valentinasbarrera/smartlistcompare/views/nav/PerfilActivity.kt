package com.valentinasbarrera.smartlistcompare.views.nav

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.model.Usuario
import com.valentinasbarrera.smartlistcompare.viewsmodel.MainViewModel

class PerfilActivity : AppCompatActivity() {
    private var isEditMode = false
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var useremailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var userText: TextView
    private lateinit var buttonEditar: Button
    private lateinit var buttonActualizar: Button
    private lateinit var buttonEliminar: Button
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var viewModel: MainViewModel
    private  var usuario: Usuario? =null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView( R.layout.activity_perfil)

        // Obtén las referencias a los elementos del diseño
        usernameEditText = findViewById(R.id.username)
        useremailEditText = findViewById(R.id.useremail)
        passwordEditText = findViewById(R.id.password)
        userText = findViewById(R.id.userwelcome)

        buttonEditar = findViewById(R.id.buttonEditar)
        buttonActualizar = findViewById(R.id.buttonActualizar)
        buttonEliminar = findViewById(R.id.buttonEliminar)
        sharedPreferences = getSharedPreferences("usuario", MODE_PRIVATE)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val id  = sharedPreferences.getInt("id", 0)

        val username = sharedPreferences.getString("nombre_usuario", null)

        val email = sharedPreferences.getString("email", null)

        val password = sharedPreferences.getString("contrasena", null)

        val userdata = Usuario(id!!, username!!, email!!, password!!)

        // Establece los valores de los EditText
        usernameEditText.setText(username)
        useremailEditText.setText(email)
        passwordEditText.setText(password)
        userText.setText("Bienvenido/a " + username + "!")
        // Establece el clic del botón "Editar"
        buttonEditar.setOnClickListener {
            EditMode()
        }

        // Establece el clic del botón "Actualizar"
        buttonActualizar.setOnClickListener {
            // Debo realizar aqui la lógica de actualización
            viewModel.updateUser(useremailEditText.text.toString()).observe(this, Observer {
                if (it != null) {
                    usuario = it
                    val intent = Intent(this, PerfilActivity::class.java)
                    startActivity(intent)
                }
            })  // Actualiza los valores de los EditText
            usernameEditText.setText(usernameEditText.text)
            useremailEditText.setText(useremailEditText.text)
            passwordEditText.setText(passwordEditText.text)
            // actualiza el shared preferences
            val editor = sharedPreferences.edit()
            editor.putString("nombre_usuario", usernameEditText.text.toString())
            editor.putString("email", useremailEditText.text.toString())
            editor.putString("contrasena", passwordEditText.text.toString())
            // Deshabilita el modo de edición
            EditMode()
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
    private fun EditMode() {
        isEditMode = !isEditMode
        // Habilita o deshabilita los EditText según el modo de edición
        usernameEditText.isEnabled = isEditMode
        useremailEditText.isEnabled = isEditMode
        passwordEditText.isEnabled = isEditMode

        // Actualiza el estado de los botones
        buttonEditar.isEnabled = !isEditMode
        buttonActualizar.isEnabled = isEditMode
    }
}
