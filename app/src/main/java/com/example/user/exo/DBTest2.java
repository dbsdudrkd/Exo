package com.example.user.exo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

/**
 * Created by USER on 2016-10-07.
 */

public class DBTest2 extends AppCompatActivity{

    MyDBHelper dbHelper = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dbdiary);
        Log.v("v", " - - onCreate");

        final NumberPicker np1 = (NumberPicker) findViewById(R.id.numberPicker1);
        final NumberPicker np2 = (NumberPicker) findViewById(R.id.numberPicker2);
        final NumberPicker np3 = (NumberPicker) findViewById(R.id.numberPicker3);

        final EditText diaryContent = (EditText) findViewById(R.id.diaryContent);

        np1.setMinValue(2000);
        np1.setMaxValue(2016);

        np2.setMinValue(1);
        np2.setMaxValue(12);

        np3.setMinValue(1);
        np3.setMaxValue(31);

        String dbName = "myDB.db";
        String tableName = "myTable";
        String columnQuery = "myDate TEXT, myStr TEXT";

        dbHelper = new MyDBHelper(DBTest2.this, dbName, tableName, columnQuery);

        Button btn = (Button) findViewById(R.id.diaryInputBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DBTest2.this, "onClick", Toast.LENGTH_SHORT).show();
                String dataInsert = np1.getValue() + "-" + np2.getValue() + "-" + np3.getValue();

                String contentInsert = diaryContent.getText().toString();

                dbHelper.insert(dataInsert, contentInsert);
                // dbHelper.update("myStr", "bbc", "id", "2");
                // dbHelper.delete("id", "2");

                String resultList[] = dbHelper.selectAll();
                String print = "";

                Log.v("v", " - - printStart");
                Log.v("v", " - - resultLen : " + resultList.length);

                for(String result : resultList) print += result + "\n";

                Log.v("v", print);
                Log.v("v", " - - printEnd");

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbHelper.close();
    }
}
