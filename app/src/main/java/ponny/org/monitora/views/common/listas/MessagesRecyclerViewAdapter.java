package ponny.org.monitora.views.common.listas;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.listas.items.ItemMessage;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<ItemMessage> {


    private final List<Message> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MessagesRecyclerViewAdapter(List<Message> mValues, OnListFragmentInteractionListener mListener) {
        this.mValues = mValues;
        this.mListener = mListener;
    }

    @Override
    public ItemMessage onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_message, parent, false);
        return new ItemMessage(view);
    }

    @Override
    public void onBindViewHolder(ItemMessage holder, int position) {
        holder.from.setText(mValues.get(position).getCreateAt());
        holder.asunto.setText(mValues.get(position).getSubject());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction("Prueba");
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mValues.size();
    }
}
