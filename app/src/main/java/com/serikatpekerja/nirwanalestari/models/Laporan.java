package com.serikatpekerja.nirwanalestari.models;

public class Laporan {
    private String nama;
    private String nik;
    private String departemen;
    private String isi;

    public Laporan(String nama, String nik, String departemen, String isi) {
        this.nama = nama;
        this.nik = nik;
        this.departemen = departemen;
        this.isi = isi;
    }

    public String getNama() { return nama; }
    public String getNik() { return nik; }
    public String getDepartemen() { return departemen; }
    public String getIsi() { return isi; }
}
