package com.am.carly.ui.cars;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.am.carly.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.esafirm.imagepicker.model.Image;
import jp.wasabeef.glide.transformations.BlurTransformation;

import java.util.ArrayList;
import java.util.List;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class ImagesSliderAdapter extends PagerAdapter {

    private AppCompatActivity mContext;
    private LayoutInflater mLayoutInflater;
    private List<Image> mSliderImageList = new ArrayList<>();

    public ImagesSliderAdapter(AppCompatActivity context) {
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public void updateImagesList(List<Image> imageList) {
        this.mSliderImageList = imageList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mSliderImageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View view = mLayoutInflater.inflate(R.layout.item_slide, null);
        ImageView imageView = view.findViewById(R.id.slideImageView);
        ImageView blurImageView = view.findViewById(R.id.blurImageView);


        Glide.with(mContext).load(mSliderImageList.get(position).getPath())
                .apply(bitmapTransform(new BlurTransformation(25)))
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(blurImageView);


        Glide.with(mContext).load(mSliderImageList.get(position).getPath())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }


}
