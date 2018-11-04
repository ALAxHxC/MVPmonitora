package ponny.org.monitora.views.common.listas.items;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import ponny.org.monitora.R;

public class ItemInbox extends RecyclerView.ViewHolder {
    public TextView txt_create;
    public TextView txt_name;
    public TextView txt_message;

    public ItemInbox(View itemView) {
        super(itemView);
        this.txt_create = itemView.findViewById(R.id.txt_create);
        this.txt_name = itemView.findViewById(R.id.txt_name);
        this.txt_message = itemView.findViewById(R.id.txt_message);

    }
}