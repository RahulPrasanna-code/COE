package com.example.coe_complaints;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static App app;
    private String email="prasannarahul22@gmail.com";
    private String password="rahul2002";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    public static AtomicReference<User> user;
    Toolbar toolbar;
    private ArrayList<Exam> exams;
    private RecyclerView recyclerView;

    private ExamsAdapter contactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Realm.init(this);
        String appID = "coe-0-enllr";
        app = new App(new AppConfiguration.Builder(appID).build());

        Credentials emailPasswordCredentials = Credentials.emailPassword(email, password);

        user = new AtomicReference<User>();
        app.loginAsync(emailPasswordCredentials, it -> {
                    if (it.isSuccess()) {
                        Log.v("AUTH", "Successfully authenticated using an email and password.");
                        user.set(app.currentUser());
                        Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, it.getError().toString(), Toast.LENGTH_SHORT).show();
                        Log.e("AUTH", it.getError().toString());
                    }
                });

        drawerLayout = findViewById(R.id.drawerview);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.topBar);

        exams = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.examsRecyclerView);

        for (int i = 1; i < 10; i++) {
            Exam exam = new Exam();
            exam.setFee(feeGeneration());
            exam.setExamName("Course" + i);
            exam.setLastDate("12/12/2022");
            if(i%3==0)
                exam.setRegistered(true);
            exams.add(exam);
        }


        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        
        toggle.syncState();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contactAdapter = new ExamsAdapter((androidx.recyclerview.widget.RecyclerView) recyclerView, exams, this);
        recyclerView.setAdapter(contactAdapter);

        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (exams.size() <= 20) {
                    exams.add(null);
                    contactAdapter.notifyItemInserted(exams.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            exams.remove(exams.size() - 1);
                            contactAdapter.notifyItemRemoved(exams.size());

                            //Generating more data
                            int index = exams.size();
                            int end = index + 10;
                            for (int i = index+1; i < end; i++) {
                                Exam exam = new Exam();
                                exam.setFee(feeGeneration());
                                exam.setExamName("Course" + i );
                                exam.setLastDate("11/12/2022");
                                if(i%5==0)
                                    exam.setRegistered(true);
                                exams.add(exam);
                            }
                            contactAdapter.notifyDataSetChanged();
                            contactAdapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(MainActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.navHome:
                // Add your navigational page code
                break;
            case R.id.navAnnouncements:
                // Add your navigational page code
                break;
            case R.id.navTimeTable:
                // Add your navigational page code
                break;
            case R.id.navResult:
                // Add your navigational page code
                break;
            case R.id.navComplaints:
                startActivity(new Intent(MainActivity.this, ComplaintsActivity.class));
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    private String feeGeneration() {
        return "Rs.1200" ;
    }
}