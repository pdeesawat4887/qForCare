package com.example.pacharapoldeesawat.demohospital;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pacharapoldeesawat.demohospital.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.io.IOException;

public class WalkInActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private User obj;
    private TextView queueA_num_text;
    private long countA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimenu);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(R.string.out_patient);
        toolbar.setTitleTextColor(getResources().getColor(R.color.titleBar));
        setSupportActionBar(toolbar);

        /** Get User Object when user login at first time */
        SharedPreferences mPrefs2 = getSharedPreferences("label", 0);
        SharedPreferences.Editor mPerfsEdit = mPrefs2.edit();
        Gson gson3 = new Gson();
        String json3 = mPrefs2.getString("MyObjectUser", "");
        obj = gson3.fromJson(json3, User.class);


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        queueA_num_text = findViewById(R.id.queueA_count);
        DatabaseReference root = FirebaseDatabase.getInstance().getReference();
        root.child("A").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                countA = (long) dataSnapshot.getValue();
                queueA_num_text.setText("ตอนนี้มีผู้ป่วยรับคิวแล้ว " + countA + " คิว");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Button walkIn = findViewById(R.id.walkbtn);
        walkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    InsertQueue.updateQueue("A", obj.getCitizenId(), WalkInActivity.this);
<<<<<<< HEAD
                    if (InsertQueue.getTimeB() != 0) {
                        Toast.makeText(WalkInActivity.this, "จองคิว A" + (countA + 1) + " เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
=======
                    if (InsertQueue.getTimeB() != 0){
                        Toast.makeText(WalkInActivity.this, "จองคิว A" + (countA+1) + " เรียบร้อยแล้ว", Toast.LENGTH_SHORT).show();
>>>>>>> 33b4441599b4d41c755ed9fda62008a0c42179a2
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View header = navigationView.getHeaderView(0);

        ImageView avatar = (ImageView) header.findViewById(R.id.avatar);
        TextView role = (TextView) header.findViewById(R.id.userRole);
        TextView id = (TextView) header.findViewById(R.id.id);

        avatar.setImageResource(R.drawable.ic_021_nurse);
        role.setText(R.string.nurse);
        id.setText("เลขประจำตัว: " + obj.getCitizenId());

    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.minimenu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_walk) {
            if (obj.getRole().equals("nurse")) {
                Intent it = new Intent(WalkInActivity.this, WalkInActivity.class);
                startActivity(it);
            } else {
                Toast.makeText(getApplicationContext(), "คุณต้องมีสิทธิ์เข้าถึง", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_phone) {
            Intent it = new Intent(WalkInActivity.this, InAppActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_manage) {
            if (obj.getRole().equals("nurse")) {
                Intent it = new Intent(WalkInActivity.this, CallQueue.class);
                startActivity(it);
            } else {
                Toast.makeText(getApplicationContext(), "คุณต้องมีสิทธิ์เข้าถึง", Toast.LENGTH_SHORT).show();
            }
        } else if (id == R.id.nav_setting) {
            if (obj.getRole().equals("nurse")) {
                Intent it = new Intent(WalkInActivity.this, Setting.class);
                startActivity(it);
            } else {
                Toast.makeText(getApplicationContext(), "คุณต้องมีสิทธิ์เข้าถึง", Toast.LENGTH_SHORT).show();
            }

        } else if (id == R.id.nav_logout) {
            Intent it = new Intent(WalkInActivity.this, LogoutActivity.class);
            startActivity(it);
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
