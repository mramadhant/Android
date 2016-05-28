package com.example.owner.bismillah;

/**
 * Created by Owner on 5/24/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DBDataSourcePasien {

    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelperPasien dbHelperP;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelperPasien.COLUMN_ID,
           DBHelperPasien.COLUMN_NAME, DBHelperPasien.COLUMN_ALAMAT};

    //DBHelper diinstantiasi pada constructor
    public  DBDataSourcePasien(Context context)
    {
        dbHelperP = new DBHelperPasien(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelperP.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelperP.close();
    }

    //method untuk create/insert barang ke database
    public Pasien createPasien(String namapasien, String alamatpasien) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelperPasien.COLUMN_NAME, namapasien);
        values.put(DBHelperPasien.COLUMN_ALAMAT, alamatpasien);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelperPasien.TABLE_NAME, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelperPasien.TABLE_NAME,
                allColumns, DBHelperPasien.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek barang
        Pasien newPasien = cursorToPasien(cursor);

        // close cursor
        cursor.close();

        // mengembalikan barang baru
        return newPasien;
    }

    private Pasien cursorToPasien(Cursor cursor)
    {
        // buat objek pasien baru
        Pasien pasien = new Pasien();
        // debug LOGCAT
        Log.v("info", "The getLONG "+cursor.getLong(0));
        Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));

		/* Set atribut pada objek barang dengan
		 * data kursor yang diambil dari database*/
        pasien.setId(cursor.getLong(0));
        pasien.setNama_pasien(cursor.getString(1));
        pasien.setAlamat_pasien(cursor.getString(2));

        //kembalikan sebagai objek barang
        return pasien;

    }
    public ArrayList<Pasien> getAllPasien() {
        ArrayList<Pasien> daftarPasien = new ArrayList<Pasien>();

        Cursor cursor = database.query(DBHelperPasien.TABLE_NAME, allColumns, null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Pasien pasien = cursorToPasien(cursor);
            daftarPasien.add(pasien);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarPasien;
    }

    public Pasien getPasien(long id) {
        Pasien pasien = new Pasien();


        Cursor cursor = database.query(DBHelperPasien.TABLE_NAME, allColumns, "_id="+id ,null,null,null,null);

        cursor.moveToFirst();

        pasien = cursorToPasien(cursor);

        cursor.close();
        return pasien;
    }

    public void updatePasien (Pasien p) {
        String strFilter = "_id=" + p.getId();

        ContentValues args = new ContentValues();

        args.put(DBHelperPasien.COLUMN_NAME, p.getNama_pasien());
        args.put(DBHelperPasien.COLUMN_ALAMAT, p.getAlamat_pasien());

        database.update(DBHelperPasien.TABLE_NAME, args, strFilter, null);
    }

    public void deletePasien(long id) {
        String strFilter = "_id=" +id;
        database.delete(DBHelperPasien.TABLE_NAME, strFilter, null);
    }

}
