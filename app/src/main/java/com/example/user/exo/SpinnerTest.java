package com.example.user.exo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by USER on 2016-10-05.
 */

public class SpinnerTest extends AppCompatActivity {

    int[] resIdList = new int[61];
    String[] resSubList = new String[resIdList.length];
    //
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.spinnertest);

        for (int i = 0; i < resIdList.length; i++) {
            String mDrawableName = "mov";
            if (i < 9) mDrawableName += "0";
            mDrawableName += (i + 1);

            int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
            resIdList[i] = resID;

            resSubList[i] = (i + 1) + "번째 영화";
        }

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, resSubList);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((ImageView)findViewById(R.id.ivPoster)).setImageResource(resIdList[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}
