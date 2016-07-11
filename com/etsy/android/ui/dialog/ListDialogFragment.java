package com.etsy.android.ui.dialog;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import com.etsy.android.lib.models.finds.FindsModule;
import java.util.List;

public class ListDialogFragment extends DialogFragment {
    OnClickListener clickListener;
    String[] list;

    public static ListDialogFragment newInstance(String[] strArr, String str, OnClickListener onClickListener) {
        ListDialogFragment listDialogFragment = new ListDialogFragment();
        listDialogFragment.list = strArr;
        listDialogFragment.clickListener = onClickListener;
        Bundle bundle = new Bundle();
        bundle.putString(FindsModule.FIELD_TITLE, str);
        listDialogFragment.setArguments(bundle);
        return listDialogFragment;
    }

    public static ListDialogFragment newInstance(List<String> list, String str, OnClickListener onClickListener) {
        ListDialogFragment listDialogFragment = new ListDialogFragment();
        list.toArray(listDialogFragment.list);
        listDialogFragment.clickListener = onClickListener;
        Bundle bundle = new Bundle();
        bundle.putString(FindsModule.FIELD_TITLE, str);
        listDialogFragment.setArguments(bundle);
        return listDialogFragment;
    }

    public Dialog onCreateDialog(Bundle bundle) {
        CharSequence charSequence = null;
        if (bundle != null) {
            charSequence = bundle.getString(FindsModule.FIELD_TITLE);
        } else if (getArguments() != null) {
            charSequence = getArguments().getString(FindsModule.FIELD_TITLE);
        }
        ListAdapter arrayAdapter = new ArrayAdapter(getActivity(), 17367043, this.list);
        Builder builder = new Builder(getActivity());
        builder.setTitle(charSequence).setSingleChoiceItems(arrayAdapter, 1, this.clickListener);
        return builder.create();
    }
}
