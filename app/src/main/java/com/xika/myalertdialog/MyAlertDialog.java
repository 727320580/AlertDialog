package com.xika.myalertdialog;

import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;

/**
 * Email 727320580@qq.com
 * Created by xika on 2017/5/3
 * Vwesion 1.0
 * Dsscription:   自定义的AlertDialog
 */

public class MyAlertDialog extends Dialog {

    private AlertController mAlert;

    protected MyAlertDialog(Context context, int themeResId) {
        super(context, themeResId);
        mAlert = new AlertController(this, getWindow());
    }

    /**
     * 动态设置内容
     *
     * @param viewId
     * @param text
     */
    public void setText(int viewId, CharSequence text) {
        mAlert.setText(viewId, text);
    }

    /**
     * 动态设置点击事件
     *
     * @param viewId
     * @param listenner
     */
    public void setClick(int viewId, View.OnClickListener listenner) {
        mAlert.setClick(viewId, listenner);
    }

    /**
     * 获取布局的View
     *
     * @param viewId
     */
    public <T extends View> T getView(int viewId) {
        return (T) mAlert.getView(viewId);
    }


    public static class Builder {
        AlertController.AlertParams p;

        /**
         * Creates a builder for an alert dialog that uses the default alert
         * dialog theme.
         * <p>
         * The default alert dialog theme is defined by
         * {@link android.R.attr#alertDialogTheme} within the parent
         * {@code context}'s theme.
         *
         * @param context the parent context
         */
        public Builder(Context context) {
            this(context, R.style.myDialog);
        }

        /**
         * Creates a builder for an alert dialog that uses an explicit theme
         * resource.
         * <p>
         * The specified theme resource ({@code themeResId}) is applied on top
         * of the parent {@code context}'s theme. It may be specified as a
         * style resource containing a fully-populated theme, such as
         * {@link android.R.style#Theme_Material_Dialog}, to replace all
         * attributes in the parent {@code context}'s theme including primary
         * and accent colors.
         * <p>
         * To preserve attributes such as primary and accent colors, the
         * {@code themeResId} may instead be specified as an overlay theme such
         * as {@link android.R.style#ThemeOverlay_Material_Dialog}. This will
         * override only the window attributes necessary to style the alert
         * window as a dialog.
         * <p>
         * Alternatively, the {@code themeResId} may be specified as {@code 0}
         * to use the parent {@code context}'s resolved value for
         * {@link android.R.attr#alertDialogTheme}.
         *
         * @param context    the parent context
         * @param themeResId the resource ID of the theme against which to inflate
         *                   this dialog, or {@code 0} to use the parent
         *                   {@code context}'s default alert dialog theme
         */
        public Builder(Context context, int themeResId) {
            p = new AlertController.AlertParams(context, themeResId);
        }

        /**
         * 设置Dialog的布局
         *
         * @param viewRes
         * @return
         */
        public Builder setContentView(View viewRes) {
            p.layoutResId = 0;
            p.mView = viewRes;
            return this;
        }

        /**
         * 设置Dialog的布局Id
         *
         * @param layoutResId
         * @return
         */
        public Builder setContentView(int layoutResId) {
            p.layoutResId = layoutResId;
            p.mView = null;
            return this;
        }

        /**
         * 设置Dialog的布局Id
         *
         * @param viewId
         * @param text
         * @return
         */
        public Builder setText(int viewId, String text) {
            p.mTextArray.put(viewId, text);
            return this;
        }

        /**
         * 设置Dialog的布局Id
         *
         * @param viewId
         * @param listenner
         * @return
         */
        public Builder setClick(int viewId, View.OnClickListener listenner) {
            p.mClickArray.put(viewId, listenner);
            return this;
        }

        /**
         * 设置Dialog的全屏宽
         *
         * @return
         */
        public Builder setFullWith() {
            p.with = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }


        /**
         * 设置Daialog的宽高
         *
         * @param with
         * @param height
         * @return
         */
        public Builder setWithAndHeight(int with, int height) {
            p.with = with;
            p.height = height;
            return this;
        }

        /**
         * 设置Daialog的默认动画
         *
         * @return
         */
        public Builder setDefaultAnimation() {
            p.mAnimatiom = R.style.dialog_default_animation;
            return this;
        }

        /**
         * 是否加入底部动画
         *
         * @param isAnimaiton
         * @return
         */
        public Builder setBottomAnimation(boolean isAnimaiton) {
            if (isAnimaiton) {
                p.mAnimatiom = R.style.dialog_from_bottom_animation;
            }
            p.gravity = Gravity.BOTTOM;
            return this;
        }

        /**
         * 设置自定义的动画
         *
         * @param style
         * @param gravity
         * @return
         */
        public Builder setAnimation(int style, int gravity) {
            p.mAnimatiom = style;
            p.gravity = gravity;
            return this;
        }


        /**
         * 设置Dialog点击空白是否会取消
         *
         * @param is
         * @return
         */
        public Builder setCancelable(boolean is) {
            p.mCancelable = is;
            return this;
        }


        // 创建AlertDialog
        public MyAlertDialog create() {
            final MyAlertDialog dialog = new MyAlertDialog(p.mContext, p.themeResId);
            p.apply(dialog.mAlert);
            dialog.setCancelable(p.mCancelable);
            if (p.mCancelable) {
                dialog.setCanceledOnTouchOutside(true);
            }
            dialog.setOnCancelListener(p.mOnCancelListener);
            dialog.setOnDismissListener(p.mOnDismissListener);
            if (p.mOnKeyListener != null) {
                dialog.setOnKeyListener(p.mOnKeyListener);
            }
            return dialog;
        }

        // 显示Dialog
        public MyAlertDialog show() {
            final MyAlertDialog dialog = create();
            dialog.show();
            return dialog;
        }

    }

}
