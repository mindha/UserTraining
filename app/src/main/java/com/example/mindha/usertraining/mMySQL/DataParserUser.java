package com.example.mindha.usertraining.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mindha.usertraining.Login;
import com.example.mindha.usertraining.MainActivity;
import com.example.mindha.usertraining.R;
import com.example.mindha.usertraining.mDataObject.User;
import com.example.mindha.usertraining.mLisView.Adapter;
import com.example.mindha.usertraining.menubar.ListTraining;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

//import static com.example.asus.lv.R.id.tipe;

/**
 * Created by ASUS on 3/30/2017.
 */

public class DataParserUser extends AsyncTask<Void,Void,Integer> {
    Context c;
//    ListView lv;
    String jsonData;

    ProgressDialog pd;
    ArrayList<User> user = new ArrayList<>();
    User s = new User();


    public DataParserUser(Context c, String jsonData) {
        this.c = c;
//        this.lv = lv;
        this.jsonData = jsonData;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

//        pd= new ProgressDialog(c);
//        pd.setTitle("Parse");
//        pd.setMessage("Parsing...Pleaase wait");
//        pd.show();
    }

    @Override
    protected Integer doInBackground(Void... params)
    {
        return this.parseData();
    }

    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

//        pd.dismiss();
        if (result==0){
//            Toast.makeText(c, "Unable to parse ", Toast.LENGTH_SHORT).show();
        }else {
            transfer(c);
//            t]
//            Adapter adapter = new Adapter(c,user);
//            lv.setAdapter(adapter);
        }
    }

    private int parseData(){
        try {
            JSONArray ja = new JSONArray(jsonData);
            JSONObject jo=null;

            user.clear();
            //User s=null;

            for (int i=0;i<ja.length();i++){

                jo=ja.getJSONObject(i);

                String id = jo.getString("id_user");
                String name=jo.getString("nama_lengkap");
                String email=jo.getString("email");

                //s=new User();
                s.setId_user(id);
                s.setNama_lengkap(name);
                s.setEmail(email);

                user.add(s);

                System.out.println("Name is:"+s.getId_user());
            }
            return 1;

        }catch (JSONException e){
            e.printStackTrace();

        }return 0;
    }

    public Context transfer(Context view){
        //super.onCreate(savedInstanceState);
        Intent intent = new Intent(view.getApplicationContext(), MainActivity.class);
        //Toast.makeText(view.getApplicationContext(), s.getId_user(), Toast.LENGTH_LONG).show();
        intent.putExtra("ID_USER",s.getId_user());
        c.startActivity(intent);
    return view;
    }
}
