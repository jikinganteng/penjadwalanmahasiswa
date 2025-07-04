package com.zikin.penjadwalanmahasiswa

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

data class Kegiatan(val id: Int, val nama: String, val tanggal: String, val jam: String, val deskripsi: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, "KegiatanDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {
        val query = "CREATE TABLE kegiatan (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, tanggal TEXT, jam TEXT, deskripsi TEXT)"
        db.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS kegiatan")
        onCreate(db)
    }

    fun insertKegiatan(nama: String, tanggal: String, jam: String, deskripsi: String): Boolean {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put("nama", nama)
        cv.put("tanggal", tanggal)
        cv.put("jam", jam)
        cv.put("deskripsi", deskripsi)
        val result = db.insert("kegiatan", null, cv)
        return result != -1L
    }

    fun getAllKegiatan(): List<Kegiatan> {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM kegiatan", null)
        val list = mutableListOf<Kegiatan>()
        if (cursor.moveToFirst()) {
            do {
                val kegiatan = Kegiatan(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4)
                )
                list.add(kegiatan)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getKegiatanById(id: Int): Kegiatan? {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM kegiatan WHERE id = ?", arrayOf(id.toString()))
        return if (cursor.moveToFirst()) {
            val kegiatan = Kegiatan(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
            )
            cursor.close()
            kegiatan
        } else {
            cursor.close()
            null
        }
    }

    fun deleteKegiatanById(id: Int): Boolean {
        val db = writableDatabase
        return db.delete("kegiatan", "id = ?", arrayOf(id.toString())) > 0
    }
}
