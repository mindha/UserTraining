package com.example.mindha.usertraining.mLisView;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mindha.usertraining.R;
import com.example.mindha.usertraining.mDataObject.Diklat;
import com.example.mindha.usertraining.mDetails.DiklatDetail;
import com.example.mindha.usertraining.mDetails.DiklatDetailFollowing;

import java.util.ArrayList;

/**
 * Created by ASUS on 3/30/2017.
 */

public class AdapterFollowing extends BaseAdapter {

    Context c;
    ArrayList<Diklat> diklats;
    LayoutInflater inflater;

    public AdapterFollowing(Context c, ArrayList<Diklat> diklats) {
        this.c = c;
        this.diklats = diklats;
        inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return diklats.size();
    }

    @Override
    public Object getItem(int position) {
        return diklats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Long.parseLong(diklats.get(position).getId());
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView==null){
            convertView=inflater.inflate(R.layout.model_following,parent,false);
        }
        TextView nama=(TextView) convertView.findViewById(R.id.nama);
        TextView tipe=(TextView) convertView.findViewById(R.id.tipe);
        TextView kategoti=(TextView) convertView.findViewById(R.id.kategori);

        nama.setText(diklats.get(position).getNama());
        tipe.setText(diklats.get(position).getTipe());
        kategoti.setText(diklats.get(position).getKategori());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(c,diklats.get(position).getId(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), DiklatDetailFollowing.class);
                //i.putExtra("USER_ID", id_user);
                i.putExtra("DIKLAT_ID", diklats.get(position).getId());
                i.putExtra("TIPE", diklats.get(position).getTipe());
                i.putExtra("KATEGORI", diklats.get(position).getKategori());
                i.putExtra("NAMA", diklats.get(position).getNama());
                i.putExtra("START_DATE", diklats.get(position).getStart_date());
                i.putExtra("END_DATE", diklats.get(position).getEnd_date());
                i.putExtra("TIME", diklats.get(position).getTime());
                i.putExtra("PLACE", diklats.get(position).getPlace());
                i.putExtra("ORGANIZER", diklats.get(position).getOrganizer());
                i.putExtra("JUMLAH_PESERTA", diklats.get(position).getJumlah_peserta());
                i.putExtra("DESKRIPSI", diklats.get(position).getDeskripsi());
                c.startActivity(i);
            }
        });
        return convertView;
    }
}
