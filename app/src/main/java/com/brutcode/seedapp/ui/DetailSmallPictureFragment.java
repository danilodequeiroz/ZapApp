package com.brutcode.seedapp.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brutcode.seedapp.Constants;
import com.brutcode.seedapp.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailSmallPictureFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static List<String> mImages;

    @BindView(R.id.drawee_detail_picture)
    public SimpleDraweeView mPicture;

    @OnClick(R.id.drawee_detail_picture)
    public void clickPicture(){
        Bundle bundle = new Bundle();
        int position = (getArguments() != null) ? getArguments().getInt(ARG_PARAM2): 0;
        bundle.putSerializable(Constants.CONTENT_BUNDLE_PHOTO_GALLERY, new ArrayList<>(mImages));
        bundle.putInt(Constants.CONTENT_BUNDLE_PHOTO_SELECTED,position);
        Intent intent = new Intent(getContext(), FullScreenPhotoActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public DetailSmallPictureFragment() {}

    public static DetailSmallPictureFragment newInstance(String url, int position, List<String> allImages) {
        DetailSmallPictureFragment fragment = new DetailSmallPictureFragment();
        mImages = allImages;
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, url);
        args.putInt(ARG_PARAM2, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String mParam1 = "";
        View returnView = inflater.inflate(R.layout.fragment_detail_small_picture, container, false);
        ButterKnife.bind(this,returnView);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        Uri uri = Uri.parse(mParam1);
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(uri).build();
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setImageRequest(imageRequest)
                .setAutoPlayAnimations(true)
                .build();
        mPicture.setController(controller);
        return  returnView;
    }

}
