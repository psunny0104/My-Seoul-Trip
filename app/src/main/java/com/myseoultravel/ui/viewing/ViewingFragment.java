package com.myseoultravel.ui.viewing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.myseoultravel.R;

public class ViewingFragment extends Fragment {

    private ViewingViewModel viewingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewingViewModel =
                ViewModelProviders.of(this).get(ViewingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_viewing, container, false);
        final TextView textView = root.findViewById(R.id.text_viewing);
        viewingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
