package com.example.rentndrive;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentndrive.ui.login.LoginActivity;

import java.util.Calendar;
import java.util.Date;

public class SearchActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Spinner countrySpinner;
    private Spinner citySpinner;
    private DatePicker dateFromPicker;
    private DatePicker dateToPicker;
    private EditText noOfPeople;
    private Spinner transmissionSpinner;
    private Spinner fuelSpinner;
    private Spinner modelSpinner;
    private Button searchBtn;
    private ProgressBar progress;

    private String country;
    private String city;
    private String fromDay;
    private String fromMonth;
    private String fromYear;
    private String toDay;
    private String toMonth;
    private String toYear;
    private String numOfPeople;
    private String transmissionType;
    private String fuelType;
    private String model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        View headerView = navigationView.getHeaderView(0);
        TextView name = (TextView) headerView.findViewById(R.id.nameTxt);
        TextView email = (TextView) headerView.findViewById(R.id.emailTxt);

        name.setText(LoginActivity.logedUser.getDisplayName());
        email.setText(LoginActivity.logedUser.getUserId());

        countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);

        dateFromPicker = (DatePicker) findViewById(R.id.dateFrom);
        dateToPicker = (DatePicker) findViewById(R.id.dateTo);
        dateFromPicker.setMinDate(new Date().getTime());
        dateToPicker.setMinDate(new Date().getTime());

        noOfPeople = (EditText) findViewById(R.id.peopleEdTxt);

        noOfPeople.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                noOfPeople.setError(null);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String no = noOfPeople.getText().toString();
                if(no.isEmpty()){
                    return;
                }
                int num = Integer.parseInt(no);
                if (num <= 0){
                    noOfPeople.setError("Capacity cannot be less than 1", getResources().getDrawable(android.R.drawable.stat_sys_warning));
                }
                if (num >= 8){
                    noOfPeople.setError("Capacity cannot be more than 7",getResources().getDrawable(android.R.drawable.stat_sys_warning));
                }

            }
        });
        transmissionSpinner = (Spinner) findViewById(R.id.transmittionSpinner);
        fuelSpinner = (Spinner) findViewById(R.id.fuelSpinner);
        modelSpinner = (Spinner) findViewById(R.id.modelSpinner);

        String[] modelsList = new String[]{"a", "b", "c"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, modelsList);

        modelSpinner.setAdapter(adapter);

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                country = countrySpinner.getSelectedItem().toString();
                city = citySpinner.getSelectedItem().toString();
                fromDay = dateFromPicker.getDayOfMonth() +"";
                fromMonth = dateFromPicker.getMonth() +"";
                fromYear = dateFromPicker.getYear() +"";

                toDay = dateToPicker.getDayOfMonth() +"";
                toMonth = dateToPicker.getMonth() +"";
                toYear = dateToPicker.getYear() +"";

                numOfPeople = noOfPeople.getText().toString();
                transmissionType = transmissionSpinner.getSelectedItem().toString();
                fuelType = fuelSpinner.getSelectedItem().toString();
                model = modelSpinner.getSelectedItem().toString();

                progress.setVisibility(View.VISIBLE);
                searchBtn.setVisibility(View.GONE);

                String show = new String(country + ", " + city + ", " + fromDay + ", " + fromMonth + ", " +fromYear + ", " + toDay + ", " +toMonth + ", " +toYear + ", " + numOfPeople + ", " +transmissionType + ", " + fuelType + ", " + model);
                Toast.makeText(getApplicationContext(), show, Toast.LENGTH_LONG).show();

                try{
                    wait(5000);
                }catch (InterruptedException e){
                    Log.e("Wait", e.toString());
                }
            }
        });

        progress = (ProgressBar) findViewById(R.id.searchProgressBar);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_search) {
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
            this.finish();
        }else if (id == R.id.nav_share) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Rent n' Drive");
                String shareMessage= "\nLet me recommend you this awsome application\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                Log.e("Share", e.toString());
            }
        } else if (id == R.id.nav_about) {
            Toast toast =  Toast.makeText(getApplicationContext(), "This app was developed during the Hack{Cyprus} by the Rn'D team", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER_HORIZONTAL,0,0);
            toast.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
