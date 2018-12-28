package com.educa62.simpleroom.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.educa62.simpleroom.R;
import com.educa62.simpleroom.db.Constant;
import com.educa62.simpleroom.db.PlayerDatabase;
import com.educa62.simpleroom.entity.Players;

public class AddDetailPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText tietPlayer;
    private TextInputEditText tietPosition;
    private TextInputEditText tietAge;
    private TextInputEditText tietGender;
    private TextInputEditText tietCountry;

    private Button bEdit;
    private Button bDelete;
    private Button bDone;
    private Button bAdd;

    private Bundle bundle;

    private Players players;

    private PlayerDatabase playerDatabase;

    private Intent intent;

    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_detail_player);
        tietPlayer = findViewById(R.id.tietPlayer);
        tietPosition = findViewById(R.id.tietPosition);
        tietAge = findViewById(R.id.tietAge);
        tietGender = findViewById(R.id.tietGender);
        tietCountry = findViewById(R.id.tietCountry);

        bEdit = findViewById(R.id.bEdit);
        bDelete = findViewById(R.id.bDelete);
        bDone = findViewById(R.id.bDone);
        bAdd = findViewById(R.id.bAdd);

        bEdit.setOnClickListener(this);
        bDelete.setOnClickListener(this);
        bDone.setOnClickListener(this);
        bAdd.setOnClickListener(this);

        bundle = getIntent().getExtras();

        players = new Players();

        playerDatabase = PlayerDatabase.Companion.createDatabase(this);

        intent = new Intent();

        if (bundle == null) {
            addPlayerData();
        } else {
            detailActivity();
        }
    }

    private void addPlayerData() {
        setTitle("Add New Player");
        bDelete.setVisibility(View.GONE);
        bEdit.setVisibility(View.GONE);
        bDone.setVisibility(View.GONE);
        bAdd.setVisibility(View.VISIBLE);
    }

    private void detailActivity() {
        setTitle("Detail Player");

        int id_ = bundle.getInt(Constant.TAG_ID);
        String sPlayer = bundle.getString(Constant.TAG_PLAYER);
        String sPosition = bundle.getString(Constant.TAG_POSITION);
        String sAge = bundle.getString(Constant.TAG_AGE);
        String sGender = bundle.getString(Constant.TAG_GENDER);
        String sCountry = bundle.getString(Constant.TAG_COUNTRY);

        bDelete.setVisibility(View.VISIBLE);
        bEdit.setVisibility(View.VISIBLE);
        bDone.setVisibility(View.GONE);
        bAdd.setVisibility(View.GONE);

        tietPlayer.setEnabled(false);
        tietPosition.setEnabled(false);
        tietAge.setEnabled(false);
        tietGender.setEnabled(false);
        tietCountry.setEnabled(false);

        tietPlayer.setText(sPlayer);
        tietPosition.setText(sPosition);
        tietAge.setText(sAge);
        tietGender.setText(sGender);
        tietCountry.setText(sCountry);

        players.setId(id_);
        players.setPlayer_(sPlayer);
        players.setPosition_(sPosition);
        players.setAge_(sAge);
        players.setGender_(sGender);
        players.setCountry_(sCountry);
    }

    @Override
    public void onClick(View v) {

        String sPlayerX = tietPlayer.getText().toString();
        String sPositionX = tietPosition.getText().toString();
        String sAgeX = tietAge.getText().toString();
        String sGenderX = tietGender.getText().toString();
        String sCountryX = tietCountry.getText().toString();

        boolean empty = sPlayerX.isEmpty() || sPositionX.isEmpty() || sAgeX.isEmpty() || sGenderX.isEmpty() || sCountryX.isEmpty();

        switch (v.getId()){
            case R.id.bEdit:

                setTitle("Edit");

                tietPlayer.setEnabled(true);
                tietPosition.setEnabled(true);
                tietAge.setEnabled(true);
                tietGender.setEnabled(true);
                tietCountry.setEnabled(true);

                bEdit.setVisibility(View.GONE);
                bDelete.setVisibility(View.GONE);
                bDone.setVisibility(View.VISIBLE);
                bAdd.setVisibility(View.GONE);

                break;
            case R.id.bDelete:
                alertDialog = new AlertDialog
                        .Builder(this)
                        .setCancelable(false)
                        .setMessage("Are you sure to delete " + sPlayerX + " ?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {

                                Toast.makeText(getApplicationContext(), "done", Toast.LENGTH_SHORT).show();

                                playerDatabase.playersDao().delete(players);

                                finish();

                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                alertDialog.cancel();
                            }
                        })
                        .show();
                break;
            case R.id.bDone:
                if (!empty) {

                    players.setPlayer_(sPlayerX);
                    players.setPosition_(sPositionX);
                    players.setAge_(sAgeX);
                    players.setGender_(sGenderX);
                    players.setCountry_(sCountryX);

                    playerDatabase.playersDao().update(players);

                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

                    finish();

                } else {

                    Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.bAdd:
                if (!empty) {

                    intent.putExtra(Constant.TAG_PLAYER, sPlayerX);
                    intent.putExtra(Constant.TAG_POSITION, sPositionX);
                    intent.putExtra(Constant.TAG_AGE, sAgeX);
                    intent.putExtra(Constant.TAG_GENDER, sGenderX);
                    intent.putExtra(Constant.TAG_COUNTRY, sCountryX);

                    Toast.makeText(this, "done", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_OK, intent);

                    finish();

                } else {

                    Toast.makeText(this, "cannot be empty", Toast.LENGTH_SHORT).show();

                    setResult(RESULT_CANCELED, intent);

                }
                break;
        }

    }
}
