package com.educa62.simpleroom;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {

    private TextInputEditText tietTeam;
    private TextInputEditText tietYear;
    private TextInputEditText tietWebsite;
    private TextInputEditText tietGender;
    private TextInputEditText tietCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        setTitle("Add New Data");
        findViewById(R.id.bAdd).setOnClickListener(this);
        tietTeam = findViewById(R.id.tietTeam);
        tietYear = findViewById(R.id.tietYear);
        tietWebsite = findViewById(R.id.tietWebsite);
        tietGender = findViewById(R.id.tietGender);
        tietCountry = findViewById(R.id.tietCountry);
    }

    @Override
    public void onClick(View v) {
        String sTeam = tietTeam.getText().toString();
        String sYear = tietYear.getText().toString();
        String sWebsite = tietWebsite.getText().toString();
        String sGender = tietGender.getText().toString();
        String sCountry = tietCountry.getText().toString();
        Intent intent = new Intent();
        if (!(sTeam.isEmpty() && sYear.isEmpty() && sWebsite.isEmpty() && sGender.isEmpty() && sCountry.isEmpty())) {
            intent.putExtra(Constant.TAG_TEAM, sTeam);
            intent.putExtra(Constant.TAG_YEAR, sYear);
            intent.putExtra(Constant.TAG_WEBSITE, sWebsite);
            intent.putExtra(Constant.TAG_GENDER, sGender);
            intent.putExtra(Constant.TAG_COUNTRY, sCountry);
            Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();
            setResult(RESULT_CANCELED, intent);
        }
    }
}
