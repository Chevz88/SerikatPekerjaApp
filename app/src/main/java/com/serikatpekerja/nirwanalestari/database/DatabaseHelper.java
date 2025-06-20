package com.serikatpekerja.nirwanalestari.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.serikatpekerja.nirwanalestari.models.Laporan;
import com.serikatpekerja.nirwanalestari.models.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "serikatpekerja.db";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users(nik TEXT PRIMARY KEY, nama TEXT, ktp TEXT, departemen TEXT, password TEXT)");
        db.execSQL("CREATE TABLE laporan(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, nik TEXT, departemen TEXT, isi TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS laporan");
        onCreate(db);
    }

    // Tambahkan user baru
    public long insertUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nik", user.getNik());
        cv.put("nama", user.getNama());
        cv.put("ktp", user.getKtp());
        cv.put("departemen", user.getDepartemen());
        cv.put("password", user.getPassword());
        return db.insert("users", null, cv);
    }

    // Login: Cek apakah user ada berdasarkan nik dan password
    public boolean checkUser(String nik, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE nik = ? AND password = ?", new String[]{nik, password});
        boolean exists = cursor.getCount() > 0;
        cursor.close();
        return exists;
    }

    // Ambil nama user berdasarkan NIK
    public String getUserName(String nik) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = "";
        Cursor cursor = db.rawQuery("SELECT nama FROM users WHERE nik = ?", new String[]{nik});
        if (cursor.moveToFirst()) {
            name = cursor.getString(0);
        }
        cursor.close();
        return name;
    }

    // Ambil user lengkap berdasarkan NIK
    public User getUserByNIK(String nik) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM users WHERE nik = ?", new String[]{nik});

        User user = null;
        if (cursor.moveToFirst()) {
            String nama = cursor.getString(cursor.getColumnIndex("nama"));
            String ktp = cursor.getString(cursor.getColumnIndex("ktp"));
            String departemen = cursor.getString(cursor.getColumnIndex("departemen"));
            String password = cursor.getString(cursor.getColumnIndex("password"));
            user = new User(nama, ktp, nik, departemen, password);
        }

        cursor.close();
        return user;
    }

    // Simpan pengaduan
    public long insertLaporan(Laporan laporan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nama", laporan.getNama());
        cv.put("nik", laporan.getNik());
        cv.put("departemen", laporan.getDepartemen());
        cv.put("isi", laporan.getIsi());
        return db.insert("laporan", null, cv);
    }

    // Ambil semua laporan
    public List<Laporan> getAllLaporan() {
        List<Laporan> laporanList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM laporan ORDER BY id DESC", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String nama = cursor.getString(cursor.getColumnIndex("nama"));
                String nik = cursor.getString(cursor.getColumnIndex("nik"));
                String departemen = cursor.getString(cursor.getColumnIndex("departemen"));
                String isi = cursor.getString(cursor.getColumnIndex("isi"));

                Laporan laporan = new Laporan(id, nama, nik, departemen, isi);
                laporanList.add(laporan);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return laporanList;
    }
}
