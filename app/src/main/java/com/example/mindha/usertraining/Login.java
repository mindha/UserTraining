package com.example.mindha.usertraining;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mindha.usertraining.mDataObject.Diklat;
import com.example.mindha.usertraining.mDataObject.User;
import com.example.mindha.usertraining.mMySQL.Downloader;
import com.example.mindha.usertraining.mMySQL.DownloaderUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    private EditText semail, spassword;
    private Context context;
    private Button btnLogin;
    private ProgressDialog pDialog;
    User s;
    String jsonData;

    ArrayList<User> user = new ArrayList<>();

//    public Login(User user){
//        this.s = user;
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = Login.this;

        //Initializing views
        pDialog = new ProgressDialog(context);
        semail = (EditText) findViewById(R.id.et_email);
        spassword = (EditText) findViewById(R.id.et_password);

        btnLogin = (Button) findViewById(R.id.btn_login);

        //Adding click listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, memail, Toast.LENGTH_LONG).show();
                login();
            }
        });
        //editTextEmail.setText("admin@agusharyanto.net");
        //editTextPassword.setText("abcd1234");
    }

    public void login() {
        //Getting values from edit texts
        final String email = semail.getText().toString().trim();
        final String password = spassword.getText().toString().trim();
        pDialog.setMessage("Login Process...");
        showDialog();
        //Creating a string request
        StringRequest stringRequest = new StringRequest(Request.Method.POST, DataLoginRegister.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //If we are getting success from server
                        if (response.contains(DataLoginRegister.LOGIN_SUCCESS)) {
                            final String memail = semail.getText().toString();
                            final String urlAddress = "http://ediklat.pe.hu/getUser.php?email="+memail+"";
                            Toast.makeText(context, "Berhasil Login", Toast.LENGTH_LONG).show();

                            DownloaderUser d = new DownloaderUser (Login.this ,urlAddress);

                            d.execute();
                            hideDialog();
                            //home();

                        } else {
                            hideDialog();
                            //Displaying an error message on toast
                            Toast.makeText(context, "Invalid email or password", Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        hideDialog();
                        Toast.makeText(context, "The server unreachable", Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                //Adding parameters to request
                params.put(DataLoginRegister.KEY_EMAIL, email);
                params.put(DataLoginRegister.KEY_PASSWORD, password);

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        Volley.newRequestQueue(this).add(stringRequest);

    }

//    private void home() {
//        Intent intent = new Intent(Login.this, MainActivity.class);
//        String name = semail.getText().toString();
//        intent.putExtra("account",name);
//
////        User a = new User();
////        String b = a.getId_user();
////
////        intent.putExtra("ID_USER",b);
//        startActivity(intent);
//
//    }
//
//    private void regis() {
//        Intent intent = new Intent(context, Registrasi.class);
//        startActivity(intent);
//        finish();
//    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }



}
