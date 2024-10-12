package com.example.marketminds.logins

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.marketminds.HomePage
import com.example.marketminds.R
import com.example.marketminds.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth


class SignUpPage : AppCompatActivity() {

    private lateinit var binding:ActivitySignUpPageBinding
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_up_page)

        binding=ActivitySignUpPageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth=FirebaseAuth.getInstance()

        binding.btnContinue.setOnClickListener {
            val email=binding.txtEmail.text.toString()
            val password=binding.txtPassword.text.toString()

            currentUser(email,password)

            }

        binding.registerButton.setOnClickListener {
            startActivity(Intent(this, LoginPage::class.java))
            }
        }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            navigateToHone()
        }
    }


    private fun currentUser(email: String, password: String) {
        if(email.isNotEmpty() && password.isNotEmpty()){
            auth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this){task->
                    if(task.isSuccessful){
                        Toast.makeText(this,"Sign-up Successful",Toast.LENGTH_SHORT).show()
                        navigateToHone()
                    }else{
                        Toast.makeText(this,"Sign-up failed",Toast.LENGTH_SHORT).show()


                    }
                }

    }else{
            Toast.makeText(this,"Please enter both email and password",Toast.LENGTH_SHORT).show()
        }


}

    private fun navigateToHone() {
        startActivity(Intent(this, HomePage::class.java))
        finish()
    }
}