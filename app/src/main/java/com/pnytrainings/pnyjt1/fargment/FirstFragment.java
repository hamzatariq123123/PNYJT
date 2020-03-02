package com.pnytrainings.pnyjt1.fargment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.pnytrainings.pnyjt1.R;

public class FirstFragment extends Fragment {

    TextView nextFagTv;

    String name ="" ,age ="";

    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_first, container, false);

        Bundle bundle = getArguments();

        if(bundle != null){
            if( bundle.containsKey("name")){
                name = bundle.getString("name")  ;
            }

            if( bundle.containsKey("age")){
                age = bundle.getString("age")  ;
            }
        }
        nextFagTv = v.findViewById(R.id.nextFagTv);

        nextFagTv.setText("name is "+name+" and age is "+age);

        nextFagTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new SecondFragment())
                        .addToBackStack("SecondFragment").commit();
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
}
