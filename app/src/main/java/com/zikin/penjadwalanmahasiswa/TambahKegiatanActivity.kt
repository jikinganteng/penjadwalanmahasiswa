package com.zikin.penjadwalanmahasiswa

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class TambahKegiatanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tambah_kegiatan)

        val nama = findViewById<EditText>(R.id.inputNama)
        val tanggal = findViewById<EditText>(R.id.inputTanggal)
        val jam = findViewById<EditText>(R.id.inputJam)
        val deskripsi = findViewById<EditText>(R.id.inputDeskripsi)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)

        val calendar = Calendar.getInstance()

        tanggal.setOnClickListener {
            DatePickerDialog(this, { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                tanggal.setText(dateFormat.format(calendar.time))
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
        }

        jam.setOnClickListener {
            TimePickerDialog(this, { _, hourOfDay, minute ->
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
                calendar.set(Calendar.MINUTE, minute)
                val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
                jam.setText(timeFormat.format(calendar.time))
            }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
        }

        val db = DatabaseHelper(this)

        btnSimpan.setOnClickListener {
            val sukses = db.insertKegiatan(
                nama.text.toString(),
                tanggal.text.toString(),
                jam.text.toString(),
                deskripsi.text.toString()
            )
            if (sukses) {
                Toast.makeText(this, "Kegiatan berhasil disimpan", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Gagal menyimpan kegiatan", Toast.LENGTH_SHORT).show()
            }
        }
    }
}