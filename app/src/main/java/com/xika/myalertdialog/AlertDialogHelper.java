package com.xika.myalertdialog;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/3
 * Vwesion 1.0
 * Dsscription:   自定义的AlertDialog 的辅助类
 */

class AlertDialogHelper {

    private View mContentView = null;

    private SparseArray<WeakReference<View>> mViewArray;

    public AlertDialogHelper(Context mContext, int viewId) {
        this();
        mContentView = LayoutInflater.from(mContext).inflate(viewId, null);
    }

    public AlertDialogHelper() {
        mViewArray = new SparseArray<>();
    }

    /**
     * 传入Dialog的布局处理
     *
     * @param viewRes
     */
    public void setContentView(View viewRes) {
        this.mContentView = viewRes;
    }

    public View getContentView() {
        return mContentView;
    }

    /**
     * Dialog真正设置View内容
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        tv.setText(text);
    }

    /**
     * Dialog真正设置View的点击事件
     *
     * @param viewId
     * @param listenner
     */
    public void setClick(int viewId, View.OnClickListener listenner) {
        View v = getView(viewId);
        v.setOnClickListener(listenner);
    }

    /**
     * 优化父类的重复调用findViewById的次数
     *
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        /*************  调用软应用的对象数据 必须判断是否存在 在调用,不然使用之前会报错 (因为GC优先 删除 软应用的数据) *******************/
        WeakReference<View> weakReference = mViewArray.get(viewId);
        View view = null;
        if (weakReference != null) {
            view = weakReference.get();
        }
        if (view == null) {
            view = mContentView.findViewById(viewId);
            mViewArray.put(viewId, new WeakReference<>(view));
        }
        return (T) view;
    }
}
