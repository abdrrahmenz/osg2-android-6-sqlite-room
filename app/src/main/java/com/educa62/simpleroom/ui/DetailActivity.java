package com.educa62.simpleroom.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.db.FootballDatabase;
import com.educa62.simpleroom.R;
import com.educa62.simpleroom.entity.Teams;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    private Teams teams;
    private TextInputEditText tietTeamDetail;
    private TextInputEditText tietYearDetail;
    private TextInputEditText tietWebsiteDetail;
    private TextInputEditText tietGenderDetail;
    private TextInputEditText tietCountryDetail;
    private String sTeam;
    private String sYear;
    private String sWebsite;
    private String sGender;
    private String sCountry;
    private FootballDatabase footballDB;
    private Button bEdit;
    private Button bDelete;
    private Button bDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle("Detail");
        tietTeamDetail = findViewById(R.id.tietTeamDetail);
        tietYearDetail = findViewById(R.id.tietYearDetail);
        tietWebsiteDetail = findViewById(R.id.tietWebsiteDetail);
        tietGenderDetail = findViewById(R.id.tietGenderDetail);
        tietCountryDetail = findViewById(R.id.tietCountryDetail);
        bEdit = findViewById(R.id.bEditDetail);
        bDelete = findViewById(R.id.bDeleteDetail);
        bDone = findViewById(R.id.bDoneDetail);
        tietTeamDetail.setEnabled(false);
        tietYearDetail.setEnabled(false);
        tietWebsiteDetail.setEnabled(false);
        tietGenderDetail.setEnabled(false);
        tietCountryDetail.setEnabled(false);
        bEdit.setOnClickListener(this);
        bEdit.setVisibility(View.VISIBLE);
        bDelete.setOnClickListener(this);
        bDelete.setVisibility(View.VISIBLE);
        bDone.setOnClickListener(this);
        bDone.setVisibility(View.GONE);
        teams = new Teams();
        Bundle bundle = getIntent().getExtras();
        footballDB = FootballDatabase.createDatabase(this);
        if (bundle != null) {
            int id_ = bundle.getInt(Constant.TAG_ID);
            sTeam = bundle.getString(Constant.TAG_TEAM);
            sYear = bundle.getString(Constant.TAG_YEAR);
            sWebsite = bundle.getString(Constant.TAG_WEBSITE);
            sGender = bundle.getString(Constant.TAG_GENDER);
            sCountry = bundle.getString(Constant.TAG_COUNTRY);
            tietTeamDetail.setText(sTeam);
            tietYearDetail.setText(sYear);
            tietWebsiteDetail.setText(sWebsite);
            tietGenderDetail.setText(sGender);
            tietCountryDetail.setText(sCountry);
            teams.setId_(id_);
            teams.setTeam_(sTeam);
            teams.setYear_(sYear);
            teams.setWebsite_(sWebsite);
            teams.setGender_(sGender);
            teams.setCountry_(sCountry);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bEditDetail:
                setTitle("Edit");
                tietTeamDetail.setEnabled(true);
                tietYearDetail.setEnabled(true);
                tietWebsiteDetail.setEnabled(true);
                tietGenderDetail.setEnabled(true);
                tietCountryDetail.setEnabled(true);
                bEdit.setVisibility(View.GONE);
                bDelete.setVisibility(View.GONE);
                bDone.setVisibility(View.VISIBLE);
                break;
            case R.id.bDeleteDetail:
                //Creating an alert dialog to confirm delete
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setMessage("Are you sure to delete this ?");
                alertDialogBuilder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();
                        footballDB.teamsDao().delete(teams);
                        finish();
                    }
                });

                alertDialogBuilder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                    }
                });

                //Showing the alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
            case R.id.bDoneDetail:
                sTeam = tietTeamDetail.getText().toString();
                sYear = tietYearDetail.getText().toString();
                sWebsite = tietWebsiteDetail.getText().toString();
                sGender = tietGenderDetail.getText().toString();
                sCountry = tietCountryDetail.getText().toString();
                teams.setTeam_(sTeam);
                teams.setYear_(sYear);
                teams.setWebsite_(sWebsite);
                teams.setGender_(sGender);
                teams.setCountry_(sCountry);
                if (!(sTeam.isEmpty() && sYear.isEmpty() && sWebsite.isEmpty() && sGender.isEmpty() && sCountry.isEmpty())) {
                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();
                    footballDB.teamsDao().update(teams);
                    finish();
                } else {
                    Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
