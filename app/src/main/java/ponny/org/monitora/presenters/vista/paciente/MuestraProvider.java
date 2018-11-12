package ponny.org.monitora.presenters.vista.paciente;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observer;
import java.util.Vector;

import ponny.org.monitora.R;
import ponny.org.monitora.models.bluetooth.BluetoothLeService;
import ponny.org.monitora.models.monitora.modelo.UserDataPaciente;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.models.monitora.servicios.ApiMonitora;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraLogin;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraMuestras;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.bluetooth.ControllerBLE;
import ponny.org.monitora.presenters.vista.LoginProvider;
import ponny.org.monitora.presenters.vista.paciente.DialogProviderPaciente;

public class MuestraProvider {
    private ControllerBLE controllerBLE;
    private Context context;
    private ServiceConnection serviceConnection;
    private ActivityProvider activityProvider;

    private TextView txt_spo2,txt_pulse,txt_pi;
    private static Muestra muestra;
    private int contador;
    private ApiMonitoraMuestras apiMonitoraMuestras;
    public MuestraProvider(Activity activity){
        this.context=activity;
        this.controllerBLE=new ControllerBLE(activity,null);
        activityProvider=new ActivityProvider(activity);
        apiMonitoraMuestras=new ApiMonitoraMuestras(activity);

        ////Log.println(Log.ASSERT,"BLE","Datos medicos "+LoginProvider.getLogin().getUserObject().getUserData().getIdMedic());
        contador=0;
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
    public void volverAlInicio(){
        activityProvider.goPacienteMain(LoginProvider.getLogin());
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
        enviarMuestra();

    }
    public void getOximetria(byte[] bytes){
        if (bytes[0] == -128) {
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
    }
    private void enviarMuestra(){
        if(contador>=context.getResources().getInteger(R.integer.valores_validos)){
            Toast.makeText(context,R.string.datos_enviados,Toast.LENGTH_SHORT).show();
            activityProvider.goPacienteMain(LoginProvider.getLogin());

        }

    }
    public static void initMuestra(){
        muestra=new Muestra(LoginProvider.getLogin().getUserObject().getUserData().get_id(),((UserDataPaciente)LoginProvider.getLogin().getUserObject().getUserData()).getIdMedic());
        muestra.getData().getOximeter().setSpo2(0);
        muestra.getData().getOximeter().setPulse(0);
        muestra.getData().getOximeter().setPi(0);
    }
    public static Muestra getMuestra(){
        try {
            if(muestra==null){
                initMuestra();
            }
            return muestra;
        }catch (NullPointerException ex){
           // ex.printStackTrace();
            Log.println(Log.ASSERT,"MUESTRA",ex.toString());
            muestra=new Muestra();
            return muestra;
        }
    }
    public List<Muestra> getMuestras(String id){
        try {
            Muestra[] muestrasobjeto=apiMonitoraMuestras.getMuestras(id);
            List<Muestra> muestras= Arrays.asList(muestrasobjeto);
           return muestras;
        } catch (IOException e) {
            Log.println(Log.ASSERT,"API",e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
