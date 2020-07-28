package com.bj.ocean.tabtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.animation.ArgbEvaluator;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Fragment> fragments = new ArrayList<>();
    List<String> titles = new ArrayList<>();

    PageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TabLayout tabLayout = findViewById(R.id.tabs);
        ViewPager2 vp = findViewById(R.id.vp);

        fragments.clear();
        fragments.add(new FragmentA());
        fragments.add(new FragmentA());
        fragments.add(new FragmentA());
        fragments.add(new FragmentA());
        titles.clear();
        titles.add("小🐱🐯");
        titles.add("小🐟🐍");
        titles.add("小🐇🐰");
        titles.add("鸟🐦🕊");

        adapter = new PageAdapter(this, fragments);
        vp.setAdapter(adapter);
        new TabLayoutMediator(tabLayout, vp, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles.get(position));
            }
        }).attach();
        vp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ArgbEvaluator evaluator = new ArgbEvaluator();
                if (position == 0) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_a), getResources().getColor(R.color.color_b));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                if (position > 0 && position < 1) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_b), getResources().getColor(R.color.color_a));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                if (position == 1) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_b), getResources().getColor(R.color.color_ccc));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                if (position > 1 && position < 2) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_ccc), getResources().getColor(R.color.color_b));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                if (position == 2) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_ccc), getResources().getColor(R.color.color_ddd));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                if (position > 2 && position < 3) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_ddd), getResources().getColor(R.color.color_ccc));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                if (position == 3) {
                    int eva = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.color_ddd), getResources().getColor(R.color.color_ccc));
                    tabLayout.setSelectedTabIndicatorColor(eva); }
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}
