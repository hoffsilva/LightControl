package com.example.hoffsilva.lightcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_status) {
            callStatusActivity();
            return true;
        }

        if (id == R.id.action_error_log) {
            callLogActivity();
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void callGardenActivity(View view) {
        Intent intent = new Intent(MainActivity.this, GardenActivity.class);
        startActivity(intent);
    }

    public void callBedRoomActivity(View view) {
        Intent intent = new Intent(MainActivity.this, BedRoomActivity.class);
        startActivity(intent);
    }

    public void callRoomActivity(View view) {
        Intent intent = new Intent(MainActivity.this, RoomActivity.class);
        startActivity(intent);
    }

    public void callStatusActivity() {
        Intent intent = new Intent(MainActivity.this, StatusActivity.class);
        startActivity(intent);
    }

    public void callLogActivity() {
        Intent intent = new Intent(MainActivity.this, LogActivity.class);
        startActivity(intent);
    }

}
