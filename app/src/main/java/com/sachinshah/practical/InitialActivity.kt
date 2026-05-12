package com.sachinshah.practical

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.sachinshah.practical.prefdatastore.PrefDatastoreUtils
import com.sachinshah.practical.room.entity.UserModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class InitialActivity : AppCompatActivity() {

    @Inject
    lateinit var prefDatastoreUtils: PrefDatastoreUtils

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_initial)


        /*     var keepShowingSplash=true
             splashScreen.setKeepOnScreenCondition { keepShowingSplash }
     */
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Handler(Looper.getMainLooper()).postDelayed({
            // keepShowingSplash=false
            openHomeActivity()
            finish()
        }, 2000)

        checkLogin()
    }

    fun setLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            prefDatastoreUtils.setLogin(true)
            prefDatastoreUtils.setUser(UserModel(firstName = "Ronak", lastName = "Jain", uid = 55))
        }

    }

    fun checkLogin() {
        CoroutineScope(Dispatchers.IO).launch {
            val isLogin = async { prefDatastoreUtils.isLogin().first() }
            if (isLogin.await()) {
                openHomeActivity()
            } else {
                setLogin()
                Handler(mainLooper).postDelayed(
                    {
                        Toast.makeText(this@InitialActivity, "Pls Login", Toast.LENGTH_SHORT).show()

                    }, 100)
            }
        }

    }


    fun openHomeActivity() {
        startActivity(Intent(this, DatabaseCrudActivity::class.java))
    }
}