package com.example.owner.bismillah;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EditDataPasien extends Activity implements View.OnClickListener {
    private DBDataSourcePasien dataSource;

    private long id;

    private String namapasien;
    private String alamatpasien;

    private EditText edNamaPasien;
    private EditText edAlamatPasien;

    private TextView txId;

    private Button btnSaveP;
    private Button btnCancel;

    private Pasien pasien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_datapasien);

        edNamaPasien = (EditText) findViewById(R.id.editText_namaP);
        edAlamatPasien = (EditText) findViewById(R.id.editText_alamatP);
        txId = (TextView) findViewById(R.id.text_id_pasien);

        dataSource = new DBDataSourcePasien(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        alamatpasien= bun.getString("alamat");
        namapasien=bun.getString("nama");

        txId.append(String.valueOf(id));

        edNamaPasien.setText(namapasien);
        edAlamatPasien.setText(alamatpasien);

        btnSaveP = (Button) findViewById(R.id.button_save_updateP);
        btnSaveP.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_updateP:
                pasien = new Pasien();
                pasien.setNama_pasien(edNamaPasien.getText().toString());
                pasien.setAlamat_pasien(edAlamatPasien.getText().toString());

                pasien.setId(id);
                dataSource.updatePasien(pasien);
                Intent i2 = new Intent(this, ViewDataPasien.class);
                startActivity(i2);
                EditDataPasien.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update:
                finish();
                dataSource.close();
                break;
        }
    }
}
