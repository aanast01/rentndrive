package com.example.rentndrive;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rentndrive.data.LoginDataSource;
import com.example.rentndrive.ui.login.LoginActivity;

import java.sql.SQLException;

public class CongratsActivity extends AppCompatActivity {

    TextView ownersName, ownersPhone, ownersEmail;
    ImageView ownersPic;
    CheckBox whatsapp;
    Button back, logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrats);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final Activity act = this;

        int selectedCar = ResultsActivity.getChoosenResult();

        ownersName = (TextView) findViewById(R.id.ownerNameCongrats);
        ownersPhone = (TextView) findViewById(R.id.ownerPhoneCongrats);
        ownersEmail = (TextView) findViewById(R.id.ownerEmail);
        ownersPic = (ImageView) findViewById(R.id.ownerPic);
        whatsapp = (CheckBox) findViewById(R.id.whatsappCheck);


        ownersPic.setImageBitmap(SearchActivity.getOwnerPic()[selectedCar]);
        ownersName.setText(SearchActivity.getOwnerName()[selectedCar]);
        ownersPhone.setText(SearchActivity.getOwnerPhone()[selectedCar]+"");
        ownersEmail.setText(SearchActivity.getOwnerEmail()[selectedCar]);
        whatsapp.setChecked(SearchActivity.getWhatsapp()[selectedCar]);

        back = (Button) findViewById(R.id.searchBtn);
        logout = (Button) findViewById(R.id.logoutBtn);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    LoginDataSource.logout();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(act, LoginActivity.class);
                startActivity(intent);
                act.finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(act, SearchActivity.class);
                startActivity(intent);
                act.finish();
            }
        });

    }

}
