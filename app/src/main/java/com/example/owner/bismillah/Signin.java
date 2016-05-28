package com.example.owner.bismillah;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Owner on 5/28/2016.
 */
public class Signin extends Activity {
    Intent i=null;
    ImageView im=null;
    EditText tv1,tv2,tv3,tv4;
    boolean flag=false;
    SQLiteDatabase db=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signin);
        tv1=(EditText)findViewById(R.id.name);
        tv2=(EditText)findViewById(R.id.email_id);
        tv3=(EditText)findViewById(R.id.phone);
        tv4=(EditText)findViewById(R.id.password);
        db=openOrCreateDatabase("mydb", MODE_PRIVATE, null);
        db.execSQL("create table if not exists login(name varchar,mobile_no varchar,email_id varchar,password varchar,flag varchar)");

    }

    public void action(View v)
    {
        switch(v.getId())
        {
            case R.id.login:
                i=new Intent(this,Login.class);
                startActivityForResult(i, 500);
                finish();
                break;
            case R.id.signin:
                String name=tv1.getText().toString();
                String email_id=tv2.getText().toString();
                String mobile_no=tv3.getText().toString();
                String password=tv4.getText().toString();
                if(name==null||name==""||name.length()<3)
                {
                    show("Please Enter Correct Name.");
                }
                else if(mobile_no==null||mobile_no==""||mobile_no.length()<10)
                {
                    show("Please Enter Correct mobile number.");
                }
                else if(email_id==null||email_id==""||email_id.length()<10)
                {
                    show("Please Enter Correct Email id.");
                }
                else if(password==null||password==""||password.length()<6)
                {
                    show("Password Harus 6 Digit");
                }
                else
                {
                    db.execSQL("insert into login values('"+name+"','"+mobile_no+"','"+email_id+"','"+password+"','nothing')");
                    i=new Intent(this,Login.class);
                    startActivityForResult(i, 500);
                    db.close();
                    finish();
                }
                Toast.makeText(this, "New User Added\n" ,
                        Toast.LENGTH_LONG) .show();

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

    }

    public void show(String str)
    {
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
    }
}
