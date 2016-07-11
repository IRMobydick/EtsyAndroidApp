package com.foresee.mobileReplay.domain;

import com.google.gson.p020a.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class DiffSet {
    private List<ImageDiff> diffs;
    private int height;
    @SerializedName(a = "fs")
    private boolean isFullScreen;
    private float scaleFactor;
    private long timestamp;
    private int width;

    public DiffSet() {
        this.diffs = new ArrayList();
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public void addDiff(ImageDiff imageDiff) {
        this.diffs.add(imageDiff);
    }

    public List<ImageDiff> getDiffs() {
        return this.diffs;
    }

    public float getScaleFactor() {
        return this.scaleFactor;
    }

    public void setScaleFactor(float f) {
        this.scaleFactor = f;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public boolean isFullScreen() {
        return this.isFullScreen;
    }

    public void setFullScreen(boolean z) {
        this.isFullScreen = z;
    }
}
