package ponny.org.monitora.presenters.vista.paciente;

import android.app.Activity;
import android.app.Dialog;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import java.io.IOException;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.models.monitora.servicios.ApiMonitoraMuestras;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.bluetooth.ControllerBLE;

public class HomeProviderPaciente {
    private ApiMonitoraMuestras apiMonitora;
    private ControllerBLE controllerBLE;
    private Activity activity;
    private DialogProviderPaciente dialogProvider;
    private ActivityProvider activityProvider;

    public HomeProviderPaciente(FragmentActivity activity) {
        this.dialogProvider = new DialogProviderPaciente(activity);
        this.activityProvider = new ActivityProvider(activity);
        this.apiMonitora = new ApiMonitoraMuestras(activity);
        controllerBLE = new ControllerBLE(activity, activityProvider);
    }

    public void scanBLE() {
        Log.println(Log.ASSERT, this.getClass().getName(), "Inicia sscan");
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            controllerBLE.getAdapter().startLeScan(controllerBLE.mLeScanCallback);
            //controlerBLE.getAdapter().startLeScan(mLeScanCallback);
        } else {
            controllerBLE.getAdapter().getBluetoothLeScanner().startScan(controllerBLE.scanCallback());
        }
    }

    public void apagarBLE() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP) {
            controllerBLE.getAdapter().stopLeScan(controllerBLE.mLeScanCallback);
        } else {
            controllerBLE.getAdapter().getBluetoothLeScanner().stopScan(controllerBLE.scanCallback());
        }
    }

    public void subirMuestra() {
        try {
            if (Muestra.possibleSend(MuestraProvider.getMuestra().getData())) {
                enviarMuestra(dialogProvider.recogerMuestra());
            } else
                dialogProvider.showToast(R.string.no_hay_datos);

        } catch (Exception e) {

            Log.println(Log.ASSERT, "API", e.getMessage());
            e.printStackTrace();
        }
    }

    public void enviarMuestra(final Dialog dialog) {
        dialogProvider.getSave().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MuestraProvider.getMuestra().setDescription(dialogProvider.getTxt_description().getText().toString() + "");
                try {

                    dialog.setContentView(R.layout.dialog_wait);
                  //  Dialog waiting = dialogProvider.createDialog(R.layout.dialog_wait);

                    boolean resultado = apiMonitora.sendMuestra(MuestraProvider.getMuestra());
                    dialog.dismiss();
                    comunicarResultado(resultado);
                } catch (IOException e) {
                    Log.println(Log.ASSERT, "API", e.getMessage());
                    e.printStackTrace();
                    dialog.dismiss();

                }
            }
        });
    }

    private void comunicarResultado(boolean resultado) {
        if (resultado) {
            MuestraProvider.initMuestra();
            dialogProvider.showToast(R.string.registro_guardado);
        } else
            dialogProvider.showToast(R.string.registro_no_guardado);

    }

}
