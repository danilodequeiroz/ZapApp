package com.brutcode.seedapp.ui.result;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.brutcode.seedapp.Constants;
import com.brutcode.seedapp.PicturePagerAdapter;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.Util;
import com.brutcode.seedapp.model.Realty;
import com.brutcode.seedapp.presenter.DetailPresenter;
import com.brutcode.seedapp.ui.contact.ContactFormDialog;
import com.brutcode.seedapp.view.DetailView;

import org.parceler.Parcels;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailScrollingActivity extends AppCompatActivity implements DetailView {

    private static final String TAG = DetailScrollingActivity.class.getSimpleName();

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;

    @BindView(R.id.vp_home_rotative_news)
    public ViewPager mViewPager;

    @BindView(R.id.text_content_card_value)
    public TextView mRealtyValue;

    @BindView(R.id.text_content_card_realty_type)
    public TextView mRealtyType;

    @BindView(R.id.text_content_card_address)
    public TextView mRealtyAddress;

    @BindView(R.id.text_content_card_qt_dorms)
    public TextView mRealtyDoms;

    @BindView(R.id.text_content_card_qt_suites)
    public TextView mRealtySuites;

    @BindView(R.id.text_content_card_qt_cargs)
    public TextView mRealtyCarGar;

    @BindView(R.id.text_content_card_qt_area)
    public TextView mRealtyArea;

    @BindView(R.id.text_content_card_desc_value)
    public TextView mRealtyDescr;

    @BindView(R.id.text_card_desc_cond_tax)
    public TextView mRealtyCondFee;

    @BindView(R.id.text_card_desc_squarem_value)
    public TextView mRealtySqrMeterValue;

    @BindView(R.id.card_other_realty_charact)
    public CardView mCardOther;

    @BindView(R.id.text_card_other_realty)
    public TextView mRealtyOthChar;

    @BindView(R.id.text_card_other_common)
    public TextView mRealtyCommonAreaCharts;

    @BindView(R.id.loading_spinner)
    public ProgressBar mProgress;

    @BindView(R.id.ll_content_detail_cards)
    public NestedScrollView mNested;

    public Realty mRealtyItem;
    public Realty mRealty;


    @OnClick({R.id.fab_send_msg,R.id.card_main_item_comment})
    public void fabClick( View v){
        new ContactFormDialog(this,this).show();
    }


    public DetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_scrolling);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        mPresenter = new DetailPresenter();
        mPresenter.setView(this);

        if (savedInstanceState == null)
            mRealtyItem = Parcels.unwrap(getIntent().getExtras().getParcelable(Constants.CONTENT_BUNDLE));
        else
            mRealtyItem = Parcels.unwrap(savedInstanceState.getParcelable(Constants.CONTENT_STATE_BUNDLE));
        mPresenter.requestRealtyByCode(mRealtyItem.getCodRealty());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setTranslucentStatusBar(getWindow());
    }

    @OnClick(R.id.btn_content_card_type_address_map)
    public void lookUpOnMaps(){
        String uri = String.format("geo:0,0?q=%1$s", Util.fullAddressFormatted(mRealty.getRealtyAddress()));
        Uri gmmIntentUri = Uri.parse(uri);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(mapIntent);
        }else {
            longToast(R.string.maps_not_installed);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(Constants.CONTENT_STATE_BUNDLE, Parcels.wrap(mRealtyItem));
    }



    @Override
    public void presentContent(Realty realty) {
        mRealty = realty;
        PicturePagerAdapter mPagerAdapter = new PicturePagerAdapter(getSupportFragmentManager(), realty.getImages());
        mViewPager.setAdapter(mPagerAdapter);
        NumberFormat brlFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        mRealtyValue.setText(brlFormat.format(mRealty.getSellPrice()));
        mRealtyType.setText(mRealty.getRealtySubType());
        mRealtyAddress.setText(Util.addressFormatted(mRealty.getRealtyAddress()));
        mRealtyDoms.setText(String.valueOf(mRealty.getDorms()));
        mRealtySuites.setText(String.valueOf(mRealty.getSuites()));
        mRealtyCarGar.setText(String.valueOf(mRealty.getParkingSpaces()));
        mRealtyArea.setText(String.valueOf(mRealty.getTotalArea()));
        mRealtyDescr.setText(mRealty.getObservation());
        String condFee = brlFormat.format(realty.getSellPrice() * 0.01); // simulando parametro condominio com 1% do valor
        mRealtyCondFee.setText(Util.fromHtml(String.format(getString(R.string.str_card_desc_cond_tax),condFee)));
        String squareVal = brlFormat.format(realty.getSellPrice() / realty.getTotalArea());
        mRealtySqrMeterValue.setText(Util.fromHtml(String.format(getString(R.string.str_card_desc_meter_value),squareVal)));
        List<String> var = mRealty.getCharacteristics();
        List<String> var2 = mRealty.getCommonCharacteristics();
        if(mRealty.getCharacteristics().isEmpty() && mRealty.getCommonCharacteristics().isEmpty())
            mCardOther.setVisibility(View.GONE);
        String joined = TextUtils.join(", ", mRealty.getCharacteristics());
        if(mRealty.getCharacteristics().isEmpty())
            mRealtyOthChar.setVisibility(View.GONE);
        else
            mRealtyOthChar.setText(Util.fromHtml(String.format(getString(R.string.str_card_other_realty_carac), TextUtils.join(", ", mRealty.getCharacteristics()))));
        if(mRealty.getCommonCharacteristics().isEmpty())
            mRealtyCommonAreaCharts.setVisibility(View.GONE);
        else
            mRealtyCommonAreaCharts.setText(Util.fromHtml(String.format(getString(R.string.str_card_other_common_area_chara), TextUtils.join(", ", mRealty.getCommonCharacteristics()))));
        hideProgress();
    }

    public void setTranslucentStatusBar(Window window) {
        if (window == null) return;
        int sdkInt = Build.VERSION.SDK_INT;
        if (sdkInt >= Build.VERSION_CODES.LOLLIPOP) {
            setTranslucentStatusBarLollipop(window);
        } else if (sdkInt >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatusBarKiKat(window);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setTranslucentStatusBarLollipop(Window window) {
        window.setStatusBarColor(ContextCompat.getColor(this,R.color.someTransparent));
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void setTranslucentStatusBarKiKat(Window window) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }

    @Override
    public void runOnUi(Runnable runnable) {

    }

    @Override
    public Application getApp() {
        return getApplication();
    }

    @Override
    public Context getViewContext() {
        return this;
    }

    @Override
    public Resources getAppResources() {
        return getResources();
    }

    @Override
    public void longToast(int stringResource) {
        Toast.makeText(this, stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shortToast(int stringResource) {
        Toast.makeText(this, stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {
        mNested.setVisibility(View.VISIBLE);
        mProgress.setVisibility(View.GONE);
    }
}
