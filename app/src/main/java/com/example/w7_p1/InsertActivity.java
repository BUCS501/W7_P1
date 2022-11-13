package com.example.w7_p1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertActivity extends AppCompatActivity {
    private DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        setContentView( R.layout.activity_insert );
    }

    public void insert( View v ) {
        // Retrieve name and price
        EditText firstName = (EditText) findViewById( R.id.firstnameEditText );
        EditText lastName = (EditText) findViewById( R.id.lastnameEditText );
        EditText email = (EditText) findViewById( R.id.emailEditText );

        String firstNameStr = firstName.getText().toString();
        String lastNameStr = lastName.getText().toString();
        String emailStr = email.getText().toString();



        // insert new candy in database
        try {

            Friend friend = new Friend( 0, firstNameStr, lastNameStr, emailStr );
            dbManager.insert( friend );
            Toast.makeText( this, "Friend added", Toast.LENGTH_SHORT ).show( );
        } catch( NumberFormatException nfe ) {
            Toast.makeText( this, "Error", Toast.LENGTH_LONG ).show( );
        }

        // clear data
        firstName.setText( "" );
        lastName.setText( "" );
        email.setText( "" );
    }

    public void goBack( View v ) {
        this.finish( );
    }
}
