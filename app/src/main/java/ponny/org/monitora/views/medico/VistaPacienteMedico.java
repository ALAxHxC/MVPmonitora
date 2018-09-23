package ponny.org.monitora.views.medico;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import java.util.List;

import ponny.org.monitora.R;
import ponny.org.monitora.models.monitora.modelo.pacientes.Entity;
import ponny.org.monitora.presenters.ActivityProvider;
import ponny.org.monitora.presenters.DialogProvider;
import ponny.org.monitora.presenters.vista.paciente.MuestraProvider;
import ponny.org.monitora.views.medico.dialogs.MessageDialogFragment;
import ponny.org.monitora.views.common.graphs.PulseChart;
import ponny.org.monitora.views.common.graphs.Spo2Chart;
import ponny.org.monitora.views.medico.fragments.pacientes.TomaFragment;

public class VistaPacienteMedico extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private ActivityProvider activityProvider;
    private Entity paciente;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private MuestraProvider muestraProvider;
    private List listaDeMuestras;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_paciente_medico);

        activityProvider = new ActivityProvider(this);
        paciente = activityProvider.getPaciente(getIntent());
        muestraProvider = new MuestraProvider(this);
        loadingMuestras();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                MessageDialogFragment messageDialogFragment = MessageDialogFragment.newInstance(paciente.getId());
                messageDialogFragment.show(fm, "fragment_edit_name");

              /*  Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });

    }

    private void loadingMuestras() {
        listaDeMuestras = muestraProvider.getMuestras(paciente.getId());
        Log.println(Log.ASSERT,"HTTP","Lista de muestras"+listaDeMuestras.size());
        if(listaDeMuestras.size()<=0){
            super.onBackPressed();
            DialogProvider.showStaticToast(getBaseContext(),R.string.no_hay_datos);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_vista_paciente_medico, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_vista_paciente_medico, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (listaDeMuestras.size() == 0)
                switch (position) {
                    case 0:
                        return TomaFragment.newInstance(listaDeMuestras);
                    case 1:
                        return Spo2Chart.newInstance(listaDeMuestras);
                    case 2:
                        return PulseChart.newInstance(listaDeMuestras);

                    default:
                        return PlaceholderFragment.newInstance(position + 1);
                }
            else {
                DialogProvider.showStaticToast(getBaseContext(), R.string.no_hay_datos);

                return TomaFragment.newInstance(listaDeMuestras);
            }
        }

        @Override
        public int getCount() {
            if (listaDeMuestras.size() < 1)
                // Show 3 total pages.
                return 3;
            else
                return 1;
        }
    }
}
