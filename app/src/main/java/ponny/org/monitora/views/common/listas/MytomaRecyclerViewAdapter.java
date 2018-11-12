package ponny.org.monitora.views.common.listas;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.muestra.Muestra;
import ponny.org.monitora.views.common.listas.items.ItemToma;
import ponny.org.monitora.views.common.permissions.OnSelectMuestra;
import ponny.org.monitora.views.paciente.fragments.TomaFragment.OnListFragmentInteractionListener;


import java.util.List;

import static ponny.org.monitora.utils.Utils.getPrettyDateFromString;

public class MytomaRecyclerViewAdapter extends RecyclerView.Adapter<ItemToma> {

    private final List<Muestra> mValues;
    private final OnSelectMuestra mListener;

    public MytomaRecyclerViewAdapter(List<Muestra> items, OnSelectMuestra listener) {
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
        String create = getPrettyDateFromString(mValues.get(position).getCreateAt());
        holder.fecha.setText(create);
        holder.pulso.setText(mValues.get(position).getData().getOximeter().getPulse() + "");
        holder.spo2.setText(mValues.get(position).getData().getOximeter().getSpo2() + "");

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.sendMuestra(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mValues.size();
    }
}
