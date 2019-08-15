package com.am.carly.ui.cars;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.am.carly.R;
import com.am.carly.data.model.Image;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import jp.wasabeef.glide.transformations.BlurTransformation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.bumptech.glide.request.RequestOptions.bitmapTransform;


public class ImagesPagerAdapter extends PagerAdapter {

    private AppCompatActivity mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<Image> mImageList;

    public ImagesPagerAdapter(AppCompatActivity context, ArrayList<Image> imageList) {
        this.mImageList = imageList;
        this.mContext = context;
        this.mLayoutInflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public boolean isViewFromObject(@NotNull View view, @NotNull Object object) {
        return view == object;
    }

    @NotNull
    @Override
    public Object instantiateItem(@NotNull ViewGroup container, final int position) {
        View view = mLayoutInflater.inflate(R.layout.item_slide, null);
        ImageView imageView = view.findViewById(R.id.slideImageView);
        ImageView blurImageView = view.findViewById(R.id.blurImageView);


        Glide.with(mContext).load(mImageList.get(position).getImageUrl())
                .apply(bitmapTransform(new BlurTransformation(25)))
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(blurImageView);


        Glide.with(mContext).load(mImageList.get(position).getImageUrl())
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);


        view.setOnClickListener(v -> showImageSlider(position));

        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NotNull ViewGroup container, int position, @NotNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);

    }

    private void showImageSlider(int position) {
        String IMAGES_LIST = "imagesList";
        String POSITION = "position";
        String SLIDE_SHOW = "slideshow";
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(IMAGES_LIST, mImageList);
        bundle.putInt(POSITION, position);
        FragmentTransaction ft = mContext.getSupportFragmentManager().beginTransaction();
        ImagesSliderFragment newFragment = ImagesSliderFragment.newInstance();
        newFragment.setArguments(bundle);
        newFragment.show(ft, SLIDE_SHOW);
    }


}
