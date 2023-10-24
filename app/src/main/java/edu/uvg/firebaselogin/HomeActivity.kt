package edu.uvg.firebaselogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC
}

class HomeActivity : AppCompatActivity() {

    var emailTextView : TextView = findViewById(R.id.emailRegister)
    var providerTextView : TextView = findViewById(R.id.providerInput)
    var logoutBtn : Button = findViewById(R.id.logOutBtn)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup( email ?: "", provider ?: "")
    }

    private fun setup( email : String, provider : String) {
        title = "Home"
        emailTextView.text = email
        providerTextView.text = provider

        logoutBtn.setOnClickListener{
            FirebaseAuth.getInstance().signOut()
            onBackPressedDispatcher.onBackPressed()
        }
    }
}