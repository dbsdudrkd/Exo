package com.example.user.exo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

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

        Log.v("v", "colLen : " + colLen);
        Log.v("v", Arrays.toString(columns));
    }

    public void delete(String whColName, String whColValue) {
        SQLiteDatabase db = getWritableDatabase();
        String deleteQuery = "DELETE FROM " + tableName;
        deleteQuery += " WHERE " + whColName + "='" + whColValue + "'";
        db.execSQL(deleteQuery);
    }

    public void update(String setColName, String setColValue, String whColName, String whColValue) {
        SQLiteDatabase db = getWritableDatabase();
        String updateQuery = "UPDATE " + tableName + " SET " + setColName + "='" + setColValue + "'";
        updateQuery += " WHERE " + whColName + "='" + whColValue + "'";
        updateQuery += ";";
        db.execSQL(updateQuery);
    }

    public void insert(String... args) {
        SQLiteDatabase db = getWritableDatabase();
        String insertQuery = "INSERT INTO " + tableName + " VALUES(";
        insertQuery += "null, ";

        for (String arg : args) insertQuery += "'" + arg + "',";
        insertQuery = insertQuery.substring(0, insertQuery.length() - 1);

        insertQuery += ");";
        db.execSQL(insertQuery);
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
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName + ";", null);

        Log.v("v", " - cursor start");
        while (cursor.moveToNext()) {
            String line = "";
            for (int i = 0; i < colLen; i++)
                line += cursor.getString(i) + ",";

            line = line.substring(0, line.length() - 1);
            resultAr.add(line);
        }
        Log.v("v", " - cursor end");
        cursor.close();
        return resultAr.toArray(new String[resultAr.size()]);
    }

}