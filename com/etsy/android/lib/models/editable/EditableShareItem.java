package com.etsy.android.lib.models.editable;

import com.etsy.android.lib.models.interfaces.ShareItemLike;
import com.etsy.android.lib.models.shopshare.ShareAnnotation;
import java.io.File;
import org.apache.commons.lang3.StringUtils;

public class EditableShareItem implements ShareItemLike {
    private ShareAnnotation mAnnotation;
    private File mFile;
    private String mText;

    public EditableShareItem() {
        this.mText = StringUtils.EMPTY;
    }

    public ShareAnnotation getPrimaryAnnotation() {
        return this.mAnnotation;
    }

    public String getText() {
        return this.mText;
    }

    public File getFile() {
        return this.mFile;
    }

    public void setPrimaryAnnotation(ShareAnnotation shareAnnotation) {
        this.mAnnotation = shareAnnotation;
    }

    public void setText(String str) {
        this.mText = str;
    }

    public void setFile(File file) {
        this.mFile = file;
    }
}
