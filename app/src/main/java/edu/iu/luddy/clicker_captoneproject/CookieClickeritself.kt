package edu.iu.luddy.clicker_captoneproject

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.os.Handler
import android.widget.ImageButton

class CookieClickeritself : AppCompatActivity() {
    private var cookie = 0
    private var unlock1v = false
    private var unlock2v = false
    private lateinit var runnable: Runnable
    private val handler = Handler(Looper.getMainLooper())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.cookieclicker)

        // Retrieve the cookie count from the intent
        cookie = intent.getIntExtra("KEY_FOR_YOUR_VARIABLE", 0)
        unlock1v = intent.getBooleanExtra("KEY_FOR_YOUR_VARIABL", false)
        unlock2v = intent.getBooleanExtra("KEY_FOR_YOUR_VARIAB", false)


        val back2intro: Button = findViewById(R.id.backtointro2)
        val upgradeMenuButton: Button = findViewById(R.id.toupgradeMenu)
        val theCookie: ImageButton = findViewById(R.id.theCookie)
        val clickCount: TextView = findViewById(R.id.clickCount)

        clickCount.text = "Amount: $cookie"

        back2intro.setOnClickListener {
            val intent = Intent(this, YOUTRIEDTOLEAVE::class.java)
            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.putExtra("KEY_FOR_YOUR_VARIABLE", cookie)
            startActivity(intent)
            finish()
        }

        upgradeMenuButton.setOnClickListener {
            val intent = Intent(this, UpgradesMenu::class.java)
            intent.putExtra("KEY_FOR_YOUR_VARIABLE", cookie)
            intent.putExtra("KEY_FOR_YOUR_VARIABL", unlock1v)
            intent.putExtra("KEY_FOR_YOUR_VARIAB", unlock2v)
            startActivity(intent)
        }

        if (unlock2v == true){
            runnable = object : Runnable{
                override fun run() {
                    cookie++
                    clickCount.text = "Amount: $cookie"
                    handler.postDelayed(this, 2000)
                }
            }
        }

        theCookie.setOnClickListener {
            cookie++
            if (unlock1v == true){
                cookie++
            }
            clickCount.text = "Amount: $cookie"
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main3)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onResume() {
        super.onResume()
        unlock2v = intent.getBooleanExtra("KEY_FOR_YOUR_VARIAB", false)
        if (unlock2v == true){
            handler.post(runnable)
        }
    }

    override fun onPause() {
        super.onPause()
        unlock2v = intent.getBooleanExtra("KEY_FOR_YOUR_VARIAB", false)
        if (unlock2v == true) {
            handler.removeCallbacks(runnable)
        }
    }
}
