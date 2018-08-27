package ponny.org.monitora.views.common.listas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.views.common.listas.items.ItemToma;
import ponny.org.monitora.views.paciente.fragments.TomaFragment.OnListFragmentInteractionListener;


import java.util.List;

public class MytomaRecyclerViewAdapter extends RecyclerView.Adapter<ItemToma> {

    private final List<Muestra> mValues;
    private final OnListFragmentInteractionListener mListener;

    public MytomaRecyclerViewAdapter(List<Muestra> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ItemToma onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_toma, parent, false);

        return new ItemToma(view);
    }

    @Override
    public void onBindViewHolder(final ItemToma holder, int position) {
        holder.mItem = mValues.get(position);
        holder.fecha.setText(mValues.get(position).getCreateAt());
        holder.pulso.setText(mValues.get(position).getData().getOximeter().getPulse() + "");
        holder.spo2.setText(mValues.get(position).getData().getOximeter().getSpo2() + "");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mValues.size();
    }
}
