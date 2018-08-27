package ponny.org.monitora.views.paciente.manager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import ponny.org.monitora.R;
import ponny.org.monitora.views.paciente.fragments.HomePacienteFragment;
import ponny.org.monitora.views.common.fragments.InboxFragmentPatient;
import ponny.org.monitora.views.paciente.fragments.TomaFragment;

public class FragmentManagerPatient extends FragmentPagerAdapter {
    private Context context;

    public FragmentManagerPatient(android.support.v4.app.FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomePacienteFragment();
            case 1:
                return new TomaFragment();
            case 2:
                return new InboxFragmentPatient();
            default:
                return new InboxFragmentPatient();
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
                //    drawable = context.getResources().getDrawable(R.drawable.home);
                title = context.getString(R.string.dash);
                break;
            case 2:
                //     drawable = context.getResources().getDrawable(R.drawable.home);
                title = context.getString(R.string.inbox);
                break;
            default:
                //  drawable = context.getResources().getDrawable(R.drawable.home);
                title = null;
                break;
        }
        return title;

    }

}
