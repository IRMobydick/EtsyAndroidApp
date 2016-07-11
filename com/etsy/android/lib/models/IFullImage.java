package com.etsy.android.lib.models;

public interface IFullImage {
    String get4to3ImageUrlForPixelWidth(int i);

    String getFullCardImageUrlForPixelWidth(int i);

    int getFullHeight();

    int getFullWidth();

    int getImageColor();
}
