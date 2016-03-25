package com.aiton.bamin.changtukepiao.Bchangtukepiao.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.aiton.administrator.shane_library.shane.utils.HTTPUtils;
import com.aiton.administrator.shane_library.shane.utils.VolleyListener;
import com.aiton.bamin.changtukepiao.Bchangtukepiao.constant.Constant;
import com.aiton.bamin.changtukepiao.R;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.Map;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener
{

    private ImageView mBack;
    private EditText mEditText_feedback;
    private TextView mSend;
    private String mId;
    private String mDeviceId;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        initSp();
        initUI();
        setListener();

    }

    private void initSp() {
        SharedPreferences sp = getSharedPreferences("isLogin", Context.MODE_PRIVATE);
        mId = sp.getString("id", "");
        mDeviceId = sp.getString("DeviceId", "");
    }

    private void initUI()
    {
        mBack = (ImageView) findViewById(R.id.iv_back);
        mSend = (TextView) findViewById(R.id.tv_send);
        mSend.setBackgroundResource(R.color.gray);
        mEditText_feedback = (EditText) findViewById(R.id.editText_feedback);
        mEditText_feedback.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ("".equals(mEditText_feedback.getText().toString())) {
                    mSend.setEnabled(false);
                    mSend.setBackgroundResource(R.color.gray);
                } else {
                    mSend.setEnabled(true);
                    mSend.setBackgroundResource(R.drawable.btn_datepicket_select);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

    }

    private void setListener()
    {
        mBack.setOnClickListener(this);
        mSend.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.iv_back:
                finish();
                AnimFromRightToLeft();
                break;
            case R.id.tv_send:
                String str_feedback = mEditText_feedback.getText().toString();
                Map<String, String> mapData = new HashMap<>();
                mapData.put("account_id", mId);
                mapData.put("content", str_feedback);
                HTTPUtils.post(FeedBackActivity.this, Constant.WebViewURL.FEED_BACK, mapData, new VolleyListener()
                {
                    @Override
                    public void onResponse(String s)
                    {
                        //意见反馈提交成功后，弹出提示信息
                        new AlertDialog.Builder(FeedBackActivity.this).setTitle("提示").setMessage("意见反馈成功，我们的工作人员会尽快处理，感谢您的反馈！")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface dialoginterface, int i)
                                    {
                                        finish();
                                    }
                                }).show();
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError)
                    {
                    }
                });
                break;
        }
    }
    private void AnimFromRightToLeft() {
        overridePendingTransition(R.anim.fade_in, R.anim.push_left_out);
    }

    public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            finish();
            AnimFromRightToLeft();
        }
        return super.onKeyDown(keyCode, event);
    };
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
