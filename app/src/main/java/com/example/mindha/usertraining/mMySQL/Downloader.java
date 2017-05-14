package com.example.mindha.usertraining.mMySQL;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

/**
 * Created by ASUS on 3/30/2017.
 */

public class Downloader extends AsyncTask<Void,Void,String> {

    Context c;
    String urlAddress;
    ListView lv;
    ProgressDialog pd;
    String user_id;

    public Downloader(Context c, String urlAddress, ListView lv, String user_id) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.lv = lv;
        this.user_id = user_id;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pd=new ProgressDialog(c);
        pd.setTitle("Mengambil List DIKLAT");
        pd.setMessage("List DIKLAT sedang dimuat. Mohon tunggu...");
        pd.show();

    }

    @Override
    protected String doInBackground(Void... params) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        pd.dismiss();

//        if (s==null){
//            Toast.makeText(c,"Unsuccessfull,Null eeturned",Toast.LENGTH_SHORT).show();
//        }else
//        {
            DataParser parser= new DataParser(c,lv,s,user_id);
            parser.execute();

//        }

    }

    private  String downloadData(){
        HttpURLConnection con=Connector.connect(urlAddress);
        if(con==null){
            return  null;
        }
        InputStream is=null;

        try{
            is=new BufferedInputStream(con.getInputStream());
            BufferedReader br=new BufferedReader(new InputStreamReader(is));

            String line=null;
            StringBuffer response=new StringBuffer();

            if(br!=null){
                while ((line=br.readLine()) !=null)
                {
                    response.append(line+"\n");
                }
                br.close();

            }else {
                return null;
            }
            return response.toString();

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (is != null){
                try {
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
