package khi.fast.lnavip;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 06-May-18.
 */

public class SportsNewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return SportsNews.newInstance() ;
    }
}

