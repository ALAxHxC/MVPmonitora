package ponny.org.monitora.views.common.listas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.inbox.Inbox;
import ponny.org.monitora.models.monitora.modelo.inbox.Message;
import ponny.org.monitora.views.common.OnListFragmentInteractionListener;
import ponny.org.monitora.views.common.listas.items.ItemInbox;
import ponny.org.monitora.views.common.listas.items.ItemMessage;

public class InboxRecyclerViewAdapter extends RecyclerView.Adapter<ItemInbox> {


    private final List<Message> mValues;


    public InboxRecyclerViewAdapter(List<Message> mValues) {
        this.mValues = mValues;
    }

    @Override
    public ItemInbox onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.inbox_item, parent, false);
        return new ItemInbox(view);
    }

    @Override
    public void onBindViewHolder(ItemInbox holder, final int position) {
        holder.txt_name.setText(mValues.get(position).getName());
        holder.txt_message.setText(mValues.get(position).getMessage());
        holder.txt_create.setText(mValues.get(position).getCreateAt());


    }

    @Override
    public int getItemCount() {
        return this.mValues.size();
    }
}
