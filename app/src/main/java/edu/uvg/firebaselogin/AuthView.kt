package edu.uvg.firebaselogin

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var signUpBtn: Button
    private lateinit var loginBtn : Button
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            R.layout.auth_main
        }
        println("HEY THERE")
        setContentView(R.layout.auth_main)

        auth = Firebase.auth

        email = findViewById(R.id.emailInput)
        password = findViewById(R.id.passwordInput)
        signUpBtn = findViewById(R.id.signUpBtn)
        loginBtn = findViewById(R.id.loginBtn)
        println("BYE")
        setup()
    }

    private fun setup() {
        title = "Authentication"
        signUpBtn.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
                    auth.createUserWithEmailAndPassword(
                        email.text.toString(),
                        password.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
        loginBtn.setOnClickListener {
            if (email.text.isNotEmpty() && password.text.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(
                        email.text.toString(),
                        password.text.toString()
                    ).addOnCompleteListener {
                        if (it.isSuccessful) {
                            showHome(it.result.user?.email ?: "", ProviderType.BASIC)
                        } else {
                            showAlert()
                        }
                    }
            }
        }
    }

    private fun showAlert() {
        Log.e("Firebase error:","Something went wrong")
    }

    private fun showHome(email : String, provider : ProviderType) {

        val homeIntent = Intent(this, HomeActivity::class.java)
            .apply {
                putExtra("email", email)
                putExtra("provider", provider.name)
            }
        startActivity(homeIntent)

    }
}