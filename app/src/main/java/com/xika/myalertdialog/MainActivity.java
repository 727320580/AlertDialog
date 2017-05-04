package com.xika.myalertdialog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private Button mShowDialog;

    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mShowDialog = (Button) findViewById(R.id.show_dialog);
        mShowDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAlertDialog alertDialog = new MyAlertDialog.Builder(mContext)
                        .setContentView(R.layout.detail_comment_dialog)
                        .setBottomAnimation(true)
                        .setFullWith()
                        .show();
                // 设置获取布局的View
                final EditText comment = alertDialog.getView(R.id.comment_editor);
                // 动态设置内容
                alertDialog.setClick(R.id.submit_btn, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = comment.getText().toString();
                        Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}
