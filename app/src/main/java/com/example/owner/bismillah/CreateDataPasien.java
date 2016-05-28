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

public class CreateDataPasien extends Activity implements OnClickListener{

    //inisilisasi elemen-elemen pada layout
    private Button buttonSubmit;
    private EditText edNamaPasien;
    private EditText edAlamatPasien;
    //inisialisasi kontroller/Data Source
    private DBDataSourcePasien dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_datapasien);

        buttonSubmit = (Button) findViewById(R.id.buttom_submitpasien);
        buttonSubmit.setOnClickListener(this);
        edNamaPasien = (EditText) findViewById(R.id.nama_pasien);
        edAlamatPasien = (EditText) findViewById(R.id.alamat_pasien);


        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSourcePasien(this);

        //membuat sambungan baru ke database
        dataSource.open();
    }

    //KETIKA Tombol Submit Diklik
    @Override
    public void onClick(View v) {
        // Inisialisasi data barang
        String namapasien = null;
        String alamatpasien = null;
        @SuppressWarnings("unused")

        //inisialisasi barang baru (masih kosong)
                Pasien pasien = null;
        if(edNamaPasien.getText()!=null && edAlamatPasien.getText()!=null)
        {
			/* jika field nama, merk, dan harga tidak kosong
			 * maka masukkan ke dalam data barang*/
            namapasien = edNamaPasien.getText().toString();
            alamatpasien = edAlamatPasien.getText().toString();
        }

        switch(v.getId())
        {
            case R.id.buttom_submitpasien:
                // insert data barang baru
                pasien = dataSource.createPasien(namapasien, alamatpasien);

                //konfirmasi kesuksesan
                Toast.makeText(this, "masuk Pasien\n" +
                        "nama" + pasien.getNama_pasien() +
                        "alamat" + pasien.getAlamat_pasien() , Toast.LENGTH_LONG).show();
                break;
        }

    }
}