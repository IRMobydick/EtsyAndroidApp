package com.etsy.android.uikit.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Iterator;

/* renamed from: com.etsy.android.uikit.util.v */
public class UserInputDialogHelper {
    private final Context f4194a;
    private final ProgressDialog f4195b;
    private final ArrayList<UserInputDialogHelper> f4196c;

    public UserInputDialogHelper(Context context) {
        this.f4196c = new ArrayList();
        this.f4194a = context;
        this.f4195b = new ProgressDialog(this.f4194a);
        this.f4195b.setProgressStyle(0);
        this.f4195b.setCancelable(false);
    }

    public void m5633a(UserInputDialogHelper userInputDialogHelper) {
        this.f4196c.add(userInputDialogHelper);
    }

    public void m5634b(UserInputDialogHelper userInputDialogHelper) {
        this.f4196c.remove(userInputDialogHelper);
    }

    public void m5629a(int i) {
        if (!this.f4195b.isShowing()) {
            this.f4195b.setMessage(this.f4194a.getString(i));
            if (this.f4195b.isShowing()) {
                this.f4195b.dismiss();
            }
            this.f4195b.show();
        }
        Iterator it = this.f4196c.iterator();
        while (it.hasNext()) {
            ((UserInputDialogHelper) it.next()).showProgress(true);
        }
    }

    public void m5628a() {
        if (this.f4195b.isShowing()) {
            this.f4195b.dismiss();
        }
        Iterator it = this.f4196c.iterator();
        while (it.hasNext()) {
            ((UserInputDialogHelper) it.next()).showProgress(false);
        }
    }

    public void m5630a(TextView textView) {
        textView.setVisibility(8);
    }

    public void m5631a(TextView textView, int i) {
        m5632a(textView, this.f4194a.getString(i));
    }

    public void m5632a(TextView textView, String str) {
        textView.setVisibility(0);
        textView.setText(str);
    }
}
