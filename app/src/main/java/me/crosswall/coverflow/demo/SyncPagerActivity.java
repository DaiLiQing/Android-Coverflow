package me.crosswall.coverflow.demo;

import android.graphics.Color;

import me.crosswall.lib.coverflow.core.syncpager.PagerAdapter;
import me.crosswall.lib.coverflow.core.syncpager.SyncCoverTransformer;
import me.crosswall.lib.coverflow.core.syncpager.SyncViewPgaer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.crosswall.lib.coverflow.core.SyncPagerContainer;

public class SyncPagerActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);

        SyncPagerContainer mContainer = (SyncPagerContainer) findViewById(R.id.pager_container);

        final SyncViewPgaer pager = mContainer.getViewPager();

        PagerAdapter adapter = new MyPagerAdapter();
        pager.setAdapter(adapter);

        pager.setOffscreenPageLimit(adapter.getCount());

        SyncViewPgaer bindingPager = (SyncViewPgaer) findViewById(R.id.pager);
        bindingPager.setAdapter(adapter);
        bindingPager.setOffscreenPageLimit(adapter.getCount());
        bindingPager.setSyncViewPgaer(pager);
        pager.setClipChildren(false);
        pager.setSyncViewPgaer(bindingPager);
        pager.setPageTransformer(false,new SyncCoverTransformer(0.3f,0f,0f));





    }

    private class MyPagerAdapter extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView view = new TextView(SyncPagerActivity.this);
            view.setText("Item "+position);
            view.setGravity(Gravity.CENTER);
            view.setBackgroundColor(Color.argb(255, position * 50, position * 10, position * 50));

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View)object);
        }

        @Override
        public int getCount() {
            return 15;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }


}