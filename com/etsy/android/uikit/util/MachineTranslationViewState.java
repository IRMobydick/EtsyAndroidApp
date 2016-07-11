package com.etsy.android.uikit.util;

import java.io.Serializable;
import org.parceler.Parcel;

@Parcel
public class MachineTranslationViewState implements Serializable {
    private static final long serialVersionUID = -2002175681426566877L;
    protected boolean mShowingOriginal;
    protected int mState;

    public MachineTranslationViewState() {
        this.mState = 1;
    }

    public void setLoadingTranslation() {
        this.mState = 2;
    }

    public void setErrorLoadingTranslation() {
        this.mState = 3;
    }

    public void setSuccessLoadingTranslation() {
        this.mShowingOriginal = false;
        this.mState = 4;
    }

    public void setShowingOriginal(boolean z) {
        this.mShowingOriginal = z;
    }

    public void toggleShowingOriginal() {
        this.mShowingOriginal = !this.mShowingOriginal;
    }

    public boolean isLoadingTranslation() {
        return this.mState == 2;
    }

    public boolean errorOccurredLoadingTranslation() {
        return this.mState == 3;
    }

    public boolean hasLoadedTranslation() {
        return this.mState == 4;
    }

    public boolean isShowingOriginal() {
        return this.mShowingOriginal;
    }
}
