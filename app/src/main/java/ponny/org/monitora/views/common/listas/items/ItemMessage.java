package ponny.org.monitora.views.common.listas.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ponny.org.monitora.R;

public class ItemMessage extends RecyclerView.ViewHolder {
    public TextView asunto;
    public TextView from;

    public ItemMessage(View itemView) {
        super(itemView);
        this.from = itemView.findViewById(R.id.txt_fecha_val);
        this.asunto = itemView.findViewById(R.id.txt_asunto_val);
    }
}
