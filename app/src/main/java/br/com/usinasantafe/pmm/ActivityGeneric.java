package br.com.usinasantafe.pmm;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.util.ArrayList;
import java.util.List;

import br.com.usinasantafe.pmm.pst.DatabaseHelper;

/**
 * Created by anderson on 01/10/2015.
 */
public class ActivityGeneric extends OrmLiteBaseActivity<DatabaseHelper> {

    public EditText editTextPadrao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getHelper();

    }

    private class EventoBotao implements View.OnClickListener {

        private String numBotao;

        public EventoBotao(String numBotao) {
            this.numBotao = numBotao;
        }

        @Override
        public void onClick(View v) {

            String texto = editTextPadrao.getText().toString();
            if (numBotao.equals(",")) {
                if (!texto.contains(",")) {
                    editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
                }
            } else {
                editTextPadrao.setText(editTextPadrao.getText() + "" + numBotao);
            }

        }

    }

    @Override
    protected void onResume() {

        super.onResume();

        if ((EditText) findViewById(R.id.editTextPadrao) != null) {
            editTextPadrao = (EditText) findViewById(R.id.editTextPadrao);
            if (!this.getLocalClassName().equals("OSActivity") && (!this.getLocalClassName().equals("RendimentoActivity"))
                    && (!this.getLocalClassName().equals("RecolhimentoActivity"))) {
                editTextPadrao.setText("");
            }
        }

        if ((Button) findViewById(R.id.buttonNum0) != null) {
            Button buttonNum0 = (Button) findViewById(R.id.buttonNum0);
            buttonNum0.setOnClickListener(new EventoBotao("0"));
        }

        if ((Button) findViewById(R.id.buttonNum1) != null) {
            Button buttonNum1 = (Button) findViewById(R.id.buttonNum1);
            buttonNum1.setOnClickListener(new EventoBotao("1"));
        }

        if ((Button) findViewById(R.id.buttonNum2) != null) {
            Button buttonNum2 = (Button) findViewById(R.id.buttonNum2);
            buttonNum2.setOnClickListener(new EventoBotao("2"));
        }

        if ((Button) findViewById(R.id.buttonNum3) != null) {
            Button buttonNum3 = (Button) findViewById(R.id.buttonNum3);
            buttonNum3.setOnClickListener(new EventoBotao("3"));
        }

        if ((Button) findViewById(R.id.buttonNum4) != null) {
            Button buttonNum4 = (Button) findViewById(R.id.buttonNum4);
            buttonNum4.setOnClickListener(new EventoBotao("4"));
        }

        if ((Button) findViewById(R.id.buttonNum5) != null) {
            Button buttonNum5 = (Button) findViewById(R.id.buttonNum5);
            buttonNum5.setOnClickListener(new EventoBotao("5"));
        }

        if ((Button) findViewById(R.id.buttonNum6) != null) {
            Button buttonNum6 = (Button) findViewById(R.id.buttonNum6);
            buttonNum6.setOnClickListener(new EventoBotao("6"));
        }

        if ((Button) findViewById(R.id.buttonNum7) != null) {
            Button buttonNum7 = (Button) findViewById(R.id.buttonNum7);
            buttonNum7.setOnClickListener(new EventoBotao("7"));
        }

        if ((Button) findViewById(R.id.buttonNum8) != null) {
            Button buttonNum8 = (Button) findViewById(R.id.buttonNum8);
            buttonNum8.setOnClickListener(new EventoBotao("8"));
        }

        if ((Button) findViewById(R.id.buttonNum9) != null) {
            Button buttonNum9 = (Button) findViewById(R.id.buttonNum9);
            buttonNum9.setOnClickListener(new EventoBotao("9"));
        }

        if ((Button) findViewById(R.id.buttonNum00) != null) {
            Button buttonNum00 = (Button) findViewById(R.id.buttonNum00);
            buttonNum00.setOnClickListener(new EventoBotao("00"));
        }

        if ((Button) findViewById(R.id.buttonVirg) != null) {
            Button buttonVirg = (Button) findViewById(R.id.buttonVirg);
            buttonVirg.setOnClickListener(new EventoBotao(","));
        }
    }


}
