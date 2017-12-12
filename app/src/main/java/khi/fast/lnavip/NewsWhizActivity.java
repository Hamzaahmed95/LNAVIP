package khi.fast.lnavip;

import android.support.v4.app.Fragment;

/**
 * Created by Hamza Ahmed on 12-Dec-17.
 */

public class NewsWhizActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return NewsWhiz.newInstance() ;
    }
}
