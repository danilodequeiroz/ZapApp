package com.brutcode.seedapp.view;

import com.brutcode.seedapp.model.Contact;

/**
 * Created by Danilo on 09/09/2016.
 */
public interface ContactDialogView extends BaseDialogView {
    void presentContent(Contact contact);
}
