package com.example.rentndrive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentndrive.data.LoginDataSource;
import com.example.rentndrive.data.model.LoggedInUser;
import com.example.rentndrive.ui.login.LoginActivity;

import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import static com.example.rentndrive.data.LoginDataSource.connectionMySQL;

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

    //region SETTERS AND GETTERS
    public static int getResultsCount() {
        return resultsCount;
    }

    public void setResultsCount(int resultsCount) {
        this.resultsCount = resultsCount;
    }

    public static int[] getCarID() {
        return carID;
    }

    public void setCarID(int[] carID) {
        this.carID = carID;
    }

    public static String[] getCountries() {
        return countries;
    }

    public void setCountries(String[] countries) {
        this.countries = countries;
    }

    public static String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }

    public static String[] getManufacture() {
        return manufacture;
    }

    public void setManufacture(String[] manufacture) {
        this.manufacture = manufacture;
    }

    public static String[] getColor() {
        return color;
    }

    public void setColor(String[] color) {
        this.color = color;
    }

    public static String[] getDamages() {
        return damages;
    }

    public void setDamages(String[] damages) {
        this.damages = damages;
    }

    public static int[] getCapacity() {
        return capacity;
    }

    public void setCapacity(int[] capacity) {
        this.capacity = capacity;
    }

    public static Bitmap[] getCarPic() {
        return carPic;
    }

    public void setCarPic(Bitmap[] carPic) {
        this.carPic = carPic;
    }

    public static String[] getTransmissionTypes() {
        return transmissionTypes;
    }

    public void setTransmissionTypes(String[] transmissionTypes) {
        this.transmissionTypes = transmissionTypes;
    }

    public static String[] getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(String[] fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    public static String[] getModels() {
        return models;
    }

    public void setModels(String[] models) {
        this.models = models;
    }

    public static String[] getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String[] licensePlate) {
        this.licensePlate = licensePlate;
    }

    public static int[] getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(int[] ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public static String[] getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String[] ownerName) {
        this.ownerName = ownerName;
    }

    public static Bitmap[] getOwnerPic() {
        return ownerPic;
    }

    public void setOwnerPic(Bitmap[] ownerPic) {
        this.ownerPic = ownerPic;
    }

    public String[] getModelsList() {
        return modelsList;
    }

    public void setModelsList(String[] modelsList) {
        this.modelsList = modelsList;
    }

    public static Boolean[] getWhatsapp() {
        return whatsapp;
    }

    public static int[] getClientPhone() {
        return clientPhone;
    }

    public static int[] getCostPerDay() {
        return costPerDay;
    }

    public static String getToDate() {
        return toDate;
    }

    public static String getFromDate() {
        return fromDate;
    }

    public static String[] getOwnerEmail() {
        return ownerEmail;
    }
    //endregion

    private static int[] carID;
    private static String[] countries;
    private static String[] cities;
    private static String[] manufacture;
    private static String[] color;
    private static String[] damages;
    private static int[] capacity;
    private Blob carBlob;
    private static Bitmap[] carPic;
    private static String[] transmissionTypes;
    private static String[] fuelTypes;
    private static String[] models;
    private static String[] licensePlate;
    private static int[] ownerPhone;
    private static Boolean[] whatsapp;
    private static int[] clientPhone;
    private static String[] ownerName;
    private Blob ownerBlob;
    private static Bitmap[] ownerPic;
    private static String fromDate;
    private static String toDate;
    private static int[] costPerDay;
    private static String[] ownerEmail;

    private static int resultsCount;

    private String[] modelsList;

    private Boolean flag=false;

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
        ImageView image = (ImageView) headerView.findViewById(R.id.imageView);

        image.setImageBitmap(LoginDataSource.userPic);
        name.setText(LoginActivity.logedUser.getUserId());
        email.setText(LoginActivity.logedUser.getDisplayName());

        countrySpinner = (Spinner) findViewById(R.id.countrySpinner);
        citySpinner = (Spinner) findViewById(R.id.citySpinner);

        dateFromPicker = (DatePicker) findViewById(R.id.dateFrom);
        dateToPicker = (DatePicker) findViewById(R.id.dateTo);
//        dateFromPicker.setMinDate(new Date().getTime());
//        dateToPicker.setMinDate(new Date().getTime());

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

//        String[] modelsList=new String[]{"-Any-","a","a","a","a","a", "a"};
        String query = "select model from cars";
        Statement stmt = null;
        try {
            stmt = connectionMySQL.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            int i=1;
            while(rs.next()) {
                i++;
            }
            modelsList=new String[i];
            modelsList[0]="-Any-";
            stmt = connectionMySQL.createStatement();
            ResultSet rs2 = stmt.executeQuery(query);
            int j=1;
            while(rs2.next()) {
                modelsList[j]=rs2.getString(1);
                j++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, modelsList);

        modelSpinner.setAdapter(adapter);

        searchBtn = (Button) findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                country = countrySpinner.getSelectedItem().toString();
                city = citySpinner.getSelectedItem().toString();
                fromDay = dateFromPicker.getDayOfMonth() +"";
                if(fromDay.length()<2){
                    fromDay = "0"+fromDay;
                }
                fromMonth = (dateFromPicker.getMonth()+1) +"";
                if(fromMonth.length()<2){
                    fromMonth = "0"+fromMonth;
                }
                fromYear = dateFromPicker.getYear() +"";

                toDay = dateToPicker.getDayOfMonth() +"";
                if(toDay.length()<2){
                    toDay = "0"+toDay;
                }
                toMonth = (dateToPicker.getMonth()+1) +"";
                if(toMonth.length()<2){
                    toMonth = "0"+toMonth;
                }
                toYear = dateToPicker.getYear() +"";
                fromDate = fromYear+"-"+fromMonth+"-"+fromDay;
                toDate = toYear+"-"+toMonth+"-"+toDay;
                String dateFrom = fromYear+"-"+fromMonth+"-"+fromDay;
                String dateTo = toYear+"-"+toMonth+"-"+toDay;

                numOfPeople = noOfPeople.getText().toString();
                transmissionType = transmissionSpinner.getSelectedItem().toString();
                fuelType = fuelSpinner.getSelectedItem().toString();
                model = modelSpinner.getSelectedItem().toString();

                progress.setVisibility(View.VISIBLE);
                searchBtn.setVisibility(View.GONE);

                String query="SELECT cars.CarID,Manufacturer,Model,Capacity,cars.Picture,City,Country, owners.Firstname, owners.Lastname,OwnerPhoneNumber,owners.Picture, Transmission, Fuel, Color, Damages, LicensePlate, CostPerDay, owners.Whatsapp, owners.Email FROM cars, cars_has_clients,owners WHERE (EndRentDate< ')"+
                        dateFrom+"' OR StartRentDate>'"+dateTo+"')  AND cars.CarID=cars_has_clients.CarID AND owners.PhoneNumber=cars.OwnerPhoneNumber ";

                if(!city.equals("-Any-")){
                    query+= "AND cars.City = '"+city+"' ";
                }
                if(!country.equals("-Any-")){
                    query+= "AND cars.Country = '"+country+"' ";
                }
                if (!numOfPeople.isEmpty()){
                    query+= "cars.Capacity>="+numOfPeople;
                }
                if(!transmissionType.equals("-Any-")){
                    query+= "and transmission = '"+transmissionType+"' ";
                }
                if(!fuelType.equals("-Any-")){
                    query+= "and fuel = '"+fuelType+"' ";
                }
                if(!model.equals("-Any-")){
                    query+= "and Model = '"+model+"' ";
                }
                query+=";";

//                String show = new String(country[0] + ", " + city[0] + ", " + fromDay + ", " + fromMonth + ", " +fromYear + ", " + toDay + ", " +toMonth + ", " +toYear + ", " + numOfPeople[0] + ", " +transmissionType[0] + ", " + fuelType[0] + ", " + model[0]);
//                Toast.makeText(getApplicationContext(), query, Toast.LENGTH_LONG).show();



                Statement stmt = null;

//                String query = "select * from cars where Country = '"+country+"' and City = '"+city+
//                        "' and Capacity = '"+numOfPeople+"' and Transmission = '"+transmissionType+"' and Fuel = '"+fuelType+"' and Model = '"+model+"';"
                try {
                    stmt = connectionMySQL.createStatement();
                    ResultSet r = stmt.executeQuery(query);
                    int count=-1;
                    while(r.next()) {
                        count++;
                    }
                    if(count==0){
                        Toast.makeText(getApplicationContext(), "No results Found", Toast.LENGTH_LONG).show();
                        return;
                    }
                    setResultsCount(count);
                    carID = new int[count];
                    manufacture = new String[count];
                    models = new String[count];
                    transmissionTypes = new String[count];
                    fuelTypes = new String[count];
                    color = new String[count];
                    damages = new String[count];
                    countries = new String[count];
                    cities = new String[count];
                    capacity = new int[count];
                    carPic = new Bitmap[count];
                    licensePlate = new String[count];
                    ownerPhone = new int[count];
                    ownerName = new String[count];
                    ownerPic = new Bitmap[count];
                    costPerDay = new int[count];
                    clientPhone = new int[count];
                    whatsapp = new Boolean[count];
                    ownerEmail = new String[count];

//                    Toast.makeText(getApplicationContext(), count+"", Toast.LENGTH_LONG).show();
                    stmt = connectionMySQL.createStatement();
                    ResultSet rs = stmt.executeQuery(query);
                    int i=-1;
                    while(rs.next()) {
                        if(i!=-1){
                            carID[i] = rs.getInt(1);
                            manufacture[i] = rs.getString(2);
                            models[i] = rs.getString(3);
                            transmissionTypes[i] = rs.getString(12);
                            fuelTypes[i] = rs.getString(13);
                            color[i] = rs.getString(14);
                            damages[i] = rs.getString(15);
                            countries[i] = rs.getString(7);
                            cities[i] = rs.getString(6);
                            capacity[i] = rs.getInt(4);
                            carBlob = rs.getBlob(5);
                            int blobLength = (int) carBlob.length();
                            byte[] blobAsBytes = carBlob.getBytes(1, blobLength);
                            carPic[i] = BitmapFactory.decodeByteArray(blobAsBytes, 0, blobAsBytes.length);
                            licensePlate[i] = rs.getString(16);
                            ownerPhone[i] = rs.getInt(10);
                            ownerName[i] = rs.getString(8);
                            ownerName[i] += " " + rs.getString(9);
                            ownerBlob = rs.getBlob(11);
                            int blob2Length = (int) ownerBlob.length();
                            byte[] blob2AsBytes = ownerBlob.getBytes(1, blob2Length);
                            ownerPic[i] = BitmapFactory.decodeByteArray(blob2AsBytes, 0, blob2AsBytes.length);
                            costPerDay[i] = rs.getInt(17);
//                            clientPhone[i] = rs.getInt(18);
                            whatsapp[i] = rs.getInt(18) > 0;
                            ownerEmail[i] = rs.getString(19);
                            flag = true;
                        }
                        i++;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if(resultsCount==-1){
                    Toast.makeText(getApplicationContext(), "No results Found", Toast.LENGTH_LONG).show();
                }else {
                    startActivity(new Intent(getApplicationContext(), ResultsActivity.class));
                }
                progress.setVisibility(View.GONE);
                searchBtn.setVisibility(View.VISIBLE);
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
            try {
                LoginDataSource.logout();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
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
        } else if (id == R.id.nav_logout){
            try {
                LoginDataSource.logout();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            this.finish();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
