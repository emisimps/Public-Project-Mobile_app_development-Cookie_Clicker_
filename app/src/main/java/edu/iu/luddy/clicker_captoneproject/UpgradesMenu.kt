package edu.iu.luddy.clicker_captoneproject

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore

class UpgradesMenu : AppCompatActivity() {
    private val dataset = arrayOf("x2 click power - Cost: 10 cookie", "auto cookies- Cost: 20 cookies")
    var unlock1V = false
    var unlock2V = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.upgradesmenu)
        //retrieve the cookie count from the intent
        var cookieCount = intent.getIntExtra("KEY_FOR_YOUR_VARIABLE", 0)
        val backButton: Button = findViewById(R.id.tocookieclicker)
        val unlock1: TextView = findViewById<TextView>(R.id.unlock1)
        val unlock2: TextView = findViewById<TextView>(R.id.unlock2)
        val db = FirebaseFirestore.getInstance()
        var u1 = false
        var u2 = false
        db.collection("upgrades")
            .document("U1")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val isEnabled = document.getBoolean("auto") ?: false

                    if (isEnabled) {
                        u1 = true
                        Log.d("hi", "hello")
                    }
                    else {
                        u1 = false
                        Log.d("bye", "goodbye")
                    }
                }
            }
        db.collection("upgrades")
            .document("U2")
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val isEnabled = document.getBoolean("auto") ?: false

                    if (isEnabled) {
                        u2 = true
                    }
                    else {
                        u2 = false
                    }
                }
            }

        unlock1V = intent.getBooleanExtra("KEY_FOR_YOUR_VARIABL", false)
        unlock2V = intent.getBooleanExtra("KEY_FOR_YOUR_VARIAB", false)

        backButton.setOnClickListener {
            //set visiable
            val intent = Intent(this, CookieClickeritself::class.java)
            //sends updated cookie count back to CookieClickeritself
            intent.putExtra("KEY_FOR_YOUR_VARIABLE", cookieCount)
            intent.putExtra("KEY_FOR_YOUR_VARIABL", unlock1V)
            intent.putExtra("KEY_FOR_YOUR_VARIAB", unlock2V)
            startActivity(intent)
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main4)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerview)
        //val selectedItemTextView: TextView = findViewById(R.id.selectedItemTextView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        val customAdapter = Adapter(dataset) { clickedItem ->
            if (clickedItem == 0 && u1 == false) {
                if (cookieCount >= 10) {
                    val options = arrayOf("Yes", "No")
                    var selectedIndex = 0

                    AlertDialog.Builder(this)
                        .setTitle("Are you sure")
                        .setSingleChoiceItems(options, selectedIndex) { _, which ->
                            selectedIndex = which
                        }
                        .setPositiveButton("OK") { _, _ ->
                            val chosen = options[selectedIndex]
                            if (chosen == "Yes") {
                                cookieCount -= 10
                                Snackbar.make(
                                    recyclerView,
                                    "Upgrade Purchased!",
                                    Snackbar.LENGTH_SHORT
                                ).show()
                                    unlock1.visibility = View.VISIBLE
                                    unlock1V = true
                                    u1 = true
                                    db.collection("upgrades")
                                        .document("U1")
                                        .update("auto", true)
                                        .addOnSuccessListener {

                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("FIREBASE", "Update failed", e)
                                        }
                            }
                        }
                        .show()
                } else {
                    Snackbar.make(
                        recyclerView,
                        "You dont have enough cookies for this purchace",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            else if (clickedItem == 1 && u2 == false) {
                if (cookieCount >= 20) {
                    val options = arrayOf("Yes", "No")
                    var selectedIndex = 0

                    AlertDialog.Builder(this)
                        .setTitle("Are you sure")
                        .setSingleChoiceItems(options, selectedIndex) { _, which ->
                            selectedIndex = which
                        }
                        .setPositiveButton("OK") { _, _ ->
                            val chosen = options[selectedIndex]
                            if (chosen == "Yes") {
                                cookieCount -= 20
                                Snackbar.make(
                                    recyclerView,
                                    "Upgrade Purchased!",
                                    Snackbar.LENGTH_SHORT
                                ).show()

                                    unlock2.visibility = View.VISIBLE
                                    unlock2V = true
                                    u2 = true
                                    db.collection("upgrades")
                                        .document("U2")
                                        .update("auto", true)
                                        .addOnSuccessListener {

                                        }
                                        .addOnFailureListener { e ->
                                            Log.e("FIREBASE", "Update failed", e)
                                        }

                            }
                        }
                        .show()
                } else {
                    Snackbar.make(
                        recyclerView,
                        "You dont have enough cookies for this purchace",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            } else {
                Snackbar.make(
                    recyclerView,
                    "you've already bought this",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }

        if (unlock1V == true){
            unlock1.visibility = View.VISIBLE
        }
        if (unlock2V == true){
            unlock2.visibility = View.VISIBLE
        }

        recyclerView.adapter = customAdapter
    }
}
