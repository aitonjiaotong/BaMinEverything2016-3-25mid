package com.aiton.bamin.changtukepiao.Dchihewanle;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.aiton.bamin.changtukepiao.R;

public class JActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_j);
    }

    public void btn_detail(View v)
    {
        Toast.makeText(JActivity.this, "跳转到商家详情页面", Toast.LENGTH_SHORT).show();
    }

    public void back(View v)
    {
        finish();
    }
}
