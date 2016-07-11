package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.ResponseConstants;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.ArrayList;
import java.util.List;

public class ActivityFeedStory extends BaseModel {
    private static final String ACTIVITY_ID = "activity_id";
    private static final String CONTEXT_SENTENCES = "context_sentences";
    private static final String OBJECTS = "objects";
    private static final String OWNERS = "owners";
    private static final String REFAVORITE = "refavorite";
    private static final String SUBJECTS = "subjects";
    private static final String VERB = "verb";
    public static final int VERB_CONNECTED = 12;
    public static final int VERB_CREATED = 5;
    public static final int VERB_FAVORITED = 1;
    public static final int VERB_FEATURED = 11;
    private static final String VERB_ID = "verb_id";
    public static final int VERB_LISTED = 7;
    public static final int VERB_RECOMMENDED = 19;
    private static final long serialVersionUID = -4441235680394650493L;
    private List<String> mContextSentences;
    private long mId;
    private List<ActivityFeedEntity> mObjects;
    private List<ActivityFeedSubjectEntity> mOwners;
    private int mRefavoriteCount;
    private List<ActivityFeedSubjectEntity> mSubjects;
    private String mVerb;
    private int mVerbId;

    public long getId() {
        return this.mId;
    }

    public int getVerbId() {
        return this.mVerbId;
    }

    public String getVerb() {
        return this.mVerb;
    }

    public List<ActivityFeedSubjectEntity> getOwners() {
        return this.mOwners;
    }

    public List<ActivityFeedEntity> getObjects() {
        return this.mObjects;
    }

    public List<ActivityFeedSubjectEntity> getSubjects() {
        return this.mSubjects;
    }

    public List<String> getContextSentences() {
        return this.mContextSentences;
    }

    public int getRefavoriteCount() {
        return this.mRefavoriteCount;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                if (ACTIVITY_ID.equals(currentName)) {
                    this.mId = jsonParser.getValueAsLong();
                }
                if (VERB_ID.equals(currentName)) {
                    this.mVerbId = jsonParser.getValueAsInt();
                } else if (VERB.equals(currentName)) {
                    this.mVerb = BaseModel.parseString(jsonParser);
                } else if (OWNERS.equals(currentName)) {
                    this.mOwners = BaseModel.parseArray(jsonParser, ActivityFeedSubjectEntity.class);
                } else if (OBJECTS.equals(currentName)) {
                    this.mObjects = BaseModel.parseArray(jsonParser, ActivityFeedEntity.class);
                } else if (SUBJECTS.equals(currentName)) {
                    this.mSubjects = BaseModel.parseArray(jsonParser, ActivityFeedSubjectEntity.class);
                } else if (CONTEXT_SENTENCES.equals(currentName)) {
                    if (jsonParser.getCurrentToken() == JsonToken.START_ARRAY) {
                        this.mContextSentences = new ArrayList();
                        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
                            if (jsonParser.getCurrentToken() == JsonToken.VALUE_STRING) {
                                this.mContextSentences.add(BaseModel.parseString(jsonParser));
                            }
                        }
                    }
                } else if (REFAVORITE.equals(currentName)) {
                    this.mRefavoriteCount = ((JsonNode) jsonParser.readValueAsTree()).get(ResponseConstants.COUNT).asInt();
                } else {
                    jsonParser.skipChildren();
                }
            }
        }
    }
}
