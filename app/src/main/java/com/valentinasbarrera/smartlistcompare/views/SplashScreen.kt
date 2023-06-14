package com.valentinasbarrera.smartlistcompare.views

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.valentinasbarrera.smartlistcompare.R
import com.valentinasbarrera.smartlistcompare.views.inicio.MainActivity
import com.valentinasbarrera.smartlistcompare.views.nav.BaseActivity


class SplashScreen : AppCompatActivity() {

    private val SLASH_TIMER = 3000L

    //variables
    private lateinit var AppLogo: ImageView;
    private lateinit var AppName: TextView;
    private lateinit var AppSlogan: TextView;
    private lateinit var AppPoweredBy: TextView;
    private lateinit var AppFrom: TextView;
    private lateinit var AppLogoDev: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    private val PREF_LOGGED_IN = "logged_in"

    //animation
    private lateinit var sideAnim: Animation;
    private lateinit var bottomAnim: Animation;

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        //hooks
        AppLogo = findViewById(R.id.ivlogo)
        AppName = findViewById(R.id.txtSplash)
        AppSlogan = findViewById(R.id.txtslogan)
        AppFrom = findViewById(R.id.txtfrom)
        AppPoweredBy = findViewById(R.id.txtdesarrollador)
        AppLogoDev = findViewById(R.id.ivlogoDev)

        sharedPreferences = getSharedPreferences("usuario", Context.MODE_PRIVATE)

        //animation
        sideAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim)
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim)

        //set animation
        AppLogo.setAnimation(sideAnim)
        AppName.setAnimation(bottomAnim)
        AppSlogan.setAnimation(sideAnim)
        AppFrom.setAnimation(bottomAnim)
        AppLogoDev.setAnimation(sideAnim)
        AppPoweredBy.setAnimation(bottomAnim)
    }

    override fun onResume() {
        super.onResume()

        val isLoggedIn = sharedPreferences.getBoolean(PREF_LOGGED_IN, false)

        if (isLoggedIn) {
            Handler().postDelayed({
                val intent = Intent(this, BaseActivity::class.java)
                startActivity(intent)
                finish()
            }, SLASH_TIMER)
        } else {
            Handler().postDelayed({
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, SLASH_TIMER)
        }
    }
}