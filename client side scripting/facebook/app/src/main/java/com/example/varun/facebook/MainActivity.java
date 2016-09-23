package com.example.varun.facebook;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    facebookhelper myDb;
    EditText editText1,editText2;
    Button btnlogin,reg;
    TextView tv_notfn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new facebookhelper(this);
        editText1 = (EditText) findViewById(R.id.editText_username);
        editText2 = (EditText) findViewById(R.id.editText_password);
        btnlogin=(Button)findViewById(R.id.button);
        tv_notfn = (TextView) findViewById(R.id.textView);
        reg = (Button) findViewById(R.id.button2);


       reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=editText1.getText().toString();
                String password=editText2.getText().toString();

              boolean inserted=myDb.insertData(email,password);
                if (inserted==true)
                {
                    Toast.makeText(getApplicationContext(),"email and password inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=editText1.getText().toString();
                String password=editText2.getText().toString();
                String stored=myDb.getEntry(email);
                if (password.equals(stored))
                {
                    Intent i=new Intent(MainActivity.this,profile.class);
                    startActivity(i);
                }
                else {

                    Toast.makeText(getApplicationContext(),"Email or password does not match",Toast.LENGTH_SHORT).show();
                    //tv_notfn.setText("Email or password does not match");
                   // tv_notfn.setVisibility(View.VISIBLE);
                }
                }


        });

        }

    private void ShowAllData(String error, String s) {
    }

    private void ShowAllData(String s) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
