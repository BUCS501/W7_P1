package com.example.w7_p1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ScrollView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    private double total;
    private ScrollView scrollView;
    private AutoCompleteTextView searchBar;
    private int buttonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DatabaseManager(this);

        ArrayList<Friend> friends = dbManager.selectAll();
        ArrayList<String> friendsEmails = new ArrayList<>();
        for (Friend friend : friends) {
            friendsEmails.add(friend.getEmail());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friendsEmails);

        searchBar = (AutoCompleteTextView) findViewById(R.id.searchBar);
        searchBar.setAdapter(adapter);
        searchBar.setThreshold(1);




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
            case R.id.delete:
                Intent deleteIntent
                        = new Intent(this, DeleteActivity.class);
                this.startActivity(deleteIntent);
                return true;
            case R.id.update:
                Intent updateIntent
                        = new Intent(this, UpdateActivity.class);
                this.startActivity(updateIntent);
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }

    }
}