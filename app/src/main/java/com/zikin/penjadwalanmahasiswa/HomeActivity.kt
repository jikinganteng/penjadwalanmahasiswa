package com.zikin.penjadwalanmahasiswa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnMasuk = findViewById<Button>(R.id.btnMasuk)
        btnMasuk.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }
    }
}
