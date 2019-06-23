package com.stone.framework.ui.splash

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.blankj.utilcode.util.LogUtils
import com.stone.framework.R
import com.stone.framework.config.ARouterConfig
import com.stone.framework.frame.BaseActivity
import com.stone.framework.gilde.GlideApp
import com.stone.framework.gilde.ImageUtil
import com.stone.framework.ui.main.ActivityMain
import com.stone.framework.util.RxJavaUtil
import com.tbruyelle.rxpermissions2.Permission
import com.tbruyelle.rxpermissions2.RxPermissions

/**
 * desc   : splash
 * author : stone
 * email  : aa86799@163.com
 * time   : 2019-06-23 09:32
 */
class SplashActivity : BaseActivity<SplashPresenter>(), SplashContact.View {

    @JvmField
    @BindView(R.id.activity_splash_iv)
    var mImageView: ImageView? = null

    @JvmField
    @BindView(R.id.activity_splash_tv)
    var mTextView: TextView? = null

    override fun onError() {
        //有异常，直接进首页
        mImageView!!.postDelayed({
            val intent = Intent(this@SplashActivity, ActivityMain::class.java)
            startActivity(intent)
            finish()
        }, 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (!isTaskRoot) {
            finish()
            return
        }
    }

    override fun getLayoutId(): Int {
        return R.layout.activity_splash
    }

    override fun init() {
        initBaiDuSdk()

        mPresenter!!.loadAdvData()

        showImage(); //仅本地的，若有网络则不要了
    }

    fun initBaiDuSdk() {
        val rxPermissions = RxPermissions(this)

        //一个或多个权限，全部成功才成功；
        rxPermissions.request(Manifest.permission.READ_PHONE_STATE)
            .compose(RxJavaUtil.rxLife(this))
            .subscribe({ permission ->
                if (permission!!) {
                    showMsg("用户给权限啦")
                    //                    SDKInitializer.initialize(getApplicationContext());
                    //                    SDKInitializer.setCoordType(CoordType.GCJ02);
                } else {
                    showMsg("用户不给权限")
                }

            }, { error ->

            })

        //一个或多个权限，分别单独处理
        rxPermissions.requestEach(Manifest.permission.READ_PHONE_STATE)
            .compose<Permission>(RxJavaUtil.rxLife<Permission>(this))
            .subscribe { permission ->
                if (permission.name.equals(Manifest.permission.READ_EXTERNAL_STORAGE, ignoreCase = true)) {
                    if (permission.granted) {//同意后调用
                        LogUtils.e("checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + true)
                    } else if (permission.shouldShowRequestPermissionRationale) {//禁止，但没有选择“以后不再询问”，以后申请权限，会继续弹出提示
                        LogUtils.e("checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-shouldShowRequestPermissionRationale:" + false)
                    } else {//禁止，但选择“以后不再询问”，以后申请权限，不会继续弹出提示
                        LogUtils.e("checkPermissionRequestEach--:" + "-READ_EXTERNAL_STORAGE-:" + false)
                    }
                }
            }

        //        rxPermissions.isGranted()  //返回 boolean： 某个权限是给授权
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

    }

    //    @Override
    //    public void showAdvImg(SplashEntity entity) {
    //        mImageView.postDelayed(() -> showImage(entity), 500);
    //    }

    //    private void showImage(SplashEntity entity) {
    //        ImageUtil.loadBackground(this, entity.getImgUrl(), mImageView, () -> {
    //        }, () -> {
    //            mTextView.setVisibility(View.VISIBLE);
    //            mTextView.setClickable(false);
    //            mTextView.setEnabled(false);
    //            mTextView.setTextColor(getResources().getColor(R.color.gray_70));
    //            mTextView.setOnClickListener(v-> {
    //                jumpToMain();
    //            });
    //            mTextView.setText("");
    //            new CountDownTimer(4800, 800) {
    //                @Override //millisUntilFinished 到完成任务的剩余时间的毫秒值
    //                public void onTick(long millisUntilFinished) {
    //                    if (isDestroyed() || isFinishing()) //防连续快速点击
    //                        return;
    //                    if (millisUntilFinished >= 3200) {
    ////                        mTextView.setText(String.format("%ds", millisUntilFinished / 1000));
    //                    } else {
    //                        mTextView.setTextColor(getResources().getColor(R.color.black));
    //                        mTextView.setBackgroundColor(getResources().getColor(R.color.a0dfdfdf));
    //                        mTextView.setClickable(true);
    //                        mTextView.setEnabled(true);
    //                        mTextView.setText(millisUntilFinished / 1000 + "");
    //                    }
    //                }
    //
    //                @Override
    //                public void onFinish() {
    //                    if (!isDestroyed() && !isFinishing()) {
    //                        mTextView.setText("0");
    ////                      mTextView.setVisibility(View.INVISIBLE);
    //                        jumpToMain();
    //                    }
    //                }
    //            }.start();
    //        }, () -> {
    //            //onLoadClear
    //        }, () -> {
    //            //onLoadFailed
    //        });
    //
    //    }

    private fun showImage() {
        ImageUtil.loadBackground(this, R.mipmap.ic_launcher, mImageView, {}, {
            mTextView?.visibility = View.VISIBLE
            mTextView?.isClickable = false
            mTextView?.isEnabled = false
            mTextView?.setTextColor(resources.getColor(R.color.gray_70))
            mTextView?.setOnClickListener {
                jumpToMain()
            }
            mTextView?.text = ""

            object : CountDownTimer(4800L, 800L) {
                override fun onTick(millisUntilFinished: Long) {
                    if (isDestroyed() || isFinishing()) //防连续快速点击
                        return;
                    if (millisUntilFinished >= 3200) {
                        //                        mTextView.setText(String.format("%ds", millisUntilFinished / 1000))
                    } else {
                        mTextView?.setTextColor(getResources().getColor(R.color.black))
                        mTextView?.setBackgroundColor(getResources().getColor(R.color.gray_a0))
                        mTextView?.isClickable = true
                        mTextView?.isEnabled = true
                        mTextView?.text = (millisUntilFinished / 1000).toString()
                    }
                }

                override fun onFinish() {
                    if (!isDestroyed() && !isFinishing()) {
                        mTextView?.setText("0")
                        //mTextView.setVisibility(View.INVISIBLE);
                        jumpToMain()
                    }
                }
            }.start()
        }, {
            //onLoadClear
        }, {
            //onLoadFailed
        })

    }

    private fun jumpToMain() {
        startActivity(Intent(this, ActivityMain::class.java))
        GlideApp.with(this).clear(mImageView!!)
        finish()
    }

}
