package com.example.owner.bismillah;

/**
 * Created by Owner on 5/24/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import static com.example.owner.bismillah.DBHelper.*;

public class DBDataSourceDokter {

    //inisialiasi SQLite Database
    private SQLiteDatabase database;

    //inisialisasi kelas DBHelper
    private DBHelperDokter dbHelperD;

    //ambil semua nama kolom
    private String[] allColumns = { DBHelperDokter.COLUMN_ID,
            DBHelperDokter.COLUMN_NAME, DBHelperDokter.COLUMN_ALAMAT};

    //DBHelper diinstantiasi pada constructor
    public DBDataSourceDokter(Context context)
    {
       dbHelperD = new DBHelperDokter(context);
    }

    //membuka/membuat sambungan baru ke database
    public void open() throws SQLException {
        database = dbHelperD.getWritableDatabase();
    }

    //menutup sambungan ke database
    public void close() {
        dbHelperD.close();
    }

    //method untuk create/insert barang ke database
    public Dokter createDokter(String namadokter, String alamatdokter) {

        // membuat sebuah ContentValues, yang berfungsi
        // untuk memasangkan data dengan nama-nama
        // kolom pada database
        ContentValues values = new ContentValues();
        values.put(DBHelperDokter.COLUMN_NAME, namadokter);
        values.put(DBHelperDokter.COLUMN_ALAMAT, alamatdokter);

        // mengeksekusi perintah SQL insert data
        // yang akan mengembalikan sebuah insert ID
        long insertId = database.insert(DBHelperDokter.TABLE_NAME, null,
                values);

        // setelah data dimasukkan, memanggil
        // perintah SQL Select menggunakan Cursor untuk
        // melihat apakah data tadi benar2 sudah masuk
        // dengan menyesuaikan ID = insertID
        Cursor cursor = database.query(DBHelperDokter.TABLE_NAME,
                allColumns, DBHelperDokter.COLUMN_ID + " = " + insertId, null,
                null, null, null);

        // pindah ke data paling pertama
        cursor.moveToFirst();

        // mengubah objek pada kursor pertama tadi
        // ke dalam objek barang
        Dokter newDokter = cursorToDokter(cursor);

        // close cursor
        cursor.close();

        // mengembalikan barang baru
        return newDokter;
    }

    private Dokter cursorToDokter(Cursor cursor)
    {
        // buat objek pasien baru
        Dokter dokter = new Dokter();
        // debug LOGCAT
        Log.v("info", "The getLONG "+cursor.getLong(0));
        Log.v("info", "The setLatLng "+cursor.getString(1)+","+cursor.getString(2));

		/* Set atribut pada objek barang dengan
		 * data kursor yang diambil dari database*/
        dokter.setId(cursor.getLong(0));
        dokter.setNama_dokter(cursor.getString(1));
        dokter.setAlamat_dokter(cursor.getString(2));

        //kembalikan sebagai objek barang
        return dokter;
    }
    public Dokter getDokter(long id) {
        Dokter dokter = new Dokter();


        Cursor cursor = database.query(DBHelperDokter.TABLE_NAME, allColumns, "_id="+id ,null,null,null,null);

        cursor.moveToFirst();

        dokter = cursorToDokter(cursor);

        cursor.close();
        return dokter;
    }
    public ArrayList<Dokter> getAllDokter() {
        ArrayList<Dokter> daftarDokter = new ArrayList<Dokter>();

        Cursor cursor = database.query(DBHelperDokter.TABLE_NAME, allColumns, null,null,null,null,null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            Dokter dokter = cursorToDokter(cursor);
            daftarDokter.add(dokter);
            cursor.moveToNext();
        }
        cursor.close();
        return daftarDokter;
    }

    public void updateDokter (Dokter d) {
        String strFilter = "_id=" + d.getId();

        ContentValues args = new ContentValues();

        args.put(DBHelperDokter.COLUMN_NAME, d.getNama_dokter());
        args.put(DBHelperDokter.COLUMN_ALAMAT, d.getAlamat_dokter());

        database.update(DBHelperDokter.TABLE_NAME, args, strFilter, null);
    }

    public void deleteDokter(long id) {
        String strFilter = "_id=" +id;
        database.delete(DBHelperDokter.TABLE_NAME, strFilter, null);
    }
}
