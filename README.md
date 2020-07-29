# TabIndicator

# Viewpager2 +Fragment 及TabLayout demo
  实现了TabIndicator 的渐变效果
  
  实现核心思路  
  使用 ArgbEvaluator 做颜色渐变效果， 源码分析
  ```
  public Object evaluate(float fraction, Object startValue, Object endValue) {

//第一部分求出A, R, G, B色彩的初始值
    int startInt = (Integer) startValue;
    float startA = ((startInt >> 24) & 0xff) / 255.0f;
    float startR = ((startInt >> 16) & 0xff) / 255.0f;
    float startG = ((startInt >>  8) & 0xff) / 255.0f;
    float startB = ( startInt        & 0xff) / 255.0f;


//第二部分求出A，R，G，B的结束值
    int endInt = (Integer) endValue;
    float endA = ((endInt >> 24) & 0xff) / 255.0f;
    float endR = ((endInt >> 16) & 0xff) / 255.0f;
    float endG = ((endInt >>  8) & 0xff) / 255.0f;
    float endB = ( endInt        & 0xff) / 255.0f;



    // convert from sRGB to linear
    startR = (float) Math.pow(startR, 2.2);
    startG = (float) Math.pow(startG, 2.2);
    startB = (float) Math.pow(startB, 2.2);


    endR = (float) Math.pow(endR, 2.2);
    endG = (float) Math.pow(endG, 2.2);
    endB = (float) Math.pow(endB, 2.2);


//根据当前的进度求出对应的数值
    // compute the interpolated color in linear space
    float a = startA + fraction * (endA - startA);
    float r = startR + fraction * (endR - startR);
    float g = startG + fraction * (endG - startG);
    float b = startB + fraction * (endB - startB);


    // convert back to sRGB in the [0..255] range
    a = a * 255.0f;
    r = (float) Math.pow(r, 1.0 / 2.2) * 255.0f;
    g = (float) Math.pow(g, 1.0 / 2.2) * 255.0f;
    b = (float) Math.pow(b, 1.0 / 2.2) * 255.0f;


    return Math.round(a) << 24 | Math.round(r) << 16 | Math.round(g) << 8 | Math.round(b);
}

```

# 实现指示器渐变颜色，可以添加滚动callBack ` 

```
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
```


