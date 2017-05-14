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

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class DiklatDetailFollowing extends AppCompatActivity {

    TextView mNama,mTipe,mKategori,mStartDate,mTime,mPlace,mJumlah,mDeskripsi;
    Button mButton;

//    RequestQueue requestQueue;
//    String urlAddress = "http://ediklat.pe.hu/inputRequestJoin.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diklat_detail_following);

        Intent intent = getIntent();
        final String user_id = intent.getStringExtra("USER_ID");
        final String id = intent.getStringExtra("DIKLAT_ID");
        String tipe = intent.getStringExtra("TIPE");
        String kategori = intent.getStringExtra("KATEGORI");
        final String nama = intent.getStringExtra("NAMA");
        final String start_date = intent.getStringExtra("START_DATE");
        final String end_date = intent.getStringExtra("END_DATE");
        final String time = intent.getStringExtra("TIME");
        final String place = intent.getStringExtra("PLACE");
        String organizer = intent.getStringExtra("ORGANIZER");
        String jumlah_peserta = intent.getStringExtra("JUMLAH_PESERTA");
        final String deskripsi = intent.getStringExtra("DESKRIPSI");

//        Toast.makeText(this, user_id, Toast.LENGTH_LONG).show();
//        Toast.makeText(this, id, Toast.LENGTH_LONG).show();

        mTipe = (TextView) findViewById(R.id.tipe);
        mKategori = (TextView) findViewById(R.id.kategori);
        mNama = (TextView) findViewById(R.id.nama);
        mStartDate = (TextView) findViewById(R.id.tanggal);
        mPlace = (TextView) findViewById(R.id.location);
        mJumlah = (TextView) findViewById(R.id.jumlah);
        mDeskripsi = (TextView) findViewById(R.id.deskripsi);
        mButton = (Button) findViewById(R.id.btn_alert);

        mTipe.setText(tipe);
        mKategori.setText(kategori);
        mNama.setText(nama);
        mStartDate.setText(start_date);
        mPlace.setText(place);
        mJumlah.setText(jumlah_peserta);
        mDeskripsi.setText(deskripsi);

        //final Long startTime =  Long.parseLong(time);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Date date = null, enddate = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(start_date);
                    enddate = new SimpleDateFormat("yyyy-MM-dd").parse(end_date);
                    
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                long startTime=date.getTime();
                long endTime=enddate.getTime();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime",startTime);
                intent.putExtra("endTime", endTime);
                intent.putExtra("title", nama);
                intent.putExtra("description",deskripsi);
                intent.putExtra("event_location",place);
                startActivity(intent);
            }
        });
    }
}
