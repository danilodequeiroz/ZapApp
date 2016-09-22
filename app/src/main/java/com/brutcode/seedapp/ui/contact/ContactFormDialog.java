package com.brutcode.seedapp.ui.contact;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.widget.EditText;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.brutcode.seedapp.R;
import com.brutcode.seedapp.model.Contact;
import com.brutcode.seedapp.presenter.ContactDialogPresenter;
import com.brutcode.seedapp.view.BaseView;
import com.brutcode.seedapp.view.ContactDialogView;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Danilo on 10/09/2016.
 */
public class ContactFormDialog extends MaterialDialog.Builder implements Validator.ValidationListener, MaterialDialog.SingleButtonCallback, ContactDialogView {

    private static final String TAG = ContactFormDialog.class.getSimpleName();
    private final Validator mValidator;
    private final ContactDialogPresenter mPresenter;

    @NotEmpty(messageResId= R.string.validation_error_empty)
    @Length(min = 5, max = 120 , messageResId = R.string.validation_error_wrong_name)
    @BindView(R.id.edit_dialog_contact_form_name)
    public TextInputEditText mNameInput;

    @NotEmpty(messageResId= R.string.validation_error_empty)
    @Email(messageResId = R.string.validation_error_empty_mail)
    @BindView(R.id.edit_dialog_contact_form_email)
    public TextInputEditText mEmailInput;

    @NotEmpty(messageResId= R.string.validation_error_empty)
    @Length(min = 10, max = 12 , messageResId = R.string.validation_error_wrong_phone)
    @BindView(R.id.edit_dialog_contact_form_phone)
    public TextInputEditText mPhoneInput;

    @NotEmpty(messageResId= R.string.validation_error_empty)
    @Length(min = 8, max = 240 , messageResId = R.string.validation_error_wrong_comment)
    @BindView(R.id.edit_dialog_contact_form_comment)
    public TextInputEditText mCommentInput;

    MaterialDialog mDialog;
    private Context mContext;

    private BaseView mBaseView;


    public ContactFormDialog(@NonNull Context context, @NonNull BaseView baseView) {
        super(context);
        mContext = context;
        mBaseView = baseView;
        customView(R.layout.dialog_contact_form, true)
                .positiveText(R.string.str_dialog_form_submit)
                .negativeText(R.string.str_dialog_form_back)
                .onPositive(this).onNegative(this).autoDismiss(false);
        mDialog = build();
        ButterKnife.bind(this, mDialog.getCustomView());
        mPresenter = new ContactDialogPresenter();
        mPresenter.setView(this);
        mValidator = new Validator(this);
        mValidator.setValidationListener(this);
    }

    @Override
    public void onValidationSucceeded() {
        Contact contact = new Contact(
                mNameInput.getText().toString(),
                mEmailInput.getText().toString(),
                mPhoneInput.getText().toString(),
                mCommentInput.getText().toString());
        mPresenter.postContact(contact);
        mDialog.dismiss();
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < errors.size(); i++) {
            if(errors.get(i).getView()instanceof EditText){
                ((EditText)errors.get(i).getView()).setError(errors.get(i).getCollatedErrorMessage(getContext()));
            }
            String errorMsg = i==errors.size()-1 ?
                    errors.get(i).getCollatedErrorMessage(getContext()) :
                    errors.get(i).getCollatedErrorMessage(getContext()) + "\n";
            builder.append(errorMsg);
        }
        longToast(builder.toString());
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        switch (which) {
            case NEGATIVE:
                dialog.dismiss();
                break;
            case POSITIVE:
            case NEUTRAL:
                mDialog = dialog;
                mValidator.validate();
                break;
        }
    }

    @Override
    public void presentContent(Contact contact) {

    }

    @Override
    public Application getApp() {
        return mBaseView.getApp();
    }

    @Override
    public void longToast(int stringResource) {
        Toast.makeText(getContext(), stringResource, Toast.LENGTH_SHORT).show();
    }

    public void longToast(String stringResource) {
        Toast.makeText(getContext(), stringResource, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        mDialog.show();
    }

    @Override
    public void hideDialog() {

    }
}
