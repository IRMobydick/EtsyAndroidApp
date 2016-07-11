package com.etsy.android.lib.models.interfaces;

import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import java.io.Serializable;

public interface ShareItemLike extends Serializable {
    ShareAnnotation getPrimaryAnnotation();

    String getText();
}
