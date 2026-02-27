package edu.iu.luddy.clicker_captoneproject

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore


class MainActivity : AppCompatActivity() {
    //firestore:
    val db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val aboutUs: Button = findViewById(R.id.aboutUs)
        val start: Button = findViewById(R.id.start)
        val db = FirebaseFirestore.getInstance()
        db.collection("upgrades")
            .document("U1")
            .update("auto", false)
            .addOnSuccessListener {

            }
            .addOnFailureListener { exception ->

            }
        db.collection("upgrades")
            .document("U2")
            .update("auto", false)
            .addOnSuccessListener {

            }
            .addOnFailureListener { exception ->

            }


        aboutUs.setOnClickListener {
            val intent = Intent(this, AboutTheGame::class.java)
            startActivity(intent)
        }
        start.setOnClickListener {
            val intent = Intent(this, CookieClickeritself::class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
