package com.example.trainingapp

import android.content.Intent
import android.os.Bundle
import android.text.SpannableString
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class RegisterActivity : AppCompatActivity() {

    //private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val textSeq = getString(R.string.textRules)
        val span = SpanGenerator(this, textSeq)
        val sps = SpannableString(textSeq)
        span.generate(
            sps,
            "политикой конфиденциальности",
            "https://www.youtube.com/watch?v=v-rWnobdpTM"
        )
        span.generate(sps, "пользовательское соглашение")
        findViewById<TextView>(R.id.textRules).text = sps

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, WelcomeActivity::class.java)
        startActivity(intent)
        return super.onContextItemSelected(item)
    }
}