package edu.iu.luddy.clicker_captoneproject

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


//firebase score upload,
//go back to game
//about game

private var cookie = 0


class YOUTRIEDTOLEAVE : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.youtriedtoleave)
        /*
        val reset: Button = findViewById(R.id.resetgame)
        val start: Button = findViewById(R.id.backtogame)

         */
        val confirm: Button = findViewById(R.id.confirm)
        cookie = intent.getIntExtra("KEY_FOR_YOUR_VARIABLE", 0)

        /*


        start.setOnClickListener {
            val intent = Intent(this, CookieClickeritself::class.java)
            intent.putExtra("KEY_FOR_YOUR_VARIABLE", cookie)
            startActivity(intent)
            finish()
        }
        reset.setOnClickListener {
            cookie = 0
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

         */


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main5)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //pick a spinner
        //spect spinner
        //on crime click move what start and reset do
        val items = arrayListOf("Restart Game", "Back to game!") // input spinner items here
        val adaper = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            items
        ) // formats the spinner
        val wow : Spinner = findViewById<Spinner>(R.id.spinner) // makes the spinner
        wow.adapter = adaper // puts the formater on the adapter
        //val selectedItem = wow.selectedItem as String // use this to receive the item selected in the spinner as a string (when you onclick the calculate button)

        confirm.setOnClickListener {
            val selectedItem = wow.selectedItem as String // use this to receive the item selected in the spinner as a string (when you onclick the calculate button)
            if (selectedItem == "Restart Game"){
                cookie = 0
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                val intent = Intent(this, CookieClickeritself::class.java)
                intent.putExtra("KEY_FOR_YOUR_VARIABLE", cookie)
                startActivity(intent)
                finish()
            }

            }

    }
}
