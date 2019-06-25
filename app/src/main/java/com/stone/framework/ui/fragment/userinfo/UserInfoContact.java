package com.stone.framework.ui.fragment.userinfo;

import com.stone.lib.common.mvp.BasePresenter;
import com.stone.lib.common.mvp.BaseView;

public interface UserInfoContact {

    interface View extends BaseView {
//        void showAdvImg(SplashEntity entity);

        void onError();
    }
    abstract class Presenter extends BasePresenter<View> {
        abstract void loadAdvData();
    }
}
