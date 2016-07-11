package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.UserProfile;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.APIv3Scope;
import com.etsy.android.lib.requests.EtsyRequest.EndpointType;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class UserProfileRequest extends EtsyRequest<UserProfile> {
    private static final long serialVersionUID = -4884962037586436298L;

    public UserProfileRequest(String str, RequestMethod requestMethod, String str2) {
        super(str, requestMethod, UserProfile.class, EndpointType.APIv3, str2);
    }

    public static UserProfileRequest updateUserProfile(EtsyId etsyId, String str, String str2) {
        UserProfileRequest userProfileRequest = new UserProfileRequest("/users/" + etsyId.getId() + "/profile", RequestMethod.POST, null);
        userProfileRequest.setV3Scope(APIv3Scope.MEMBER);
        userProfileRequest.addBodyParam(ResponseConstants.LAST_NAME, str2);
        userProfileRequest.addBodyParam(ResponseConstants.FIRST_NAME, str);
        return userProfileRequest;
    }
}
