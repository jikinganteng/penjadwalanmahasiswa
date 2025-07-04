package com.zikin.penjadwalanmahasiswa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var container: LinearLayout
    private lateinit var db: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnTambah = findViewById<Button>(R.id.btnTambah)
        container = findViewById(R.id.containerKegiatan)
        db = DatabaseHelper(this)

        btnTambah.setOnClickListener {
            startActivity(Intent(this, TambahKegiatanActivity::class.java))
        }
    }

    override fun onResume() {
        super.onResume()
        loadKegiatan()
    }

    private fun loadKegiatan() {
        container.removeAllViews()
        val listKegiatan = db.getAllKegiatan()

        listKegiatan.forEach { kegiatan ->
            val textView = TextView(this).apply {
                text = "${kegiatan.nama} - ${kegiatan.tanggal}"
                textSize = 16f
                setPadding(0, 10, 0, 10)
                setOnClickListener {
                    val intent = Intent(this@MainActivity, DetailKegiatanActivity::class.java)
                    intent.putExtra("id", kegiatan.id)
                    startActivity(intent)
                }
            }
            container.addView(textView)
        }
    }
}
