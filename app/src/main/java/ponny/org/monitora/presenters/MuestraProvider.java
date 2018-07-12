package ponny.org.monitora.presenters;

import android.app.Activity;
import android.bluetooth.BluetoothGattService;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.bluetooth.BluetoothLeService;
import ponny.org.monitora.models.monitora.ApiMonitora;
import ponny.org.monitora.models.monitora.modelo.LoginMonitora;
import ponny.org.monitora.models.monitora.modelo.UserDataPaciente;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.presenters.bluetooth.ControllerBLE;
import ponny.org.monitora.presenters.vista.LoginProvider;

public class MuestraProvider {
    private ControllerBLE controllerBLE;
    private Context context;
    private ServiceConnection serviceConnection;
    private TextView txt_spo2,txt_pulse,txt_pi;
    private static Muestra muestra;
    private int contador;
    public MuestraProvider(Activity activity){
        this.context=activity;
        this.controllerBLE=new ControllerBLE(activity,null);
        ////Log.println(Log.ASSERT,"BLE","Datos medicos "+LoginProvider.getLogin().getUserObject().getUserData().getIdMedic());
        contador=0;
    }
    public void initMuestra(){
        muestra=new Muestra(LoginProvider.getLogin().getUserObject().get_id(),((UserDataPaciente)LoginProvider.getLogin().getUserObject().getUserData()).getIdMedic());
    }
    public void initView(TextView spo2,TextView pulse, TextView pi){
        this.txt_spo2=spo2;
        this.txt_pulse=pulse;
    }

    public IntentFilter makeGattUpdateIntentFilter() {
        final IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_CONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_DISCONNECTED);
        intentFilter.addAction(BluetoothLeService.ACTION_GATT_SERVICES_DISCOVERED);
        intentFilter.addAction(BluetoothLeService.ACTION_DATA_AVAILABLE);
        return intentFilter;
    }
    public void displayServicesBLEOximetro(){
        controllerBLE.displayGattServicesService(controllerBLE.getmBluetoothLeService().getSupportedGattServices());
    }
   public ServiceConnection createServiceBLE(final String address, final Activity activity) throws Exception {
        try {
            this.serviceConnection = new ServiceConnection() {

                @Override
                public void onServiceConnected(ComponentName componentName, IBinder service) {
                    controllerBLE.setmBluetoothLeService(((BluetoothLeService.LocalBinder) service).getService());
                    if (!controllerBLE.getmBluetoothLeService().initialize()) {
                        activity.finish();
                        // finish();
                    }
                    Log.println(Log.ASSERT, "BLE", "Conecto");

                    controllerBLE.getmBluetoothLeService().connect(address);
                }

                @Override
                public void onServiceDisconnected(ComponentName componentName) {
                    controllerBLE.setmBluetoothLeService(null);
                }
            };
        }catch (Exception ex){
            throw(ex);
        }
      return this.serviceConnection;
   }

    public ServiceConnection getServiceConnection() {
        return serviceConnection;
    }
    public void tratarMuestraOximetria(byte[] bytes){
        getOximetria(bytes);

    }
    public void getOximetria(byte[] bytes){
        if (bytes[0] == -128) {
          //  txt_pulse.setText("0");
            //txt_spo2.setText("0");
            return;
        }
        muestra.getData().getOximeter().setPi((byte) bytes[3] & 0xFF);
        muestra.getData().getOximeter().setPulse((byte) bytes[1] & 0xFF);
        muestra.getData().getOximeter().setSpo2((byte) bytes[2] & 0xFF);
        txt_pulse.setText(""+muestra.getData().getOximeter().getPulse());
        txt_spo2.setText(""+muestra.getData().getOximeter().getSpo2());
        if(muestra.getData().getOximeter().datosValidos()){
            contador++;
        }
       if(contador>=context.getResources().getInteger(R.integer.valores_validos)){

       }

    }
}
