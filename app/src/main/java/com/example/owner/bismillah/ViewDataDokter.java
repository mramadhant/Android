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

/**
 * Created by Owner on 5/25/2016.
 */
public class ViewDataDokter extends ListActivity implements AdapterView.OnItemLongClickListener{
    private DBDataSourceDokter dataSource;

    private ArrayList<Dokter> values;
    private Button editButtonD;
    private Button delButtonD;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdatadokter);

        dataSource = new DBDataSourceDokter(this);

        try {
            dataSource.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        values= dataSource.getAllDokter();

        ArrayAdapter<Dokter> adapter = new ArrayAdapter<Dokter>(this,
                android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = (ListView) findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);

    }

    public boolean onItemLongClick (final AdapterView<?> adapter, View v, int pos, final long id) {
        final Dialog dialog= new Dialog(this);
        dialog.setContentView(R.layout.dialog_viewdokter);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Dokter d = (Dokter) getListAdapter().getItem(pos);
        editButtonD = (Button) dialog.findViewById(R.id.button_edit_dataD);
        delButtonD = (Button) dialog.findViewById(R.id.button_delete_dataD);

        editButtonD.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switchToEdit(d.getId());
                        dialog.dismiss();
                    }
                }
        );
        delButtonD.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dataSource.deleteDokter(d.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }

    public void switchToEdit (long id) {
        Dokter d= dataSource.getDokter(id);
        Intent i3 = new Intent(this, EditDataDokter.class);
        Bundle bun = new Bundle();
        bun.putLong("id", d.getId());
        bun.putString("nama", d.getNama_dokter());
        bun.putString("alamat", d.getAlamat_dokter());
        i3.putExtras(bun);
        finale();
        startActivity(i3);
    }

    public void finale() {
        ViewDataDokter.this.finish();
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
