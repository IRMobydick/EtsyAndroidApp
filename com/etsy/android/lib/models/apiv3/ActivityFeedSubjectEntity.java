package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.fasterxml.jackson.core.JsonParser;

public class ActivityFeedSubjectEntity extends ActivityFeedEntity {
    private static final long serialVersionUID = 4353918611676986449L;

    protected void parseField(JsonParser jsonParser, String str) {
        if (!ActivityFeedEntity.DATA.equals(str)) {
            super.parseField(jsonParser, str);
        } else if (ActivityFeedEntity.DATASET.equals(this.mType)) {
            parseField(jsonParser, ActivityFeedEntity.DATASET);
        } else {
            this.mData = BaseModel.parseObject(jsonParser, ActivityFeedSubject.class);
        }
    }
}
