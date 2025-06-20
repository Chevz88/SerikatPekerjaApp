package com.serikatpekerja.nirwanalestari.models;

public class Laporan {

    private int id;
    private String nama;
    private String nik;
    private String departemen;
    private String isi;

    // Konstruktor untuk insert (tanpa id)
    public Laporan(String nama, String nik, String departemen, String isi) {
        this.nama = nama;
        this.nik = nik;
        this.departemen = departemen;
        this.isi = isi;
    }

    // ✅ Konstruktor tambahan untuk menampilkan data lengkap dengan ID
    public Laporan(int id, String nama, String nik, String departemen, String isi) {
        this.id = id;
        this.nama = nama;
        this.nik = nik;
        this.departemen = departemen;
        this.isi = isi;
    }

    // Getter dan Setter
    public int getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public String getNik() {
        return nik;
    }

    public String getDepartemen() {
        return departemen;
    }

    public String getIsi() {
        return isi;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public void setDepartemen(String departemen) {
        this.departemen = departemen;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }
}
