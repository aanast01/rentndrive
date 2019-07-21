package com.example.rentndrive;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultsActivity extends AppCompatActivity {

    LinearLayout result1,result2,result3,result4,result5,result6,result7,result8;

    ImageView carImage1,carImage2,carImage3,carImage4,carImage5,carImage6,carImage7,carImage8;

    TextView carID1,carID2,carID3,carID4,carID5,carID6,carID7,carID8;

    TextView carModel1,carModel2,carModel3,carModel4,carModel5,carModel6,carModel7,carModel8;

    TextView carManufacture1,carManufacture2,carManufacture3,carManufacture4,carManufacture5,carManufacture6,carManufacture7,carManufacture8;

    TextView carFuel1,carFuel2,carFuel3,carFuel4,carFuel5,carFuel6,carFuel7,carFuel8;

    TextView carTrans1,carTrans2,carTrans3,carTrans4,carTrans5,carTrans6,carTrans7,carTrans8;

    TextView carCity1,carCity2,carCity3,carCity4,carCity5,carCity6,carCity7,carCity8;

    public static int getChoosenResult() {
        return choosenResult;
    }

    private static int choosenResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        result1 = (LinearLayout) findViewById(R.id.result1);
        result2 = (LinearLayout) findViewById(R.id.result2);
        result3 = (LinearLayout) findViewById(R.id.result3);
        result4 = (LinearLayout) findViewById(R.id.result4);
        result5 = (LinearLayout) findViewById(R.id.result5);
        result6 = (LinearLayout) findViewById(R.id.result6);
        result7 = (LinearLayout) findViewById(R.id.result7);
        result8 = (LinearLayout) findViewById(R.id.result8);

        carImage1 = (ImageView) findViewById(R.id.carImage1);
        carImage2 = (ImageView) findViewById(R.id.carImage2);
        carImage3 = (ImageView) findViewById(R.id.carImage3);
        carImage4 = (ImageView) findViewById(R.id.carImage4);
        carImage5 = (ImageView) findViewById(R.id.carImage5);
        carImage6 = (ImageView) findViewById(R.id.carImage6);
        carImage7 = (ImageView) findViewById(R.id.carImage7);
        carImage8 = (ImageView) findViewById(R.id.carImage8);

        carID1 = (TextView) findViewById(R.id.carID1);
        carID2 = (TextView) findViewById(R.id.carID2);
        carID3 = (TextView) findViewById(R.id.carID3);
        carID4 = (TextView) findViewById(R.id.carID4);
        carID5 = (TextView) findViewById(R.id.carID5);
        carID6 = (TextView) findViewById(R.id.carID6);
        carID7 = (TextView) findViewById(R.id.carID7);
        carID8 = (TextView) findViewById(R.id.carID8);

        carManufacture1 = (TextView) findViewById(R.id.carManufacture1);
        carManufacture2 = (TextView) findViewById(R.id.carManufacture2);
        carManufacture3 = (TextView) findViewById(R.id.carManufacture3);
        carManufacture4 = (TextView) findViewById(R.id.carManufacture4);
        carManufacture5 = (TextView) findViewById(R.id.carManufacture5);
        carManufacture6 = (TextView) findViewById(R.id.carManufacture6);
        carManufacture7 = (TextView) findViewById(R.id.carManufacture7);
        carManufacture8 = (TextView) findViewById(R.id.carManufacture8);

        carModel1 = (TextView) findViewById(R.id.carModel1);
        carModel2 = (TextView) findViewById(R.id.carModel2);
        carModel3 = (TextView) findViewById(R.id.carModel3);
        carModel4 = (TextView) findViewById(R.id.carModel4);
        carModel5 = (TextView) findViewById(R.id.carModel5);
        carModel6 = (TextView) findViewById(R.id.carModel6);
        carModel7 = (TextView) findViewById(R.id.carModel7);
        carModel8 = (TextView) findViewById(R.id.carModel8);

        carFuel1 = (TextView) findViewById(R.id.carFuel1);
        carFuel2 = (TextView) findViewById(R.id.carFuel2);
        carFuel3 = (TextView) findViewById(R.id.carFuel3);
        carFuel4 = (TextView) findViewById(R.id.carFuel4);
        carFuel5 = (TextView) findViewById(R.id.carFuel5);
        carFuel6 = (TextView) findViewById(R.id.carFuel6);
        carFuel7 = (TextView) findViewById(R.id.carFuel7);
        carFuel8 = (TextView) findViewById(R.id.carFuel8);

        carTrans1 = (TextView) findViewById(R.id.carTans1);
        carTrans2 = (TextView) findViewById(R.id.carTans2);
        carTrans3 = (TextView) findViewById(R.id.carTans3);
        carTrans4 = (TextView) findViewById(R.id.carTans4);
        carTrans5 = (TextView) findViewById(R.id.carTans5);
        carTrans6 = (TextView) findViewById(R.id.carTans6);
        carTrans7 = (TextView) findViewById(R.id.carTans7);
        carTrans8 = (TextView) findViewById(R.id.carTans8);

        carCity1 = (TextView) findViewById(R.id.carCity1);
        carCity2 = (TextView) findViewById(R.id.carCity2);
        carCity3 = (TextView) findViewById(R.id.carCity3);
        carCity4 = (TextView) findViewById(R.id.carCity4);
        carCity5 = (TextView) findViewById(R.id.carCity5);
        carCity6 = (TextView) findViewById(R.id.carCity6);
        carCity7 = (TextView) findViewById(R.id.carCity7);
        carCity8 = (TextView) findViewById(R.id.carCity8);


        carImage1.setImageBitmap(SearchActivity.getCarPic()[0]);
        carID1.setText("€"+SearchActivity.getCostPerDay()[0]);
        carManufacture1.setText(SearchActivity.getManufacture()[0]);
        carModel1.setText(SearchActivity.getModels()[0]);
        carFuel1.setText(SearchActivity.getFuelTypes()[0]);
        carTrans1.setText(SearchActivity.getTransmissionTypes()[0]);
        carCity1.setText(SearchActivity.getCities()[0]);

        int resultsCount = SearchActivity.getResultsCount();
        switch (resultsCount){
            case 2:
                result2.setVisibility(View.VISIBLE);
                showResult2();
                break;
            case 3:
                result2.setVisibility(View.VISIBLE);
                result3.setVisibility(View.VISIBLE);
                showResult2();
                showResult3();
                break;
            case 4:
                result2.setVisibility(View.VISIBLE);
                result3.setVisibility(View.VISIBLE);
                result4.setVisibility(View.VISIBLE);
                showResult2();
                showResult3();
                showResult4();
                break;
            case 5:
                result2.setVisibility(View.VISIBLE);
                result3.setVisibility(View.VISIBLE);
                result4.setVisibility(View.VISIBLE);
                result5.setVisibility(View.VISIBLE);
                showResult2();
                showResult3();
                showResult4();
                showResult5();
                break;
            case 6:
                result2.setVisibility(View.VISIBLE);
                result3.setVisibility(View.VISIBLE);
                result4.setVisibility(View.VISIBLE);
                result5.setVisibility(View.VISIBLE);
                result6.setVisibility(View.VISIBLE);
                showResult2();
                showResult3();
                showResult4();
                showResult5();
                showResult6();
                break;
            case 7:
                result2.setVisibility(View.VISIBLE);
                result3.setVisibility(View.VISIBLE);
                result4.setVisibility(View.VISIBLE);
                result5.setVisibility(View.VISIBLE);
                result6.setVisibility(View.VISIBLE);
                result7.setVisibility(View.VISIBLE);
                showResult2();
                showResult3();
                showResult4();
                showResult5();
                showResult6();
                showResult7();
                break;
            default:
                result2.setVisibility(View.VISIBLE);
                result3.setVisibility(View.VISIBLE);
                result4.setVisibility(View.VISIBLE);
                result5.setVisibility(View.VISIBLE);
                result6.setVisibility(View.VISIBLE);
                result7.setVisibility(View.VISIBLE);
                result8.setVisibility(View.VISIBLE);
                showResult2();
                showResult3();
                showResult4();
                showResult5();
                showResult6();
                showResult7();
                showResult8();
                break;
        }


        result1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=0;
            }
        });

        result2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=1;
            }
        });

        result3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=2;
            }
        });

        result4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=3;
            }
        });

        result5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=4;
            }
        });

        result6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=5;
            }
        });

        result7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=6;
            }
        });

        result8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), DetailsActivity.class));
                choosenResult=7;
            }
        });
    }

    void showResult2(){
        carImage2.setImageBitmap(SearchActivity.getCarPic()[1]);
        carID2.setText("€"+SearchActivity.getCostPerDay()[1]);
        carManufacture2.setText(SearchActivity.getManufacture()[1]);
        carModel2.setText(SearchActivity.getModels()[1]);
        carFuel2.setText(SearchActivity.getFuelTypes()[1]);
        carTrans2.setText(SearchActivity.getTransmissionTypes()[1]);
        carCity2.setText(SearchActivity.getCities()[1]);
    }

    void showResult3(){
        carImage3.setImageBitmap(SearchActivity.getCarPic()[2]);
        carID3.setText("€"+SearchActivity.getCostPerDay()[2]);
        carManufacture3.setText(SearchActivity.getManufacture()[2]);
        carModel3.setText(SearchActivity.getModels()[2]);
        carFuel3.setText(SearchActivity.getFuelTypes()[2]);
        carTrans3.setText(SearchActivity.getTransmissionTypes()[2]);
        carCity3.setText(SearchActivity.getCities()[2]);
    }

    void showResult4(){
        carImage4.setImageBitmap(SearchActivity.getCarPic()[3]);
        carID4.setText("€"+SearchActivity.getCostPerDay()[3]);
        carManufacture4.setText(SearchActivity.getManufacture()[3]);
        carModel4.setText(SearchActivity.getModels()[3]);
        carFuel4.setText(SearchActivity.getFuelTypes()[3]);
        carTrans4.setText(SearchActivity.getTransmissionTypes()[3]);
        carCity4.setText(SearchActivity.getCities()[3]);
    }

    void showResult5(){
        carImage5.setImageBitmap(SearchActivity.getCarPic()[4]);
        carID5.setText("€"+SearchActivity.getCostPerDay()[4]);
        carManufacture5.setText(SearchActivity.getManufacture()[4]);
        carModel5.setText(SearchActivity.getModels()[4]);
        carFuel5.setText(SearchActivity.getFuelTypes()[4]);
        carTrans5.setText(SearchActivity.getTransmissionTypes()[4]);
        carCity5.setText(SearchActivity.getCities()[4]);
    }

    void showResult6(){
        carImage6.setImageBitmap(SearchActivity.getCarPic()[5]);
        carID6.setText("€"+SearchActivity.getCostPerDay()[5]);
        carManufacture6.setText(SearchActivity.getManufacture()[5]);
        carModel6.setText(SearchActivity.getModels()[5]);
        carFuel6.setText(SearchActivity.getFuelTypes()[5]);
        carTrans6.setText(SearchActivity.getTransmissionTypes()[5]);
        carCity6.setText(SearchActivity.getCities()[5]);
    }

    void showResult7(){
        carImage7.setImageBitmap(SearchActivity.getCarPic()[6]);
        carID7.setText("€"+SearchActivity.getCostPerDay()[6]);
        carManufacture7.setText(SearchActivity.getManufacture()[6]);
        carModel7.setText(SearchActivity.getModels()[6]);
        carFuel7.setText(SearchActivity.getFuelTypes()[6]);
        carTrans7.setText(SearchActivity.getTransmissionTypes()[6]);
        carCity7.setText(SearchActivity.getCities()[6]);
    }

    void showResult8(){
        carImage8.setImageBitmap(SearchActivity.getCarPic()[7]);
        carID8.setText("€"+SearchActivity.getCostPerDay()[7]);
        carManufacture8.setText(SearchActivity.getManufacture()[7]);
        carModel8.setText(SearchActivity.getModels()[7]);
        carFuel8.setText(SearchActivity.getFuelTypes()[7]);
        carTrans8.setText(SearchActivity.getTransmissionTypes()[7]);
        carCity8.setText(SearchActivity.getCities()[7]);
    }
}
