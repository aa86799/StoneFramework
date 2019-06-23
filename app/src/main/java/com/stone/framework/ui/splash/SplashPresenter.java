package com.stone.framework.ui.splash;

import com.stone.framework.App;
import com.stone.framework.R;
import com.stone.framework.config.api.ApiHelper;
import com.stone.framework.util.RxJavaUtil;

/**
 * Created by cdy on 2017/12/13.
 */

public class SplashPresenter extends SplashContact.Presenter {

    @Override
    void loadAdvData() {
//        ApiHelper.splash()
//                .compose(RxJavaUtil.schLife(mView))
//                .compose(RxJavaUtil.<SplashEntity>dataObj())
//                .subscribe(result -> {
//                    if (result.isSuccess()) {
//                        mView.showAdvImg(result.getData());
//                    }
//                }, error -> {
//                    error.printStackTrace();
//                    mView.showMsg(App.getAppContext().getString(R.string.system_error));
//                    mView.onError();
//                });

    }
}
