package com.task.goalphakids;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterListener {
    EditText userName, email;
    Button insert;
    FloatingActionButton floatingActionButton;
    LinearLayout llAddInfo;
    RecyclerView rv;
    UserDatabase userDatabase;
    UserDao userDao;
    InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userName = findViewById(R.id.userName);
        email = findViewById(R.id.emailId);
        insert = findViewById(R.id.btnInsert);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        rv = findViewById(R.id.rvDB);
        llAddInfo = findViewById(R.id.llAddInfo);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llAddInfo.setVisibility(View.VISIBLE);
            }
        });

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llAddInfo.setVisibility(View.GONE);
                String name = userName.getText().toString();
                String emailid = email.getText().toString();

                if (!name.equals("") && !emailid.equals("")) {
                    InfoModel pojo = new InfoModel(Math.random(), name, emailid);
                    adapter.addUser(pojo);
                    userDao.Insert(pojo);
                    userName.setText("");
                    email.setText("");
                    Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Please fill the data", Toast.LENGTH_SHORT).show();
                }
            }
        });
        adapter = new InfoAdapter(this,this);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));

    }
    private void fetchData() {
        adapter.clearData();
        List<InfoModel> userList = userDao.getAllUsers();
        for (int i = 0; i < userList.size(); i++) {
            InfoModel pojo = userList.get(i);
            adapter.addUser(pojo);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        fetchData();
    }
    @Override
    public void onUpdate(InfoModel infoModel) {
        Intent i = new Intent(this, UpdateActivity.class);
        i.putExtra("model", infoModel);
        startActivity(i);
    }

    @Override
    public void onDelete(double id, int pos) {
        userDao.delete(id);
        adapter.removeUser(pos);

    }


}