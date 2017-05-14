package com.example.mindha.usertraining.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mindha.usertraining.mDataObject.Diklat;
import com.example.mindha.usertraining.mLisView.Adapter;
import com.example.mindha.usertraining.mLisView.AdapterFollowing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import static com.example.asus.lv.R.id.tipe;

/**
 * Created by ASUS on 3/30/2017.
 */

public class DataParserFollowing extends AsyncTask<Void,Void,Integer> {
    Context c;
    ListView lv;
    String jsonData;

    ProgressDialog pd;
    ArrayList<Diklat> diklat = new ArrayList<>();


    public DataParserFollowing(Context c, ListView lv, String jsonData) {
        this.c = c;
        this.lv = lv;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pd= new ProgressDialog(c);
        pd.setTitle("Parse");
        pd.setMessage("Parsing...Pleaase wait");
        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params)
    {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        pd.dismiss();
        if (result==0){
            Toast.makeText(c, "Tidak ada DIKLAT yang diikuti atau DC", Toast.LENGTH_SHORT).show();
        }else {

            AdapterFollowing adapter = new AdapterFollowing(c,diklat);
            lv.setAdapter(adapter);
        }
    }

    private int parseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo=null;

            diklat.clear();
            Diklat s=null;

            for (int i=0;i<ja.length();i++){

                jo=ja.getJSONObject(i);

                String id = jo.getString("id");
                String nama = jo.getString("nama");
                String tipe = jo.getString("tipe");
                String kategori = jo.getString("kategori");
                String place=jo.getString("place");
                String start_date =jo.getString("start_date");
                String end_date = jo.getString("end_date");
                String time = jo.getString("time");
                String organizer = jo.getString("organizer");
                String description = jo.getString("deskripsi");
                String jumlah_peserta = jo.getString("jumlah_peserta");

                s=new Diklat();
                s.setId(id);
                s.setNama(nama);
                s.setPlace(place);
                s.setStart_date(start_date);
                s.setKategori(kategori);
                s.setEnd_date(end_date);
                s.setTime(time);
                s.setOrganizer(organizer);
                s.setDeskripsi(description);
                s.setTipe(tipe);
                s.setJumlah_peserta(jumlah_peserta);

                diklat.add(s);

            }
            return 1;



        }catch (JSONException e){
            e.printStackTrace();

        }return 0;
    }
}
