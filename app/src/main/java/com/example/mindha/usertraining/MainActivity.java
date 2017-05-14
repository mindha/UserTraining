package com.example.mindha.usertraining;

import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mindha.usertraining.mDataObject.User;
import com.example.mindha.usertraining.mMySQL.DownloaderUser;
import com.example.mindha.usertraining.menubar.Follower;
import com.example.mindha.usertraining.menubar.Following;
import com.example.mindha.usertraining.menubar.ListTraining;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TextView nemail;
    SharedPreferences pref;
//    ArrayList<User> users;
//    User s;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String id_user = intent.getStringExtra("ID_USER");

        //Toast.makeText(this, id_user, Toast.LENGTH_LONG).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        nemail = (TextView)findViewById(R.id.username);

        ListTraining input = new ListTraining();
        FragmentTransaction fragment = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("NO_ID", id_user);
        input.setArguments(bundle);
        fragment.replace(R.id.fragment_container,input);
        fragment.commit();


// TODO ini untuk bagian ambil username [belum jadi tapi]
//        Toast.makeText(this, user, Toast.LENGTH_LONG).show();
//        nemail.setText(id_user);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        navigationView.getMenu().getItem(0).setChecked(true);
        navigationView.setCheckedItem(R.id.nav_list_training);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Keluar?");
        builder.setCancelable(true);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public  void onClick(DialogInterface dialog, int id){
                finish();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();


    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = getIntent();
        String id_user = intent.getStringExtra("ID_USER");
        int id = item.getItemId();

        if (id == R.id.nav_list_training) {
            // Handle the camera action
            ListTraining input = new ListTraining();
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("NO_ID", id_user);
            //Toast.makeText(this, id_user, Toast.LENGTH_LONG).show();
// set Fragmentclass Arguments
            input.setArguments(bundle);
            fragment.replace(R.id.fragment_container,input);
            fragment.commit();
        } else if (id == R.id.nav_following) {
            Following input = new Following();
            FragmentTransaction fragment = getFragmentManager().beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putString("NO_ID", id_user);
            input.setArguments(bundle);
            fragment.replace(R.id.fragment_container,input);
            fragment.commit();
//        } else if (id == R.id.nav_follower) {
//            Follower input = new Follower();
//            FragmentTransaction fragment = getFragmentManager().beginTransaction();
//            fragment.replace(R.id.fragment_container,input);
//            fragment.commit();
//
        } else if (id == R.id.nav_logout) {
            logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
