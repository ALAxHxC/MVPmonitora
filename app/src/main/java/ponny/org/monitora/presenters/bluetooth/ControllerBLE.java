package ponny.org.monitora.presenters.bluetooth;

import android.app.Activity;
import android.app.Dialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.bluetooth.BluetoothLeService;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.DialogProvider;

public class ControllerBLE {
    public static final int REQUEST_ENABLE_BT = 1;
    private Context mContext;
    private BluetoothLeService mBluetoothLeService;
    private boolean conexion;
    private Activity activity;
    private ActivityProvider activityProvider;
    private DialogProvider dialogProvider;
    public ControllerBLE(Activity activity, ActivityProvider activityProvider) {
        this.mContext = activity.getBaseContext();
        this.dialogProvider=new DialogProvider(mContext);
        this.activityProvider=activityProvider;
        this.activity=activity;
        validarBluetooth();
        verificacionBluetooth();
    }
    public void validarBluetooth(){
        if (!mContext.getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) || (BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE) == null) {
            dialogProvider.finshApp(R.string.error_fatal, R.string.No_bluetooth);
        }

    }
    public Intent verificacionBluetooth() {
        if (!((BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter().isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            return enableBtIntent ;
        }
        return null;
    }

    public BluetoothAdapter getAdapter() {
        return ((BluetoothManager) mContext.getSystemService(Context.BLUETOOTH_SERVICE)).getAdapter();
    }
    public BluetoothLeService getmBluetoothLeService() {
        return mBluetoothLeService;
    }

    public void setmBluetoothLeService(BluetoothLeService mBluetoothLeService) {
        this.mBluetoothLeService = mBluetoothLeService;
    }
    public ScanCallback scanCallback() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return (new ScanCallback() {
                @Override
                public void onScanResult(int callbackType, ScanResult result) {
                    super.onScanResult(callbackType, result);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        if(result.getDevice().getName()==null){
                            //  result.getScanRecord().getServiceUuids()
                            return;
                        }
                        if (result.getDevice().getName().equalsIgnoreCase(mContext.getString(R.string.name_device))) {

                            activityProvider.goOximetria(result.getDevice().getAddress());
                            getAdapter().getBluetoothLeScanner().stopScan(scanCallback());
                            // conectarDevice(result.getDevice().getAddress(), result.getDevice().getName());
                        }
                    } else {
                        Log.println(Log.ASSERT, "BLE", "no se activa");
                    }
                }
            });
        } else {
            return null;
        }
    }

    public  BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            try {
                                if(device.getName()==null)
                                { //Log.println(Log.ASSERT, "BLE", device.getAddress());
                                    return;
                                }
                           //     Log.println(Log.ASSERT, "BLE", device.getName());

                                if (device.getName().equalsIgnoreCase(mContext.getString(R.string.name_device))) {
                                    Log.println(Log.ASSERT, "BLE", "Conectara");

                                    activityProvider.goOximetria(device.getAddress());
                                    getAdapter().stopLeScan(mLeScanCallback);
                                    //controlerBLE.conectarDevice(device.getAddress(), device.getName());
                                }
                            } catch (Exception ex) {
                                Log.println(Log.ASSERT,"BLE",ex.toString());
                                ex.printStackTrace();
                            }
                        }
                    });
                }
            };


    /**
     * Muestra lso servicios y perfiles del dispositivo
     *
     * @param gattServices
     */
    public void displayGattServicesService(List<BluetoothGattService> gattServices) {
        Log.println(Log.ASSERT, "BLE", "Servicios BLE");
        if (gattServices == null)
            return;
        for (BluetoothGattService gattService : gattServices) {
            String uuid = gattService.getUuid().toString();
            List<BluetoothGattCharacteristic> gattCharacteristics = gattService
                    .getCharacteristics();

            if (uuid.equals(this.activity.getString(R.string.servicio_oximetero))) {
                for (BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics) {
                    String uuid1 = gattCharacteristic.getUuid().toString();
                    if (uuid1.equals(this.activity.getString(R.string.caracteristica_oximetro))) {
                        getmBluetoothLeService().setCharacteristicNotification(
                                gattCharacteristic, true);

                    }
                }
            }
        }
    }

}
