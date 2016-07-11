package com.etsy.android.lib.convos;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.etsy.android.lib.R;
import com.etsy.android.lib.logger.AnalyticsLogAttribute;
import com.etsy.android.lib.logger.ITrackedObject;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class Draft implements Parcelable, ITrackedObject {
    public static Creator<Draft> CREATOR = null;
    public static final String IMAGE_DELIMITER = ":";
    protected long mConvoId;
    protected int mCursorEndPosition;
    protected int mCursorStartPosition;
    protected List<File> mImages;
    protected String mMessage;
    protected Status mStatus;
    protected String mSubject;
    protected String mUserName;

    /* renamed from: com.etsy.android.lib.convos.Draft.1 */
    final class C04391 implements Creator<Draft> {
        C04391() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m944a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m945a(i);
        }

        public Draft m944a(Parcel parcel) {
            return new Draft(parcel);
        }

        public Draft[] m945a(int i) {
            return new Draft[i];
        }
    }

    public enum Status {
        IN_DRAFT(R.convo_status_draft),
        SENDING(R.convo_status_sending),
        FAILED(R.convo_status_failed);
        
        protected int mResId;

        private Status(int i) {
            this.mResId = i;
        }

        public int getResId() {
            return this.mResId;
        }
    }

    public Draft() {
        this.mConvoId = 0;
        this.mMessage = StringUtils.EMPTY;
        this.mSubject = StringUtils.EMPTY;
        this.mUserName = StringUtils.EMPTY;
        this.mCursorStartPosition = 0;
        this.mCursorEndPosition = 0;
        this.mImages = new ArrayList(3);
        this.mStatus = Status.IN_DRAFT;
    }

    public Draft saveCursorPosition(int i, int i2) {
        this.mCursorStartPosition = i;
        this.mCursorEndPosition = i2;
        return this;
    }

    public int getCursorStartPosition() {
        return this.mCursorStartPosition;
    }

    public int getCursorEndPosition() {
        return this.mCursorEndPosition;
    }

    public Draft convoId(long j) {
        this.mConvoId = j;
        return this;
    }

    public long getConvoId() {
        return this.mConvoId;
    }

    public Draft message(String str) {
        this.mMessage = str;
        return this;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public Draft userName(String str) {
        this.mUserName = str;
        return this;
    }

    public String getUserName() {
        return this.mUserName;
    }

    public Draft subject(String str) {
        this.mSubject = str;
        return this;
    }

    public String getSubject() {
        return this.mSubject;
    }

    public Draft images(List<File> list) {
        this.mImages = list;
        return this;
    }

    public Status getStatus() {
        return this.mStatus;
    }

    public void setStatus(Status status) {
        this.mStatus = status;
    }

    public boolean isSending() {
        return this.mStatus == Status.SENDING;
    }

    public boolean isInDraft() {
        return this.mStatus == Status.IN_DRAFT;
    }

    public boolean isFailed() {
        return this.mStatus == Status.FAILED;
    }

    public List<File> getImages() {
        return this.mImages;
    }

    public void parseImageString(String str) {
        imagesFromStrings(str.split(IMAGE_DELIMITER, 3));
    }

    public String imagePathsAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        if (this.mImages != null) {
            for (int i = 0; i < this.mImages.size(); i++) {
                if (i != 0) {
                    stringBuilder.append(IMAGE_DELIMITER);
                }
                stringBuilder.append(((File) this.mImages.get(i)).getAbsolutePath());
            }
        }
        return stringBuilder.toString();
    }

    protected void imagesFromStrings(String[] strArr) {
        if (strArr != null) {
            for (Object obj : strArr) {
                if (!TextUtils.isEmpty(obj)) {
                    this.mImages.add(new File(obj));
                }
            }
        }
    }

    public Draft(Parcel parcel) {
        this.mConvoId = 0;
        this.mMessage = StringUtils.EMPTY;
        this.mSubject = StringUtils.EMPTY;
        this.mUserName = StringUtils.EMPTY;
        this.mCursorStartPosition = 0;
        this.mCursorEndPosition = 0;
        this.mImages = new ArrayList(3);
        this.mStatus = Status.IN_DRAFT;
        this.mConvoId = parcel.readLong();
        this.mMessage = parcel.readString();
        this.mSubject = parcel.readString();
        this.mUserName = parcel.readString();
        this.mCursorStartPosition = parcel.readInt();
        this.mCursorEndPosition = parcel.readInt();
        imagesFromStrings(parcel.createStringArray());
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mConvoId);
        parcel.writeString(this.mMessage);
        parcel.writeString(this.mSubject);
        parcel.writeString(this.mUserName);
        parcel.writeInt(this.mCursorStartPosition);
        parcel.writeInt(this.mCursorEndPosition);
        parcel.writeStringArray(writeImagesToStringArray());
    }

    private String[] writeImagesToStringArray() {
        if (this.mImages == null) {
            return null;
        }
        String[] strArr = new String[this.mImages.size()];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = ((File) this.mImages.get(i)).getAbsolutePath();
        }
        return strArr;
    }

    static {
        CREATOR = new C04391();
    }

    @Nullable
    public HashMap<AnalyticsLogAttribute, Object> getTrackingParameters() {
        HashMap<AnalyticsLogAttribute, Object> hashMap = new HashMap(1);
        hashMap.put(AnalyticsLogAttribute.CONVO_ID, Long.valueOf(this.mConvoId));
        return hashMap;
    }
}
