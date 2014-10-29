package com.example.jakobhartman.healthcenterdirectory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "MyDBName.db";
    public static final String TABLE_NAME = "department_contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_DEP = "department";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_NUMBER = "number";

    public DBHelper(Context context){
        super(context,DATABASE_NAME,null,1);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE department_contacts " +
                        "(id integer primary key," +
                        "department text," +
                        "name text," +
                        "number text)"
        );
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS department_contacts");
        onCreate(db);
    }

    public boolean insertContact  (String department, String name, String phone)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("department", department);
        contentValues.put("name", name);
        contentValues.put("number", phone);

        db.insert("department_contacts", null, contentValues);
        return true;
    }

    public Cursor getData(String dep){
        String department = dep;
        SQLiteDatabase db = this.getReadableDatabase();
        String select = "SELECT * FROM "+TABLE_NAME+" WHERE "+COLUMN_DEP+" = '"+department+"'";
        //Cursor results = db.query(TABLE_NAME, null, COLUMN_DEP+"="+dep, null,null,null,null);
        Cursor results =  db.rawQuery(select,null);
        //Cursor results =  db.rawQuery("select * from department_contacts",null);
        return results;
    }
}
