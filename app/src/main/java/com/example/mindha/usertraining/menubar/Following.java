package com.example.mindha.usertraining.menubar;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mindha.usertraining.R;
import com.example.mindha.usertraining.mMySQL.DownloaderFollowing;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mindha on 01/05/2017.
 */

public class Following extends Fragment {
    View myView;
    RequestQueue requestQueue;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_following,container,false);
        final String id_user = getArguments().getString("NO_ID");
        String urlAddress="http://ediklat.pe.hu/listFollowing.php?id_user="+id_user+"";

//  TODO Ini min setingan POST ke API. Tulis kode nya dibawah

        requestQueue = Volley.newRequestQueue(getActivity());
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
                parameters.put("id",id_user);

                return parameters;
            }
        };
        requestQueue.add(request);

        final ListView lv = (ListView) myView.findViewById(R.id.following);

        DownloaderFollowing d = new DownloaderFollowing (getActivity(),urlAddress,lv);
        d.execute();

        return myView;
    }
}
