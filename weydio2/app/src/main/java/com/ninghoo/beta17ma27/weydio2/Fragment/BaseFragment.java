package com.ninghoo.beta17ma27.weydio2.Fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.ninghoo.beta17ma27.weydio2.R;

/**
 * Created by ningfu on 17-4-13.
 */

public class BaseFragment extends Fragment {
    @Override
    public void startActivity(Intent intent) {
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.anmi_in_right_left, R.anim.anmi_out_right_left);

    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode) {
        getActivity().startActivityForResult(intent,requestCode);
        getActivity().overridePendingTransition(R.anim.anmi_in_right_left, R.anim.anmi_out_right_left);

    }
}