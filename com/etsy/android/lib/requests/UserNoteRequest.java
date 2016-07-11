package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.BaseModel;
import com.etsy.android.lib.models.UserNote;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class UserNoteRequest<Result extends BaseModel> extends EtsyRequest<Result> {
    private static final long serialVersionUID = -1424044279750512005L;

    public UserNoteRequest(String str, RequestMethod requestMethod, Class<Result> cls) {
        super(str, requestMethod, cls);
    }

    public static UserNoteRequest<UserNote> createPrivateOrderNote(EtsyId etsyId, String str) {
        UserNoteRequest<UserNote> userNoteRequest = new UserNoteRequest("/usernote", RequestMethod.POST, UserNote.class);
        Map hashMap = new HashMap();
        hashMap.put("subject_type", UserNote.SUBJECT_TYPE_RECEIPT);
        hashMap.put("subject_id", etsyId.getId());
        hashMap.put("note", str);
        userNoteRequest.addParams(hashMap);
        return userNoteRequest;
    }

    public static UserNoteRequest<UserNote> updatePrivateOrderNote(EtsyId etsyId, String str) {
        UserNoteRequest<UserNote> userNoteRequest = new UserNoteRequest("/usernote/" + etsyId.getId(), RequestMethod.PUT, UserNote.class);
        Map hashMap = new HashMap();
        hashMap.put("note", str);
        userNoteRequest.addParams(hashMap);
        return userNoteRequest;
    }

    public static UserNoteRequest<UserNote> deleteNote(EtsyId etsyId) {
        return new UserNoteRequest("/usernote/" + etsyId.getId(), RequestMethod.DELETE, UserNote.class);
    }
}
