package com.example.owner.bismillah;

/**
 * Created by Owner on 5/24/2016.
 */

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateDataDokter extends Activity implements OnClickListener{

    //inisilisasi elemen-elemen pada layout
    private Button buttonSubmit;
    private EditText edNamaDokter;
    private EditText edAlamatDokter;
    //inisialisasi kontroller/Data Source
    private DBDataSourceDokter dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_datadokter);

        buttonSubmit = (Button) findViewById(R.id.buttom_submitdokter);
        buttonSubmit.setOnClickListener(this);
        edNamaDokter = (EditText) findViewById(R.id.nama_dokter);
        edAlamatDokter = (EditText) findViewById(R.id.alamat_dokter);


        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSourceDokter(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }

    //KETIKA Tombol Submit Diklik
    @Override
    public void onClick(View v) {
        // Inisialisasi data barang
        String namadokter = null;
        String alamatdokter = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
                Dokter dokter = null;
        if(edNamaDokter.getText()!=null && edAlamatDokter.getText()!=null)
        {
			/* jika field nama, merk, dan harga tidak kosong
			 * maka masukkan ke dalam data barang*/
            namadokter = edNamaDokter.getText().toString();
            alamatdokter = edAlamatDokter.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.buttom_submitdokter:
                // insert data barang baru
                dokter = dataSource.createDokter(namadokter, alamatdokter);

                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Dokter\n" +
                        "nama" + dokter.getNama_dokter() +
                        "alamat" + dokter.getAlamat_dokter() , Toast.LENGTH_LONG).show();
                break;
        }

    }
}