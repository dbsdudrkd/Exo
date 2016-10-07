package com.example.user.exo;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
        Log.i(" info", "log i2");
        Log.d(" debug", "log d2");
        Log.v(" verbose", "log v2");
        Log.w(" warring", "log w2");
        Log.e(" error", "log e2");

        tvPrint = (TextView) findViewById(R.id.tvPrint);
        tvPrint.setText("");

        String dbName = "myDB.db";
        String tableName = "myTable";
        String columnQuery = "myStr TEXT";

        MyDBHelper dbHelper = new MyDBHelper(this, dbName, tableName, columnQuery);

        // dbHelper.insert("점심이냐");
        // dbHelper.update("myStr", "ㅇㅇ", "id", "2");
        dbHelper.delete("id", "2");

        String result[] = dbHelper.selectAll();
        addPrint(" - - printStart");
        addPrint(" - - resultLen : " + result.length);
        for(String str : result) {
            addPrint(str);
        }

        addPrint(" - - printEnd");

        dbHelper.close();

    }

    public static void addPrint(String str) {
        tvPrint.setText(tvPrint.getText().toString() + str + "\r\n");
    }


}
