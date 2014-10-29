package com.example.jakobhartman.healthcenterdirectory;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;




public class settings extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        final DBHelper mydb = new DBHelper(this);

        Firebase.setAndroidContext(this); // initialize firebase
        Firebase ref = new Firebase("https://boiling-fire-7455.firebaseio.com/CenterNumbers");
                 ref.addListenerForSingleValueEvent(new ValueEventListener() {
                     @Override
                     public void onDataChange(DataSnapshot dataSnapshot) {
                         for(DataSnapshot data : dataSnapshot.getChildren()){
                             for (DataSnapshot contact : data.getChildren()){

                                 mydb.insertContact(data.getName(),contact.child("Name").getValue().toString(),contact.child("Number").getValue().toString());

                                 Log.i("firebase data", data.getName());
                                 Log.i("firebase data", contact.child("Name").getValue().toString());
                                 Log.i("firebase data", contact.child("Number").getValue().toString());

                             }
                         }
                         SQLiteDatabase db = mydb.getReadableDatabase();
                         String select = "SELECT * FROM department_contacts WHERE department = 'General'";
                         //Cursor results =  db.rawQuery("select * from department_contacts",null);
                         Cursor results = db.rawQuery(select,null);
                         results.moveToFirst();
                         Log.i("query results: ", results.getString(1));


                     }



                     @Override
                     public void onCancelled(FirebaseError firebaseError) {

                     }
                 });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.settings, menu);
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
