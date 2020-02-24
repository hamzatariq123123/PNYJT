package com.pnytrainings.pnyjt1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class FragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        launchFragment();
    }

    public void launchFragment(){
        //String input = inputEdt.getText().toString();
        String data= "hamza";
        String age= "25";

        Bundle bundle = new Bundle();
        bundle.putString("name",data);
        bundle.putString("age",age);


        FirstFragment firstFragment = new FirstFragment();
        firstFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.container,firstFragment).commit();

    }

    public String getName(){
        return  "pny trainngs";
    }
}
