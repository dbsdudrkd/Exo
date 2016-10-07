package com.example.user.exo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.user.exo.DBTest.addPrint;

/**
 * Created by USER on 2016-10-06.
 */

public class MyDBHelper extends SQLiteOpenHelper {

    private String tableName = null;
    private String createColumsQuery = "";
    private String[] columns = null;
    private int colLen = -1;

    public MyDBHelper(Context context, String dbFileName, String tableName, String createColumsQuery) {
        super(context, dbFileName, null, 1);
        this.tableName = tableName;
        this.createColumsQuery = "id INTEGER PRIMARY KEY AUTOINCREMENT, " + createColumsQuery;

        columns = this.createColumsQuery.split(",");
        colLen = columns.length;

        for(int i = 0; i < colLen; i++)
            columns[i] = columns[i].trim().split("\\s")[0];

        addPrint("colLen : " + colLen);
        addPrint(Arrays.toString(columns));
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + tableName
                + "(" + createColumsQuery + ");";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE");
        onCreate(db);
    }

    public String[] selectAll() {
        ArrayList<String> resultAr = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + ";", null);

        addPrint(" - cursor start");
        while (cursor.moveToNext()) {
            String line = "";
            for (int i = 0; i < colLen; i++)
                line += cursor.getString(i) + ",";

            line = line.substring(0, line.length() - 1);
            resultAr.add(line);
        }
        addPrint(" - cursor end");
        cursor.close();
        db.close();
        return resultAr.toArray(new String[resultAr.size()]);
    }

}