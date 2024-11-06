package com.jah.amuedo_hidalgo_jaime_examen_tema4;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    Button btnPassenger, btnAirline, btnDestination, btnNewPassenger;
    TextView lblPassenger, lblAirline, lblDestination;
    String[] arrDestination, arrAirline;
    boolean[] checkedDestination;
    int[] checkedAirline;
    String strName, strDestination;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initComponents();

        btnNewPassenger.setOnClickListener(view -> createDialogNewPassenger());
        btnPassenger.setOnClickListener(view -> createDialogPassenger());
        btnAirline.setOnClickListener(view -> createDialogAirline());
        btnDestination.setOnClickListener(view -> createDialogDestination());

        if(savedInstanceState != null){
            lblPassenger.setText(savedInstanceState.getString("lblPassenger"));
            lblAirline.setText(savedInstanceState.getString("lblAirline"));
            lblDestination.setText(savedInstanceState.getString("lblDestination"));
            arrDestination = savedInstanceState.getStringArray("arrDestination");
            arrAirline = savedInstanceState.getStringArray("arrAirline");
            checkedDestination = savedInstanceState.getBooleanArray("checkedDestination");
            checkedAirline = savedInstanceState.getIntArray("checkedAirline");
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("lblPassenger", lblPassenger.getText().toString());
        outState.putString("lblAirline", lblAirline.getText().toString());
        outState.putString("lblDestination", lblDestination.getText().toString());
        outState.putStringArray("arrDestination", arrDestination);
        outState.putStringArray("arrAirline", arrAirline);
        outState.putBooleanArray("checkedDestination", checkedDestination);
        outState.putIntArray("checkedAirline", checkedAirline);
    }

    private void createDialogDestination() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_destination_title)
                .setMultiChoiceItems(arrDestination, checkedDestination, (dialogInterface, which, checked) -> checkedDestination[which] = checked)
                .setNeutralButton(R.string.dialog_destination_neutral, (dialogInterface, i) -> clearDestinations())
                .setNegativeButton(R.string.dialog_destination_negative, (dialogInterface, i) -> checkCheckeds())
                .setPositiveButton(R.string.dialog_destination_positive, (dialogInterface, i) -> showCheckedDestinations())
                .create()
                .show();
    }

    private void checkCheckeds() {
        for(int ind = 0;ind < arrDestination.length;ind++){
            if(lblDestination.getText().toString().contains(arrDestination[ind])){
                checkedDestination[ind] = true;
            }else{
                checkedDestination[ind] = false;
            }
        }
    }

    private void clearDestinations() {
        lblDestination.setText("");
        Arrays.fill(checkedDestination, false);
    }

    private void showCheckedDestinations() {
        strDestination = "";
        for(int ind = 0;ind < arrDestination.length;ind++){
            if(checkedDestination[ind]){
                strDestination += arrDestination[ind] + "\n";
            }
        }
        lblDestination.setText(strDestination);
    }

    private void createDialogAirline() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_airline_title)
                .setSingleChoiceItems(arrAirline, checkedAirline[0], (dialogInterface, which) -> checkedAirline[0] = which)
                .setNegativeButton(R.string.dialog_airline_negative, null)
                .setPositiveButton(R.string.dialog_airline_positive, (dialogInterface, i) -> lblAirline.setText(arrAirline[checkedAirline[0]]))
                .create()
                .show();

    }

    private void createDialogPassenger() {
        final View custom_layout = getLayoutInflater().inflate(R.layout.custom_layout, null);
        EditText txtName = custom_layout.findViewById(R.id.txtName);
        txtName.setText(lblPassenger.getText().toString());

        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_name_title)
                .setView(custom_layout)
                .setNegativeButton(R.string.dialog_name_negative, null)
                .setPositiveButton(R.string.dialog_name_positive, (dialogInterface, i) -> {
                    strName = txtName.getText().toString();
                    lblPassenger.setText(strName);
                })
                .create()
                .show();
    }

    private void createDialogNewPassenger() {
        new AlertDialog.Builder(this)
                .setCancelable(false)
                .setIcon(R.drawable.ic_launcher_foreground)
                .setTitle(R.string.dialog_new_passenger_title)
                .setMessage(R.string.dialog_new_passenger_message)
                .setNegativeButton(R.string.dialog_new_passenger_negative, null)
                .setPositiveButton(R.string.dialog_new_passenger_positive, (dialogInterface, i) -> cleanAll())
                .create()
                .show();
    }

    private void cleanAll() {
        lblPassenger.setText("");
        lblAirline.setText("");
        lblDestination.setText("");
        Arrays.fill(checkedDestination, false);
        checkedAirline[0] = -1;
    }

    private void initComponents() {
        btnPassenger = findViewById(R.id.btnPassenger);
        btnAirline = findViewById(R.id.btnAirline);
        btnDestination = findViewById(R.id.btnDestination);
        btnNewPassenger = findViewById(R.id.btnNewPassenger);
        lblAirline = findViewById(R.id.lblAirline);
        lblPassenger = findViewById(R.id.lblPassenger);
        lblDestination = findViewById(R.id.lblDestination);
        arrAirline = new String[3];
        arrAirline = getResources().getStringArray(R.array.arrAirline);
        arrDestination = new String[5];
        arrDestination = getResources().getStringArray(R.array.arrDestinations);
        checkedDestination = new boolean[5];
        Arrays.fill(checkedDestination, false);
        checkedAirline = new int[]{-1};
        strName = "";
    }
}