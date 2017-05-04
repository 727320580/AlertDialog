package com.xika.myalertdialog;

import android.content.Context;
import android.content.DialogInterface;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import java.util.HashMap;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/3
 * Vwesion 1.0
 * Dsscription:   自定义AlertDialog的控制类
 */

public class AlertController {

    private AlertDialogHelper mAlertHelper;

    private MyAlertDialog myAlertDialog;

    private Window mWindow;

    public AlertController(MyAlertDialog myAlertDialog, Window window) {
        this.myAlertDialog = myAlertDialog;
        this.mWindow = window;
    }

    public MyAlertDialog getDialog() {
        return myAlertDialog;
    }

    // 获取窗口用来绘制 宽高 和动画
    public Window getWindow() {
        return mWindow;
    }

    // 获取helper内容
    public void setAlertHelper(AlertDialogHelper mAlerthelper){
        this.mAlertHelper=mAlerthelper;
    }

    // 设置View的内容
    public void setText(int viewId, CharSequence text) {
        mAlertHelper.setText(viewId,text);
    }

    // 设置View的点击
    public void setClick(int viewId, View.OnClickListener listenner) {
        mAlertHelper.setClick(viewId,listenner);
    }

    // 获取布局的空间
    public <T extends View> T getView(int viewId) {
       return mAlertHelper.getView(viewId);
    }

    public static class AlertParams {
        public Context mContext;
        // Dialog的主题参数
        public int themeResId;
        // Dialog点击空白是否消失(true 默认消失)
        public boolean mCancelable = true;
        // Dialog的Cancel 监听
        public DialogInterface.OnCancelListener mOnCancelListener;
        // Dialog的dissmiss 监听
        public DialogInterface.OnDismissListener mOnDismissListener;
        // Dialong的Kye 监听
        public DialogInterface.OnKeyListener mOnKeyListener;
        // Dialog的布局
        public View mView;
        // Dialog的布局Id
        public int layoutResId;
        // 存入Dialog需要修改的View的内容
        public SparseArray<CharSequence> mTextArray = new SparseArray<>();
        // 存放Dialog需要设置的View的点击事件
        public SparseArray<View.OnClickListener> mClickArray = new SparseArray<>();
        // Dialog默认宽自适应
        public int with = ViewGroup.LayoutParams.WRAP_CONTENT;
        // Dialog默认的高未自适应
        public int height = ViewGroup.LayoutParams.WRAP_CONTENT;
        // Dialog的动画效果
        public int mAnimatiom = 0;
        // Dialog的默认位置
        public int gravity = Gravity.CENTER;


        public AlertParams(Context context, int themeResId) {
            this.mContext = context;
            this.themeResId = themeResId;
        }

        public void apply(AlertController mAlert) {

            AlertDialogHelper mAlertHelper = null;

            // 通过view 设置布局
            if (mView != null) {
                mAlertHelper = new AlertDialogHelper();
                mAlertHelper.setContentView(mView);
            }

            // 通过Id 设置布局
            if (layoutResId != 0) {
                mAlertHelper = new AlertDialogHelper(mContext, layoutResId);
            }

            // 判断是否传入Dialog的布局
            if (mAlertHelper == null) {
                throw new IllegalArgumentException("请设置AlertDialog的布局");
            }

            // 获取dialog
            mAlert.getDialog().setContentView(mAlertHelper.getContentView());

            // 设置helper
            mAlert.setAlertHelper(mAlertHelper);


            // 设置DialogView的内容
            int mTextArraySize = mTextArray.size();
            for (int i = 0; i < mTextArraySize; i++) {
                mAlert.setText(mTextArray.keyAt(i), mTextArray.valueAt(i));
            }

            // 设置DialogView的点击
            int mClickArraySize = mClickArray.size();
            for (int i = 0; i < mClickArraySize; i++) {
                mAlert.setClick(mClickArray.keyAt(i), mClickArray.valueAt(i));
            }

            // 设置动画 和dialog 的位置
            Window window = mAlert.getWindow();
            window.setGravity(gravity);
            if (mAnimatiom != 0) {
                window.setWindowAnimations(mAnimatiom);
            }
            // 设置Dialog 的宽高;
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            // 继承的Dialog的sytle 默认 设计了Padding 为10dp
            window.getDecorView().setPadding(0, 0, 0, 0);
            layoutParams.width = with;
            layoutParams.height = height;
            window.setAttributes(layoutParams);
        }
    }

}
