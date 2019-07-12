package com.stone.module.pet.util;

import com.blankj.utilcode.util.ToastUtils;
import com.stone.lib.common.mvp.BaseView;
import com.stone.lib.common.ui.base.BaseCompatFragment;
import com.stone.module.pet.bean.api.BeanResponse;
import com.stone.module.pet.ui.base.BaseActivity;
import com.stone.module.pet.ui.base.BaseDialogFragment;
import com.trello.rxlifecycle3.android.ActivityEvent;
import com.trello.rxlifecycle3.android.FragmentEvent;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * desc   :
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019/5/24 11:37
 */
public class RxJavaUtil {

    /**
     * 线程调度
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> schedule() {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 变换。
     *
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<BeanResponse, BeanResponse<T>> dataObj() {
        return upstream -> upstream.map((Function<BeanResponse, BeanResponse<T>>) result -> {
            if (result.getSuccess()) {
                return result;
            } else {//失败，弹消息
                ToastUtils.showShort(result.getMessage());
                return result;
            }
        });
    }

    /**
     * rxjava生命周期：绑定 到 某一事件，用于解除订阅
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> rxLife(BaseView view) {
        if (view instanceof BaseCompatFragment) {
            return ((BaseCompatFragment) view).bindUntilEvent(FragmentEvent.DESTROY);
        } else if (view instanceof BaseActivity) {
            return ((BaseActivity) view).bindUntilEvent(ActivityEvent.DESTROY);
        } else/* if (view instanceof BaseDialogFragment) */ {
            return ((BaseDialogFragment) view).bindUntilEvent(FragmentEvent.DESTROY);
        }
    }

    /**
     * 线程调度  加 rxjava生命周期
     *
     * @param view
     * @param <T>
     * @return
     */
    public static <T> ObservableTransformer<T, T> schLife(BaseView view) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(rxLife(view));
    }

}
