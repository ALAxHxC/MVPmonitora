package ponny.org.monitora.views.common.listas.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;

public class ItemToma extends RecyclerView.ViewHolder {
    public final View mView;
    public final TextView fecha;
    public final TextView spo2;
    public final TextView pulso;

    public Muestra mItem;

    public ItemToma(View view) {
        super(view);
        mView = view;
        fecha = (TextView) view.findViewById(R.id.txt_fecha_val);
        spo2 = (TextView) view.findViewById(R.id.txt_spo2_val);
        pulso = (TextView) view.findViewById(R.id.txt_pulso_val);

    }

    @Override
    public String toString() {
        return super.toString() + " '" + fecha.getText() + "'";
    }
}
