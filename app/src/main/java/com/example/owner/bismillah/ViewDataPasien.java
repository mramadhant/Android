package com.example.owner.bismillah;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewDataPasien extends ListActivity implements AdapterView.OnItemLongClickListener {
    private DBDataSourcePasien dataSource;

    private ArrayList<Pasien> values;
    private Button editButtonP;
    private Button delButtonP;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdatapasien);

        dataSource = new DBDataSourcePasien(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values= dataSource.getAllPasien();

        ArrayAdapter<Pasien> adapter = new ArrayAdapter<Pasien>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);

    }

    public boolean onItemLongClick (final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_viewpasien);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Pasien p = (Pasien) getListAdapter().getItem(pos);
        editButtonP = (Button) dialog.findViewById(R.id.button_edit_dataP);
        delButtonP = (Button) dialog.findViewById(R.id.button_delete_dataP);

        editButtonP.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(p.getId());
                        dialog.dismiss();
                    }
                }
        );
        delButtonP.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataSource.deletePasien(p.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }

    public void switchToEdit (long id) {
        Pasien p= dataSource.getPasien(id);
        Intent i2 = new Intent(this, EditDataPasien.class);
        Bundle bun = new Bundle();
        bun.putLong("id", p.getId());
        bun.putString("nama", p.getNama_pasien());
        bun.putString("alamat", p.getAlamat_pasien());
        i2.putExtras(bun);
        finale();
        startActivity(i2);
    }

    public void finale() {
        ViewDataPasien.this.finish();
        dataSource.close();
    }

    @Override
    protected void onResume() {
        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onResume();
    }
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
