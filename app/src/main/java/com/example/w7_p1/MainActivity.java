package com.example.w7_p1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private double total;
    private ScrollView scrollView;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        dbManager = new DatabaseManager(this);
        total = 0.0;


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        return super.onCreateOptionsMenu(menu);   //get rid of default behavior.

        // Inflate the menu; this adds items to the action bar
        getMenuInflater().inflate(R.menu.menu_basic, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id) {
            case R.id.add:
                Intent insertIntent
                        = new Intent(this, InsertActivity.class);
                this.startActivity(insertIntent);
                return true;
            case R.id.update:
                Intent deleteIntent
                        = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.delete:
                Intent updateIntent
                        = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}