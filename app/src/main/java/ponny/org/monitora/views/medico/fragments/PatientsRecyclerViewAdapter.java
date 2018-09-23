package ponny.org.monitora.views.medico.fragments;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.pacientes.Entity;
import ponny.org.monitora.models.monitora.modelo.pacientes.ListPatients;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.listeners.FragmentPatientListener;

/**
 * {@link RecyclerView.Adapter} that can display a {@link } and makes a call to the
 * specified {@link FragmentPatientListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class PatientsRecyclerViewAdapter extends RecyclerView.Adapter<PatientsRecyclerViewAdapter.ViewHolder> {

    private final ListPatients mValues;
    private final FragmentPatientListener mListener;
    private final ActivityProvider activityProvider;
    public PatientsRecyclerViewAdapter(ListPatients listPatients, FragmentPatientListener listener, ActivityProvider activityProvider) {
        mValues = listPatients;
        Log.println(Log.ASSERT,"Lista de pacientes",listPatients.getEntity().size()+"");
        mListener = listener;
        this.activityProvider=activityProvider;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mItem = mValues.getEntity().get(position);
        holder.namesTxt.setText(mValues.getEntity().get(position).getFirstNames()+" "+mValues.getEntity().get(position).getLastNames());
        holder.documentTxt.setText(mValues.getEntity().get(position).getDocument().getIdentification());
        holder.profile.setLayerType(View.LAYER_TYPE_SOFTWARE,null);

        holder.profile.setImageResource(R.drawable.profile65);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activityProvider.goVistaPaciente(mValues.getEntity().get(position));
            }
        });
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.getEntity().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView documentTxt;
        public final TextView namesTxt;
        public final CircleImageView profile;
        public Entity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            documentTxt = (TextView) view.findViewById(R.id.textViewCC_list);
            namesTxt = (TextView) view.findViewById(R.id.textViewNames_list);
            profile = (CircleImageView)view.findViewById(R.id.imageProfilePaciente);
        }

        @Override
        public String toString()  {
            return super.toString() + " '" + documentTxt.getText() + "'";
        }
    }
}
