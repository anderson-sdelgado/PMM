package br.com.usinasantafe.cmm.view;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;

import java.util.ArrayList;

import br.com.usinasantafe.cmm.NetworkChangeListerner;
import br.com.usinasantafe.cmm.model.pst.DatabaseHelper;
import br.com.usinasantafe.cmm.R;

/**
 * Created by anderson on 01/10/2015.
 */
public class ActivityGeneric extends OrmLiteBaseActivity<DatabaseHelper> implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    public EditText editTextPadrao;

    public static boolean connectNetwork;

    private Location location;
    private GoogleApiClient googleApiClient;
    private LocationRequest locationRequest;
    private static final long UPDATE_INTERVAL = 60000, FASTEST_INTERVAL = 60000; // = 5 seconds
    private ArrayList<String> permissionsToRequest;
    private ArrayList<String> permissionsRejected = new ArrayList<>();
    private ArrayList<String> permissions = new ArrayList<>();
    private static final int ALL_PERMISSIONS_RESULT = 1011;

    private NetworkChangeListerner networkChangeListerner = new NetworkChangeListerner();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getHelper();

        if (!this.getLocalClassName().contains("TelaInicialActivity")){

            permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            permissions.add(Manifest.permission.CAMERA);

            permissionsToRequest = permissionsToRequest(permissions);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(
                            new String[permissionsToRequest.size()]), ALL_PERMISSIONS_RESULT);
                }
            }

//            googleApiClient = new GoogleApiClient.Builder(this).
//                    addApi(LocationServices.API).
//                    addConnectionCallbacks(this).
//                    addOnConnectionFailedListener(this).build();
//
//            if (googleApiClient != null) {
//                googleApiClient.connect();
//            }

        }

    }

    private ArrayList<String> permissionsToRequest(ArrayList<String> wantedPermissions) {
        ArrayList<String> result = new ArrayList<>();

        for (String perm : wantedPermissions) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }

        return result;
    }

    private boolean hasPermission(String permission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
        }

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        IntentFilter intentFilter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeListerner, intentFilter);

//        if (googleApiClient != null) {
//            googleApiClient.connect();
//        }

    }

    @Override
    protected void onPause() {

        super.onPause();
//        if (googleApiClient != null  &&  googleApiClient.isConnected()) {
//            LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
//            googleApiClient.disconnect();
//        }

    }

    @Override
    protected void onStop() {
        unregisterReceiver(networkChangeListerner);
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        startLocationUpdates();
    }

    private void startLocationUpdates() {
        locationRequest = new LocationRequest();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(UPDATE_INTERVAL);
        locationRequest.setFastestInterval(FASTEST_INTERVAL);

        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                &&  ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "You need to enable permissions to display location !", Toast.LENGTH_SHORT).show();
        }

        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == ALL_PERMISSIONS_RESULT) {
            for (String perm : permissionsToRequest) {
                if (!hasPermission(perm)) {
                    permissionsRejected.add(perm);
                }
            }

            if (permissionsRejected.size() > 0) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (shouldShowRequestPermissionRationale(permissionsRejected.get(0))) {
                        new AlertDialog.Builder(this).
                                setMessage("These permissions are mandatory to get your location. You need to allow them.").
                                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(permissionsRejected.
                                                    toArray(new String[permissionsRejected.size()]), ALL_PERMISSIONS_RESULT);
                                        }
                                    }
                                }).setNegativeButton("Cancel", null).create().show();

                        return;
                    }
                }
            } else {
                if (googleApiClient != null) {
                    googleApiClient.connect();
                }
            }
        }
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

        if (findViewById(R.id.editTextPadrao) != null) {
            editTextPadrao = findViewById(R.id.editTextPadrao);
            if (!this.getLocalClassName().equals("view.OSActivity")
                    && (!this.getLocalClassName().equals("view.RendimentoActivity"))
                    && (!this.getLocalClassName().equals("view.RecolhimentoActivity"))
                    && (!this.getLocalClassName().equals("view.PneuActivity"))) {
                editTextPadrao.setText("");
            }
        }

        if (findViewById(R.id.buttonNum0) != null) {
            Button buttonNum0 = findViewById(R.id.buttonNum0);
            buttonNum0. setOnClickListener(new EventoBotao("0"));
        }

        if (findViewById(R.id.buttonNum1) != null) {
            Button buttonNum1 = findViewById(R.id.buttonNum1);
            buttonNum1.setOnClickListener(new EventoBotao("1"));
        }

        if (findViewById(R.id.buttonNum2) != null) {
            Button buttonNum2 = findViewById(R.id.buttonNum2);
            buttonNum2.setOnClickListener(new EventoBotao("2"));
        }

        if (findViewById(R.id.buttonNum3) != null) {
            Button buttonNum3 = findViewById(R.id.buttonNum3);
            buttonNum3.setOnClickListener(new EventoBotao("3"));
        }

        if (findViewById(R.id.buttonNum4) != null) {
            Button buttonNum4 = findViewById(R.id.buttonNum4);
            buttonNum4.setOnClickListener(new EventoBotao("4"));
        }

        if (findViewById(R.id.buttonNum5) != null) {
            Button buttonNum5 = findViewById(R.id.buttonNum5);
            buttonNum5.setOnClickListener(new EventoBotao("5"));
        }

        if (findViewById(R.id.buttonNum6) != null) {
            Button buttonNum6 = findViewById(R.id.buttonNum6);
            buttonNum6.setOnClickListener(new EventoBotao("6"));
        }

        if (findViewById(R.id.buttonNum7) != null) {
            Button buttonNum7 = findViewById(R.id.buttonNum7);
            buttonNum7.setOnClickListener(new EventoBotao("7"));
        }

        if (findViewById(R.id.buttonNum8) != null) {
            Button buttonNum8 = findViewById(R.id.buttonNum8);
            buttonNum8.setOnClickListener(new EventoBotao("8"));
        }

        if (findViewById(R.id.buttonNum9) != null) {
            Button buttonNum9 = findViewById(R.id.buttonNum9);
            buttonNum9.setOnClickListener(new EventoBotao("9"));
        }

        if (findViewById(R.id.buttonNum00) != null) {
            Button buttonNum00 = findViewById(R.id.buttonNum00);
            buttonNum00.setOnClickListener(new EventoBotao("00"));
        }

        if (findViewById(R.id.buttonVirg) != null) {
            Button buttonVirg = findViewById(R.id.buttonVirg);
            buttonVirg.setOnClickListener(new EventoBotao(","));
        }
    }

    public Double getLatitude(){
//        if(location != null){
//            return location.getLatitude();
//        }
//        else{
//            return 0D;
//        }
        return 0D;
    }

    public Double getLongitude(){
//        if(location != null){
//            return location.getLongitude();
//        }
//        else {
//            return 0D;
//        }
        return 0D;
    }

}
