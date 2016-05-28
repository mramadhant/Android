package com.example.owner.bismillah;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    /**
     * deklarasi konstanta-konstanta yang digunakan pada database, seperti nama tabel,
     * nama-nama kolom, nama database, dan versi dari database
     **/
    public static final String TABLE_NAME = "data_inventori";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nama_barang";
    public static final String COLUMN_MERK = "merk_barang";
    public static final String COLUMN_HARGA = "harga_barang";
    private static final String db_name = "inventori.db";
    private static final int db_version = 1;

    // Perintah SQL untuk membuat tabel database baru
    private static final String db_create = "create table "
            + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_NAME + " varchar(50) not null, "
            + COLUMN_MERK + " varchar(50) not null, "
            + COLUMN_HARGA + " varchar(50) not null);";

    public DBHelper(Context context) {
        super(context, db_name, null, db_version);
        // Auto generated
    }

    //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    // dijalankan apabila ingin mengupgrade database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(DBHelper.class.getName(), "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }
}
    class DBHelperDokter extends SQLiteOpenHelper {
        /**
         * deklarasi konstanta-konstanta yang digunakan pada database, seperti nama tabel,
         * nama-nama kolom, nama database, dan versi dari database
         **/
        public static final String TABLE_NAME = "data_dokter";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "nama_dokter";
        public static final String COLUMN_ALAMAT = "alamat_dokter";
        private static final String db_name = "dokter.db";
        private static final int db_version = 1;

        // Perintah SQL untuk membuat tabel database baru
        private static final String db_create = "create table "
                + TABLE_NAME + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " varchar(50) not null, "
                + COLUMN_ALAMAT + " varchar(50) not null); ";

        public DBHelperDokter(Context context) {
            super(context, db_name, null, db_version);
            // Auto generated
        }

        //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(db_create);
        }

        // dijalankan apabila ingin mengupgrade database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DBHelperDokter.class.getName(), "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }
    }


    class DBHelperPasien extends SQLiteOpenHelper {
        /**
         * deklarasi konstanta-konstanta yang digunakan pada database, seperti nama tabel,
         * nama-nama kolom, nama database, dan versi dari database
         **/
        public static final String TABLE_NAME = "data_pasien";
        public static final String COLUMN_ID = "_id";
        public static final String COLUMN_NAME = "nama_pasien";
        public static final String COLUMN_ALAMAT = "alamat_pasien";
        private static final String db_name = "pasien.db";
        private static final int db_version = 1;

        // Perintah SQL untuk membuat tabel database baru
        private static final String db_create = "create table "
                + TABLE_NAME + "("
                + COLUMN_ID + " integer primary key autoincrement, "
                + COLUMN_NAME + " varchar(50) not null, "
                + COLUMN_ALAMAT + " varchar(50) not null);";

        public DBHelperPasien(Context context) {
            super(context, db_name, null, db_version);
            // Auto generated
        }

        //mengeksekusi perintah SQL di atas untuk membuat tabel database baru
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(db_create);
        }

        // dijalankan apabila ingin mengupgrade database
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(DBHelperPasien.class.getName(), "Upgrading database from version " + oldVersion + " to "
                    + newVersion + ", which will destroy all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);

        }

    }