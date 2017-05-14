package com.example.mindha.usertraining.mDetails;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mindha.usertraining.R;
import com.example.mindha.usertraining.mDataObject.Diklat;

import java.util.HashMap;
import java.util.Map;

public class DiklatDetail extends AppCompatActivity {

    TextView mNama,mTipe,mKategori,mStartDate,mTime,mPlace,mJumlah,mDeskripsi;
    Button mButton;

    RequestQueue requestQueue;
    String urlAddress = "http://ediklat.pe.hu/inputRequestJoin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diklat_detail);

        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("USER_ID");
        final String id = intent.getStringExtra("DIKLAT_ID");
        String tipe = intent.getStringExtra("TIPE");
        String kategori = intent.getStringExtra("KATEGORI");
        String nama = intent.getStringExtra("NAMA");
        String start_date = intent.getStringExtra("START_DATE");
        String end_date = intent.getStringExtra("END_DATE");
        String time = intent.getStringExtra("TIME");
        String place = intent.getStringExtra("PLACE");
        String organizer = intent.getStringExtra("ORGANIZER");
        String jumlah_peserta = intent.getStringExtra("JUMLAH_PESERTA");
        String deskripsi = intent.getStringExtra("DESKRIPSI");

//        Toast.makeText(this, user_id, Toast.LENGTH_LONG).show();
//        Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        mTipe = (TextView) findViewById(R.id.tipe);
        mKategori = (TextView) findViewById(R.id.kategori);
        mNama = (TextView) findViewById(R.id.nama);
        mStartDate = (TextView) findViewById(R.id.tanggal);
        mPlace = (TextView) findViewById(R.id.location);
        mJumlah = (TextView) findViewById(R.id.jumlah);
        mDeskripsi = (TextView) findViewById(R.id.deskripsi);
        mButton = (Button) findViewById(R.id.btn_join);

        mTipe.setText(tipe);
        mKategori.setText(kategori);
        mNama.setText(nama);
        mStartDate.setText(start_date);
        mPlace.setText(place);
        mJumlah.setText(jumlah_peserta);
        mDeskripsi.setText(deskripsi);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskReques asyncTaskReques = new AsyncTaskReques();
                asyncTaskReques.execute();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_diklat_menu, menu);
        return true;
    }

    private class AsyncTaskReques extends AsyncTask<String, Integer, String> {

        ProgressDialog pd;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd=new ProgressDialog(DiklatDetail.this);
            pd.setTitle("Meminta Join");
            pd.setMessage("Request Join DIKLAT sedang diproses. Mohon ditunggu...");
            pd.show();
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                public void run() {
                }
            }, 3000);

        }

        @Override
        protected String doInBackground(String... params) {
            return askRequest();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            pd.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(DiklatDetail.this);
            builder.setMessage("Anda berhasil masuk ke DIKLAT.!")
                    .setCancelable(false)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            //do things
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }

        public String askRequest(){
            requestQueue = Volley.newRequestQueue(DiklatDetail.this);
            Intent intent = getIntent();
            final String user_id = intent.getStringExtra("USER_ID");
            final String id = intent.getStringExtra("DIKLAT_ID");
            StringRequest request = new StringRequest(Request.Method.POST, urlAddress, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){

                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> parameters = new HashMap<String, String>();
                    parameters.put("id",id);
                    parameters.put("id_user", user_id);

                    return parameters;
                }
            };
            requestQueue.add(request);
            return null;
        }
    }

    public void doThis(MenuItem item){
        Toast.makeText(this, "Hello World", Toast.LENGTH_LONG).show();
    }


//    public void askRequest(){
//        requestQueue = Volley.newRequestQueue(this);
//        Intent intent = getIntent();
//        final String user_id = intent.getStringExtra("USER_ID");
//        final String id = intent.getStringExtra("DIKLAT_ID");
//        StringRequest request = new StringRequest(Request.Method.POST, urlAddress, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//            }
//        }, new Response.ErrorListener(){
//            @Override
//            public void onErrorResponse(VolleyError error){
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String,String> parameters = new HashMap<String, String>();
//                parameters.put("id",id);
//                parameters.put("id_user", user_id);
//
//                return parameters;
//            }
//        };
//        requestQueue.add(request);
//    }
}
