package com.myseoultravel.ui.making;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.myseoultravel.R;
import com.myseoultravel.SelectActivity;

public class MakingFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_making, container, false);
        Button next = (Button) root.findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                changeFragment(v);
            }
        });
        return root;
    }

    public void changeFragment( View v ) {
        switch( v.getId() ) {
            default:
            case R.id.next: {
                Intent intent = new Intent(getActivity(), SelectActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
