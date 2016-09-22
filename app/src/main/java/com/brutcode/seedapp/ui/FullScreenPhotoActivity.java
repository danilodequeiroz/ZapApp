package com.brutcode.seedapp.ui;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.brutcode.seedapp.Constants;
import com.brutcode.seedapp.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by Danilo on 10/09/2016.
 */
public class FullScreenPhotoActivity extends AppCompatActivity {

    private static final String TAG = FullScreenPhotoActivity.class.getSimpleName();

    private ViewPager mPhotosPager;

    @BindView(R.id.ib_fullscreen_gallery_close_button)
    public ImageButton mCloseButton;

    @BindView(R.id.tv_fullscreen_gallery_nothing_to_show)
    public TextView mNothingToShowText;

    List<String> photos = null;
    int selectedImageIndex = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_photo);
        ButterKnife.bind(this);
        if (!getArgs()) {
            finish();
        }

        mPhotosPager = (ViewPager) findViewById(R.id.vp_fullscreen_gallery);
        mPhotosPager.setOffscreenPageLimit(8);
        mPhotosPager.setAdapter(new PhotoPager(photos));
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (selectedImageIndex > 0 && selectedImageIndex < photos.size()) {
            mPhotosPager.setCurrentItem(selectedImageIndex);
        }
    }

    boolean getArgs() {
        Bundle args = getIntent().getExtras();
        if (args != null) {
            photos = (ArrayList<String>) args.getSerializable(Constants.CONTENT_BUNDLE_PHOTO_GALLERY);
            if (photos == null) {
                mNothingToShowText.setVisibility(View.VISIBLE);
                return false;
            }
            selectedImageIndex = args.getInt(Constants.CONTENT_BUNDLE_PHOTO_SELECTED);
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        getArgs();
    }

    public class PhotoPager extends PagerAdapter {
        private List<String> photos;

        public PhotoPager(List<String> photos) {
            this.photos = photos;
        }

        @Override
        public int getCount() {
            return photos.size();
        }

        @BindView(R.id.imgv_view_fullscreen_photo_image)
        public PhotoDraweeView mPhotoDraweeView;

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View page = LayoutInflater.from(container.getContext()).inflate(R.layout.view_touchview_fullscreen_gallery, container, false);
            ButterKnife.bind(this,page);
            container.addView(page);
            bindView(page,container.getContext(),photos.get(position));
            return (page);
        }

        private void bindView(View page, Context ctx, String photo) {
            Uri uri = Uri.parse(photo);
            mPhotoDraweeView.setPhotoUri(uri);
            ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(imageRequest)
                    .setAutoPlayAnimations(true)
                    .build();
            mPhotoDraweeView.setController(controller);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

    }

}
