package ponny.org.monitora.views.medico.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ponny.org.monitora.R;
import ponny.org.monitora.views.paciente.fragments.HomePacienteFragment;

public class FragmentManagerMedic extends FragmentPagerAdapter {
    private Context context;

    public FragmentManagerMedic(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new PatientFragment();
            case 1:
                return  new ActivityFragment();
            case 2:
                return new MessagesFragment();
            default:
                return new PatientFragment();
            //  return new RegistrosPacienteFragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        // Generate title based on item position
        //   Drawable drawable;
        String title;
        switch (position) {
            case 0:
                title = context.getString(R.string.home);
                //   drawable = context.getResources().getDrawable(R.drawable.home);
                break;
            case 1:
                title = context.getString(R.string.activity);
                //   drawable = context.getResources().getDrawable(R.drawable.home);
                break;
            case 2:
                title = context.getString(R.string.messages);
                //   drawable = context.getResources().getDrawable(R.drawable.home);
                break;
            default:
                //  drawable = context.getResources().getDrawable(R.drawable.home);
                title = null;
                break;
        }
        return title;

    }
}
