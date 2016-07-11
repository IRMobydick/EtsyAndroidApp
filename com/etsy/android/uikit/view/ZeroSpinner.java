package com.etsy.android.uikit.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: com.etsy.android.uikit.view.w */
public class ZeroSpinner implements InvocationHandler {
    protected SpinnerAdapter f4285a;
    protected Method f4286b;
    final /* synthetic */ ZeroSpinner f4287c;

    protected ZeroSpinner(ZeroSpinner zeroSpinner, SpinnerAdapter spinnerAdapter) {
        this.f4287c = zeroSpinner;
        this.f4285a = spinnerAdapter;
        try {
            this.f4286b = SpinnerAdapter.class.getMethod("getView", new Class[]{Integer.TYPE, View.class, ViewGroup.class});
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public Object invoke(Object obj, Method method, Object[] objArr) {
        try {
            return (!method.equals(this.f4286b) || (((Integer) objArr[0]).intValue() >= 0 && !(this.f4287c.requestingTopView && ((Integer) objArr[0]).intValue() == 0))) ? method.invoke(this.f4285a, objArr) : m5672a(-1, (View) objArr[1], (ViewGroup) objArr[2]);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Throwable e2) {
            throw new RuntimeException(e2);
        }
    }

    protected View m5672a(int i, View view, ViewGroup viewGroup) {
        if (i >= 0) {
            return this.f4285a.getView(i, view, viewGroup);
        }
        TextView textView = (TextView) ((LayoutInflater) this.f4287c.getContext().getSystemService("layout_inflater")).inflate(this.f4287c.mPromptViewResource, viewGroup, false);
        textView.setText(this.f4287c.getPrompt());
        return textView;
    }
}
