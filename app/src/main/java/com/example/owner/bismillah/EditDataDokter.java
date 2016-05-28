package com.example.owner.bismillah;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Owner on 5/25/2016.
 */
public class EditDataDokter extends Activity implements View.OnClickListener{
    private DBDataSourceDokter dataSource;

    private long id;

    private String namadokter;
    private String alamatdokter;

    private EditText edNamaDokter;
    private EditText edAlamatDokter;

    private TextView txId;

    private Button btnSaveD;
    private Button btnCancel;

    private Dokter dokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_datadokter);

        edNamaDokter = (EditText) findViewById(R.id.editText_namaD);
        edAlamatDokter = (EditText) findViewById(R.id.editText_alamatD);
        txId = (TextView) findViewById(R.id.text_id_dokter);

        dataSource = new DBDataSourceDokter(this);
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        alamatdokter= bun.getString("alamat");
        namadokter=bun.getString("nama");

        txId.append(String.valueOf(id));

        edNamaDokter.setText(namadokter);
        edAlamatDokter.setText(alamatdokter);

        btnSaveD = (Button) findViewById(R.id.button_save_updateD);
        btnSaveD.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button_save_updateD:
                dokter = new Dokter();
                dokter.setNama_dokter(edNamaDokter.getText().toString());
                dokter.setAlamat_dokter(edAlamatDokter.getText().toString());

                dokter.setId(id);
                dataSource.updateDokter(dokter);
                Intent i3 = new Intent(this, ViewDataDokter.class);
                startActivity(i3);
                EditDataDokter.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update:
                finish();
                dataSource.close();
                break;
        }
    }
}
