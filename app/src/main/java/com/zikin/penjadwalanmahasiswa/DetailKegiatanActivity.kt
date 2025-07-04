package com.zikin.penjadwalanmahasiswa

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DetailKegiatanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_kegiatan)

        val txtDetail = findViewById<TextView>(R.id.txtDetail)
        val btnHapus = findViewById<Button>(R.id.btnHapus)
        val id = intent.getIntExtra("id", -1)

        val db = DatabaseHelper(this)
        val kegiatan = db.getKegiatanById(id)

        kegiatan?.let {
            val info = "Nama: ${it.nama}\nTanggal: ${it.tanggal}\nJam: ${it.jam}\nDeskripsi: ${it.deskripsi}"
            txtDetail.text = info
        }

        btnHapus.setOnClickListener {
            val deleted = db.deleteKegiatanById(id)
            if (deleted) {
                Toast.makeText(this, "Kegiatan berhasil dihapus", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Gagal menghapus kegiatan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
