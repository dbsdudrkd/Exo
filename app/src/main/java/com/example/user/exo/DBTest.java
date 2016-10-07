package com.example.user.exo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by USER on 2016-10-06.
 */

public class DBTest extends AppCompatActivity {

    DBHelper dbh = null;
    static TextView tvPrint = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblayout);

        tvPrint = (TextView) findViewById(R.id.tvPrint);
        tvPrint.setText("");

        String dbName = "myDB.db";
        String tableName = "myTable";
        String columnQuery = "myStr TEXT";

        MyDBHelper mDBHelper = new MyDBHelper(this, dbName, tableName, columnQuery);

        // SQLiteDatabase db = mDBHelper.getWritableDatabase(); // write 다할수있음
        // db.execSQL("INSERT INTO " + tableName + " VALUES(null, '점심시간')");

        String result[] = mDBHelper.selectAll();
        addPrint(" - - printStart");
        addPrint(" - - resultLen : " + result.length);
        for(String str : result) {
            addPrint(str);
        }
        addPrint(" - - printEnd");

        mDBHelper.close();

    }

    public static void addPrint(String str) {
        tvPrint.setText(tvPrint.getText().toString() + str + "\r\n");
    }


}
