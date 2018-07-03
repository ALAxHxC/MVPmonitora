package ponny.org.monitora.views.paciente.manager;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import ponny.org.monitora.R;
import ponny.org.monitora.views.paciente.fragments.HomePacienteFragment;
import ponny.org.monitora.views.paciente.fragments.InboxFragmentPatient;
import ponny.org.monitora.views.paciente.fragments.TomaFragment;

public class FragmentManagerPatient extends FragmentPagerAdapter {
    private Context context;

    public FragmentManagerPatient(android.support.v4.app.FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
    switch (position){
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
            /*
            //SpannableStringBuilder sb = new SpannableStringBuilder(" " + title); // space added before text for convenience

            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(drawable, ImageSpan.ALIGN_BASELINE);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            return sb;*/
            return title;

    }

}
