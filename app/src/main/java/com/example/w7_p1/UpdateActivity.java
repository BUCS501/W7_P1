package com.example.w7_p1;

import android.graphics.Point;
import android.os.Bundle;

import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class UpdateActivity extends AppCompatActivity {
    DatabaseManager dbManager;

    public void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        dbManager = new DatabaseManager( this );
        updateView( );
    }

    // Build a View dynamically with all the candies
    public void updateView( ) {
        ArrayList<Friend> friends = dbManager.selectAll( );
        if( friends.size( ) > 0 ) {
            // create ScrollView and GridLayout
            ScrollView scrollView = new ScrollView( this );
            GridLayout grid = new GridLayout( this );
            grid.setRowCount( friends.size( ) );
            grid.setColumnCount( 5 );

            // create arrays of components
            TextView [] ids = new TextView[friends.size( )];
            EditText [][] namesAndEmail = new EditText[friends.size( )][3];
            Button [] buttons = new Button[friends.size( )];
            ButtonHandler bh = new ButtonHandler( );

            // retrieve width of screen
            Point size = new Point( );
            getWindowManager( ).getDefaultDisplay( ).getSize( size );
            int width = size.x;

            int i = 0;

            for ( Friend friend : friends ) {
                // create the TextView for the friend's id
                ids[i] = new TextView( this );
                ids[i].setGravity( Gravity.CENTER );
                ids[i].setText( "" + friend.getId( ) );

                // create the two EditTexts for the friend's name and price
                namesAndEmail[i][0] = new EditText( this );
                namesAndEmail[i][1] = new EditText( this );
                namesAndEmail[i][2] = new EditText( this );
                namesAndEmail[i][0].setText( friend.getFirstname() );
                namesAndEmail[i][1].setText( friend.getLastname() );
                namesAndEmail[i][2].setText( friend.getEmail() );


                namesAndEmail[i][0].setId( 10 * friend.getId( ) );
                namesAndEmail[i][1].setId( 10 * friend.getId( ) + 1 );
                namesAndEmail[i][2].setId( 10 * friend.getId( ) + 2 );

                // create the button
                buttons[i] = new Button( this );
                buttons[i].setText( "Update" );
                buttons[i].setId( friend.getId( ) );

                // set up event handling
                buttons[i].setOnClickListener( bh );

                // add the elements to grid
                grid.addView( ids[i], width / 10,
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmail[i][0], ( int ) ( width * .2 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmail[i][1], ( int ) ( width * .2 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( namesAndEmail[i][2], ( int ) ( width * .25 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );
                grid.addView( buttons[i], ( int ) ( width * .25 ),
                        ViewGroup.LayoutParams.WRAP_CONTENT );

                i++;
            }
            scrollView.addView( grid );
            setContentView( scrollView );
        }
    }

    private class ButtonHandler implements View.OnClickListener {
        public void onClick( View v ) {
            // retrieve name and price of the friend
            int friendId = v.getId( );
            EditText firstnameET = ( EditText ) findViewById( 10 * friendId );
            EditText lastnameEt = ( EditText ) findViewById( 10 * friendId + 1 );
            EditText emailET = ( EditText ) findViewById( 10 * friendId + 2 );
            String firstname = firstnameET.getText( ).toString( );
            String lastname = lastnameEt.getText( ).toString( );
            String email = emailET.getText( ).toString( );

            // update friend in database
            try {
                dbManager.updateById( friendId, firstname, lastname, email );

                Toast.makeText( UpdateActivity.this, "Friend updated",
                        Toast.LENGTH_SHORT ).show( );

                // update screen
                updateView( );
            } catch( NumberFormatException nfe ) {
                Toast.makeText( UpdateActivity.this,
                        "Error", Toast.LENGTH_LONG ).show( );
            }
        }
    }
}
