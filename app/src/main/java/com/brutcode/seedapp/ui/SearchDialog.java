package com.brutcode.seedapp.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.brutcode.seedapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Danilo on 07/07/2016.
 */
public class SearchDialog extends MaterialDialog {

    @BindView(R.id.edtx_dialog_input_name)
    public EditText mInputName;
    @BindView(R.id.edtx_dialog_input_year)
    public EditText mInputYear;

    public EditText.OnEditorActionListener mActionListener = new EditText.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                positiveButton.performClick();
                return true;
            }
            return false;
        }
    };

    public SearchDialog(@NonNull Context context, Builder builder) {
        super(builder);
        View view = View.inflate(context, R.layout.view_search_film, null);
        builder.customView(view, true);
        ButterKnife.bind(this, view);
    }


}
