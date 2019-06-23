package com.stone.framework.ui.splash;

import com.stone.framework.frame.mvp.BasePresenter;
import com.stone.framework.frame.mvp.BaseView;

/**
 * Created by cdy on 2017/12/13.
 */

public interface SplashContact {

    interface View extends BaseView {
//        void showAdvImg(SplashEntity entity);

        void onError();
    }
    abstract class Presenter extends BasePresenter<View> {
        abstract void loadAdvData();
    }
}
