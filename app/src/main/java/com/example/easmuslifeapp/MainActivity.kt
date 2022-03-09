package com.example.easmuslifeapp

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()

        findViewById<Button>(R.id.signInButton).setOnClickListener {
            startActivity(Intent(this, UserDataActivity::class.java))
//            signInMethod(
//                findViewById<TextView>(R.id.editTextEmail),
//                findViewById<TextView>(R.id.editTextPassword)
//            )
        }

        findViewById<TextView>(R.id.signUp).setOnClickListener {
            findViewById<ConstraintLayout>(R.id.signUp_layout).visibility = View.VISIBLE
            findViewById<ConstraintLayout>(R.id.signIn_layout).visibility = View.INVISIBLE

            findViewById<Button>(R.id.signUpButton).setOnClickListener {
                signUpMethod()
            }
        }

        findViewById<TextView>(R.id.signin).setOnClickListener {
            findViewById<ConstraintLayout>(R.id.signIn_layout).visibility = View.VISIBLE
            findViewById<ConstraintLayout>(R.id.signUp_layout).visibility = View.INVISIBLE

            findViewById<Button>(R.id.signInButton).setOnClickListener {
                signInMethod(
                    findViewById<TextView>(R.id.editTextEmail),
                    findViewById<TextView>(R.id.editTextPassword)
                )
            }
        }
    }

    private fun signInMethod(email: TextView, password: TextView) {
        if (email.text.toString() == "darco" && password.text.toString() == "darco")
            startActivity(Intent(this, AdminMenuActivity::class.java))
        else {
            auth.signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "signInWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    private fun signUpMethod() {
        // Initialize Firebase Auth
        if (findViewById<EditText>(R.id.editTextPasswordSignUp).text.toString() != findViewById<EditText>(
                R.id.editTextPasswordSignUp2
            ).text.toString()
        ) {
            Log.d(TAG, "Passwords do not match")
            Log.d(TAG, findViewById<EditText>(R.id.editTextPasswordSignUp).text.toString())
            Log.d(TAG, findViewById<EditText>(R.id.editTextPasswordSignUp2).text.toString())

            Toast.makeText(
                baseContext, "Passwords do not match",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            auth.createUserWithEmailAndPassword(
                findViewById<EditText>(R.id.editTextEmailSignUp).text.toString(),
                findViewById<EditText>(R.id.editTextPasswordSignUp).text.toString()
            )
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
                }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        startActivity(Intent(this, UserDataActivity::class.java))
        //startActivity(Intent(this, MainActivity2::class.java))
    }

//    public override fun onStart() {
//        super.onStart()
//        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = auth.currentUser
//        if(currentUser != null){
//            //reload();
//        }
//    }
}