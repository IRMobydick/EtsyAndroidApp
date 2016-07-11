package com.foresee.mobileReplay.domain;

import com.google.gson.p020a.SerializedName;

public class ImageDiff {
    @SerializedName(a = "h")
    private int height;
    private String id;
    @SerializedName(a = "w")
    private int width;
    private int f4336x;
    private int f4337y;

    public ImageDiff(String str, int i, int i2, int i3, int i4) {
        this.id = str;
        this.f4336x = i;
        this.f4337y = i2;
        this.width = i3;
        this.height = i4;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public int getX() {
        return this.f4336x;
    }

    public void setX(int i) {
        this.f4336x = i;
    }

    public int getY() {
        return this.f4337y;
    }

    public void setY(int i) {
        this.f4337y = i;
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
}
