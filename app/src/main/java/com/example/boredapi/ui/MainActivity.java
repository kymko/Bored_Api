package com.example.boredapi.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.boredapi.R;
import com.example.boredapi.data.model.ActivitiesModel;
import com.example.boredapi.remote.RetrofitBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView textActivity;
    private TextView textPrice;
    private TextView textCategory;
    private TextView textFree;
    private Spinner spinnerCategories;
    private final String[] categories = {"relaxation", "cooking", "recreational", "social", "busywork", "Type"};
    private final int listSize = categories.length - 1;
    private String poss = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        spinnerCategories.setOnItemSelectedListener(this);
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories) {
            @Override
            public int getCount() {
                return (listSize);
            }
        };

        adapterSpinner.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerCategories.setAdapter(adapterSpinner);
        spinnerCategories.setSelection(listSize);
    }

    private void initViews() {
        textActivity = findViewById(R.id.text_activity);
        textPrice = findViewById(R.id.text_price);
        textCategory = findViewById(R.id.text_category);
        textFree = findViewById(R.id.text_free);
        spinnerCategories = findViewById(R.id.spinnerType);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            poss = categories[0];
            textCategory.setText(categories[0]);
        } else if (position == 1) {
            poss = categories[1];
            textCategory.setText(categories[1]);
        } else if (position == 2) {
            poss = categories[2];
            textCategory.setText(categories[2]);
        } else if (position == 3) {
            poss = categories[3];
            textCategory.setText(categories[3]);
        } else {
            poss = categories[4];
            textCategory.setText(categories[4]);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onClick(View view) {

//        RetrofitBuilder.getInstance().getActivities().enqueue(new Callback<ActivitiesModel>() {
//            @Override
//            public void onResponse(Call<ActivitiesModel> call, Response<ActivitiesModel> response) {
//                if (response.isSuccessful() && response.body() != null) {
//                    Log.d("tag", "success: " + response.body().getActivities());
//                    Log.d("tag", "price: " + response.body().getPrice());
//                    textActivity.setText(response.body().getActivities());
//                    if (response.body().getPrice() == 0.0) {
//                        textFree.setTextSize(28);
//                    } else {
//                        textFree.setTextSize(25);
//                    }
//                    textPrice.setText(String.valueOf(response.body().getPrice()) + " $");
//
//                    textCategory.setText(response.body().getType());
//
//
//                } else {
//                    Log.e("tag", "success: " + response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ActivitiesModel> call, Throwable t) {
//                Log.e("tag", "failure: " + t.getLocalizedMessage());
//
//            }
//        });

        RetrofitBuilder.getInstance().getType(poss).enqueue(new Callback<ActivitiesModel>() {
            @Override
            public void onResponse(Call<ActivitiesModel> call, Response<ActivitiesModel> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.d("tag", "success: " + response.body().getType());
                    Log.d("tag", "success: " + response.body().getActivities());
                    textActivity.setText(response.body().getActivities());
                    if (response.body().getPrice() == 0.0) {
                        textFree.setTextSize(28);
                    } else {
                        textFree.setTextSize(25);
                    }
                    textPrice.setText(String.valueOf(response.body().getPrice()) + " $");

                    textCategory.setText(response.body().getType());

                } else {
                    Log.d("tag", "erorr: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<ActivitiesModel> call, Throwable t) {
                Log.d("tag", "fail: " + t.getLocalizedMessage());
            }
        });
    }
}