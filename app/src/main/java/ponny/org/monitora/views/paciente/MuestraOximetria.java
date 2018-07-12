package ponny.org.monitora.views.paciente;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import ponny.org.monitora.R;
import ponny.org.monitora.models.bluetooth.BluetoothLeService;
import ponny.org.monitora.presenters.MuestraProvider;
import ponny.org.monitora.presenters.bluetooth.ControllerBLE;

public class MuestraOximetria extends AppCompatActivity {
    @BindView(R.id.txt_pulso_value)
    TextView pulso;
    @BindView(R.id.txt_spo2_value)
    TextView spo2;
    private String mac;
    private MuestraProvider muestraProvider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muestra_oximetria);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        muestraProvider=new MuestraProvider(this);
        muestraProvider.initView(spo2,pulso,null);
        muestraProvider.initMuestra();
        getExtraData();
        initService();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    private void getExtraData(){
         mac=getIntent().getExtras().getString(getString(R.string.address));
         Log.println(Log.ASSERT,"BLE",mac);
    }
    private void initService(){
        try {
            Intent gattServiceIntent = new Intent(this, BluetoothLeService.class);
          //  ServiceConnection serviceConnection=
          //Log.println(Log.ASSERT,"BLE",serviceConnection.toString());
            bindService(gattServiceIntent,muestraProvider.createServiceBLE(mac, this), BIND_AUTO_CREATE);
        }catch (Exception ex){
            ex.printStackTrace();

        }
        }
    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(mGattUpdateReceiver, muestraProvider.makeGattUpdateIntentFilter());
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mGattUpdateReceiver);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
       unbindService(muestraProvider.getServiceConnection());
      // muestraProvider.
        //mBluetoothLeService = null;
    }
    @Override
    public void onBackPressed() {

    }

    /**
     *
     */
    private final BroadcastReceiver mGattUpdateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            final String action = intent.getAction();
            if (BluetoothLeService.ACTION_GATT_CONNECTED.equals(action)) {
                Log.println(Log.ASSERT, "BLE", "conectado");
                invalidateOptionsMenu();
            } else if (BluetoothLeService.ACTION_GATT_DISCONNECTED.equals(action)) {
                Log.println(Log.ASSERT, "BLE", "Desconectado");
            } else if (BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED.equals(action)) {
                Log.println(Log.ASSERT, "BLE", "Descubre");
                muestraProvider.displayServicesBLEOximetro();
            } else if (BluetoothLeService.ACTION_DATA_AVAILABLE.equals(action)) {
                try {//Limpia salid
                    byte[] bytes = intent.getExtras().getByteArray("data");
                    muestraProvider.tratarMuestraOximetria(bytes);
                } catch (NullPointerException ex) {
                    Log.println(Log.ASSERT, "BLE", ex.toString());
                }
            }
        }
    };

}