package com.example.user.exo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by USER on 2016-10-06.
 */

public class DBTest extends AppCompatActivity {

    DBHelper dbh = null;
    static TextView tvPrint = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dblayout);

        tvPrint = (TextView) findViewById(R.id.tvPrint);
        tvPrint.setText("");

        MyDBHelper mDBHelper = new MyDBHelper(this, "Today.db", null, 2);


        SQLiteDatabase db = mDBHelper.getWritableDatabase(); //write 다할수있음
        db.execSQL("INSERT INTO today VALUES(null, '점심시간', '2011/07/15', '12:00', '식사', '1')");
        db.execSQL("INSERT INTO today VALUES(null, '저녁시간', '2011/07/16', '18:00', '식사', '2')");
        db.execSQL("INSERT INTO today VALUES(null, '아침시간', '2011/07/17', '08:00', '식사', '3')");


        // Cursor cursor = db.rawQuery("SELECT _id,title, time, memo FROM today WHERE date = '2011/07/15'", null);
        Cursor cursor = db.rawQuery("SELECT * FROM today;", null);

        while (cursor.moveToNext()) {
            addPrint("print : " + cursor.getInt(0) + ", " + cursor.getString(1) + ", " + cursor.getString(2) + ", " + cursor.getString(3) + ", " + cursor.getString(4) + ", " + cursor.getString(5));
        }

        db.execSQL("DROP TABLE today;");
        db.execSQL("CREATE TABLE today(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title TEXT, " + "date TEXT , " + "time TEXT, "
                + "memo TEXT, " + "priority INTEGER);");

        mDBHelper.close();
    }

    public static void addPrint(String str) {
        tvPrint.setText(tvPrint.getText().toString() + str + "\r\n");
    }


}
