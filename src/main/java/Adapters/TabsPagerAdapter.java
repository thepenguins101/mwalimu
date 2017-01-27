package Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kevoh.thepenguins.CollegesFragment;
import com.example.kevoh.thepenguins.ProgrammesFragment;

/**
 * Created by KeVoH on 1/25/2017.
 */
public class TabsPagerAdapter extends FragmentStatePagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position)

        {
            case 0:
            CollegesFragment fragment = new CollegesFragment();
                return fragment;
            case 1:
                ProgrammesFragment fragment1 = new ProgrammesFragment();
                return fragment1;


        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
