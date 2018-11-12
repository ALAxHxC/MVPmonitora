package ponny.org.monitora.views.paciente;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ponny.org.monitora.LoginActivity;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.Login;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.bluetooth.ControllerBLE;
import ponny.org.monitora.presenters.vista.paciente.MuestraProvider;
import ponny.org.monitora.views.ParentMain;
import ponny.org.monitora.views.common.permissions.PermissionCheckActivity;
import ponny.org.monitora.views.medico.dialogs.MessageDialogFragment;
import ponny.org.monitora.views.paciente.dialogs.MessagePatientDialogFragment;
import ponny.org.monitora.views.paciente.manager.FragmentManagerPatient;

public class PacienteMain extends ParentMain
        implements NavigationView.OnNavigationItemSelectedListener {
    private MessagePatientDialogFragment messageProvider;
    protected static final int REQUEST_CHECK_SETTINGS = 0x1;
    @BindView(R.id.viewpager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab_message)
    FloatingActionButton message;
    private  MessagePatientDialogFragment messageDialogFragment;
    private ControllerBLE controllerBLE;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente_main);

        ButterKnife.bind(this);
        message.setVisibility(View.INVISIBLE);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                // We need to ask the user to grant permission. We've offloaded that to a different activity for clarity.
            Log.println(Log.ASSERT,"BLE","BUSCANDO PERMISOS");
            startActivity(new Intent(this, PermissionCheckActivity.class));
        }
        controllerBLE=new ControllerBLE(this,new ActivityProvider(this));
        settingsrequest();
        activarBluetooth();
        FragmentManagerPatient fragmentManagerPatient =new FragmentManagerPatient(getSupportFragmentManager(),this);

        viewPager.setAdapter(fragmentManagerPatient);
        tabLayout.setupWithViewPager(viewPager);

        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void activarBluetooth() {
        LocationRequest request = LocationRequest.create();
        request.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(request);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        Intent bleEnable = controllerBLE.verificacionBluetooth();
        if (bleEnable != null)
            startActivityForResult(bleEnable, controllerBLE.REQUEST_ENABLE_BT);

    }
    public void settingsrequest()
    {

       GoogleApiClient googleApiClient = new GoogleApiClient.Builder(PacienteMain.this)
                .addApi(LocationServices.API)
                .build();
        googleApiClient.connect();
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_LOW_POWER);
        locationRequest.setInterval(30 * 1000);
        locationRequest.setFastestInterval(5 * 1000);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        builder.setAlwaysShow(true); //this is the key ingredient

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(googleApiClient, builder.build());
        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {
            @Override
            public void onResult(LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can initialize location
                        // requests here.
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied. But could be fixed by showing the user
                        // a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(PacienteMain.this, REQUEST_CHECK_SETTINGS);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way to fix the
                        // settings so we won't show the dialog.
                        break;
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
// Check for the integer request code originally supplied to startResolutionForResult().
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        //startLocationUpdates();
                        break;
                    case Activity.RESULT_CANCELED:
                        settingsrequest();//keep asking if imp or do whatever
                        break;
                }
                break;
        }
        if (requestCode == controllerBLE.REQUEST_ENABLE_BT && resultCode == Activity.RESULT_CANCELED) {
            activarBluetooth();
            return;
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.close) {
            startActivity(new Intent(this,LoginActivity.class));
            // Handle the camera action
           // this.startActivity(new Intent());
        } else if (id == R.id.restart) {
            MuestraProvider.initMuestra();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @OnClick(R.id.fab_message)
    public void OnClickMessage(){
        /*messageDialogFragment = MessagePatientDialogFragment.newInstance(getString(R.string.enviar_mensaje));
        messageDialogFragment.show(getSupportFragmentManager(),"fragment_edit_name");*/
    }
    private void scanearDispositivo(final boolean enable) {}

}
