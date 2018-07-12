package ponny.org.monitora.presenters.vista.paciente;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import javax.crypto.Cipher;

import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.presenters.bluetooth.ControllerBLE;

public class HomeProvider {
    private Context context;
    private ControllerBLE controllerBLE;
    private Activity activity;
    private DialogProvider dialogProvider;
    private ActivityProvider activityProvider;
    public HomeProvider( FragmentActivity activity) {
        this.context = activity.getBaseContext();
        this.dialogProvider=new DialogProvider(activity);
        this.activityProvider=new ActivityProvider(activity);
        controllerBLE=new ControllerBLE(activity,activityProvider);
    }
    public void scanBLE(){
        Log.println(Log.ASSERT,this.getClass().getName(),"Inicia sscan");
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            controllerBLE.getAdapter().startLeScan(controllerBLE.mLeScanCallback);
            //controlerBLE.getAdapter().startLeScan(mLeScanCallback);
        }else{
            controllerBLE.getAdapter().getBluetoothLeScanner().startScan(controllerBLE.scanCallback());
        }
    }
    public  void apagarBLE(){
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            controllerBLE.getAdapter().stopLeScan(controllerBLE.mLeScanCallback);
        }else{
            controllerBLE.getAdapter().getBluetoothLeScanner().stopScan(controllerBLE.scanCallback());
        }
    }
}
