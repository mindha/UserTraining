package com.example.mindha.usertraining.menubar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mindha.usertraining.R;
import com.example.mindha.usertraining.mMySQL.Downloader;
import com.example.mindha.usertraining.mMySQL.DownloaderUser;

/**
 * Created by Mindha on 01/05/2017.
 */

public class ListTraining extends Fragment /*implements View.OnClickListener*/{

    String urlAddress="http://ediklat.pe.hu/listview.php";

    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_list_training,container,false);

        //onClick(myView);

        String id_user = getArguments().getString("NO_ID");
        getData(id_user);
        return myView;
    }


    public void getData(String id_user){
        final ListView lv = (ListView)myView.findViewById(R.id.lv);
        Downloader d = new Downloader (getActivity(),urlAddress,lv,id_user);
        d.execute();
    }

//    private void listTraining (View view){
//        final ListView lv = (ListView)view.findViewById(R.id.lv);
//        FloatingActionButton fab = (FloatingActionButton)view.findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick (View view){
//                Downloader d = new Downloader (getActivity(),urlAddress,lv);
//                d.execute();
//            }
//        });
//    }

//    @Override
//    public void onClick(View v) {
//        String id_user = getArguments().getString("NO_ID");
//        final ListView lv = (ListView)v.findViewById(R.id.lv);
//        Downloader d = new Downloader (getActivity(),urlAddress,lv,id_user);
//        d.execute();
//
//    }
}
