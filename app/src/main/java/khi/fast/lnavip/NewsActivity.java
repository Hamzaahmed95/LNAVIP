package khi.fast.lnavip;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 18-Aug-17.
 */

public class NewsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return NewsFragment.newInstance() ;
    }
}
