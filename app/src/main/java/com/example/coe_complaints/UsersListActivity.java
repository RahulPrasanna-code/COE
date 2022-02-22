package com.example.coe_complaints;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class UsersListActivity extends AppCompatActivity {

    private RecyclerView recUsersView;
    private ImageView btnPrev;

    private ArrayList<User> users = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        addDummyData();

        recUsersView = findViewById(R.id.usersRecView);
        btnPrev = findViewById(R.id.btnPrevious);

        UsersRecAdapter adapter = new UsersRecAdapter(this);

        adapter.setUsers(users);

        recUsersView.setAdapter(adapter);
        recUsersView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
    }

    private void addDummyData() {

        users.add(new User("User Name 1","username1@gmail.com"));
        users.add(new User("User Name 2","username2@gmail.com"));
        users.add(new User("User Name 3","username3@gmail.com"));
        users.add(new User("User Name 4","username4@gmail.com"));
        users.add(new User("User Name 5","username5@gmail.com"));
        users.add(new User("User Name 6","username6@gmail.com"));

    }
}