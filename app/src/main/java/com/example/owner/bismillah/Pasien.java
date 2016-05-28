package com.example.owner.bismillah;

/**
 * Created by owner on 5/24/2016.
 */
public class Pasien {
        private long id;
        private String nama_pasien;
        private String alamat_pasien;

        public Pasien()
        {

        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getNama_pasien() {
            return nama_pasien;
        }

        public void setNama_pasien (String nama_pasien) {
            this.nama_pasien = nama_pasien;
        }

        public String getAlamat_pasien() {
            return alamat_pasien;
        }
        public void setAlamat_pasien (String alamat_pasien) {
            this.alamat_pasien = alamat_pasien;
        }


        @Override
        public String toString()
        {
            return "Nama Pasien "+ nama_pasien +" "+ alamat_pasien;
        }
    }
