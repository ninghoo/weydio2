package com.ninghoo.beta17ma27.weydio2.WeiboDocument.Interface;

import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Interface.BaseView;
import com.ninghoo.beta17ma27.weydio2.WeiboDocument.Model.StatusEntity;

import java.util.List;

/**
 * Created by ningfu on 17-4-13.
 */

public interface HomeView extends BaseView
{
    void onSuccess(List<StatusEntity> list);
}
