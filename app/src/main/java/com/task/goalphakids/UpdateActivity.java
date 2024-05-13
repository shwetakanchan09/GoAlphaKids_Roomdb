package com.task.goalphakids;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateActivity extends AppCompatActivity {
    EditText name,email;
    Button update;
    UserDatabase userDatabase;
    UserDao userDao;
    InfoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        name = findViewById(R.id.etName);
        email = findViewById(R.id.etEmail);
        update = findViewById(R.id.btnUpdate);

        userDatabase = UserDatabase.getInstance(this);
        userDao = userDatabase.getDao();

        InfoModel infoModel =(InfoModel) getIntent().getSerializableExtra("model");

        name.setText(infoModel.getName());
        email.setText(infoModel.getEmail());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InfoModel user = new InfoModel(infoModel.getId(),name.getText().toString(),email.getText().toString());
                userDao.Update(user);
                finish();
            }
        });



    }
}