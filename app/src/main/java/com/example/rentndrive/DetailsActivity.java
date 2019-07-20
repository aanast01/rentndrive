package com.example.rentndrive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.rentndrive.data.LoginDataSource.connectionMySQL;

public class DetailsActivity extends AppCompatActivity {

    private TextView manufacture;
    private TextView model;
    private TextView capacity;
    private TextView city;
    private TextView country;
    private TextView ownersName;
    private TextView ownersPhone;
    private TextView trans;
    private TextView fuel;
    private TextView color;
    private TextView damages;
    private TextView plate;
    private TextView dateFrom;
    private TextView dateTo;
    private TextView cost;
    private ImageView carPic;
    private Button confirmBtn;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        manufacture = (TextView) findViewById(R.id.manufatureTxt);
        model = (TextView) findViewById(R.id.modelTxt);
        capacity = (TextView) findViewById(R.id.capacityTxt);
        city = (TextView) findViewById(R.id.cityTxt);
        country = (TextView) findViewById(R.id.countryTxt);
        ownersName = (TextView) findViewById(R.id.ownerNameTxt);
        ownersPhone = (TextView) findViewById(R.id.ownerPhoneTxt);
        trans = (TextView) findViewById(R.id.transTxt);
        fuel = (TextView) findViewById(R.id.fuelTxt);
        color = (TextView) findViewById(R.id.colorTxt);
        damages = (TextView) findViewById(R.id.damegesTxt);
        plate = (TextView) findViewById(R.id.plateTxt);
        carPic= (ImageView) findViewById(R.id.carPicImage);
        dateFrom= (TextView) findViewById(R.id.dateFromTxt);
        dateTo= (TextView) findViewById(R.id.dateToTxt);
        cost= (TextView) findViewById(R.id.costTxt);
        confirmBtn = (Button) findViewById(R.id.confirmBtn);
        progressBar = (ProgressBar) findViewById(R.id.confirmProg);

        final int selectedCar = ResultsActivity.getChoosenResult();
        manufacture.setText(SearchActivity.getManufacture()[selectedCar]);
        model.setText(SearchActivity.getModels()[selectedCar]);
        capacity.setText(SearchActivity.getCapacity()[selectedCar]+"");
        city.setText(SearchActivity.getCities()[selectedCar]);
        country.setText(SearchActivity.getCountries()[selectedCar]);
        ownersName.setText(SearchActivity.getOwnerName()[selectedCar]);
        ownersPhone.setText(SearchActivity.getOwnerPhone()[selectedCar]+"");
        trans.setText(SearchActivity.getTransmissionTypes()[selectedCar]);
        fuel.setText(SearchActivity.getFuelTypes()[selectedCar]);
        color.setText(SearchActivity.getColor()[selectedCar]);
        damages.setText(SearchActivity.getDamages()[selectedCar]);
        plate.setText(SearchActivity.getLicensePlate()[selectedCar]);
        carPic.setImageBitmap(SearchActivity.getCarPic()[selectedCar]);
        dateFrom.setText(SearchActivity.getFromDate());
        dateTo.setText(SearchActivity.getToDate());

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                confirmBtn.setVisibility(View.GONE);
                Statement stmt = null;

                String query = "INSERT INTO cars_has_clients (CarID, ClientPhoneNumber, StartRentDate, EndRentDate) VALUES ("+SearchActivity.getCarID()[selectedCar]+","+SearchActivity.getClientPhone()[selectedCar]+",'"+dateFrom+"','"+dateTo+"')";

                try {
                    stmt = connectionMySQL.createStatement();
                    ResultSet r = stmt.executeQuery(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                progressBar.setVisibility(View.GONE);
                confirmBtn.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), "CONGRATS!! Your reservation have been successfuly stored!", Toast.LENGTH_LONG).show();

            }
        });


    }
}
