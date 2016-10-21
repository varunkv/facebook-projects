package com.example.varun.facebook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String LOGIN_URL="http://services.trainees.baabtra.com/BM-41756/LoginService.php";
    public static final String KEY_USERNAME="email";
    public static final String KEY_PASSWORD="password";

    private EditText editText_usernme;
    private EditText editText_password;
    private Button button_login;
    SharedPreferences sharedPreferences;

    private String email;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText_usernme=(EditText)findViewById(R.id.editText_username);
        editText_password=(EditText)findViewById(R.id.editText_password);

        button_login=(Button)findViewById(R.id.button);
        //sharedPreferences=getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userlogin();
            }
        });

    }
    private void userlogin(){
        email=editText_usernme.getText().toString();
        password=editText_password.getText().toString();

        StringRequest stringRequest= new StringRequest(Request.Method.POST,LOGIN_URL,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("200")&&response.contains("Success"))
                {
                    System.out.println(response);
                    openprofile();
                }
                else{
                    Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                    //Toast.makeText(MainActivity.this,"Log in failed",Toast.LENGTH_LONG).show();
                }
            }
        },
        new Response.ErrorListener(){
            @Override
        public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
            }
        }
        ){
           @Override
           protected  Map<String, String>getParams()throws AuthFailureError{
                Map<String,String>map=new HashMap<String,String>();
                map.put(KEY_USERNAME,email);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
    private void openprofile(){
        Intent intent=new Intent(MainActivity.this,Main2Activity.class);
        startActivity(intent);
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
