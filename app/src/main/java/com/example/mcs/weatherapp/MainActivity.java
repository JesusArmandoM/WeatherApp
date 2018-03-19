package com.example.mcs.weatherapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private EditText editText;
    private Button zipButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        zipButton =  findViewById(R.id.zip_button);


        zipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cad = editText.getText().toString();

                if (cad.length() != 5) {
                    Toast.makeText(getApplicationContext(), getString(R.string.message_error), Toast.LENGTH_SHORT).show();

                    return;
                }

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra(ResultActivity.ZIP_VALUE, Integer.valueOf(cad));
                startActivity(intent);

            }
        });



        editText = (EditText) findViewById(R.id.editTextZip);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }




//    public void toResult(View view)
//    {
//
//        String cad = editText.getText().toString();
//
//        if (cad.length() != 5)
//        {
//            Toast.makeText(this, getString(R.string.message_error), Toast.LENGTH_SHORT).show();
//
//            return;
//        }
//
//
//
//        Intent intent = new Intent (MainActivity.this,ResultActivity.class);
//        intent.putExtra(ResultActivity.ZIP_VALUE, Integer.valueOf(cad));
//        startActivity(intent);
//
//
//
//    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.weather) {



        } else if (id == R.id.logOut) {

            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
