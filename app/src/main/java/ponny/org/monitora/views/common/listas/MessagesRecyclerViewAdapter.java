package ponny.org.monitora.views.common.listas;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.mensajes.Message;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.listas.items.ItemMessage;

import static ponny.org.monitora.utils.Utils.getPrettyDateFromString;

public class MessagesRecyclerViewAdapter extends RecyclerView.Adapter<ItemMessage> {


    private final List<Inbox> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MessagesRecyclerViewAdapter(List<Inbox> mValues, OnListFragmentInteractionListener mListener) {
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
    public void onBindViewHolder(ItemMessage holder, final int position) {
        String create = getPrettyDateFromString(mValues.get(position).getCreateAt());
        holder.from.setText(create);
        holder.asunto.setText(mValues.get(position).getSubject());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onFragmentInteraction(mValues.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mValues.size();
    }
}
