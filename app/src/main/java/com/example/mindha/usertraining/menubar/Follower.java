package com.example.mindha.usertraining.menubar;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mindha.usertraining.R;

/**
 * Created by Mindha on 01/05/2017.
 */

public class Follower extends Fragment {
    View myView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_follower,container,false);
        return myView;
    }
}
