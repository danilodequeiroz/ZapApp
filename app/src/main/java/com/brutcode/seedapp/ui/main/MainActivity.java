package com.brutcode.seedapp.ui.main;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brutcode.seedapp.Constants;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.RealtyAdapter;
import com.brutcode.seedapp.model.Realty;
import com.brutcode.seedapp.presenter.MainPresenter;
import com.brutcode.seedapp.ui.BaseActivity;
import com.brutcode.seedapp.ui.result.DetailScrollingActivity;
import com.brutcode.seedapp.view.MainView;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainView, RealtyAdapter.OnItemClickListener {


    private static final String TAG = MainActivity.class.getSimpleName();

    @BindView(R.id.recycler_act_main_list)
    public RecyclerView mRecycler;

    @BindView(R.id.toolbar)
    public Toolbar mToolbar;


    private MainPresenter mPresenter;

    private MaterialDialog mProgressDialog;
    private MaterialDialog.ListCallbackSingleChoice mItemCallback;
    private MaterialDialog mSortDialog;

    @OnClick(R.id.fab_search_dlg)
    void fabSearchClick() {
        mSortDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter();
        mPresenter.setView(this);
        setUpDialogs();
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(llm);
        RealtyAdapter adapter = new RealtyAdapter(this, this);
        mRecycler.setAdapter(adapter);
    }


    private void setUpDialogs() {
        mItemCallback = new MaterialDialog.ListCallbackSingleChoice() {
            @Override
            public boolean onSelection(MaterialDialog dialog, View itemView, int which, CharSequence text) {
                ((RealtyAdapter) mRecycler.getAdapter()).sortItems(which);
                mRecycler.smoothScrollToPosition(0);
                return true;
            }
        };
        DialogInterface.OnCancelListener progCancelListener = new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                mPresenter.cancelGetMovieRequest();
            }
        };
        mProgressDialog = new MaterialDialog.Builder(this)
                .progress(true, 0)
                .content(R.string.loading)
                .autoDismiss(true)
                .cancelListener(progCancelListener)
                .build();
        mSortDialog = new MaterialDialog.Builder(this)
                .title(R.string.str_dialog_title_sort)
                .items(R.array.sort_options)
                .itemsCallbackSingleChoice(-1, mItemCallback)
                .positiveText(R.string.str_dialog_positive)
                .negativeText(R.string.str_dialog_negative)
                .build();
        mPresenter.requestList();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(Constants.PROGRESS_DIALOG_STATE_BUNDLE, mProgressDialog.isShowing());
        outState.putBoolean(Constants.SEARCH_DIALOG_STATE_BUNDLE, mSortDialog.isShowing());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getBoolean(Constants.SEARCH_DIALOG_STATE_BUNDLE)) {
            mSortDialog.show();
        }
    }

    @Override
    public void presentContent(List<Realty> realties) {
        hideProgress();
        RealtyAdapter adapter = (RealtyAdapter) mRecycler.getAdapter();
        adapter.insertItens(realties);
    }

    @Override
    public void runOnUi(Runnable runnable) {
        runOnUiThread(runnable);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
    public void showProgress() {
        mProgressDialog.show();
    }

    @Override
    public void hideProgress() {
        mProgressDialog.cancel();
    }

    @Override
    public void longToast(int stringResource) {
        Toast.makeText(MainActivity.this, stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void shortToast(int stringResource) {
        Toast.makeText(MainActivity.this, stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(int position) {
        Realty item = ((RealtyAdapter) mRecycler.getAdapter()).getItem(position);
        Parcelable wrappedParcelable = Parcels.wrap(item);
        Intent intent = new Intent(this, DetailScrollingActivity.class);
        intent.putExtra(Constants.CONTENT_BUNDLE, wrappedParcelable);
        startActivity(intent);
    }
}
