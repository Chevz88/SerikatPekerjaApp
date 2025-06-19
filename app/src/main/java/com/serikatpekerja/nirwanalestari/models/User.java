package com.serikatpekerja.nirwanalestari.models;

public class User {
    private String nama;
    private String ktp;
    private String nik;
    private String departemen;
    private String password;

    public User(String nama, String ktp, String nik, String departemen, String password) {
        this.nama = nama;
        this.ktp = ktp;
        this.nik = nik;
        this.departemen = departemen;
        this.password = password;
    }

    public String getNama() { return nama; }
    public String getKtp() { return ktp; }
    public String getNik() { return nik; }
    public String getDepartemen() { return departemen; }
    public String getPassword() { return password; }
}
