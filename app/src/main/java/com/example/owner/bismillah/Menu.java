package com.example.owner.bismillah;

/**
 * Created by Owner on 5/24/2016.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Menu extends Activity implements OnClickListener {

    private Button bTambah;
    private Button bLihat;

    private Button bTambahPasien;
    private Button bLihatPasien;

    private Button bTambahDokter;
    private Button bLihatDokter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);

        bTambah = (Button) findViewById(R.id.button_tambah);
        bTambah.setOnClickListener(this);
        bLihat = (Button) findViewById(R.id.button_view);
        bLihat.setOnClickListener(this);

        bTambahPasien = (Button) findViewById(R.id.button_tambahpasien);
        bTambahPasien.setOnClickListener(this);
        bLihatPasien = (Button) findViewById(R.id.button_viewp);
        bLihatPasien.setOnClickListener(this);

        bTambahDokter = (Button) findViewById(R.id.button_tambahdokter);
        bTambahDokter.setOnClickListener(this);
        bLihatDokter = (Button) findViewById(R.id.button_viewd);
        bLihatDokter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.button_tambah:
                Intent i = new Intent(this, CreateData.class);
                startActivity(i);
                break;
            case R.id.button_view :
                Intent ia = new Intent(this, ViewData.class);
                startActivity(ia);
                break;

            case R.id.button_tambahpasien:
                Intent i2 = new Intent(this, CreateDataPasien.class);
                startActivity(i2);
                break;
            case R.id.button_viewp :
                Intent ib = new Intent(this, ViewDataPasien.class);
                startActivity(ib);
                break;

            case R.id.button_tambahdokter:
                Intent i3 = new Intent(this, CreateDataDokter.class);
                startActivity(i3);
                break;
            case R.id.button_viewd :
                Intent ic = new Intent(this, ViewDataDokter.class);
                startActivity(ic);
                break;

            }
        }
    }
