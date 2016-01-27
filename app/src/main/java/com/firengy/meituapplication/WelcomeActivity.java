package com.firengy.meituapplication;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.firengy.meituapplication.adapters.WelcomePagerAdapter;
import com.firengy.meituapplication.databinding.ActivityWelcomeBinding;
import com.firengy.meituapplication.tools.ImageTool;

import java.util.ArrayList;
import java.util.List;

public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ActivityWelcomeBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_welcome);

        List<View> views = new ArrayList<>();

        LayoutInflater inflater = LayoutInflater.from(this);

        //初始化
        initData(views, inflater);
        initRadioGroup(binding, views, inflater);
        initPager(binding, views);
    }

    /**
     * 初始化ViewPager
     * @param binding
     * @param views
     */
    private void initPager(final ActivityWelcomeBinding binding, List<View> views) {
        binding.welcomeViewPager.setAdapter(new WelcomePagerAdapter(views));

        //设置ViewPager页面切换监听事件 及 处理，将pager和radioGroup关联
        binding.welcomeViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                binding.welcomeRadioGroup.check(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 创建引导页的indicator（指示器）
     * @param binding
     * @param views
     * @param inflater
     */
    private void initRadioGroup(ActivityWelcomeBinding binding, List<View> views, LayoutInflater inflater) {
        //设置welcome页面的indicator
        for (int i = 0; i < views.size(); i++) {
            View radioBtn = inflater.inflate(R.layout.activity_welcome_group_indicator, binding.welcomeRadioGroup, false);
            //方便以后根据id查找相应radioButton
            radioBtn.setId(i);
            //添加到radioGroup
            binding.welcomeRadioGroup.addView(radioBtn);
        }
        //默认选中第一个点
        binding.welcomeRadioGroup.check(0);
    }

    /**
     * 初始化图像名数据
     * @param views
     * @param inflater
     */
    private void initData(List<View> views, LayoutInflater inflater) {
        View view = null;
        for (int i = 1; i <=3 ; i++) {
            view = new ImageView(this);
            ((ImageView)view).setImageResource(ImageTool.getDrawableId("guide" + i));
            ((ImageView)view).setScaleType(ImageView.ScaleType.FIT_XY);
            views.add(view);
        }
        view = inflater.inflate(R.layout.activity_welcome_item, null);
        view.setBackgroundResource(ImageTool.getDrawableId("guide4"));
        views.add(view);
    }

    /**
     * 按钮点击事件处理
     * @param view
     */
    public void btnEnterMainApplication(View view) {
        //跳转主功能界面
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
