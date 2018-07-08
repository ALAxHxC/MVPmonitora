package ponny.org.monitora.models.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;

public class ControllerBLE {
    public static final int REQUEST_ENABLE_BT = 1;
    private Context mContext;
    private BluetoothAdapter.LeScanCallback mLeScanCallback;
    private BluetoothLeService mBluetoothLeService;
    private boolean conexion;

}
