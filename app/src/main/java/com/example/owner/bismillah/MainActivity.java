package com.example.owner.bismillah;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends Activity {

    Intent i=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login_sigin(View v)
    {
        switch(v.getId())
        {
            case R.id.log_in:
                i=new Intent(this,Login.class);
                startActivityForResult(i, 500);
                break;
            case R.id.sign_in:
                i=new Intent(this,Signin.class);
                startActivityForResult(i, 500);
                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }
}
