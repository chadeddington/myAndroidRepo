package com.example.jakobhartman.healthcenterdirectory;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.activeandroid.query.Select;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import localDatabase.DepartmentContact;


public class centerDetails extends Activity {
    ArrayList<String> contactNames;
    ArrayList<String> phoneNumbers;
    ListView list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_center_details);
        list = (ListView) findViewById(R.id.listView);
        Intent intent = getIntent(); //Gets the extra data passed in the intent
        contactNames = new ArrayList();
        phoneNumbers = new ArrayList();


        String text = intent.getStringExtra("Contacts"); //Gets the string value extra labeled "Contacts"


        /**********************************************************************************
         * Local Database Fill page with  active android
         **********************************************************************************/

        DBHelper mydb = new DBHelper(this);

        Cursor contacts = mydb.getData(text); //search the database where department == text

        contactNames.add("Name");
        phoneNumbers.add("Number");

        contacts.moveToFirst();
        while(contacts.moveToNext()){
            // SQLiteDatabase db = mydb.getReadableDatabase();
             //String select = "SELECT * FROM department_contacts WHERE department = 'General'";
             //Cursor results =  db.rawQuery("select * from department_contacts",null);
             //Cursor results = db.rawQuery(select,null);
             //results.moveToFirst();
             //Log.i("query results: ", results.getString(1));
            contactNames.add(contacts.getString(contacts.getColumnIndex(DBHelper.COLUMN_NAME)));
            phoneNumbers.add(contacts.getString(contacts.getColumnIndex(DBHelper.COLUMN_NUMBER)));
        }
        customListViewAdapter customList = new customListViewAdapter(this,contactNames,phoneNumbers);

        list.setAdapter(customList);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.center_details, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
