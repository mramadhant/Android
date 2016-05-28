package com.example.owner.bismillah;

/**
 * Created by Owner on 5/24/2016.
 */
public class Dokter {
    private long id;
    private String nama_dokter;
    private String alamat_dokter;

    public Dokter()
    {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama_dokter() {
        return nama_dokter;
    }

    public void setNama_dokter (String nama_dokter) {
        this.nama_dokter = nama_dokter;
    }

    public String getAlamat_dokter() {
        return alamat_dokter;
    }
    public void setAlamat_dokter (String alamat_dokter) {
        this.alamat_dokter = alamat_dokter;
    }


    @Override
    public String toString()
    {
        return "Nama Pasien "+ nama_dokter +" "+ alamat_dokter;
    }
}
