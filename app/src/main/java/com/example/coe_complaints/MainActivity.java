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
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private String email="prasannarahul22@gmail.com";
    private String password="rahul2002";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    Toolbar toolbar;
    private RealmResults<Exam> exams;
    private RecyclerView recyclerView;
    private FloatingActionButton btnCreateExam;

    private ExamsAdapter contactAdapter;
    private Realm backgroundThreadRealm;
    public static boolean dataAdded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        Realm.init(this);

        backgroundThreadRealm = Realm.getDefaultInstance();


        drawerLayout = findViewById(R.id.drawerview);
        navigationView = findViewById(R.id.navigation_view);
        toolbar = findViewById(R.id.topBar);
        btnCreateExam = findViewById(R.id.btnAddExam);

        recyclerView = (RecyclerView) findViewById(R.id.examsRecyclerView);

        addSampleData();
        dataAdded=true;

        exams = backgroundThreadRealm.where(Exam.class).findAll();

        navigationView.setNavigationItemSelectedListener(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        
        toggle.syncState();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        contactAdapter = new ExamsAdapter((androidx.recyclerview.widget.RecyclerView) recyclerView, exams, this);
        recyclerView.setAdapter(contactAdapter);
        exams.addChangeListener(new RealmChangeListener<RealmResults<Exam>>() {
            @Override
            public void onChange(RealmResults<Exam> exams) {

                contactAdapter.setExams(exams);
            }
        });


        contactAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (exams.size() <= 20) {
                    contactAdapter.notifyItemInserted(exams.size() - 1);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            contactAdapter.notifyItemRemoved(exams.size());

                            //Generating more data
                            int index = exams.size();
                            int end = index + 10;
                            contactAdapter.setLoaded();
                        }
                    }, 5000);
                } else {
                    Toast.makeText(MainActivity.this, "Loading data completed", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnCreateExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CreateExamActivity.class));
            }
        });

    }

    private void addSampleData() {

        if(!dataAdded)
        {
            for (int i = 1; i < 30; i++) {

                int finalI = i;
                backgroundThreadRealm.executeTransaction(realm->{
                    Number num = realm.where(Exam.class).max("_id");
                    int nextID;
                    if(num == null) {
                        nextID = 1;
                    } else {
                        nextID = num.intValue() + 1;
                    }
                    Exam exam = realm.createObject(Exam.class,nextID);
                    exam.setExamFee(feeGeneration());
                    exam.setEligibility("2nd year");
                    exam.setExamName("Course" + finalI);
                    exam.setExamDate("01/04/23");
                    exam.setLastDate("12/12/22");
                    exam.setLastDateWithFine("15/12/22");
                    if(finalI %3==0)
                        exam.setRegistered(true);
                    realm.insert(exam);
                });
            }
        }


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
            case R.id.navAdmin:
                startActivity(new Intent(MainActivity.this,UsersListActivity.class));
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