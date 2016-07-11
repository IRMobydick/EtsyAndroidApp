package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.User;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;
import java.util.HashMap;
import java.util.Map;

public class CirclesRequest extends EtsyRequest<User> {
    private static final String FIELDS = "user_id,login_name,creation_tsz,following_count,follower_count";
    private static final String INCLUDES = "Profile(image_url_75x75,city,transaction_sold_count,is_seller,bio,first_name,last_name,login_name)/Country(name)";
    private static final long serialVersionUID = 4638979632761521011L;

    public CirclesRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, User.class);
    }

    public static CirclesRequest findUserInCircle(EtsyId etsyId) {
        CirclesRequest circlesRequest = new CirclesRequest("/users/__SELF__/connected_users/" + etsyId.getId(), RequestMethod.GET);
        Map hashMap = new HashMap();
        hashMap.put("fields", FIELDS);
        hashMap.put("includes", INCLUDES);
        circlesRequest.addParams(hashMap);
        return circlesRequest;
    }

    public static CirclesRequest connectToUser(String str) {
        CirclesRequest circlesRequest = new CirclesRequest("/users/__SELF__/connected_users", RequestMethod.POST);
        Map hashMap = new HashMap();
        hashMap.put("to_user_id", str);
        hashMap.put("fields", FIELDS);
        hashMap.put("includes", INCLUDES);
        circlesRequest.addParams(hashMap);
        return circlesRequest;
    }

    public static CirclesRequest removeFromUser(String str) {
        CirclesRequest circlesRequest = new CirclesRequest("/users/__SELF__/connected_users", RequestMethod.DELETE);
        Map hashMap = new HashMap();
        hashMap.put("to_user_id", str);
        hashMap.put("fields", FIELDS);
        hashMap.put("includes", INCLUDES);
        circlesRequest.addParams(hashMap);
        return circlesRequest;
    }

    public static CirclesRequest getMembersOfUsersCircle(EtsyId etsyId) {
        return new CirclesRequest("/users/" + etsyId.getId() + "/connected_users", RequestMethod.GET);
    }

    public static CirclesRequest getMembersOfMyCircle() {
        return new CirclesRequest("/users/__SELF__/connected_users", RequestMethod.GET);
    }

    public static CirclesRequest getCirclesContainingUser(EtsyId etsyId) {
        return new CirclesRequest("/users/" + etsyId.getId() + "/circles", RequestMethod.GET);
    }

    public static CirclesRequest getCirclesContainingMe() {
        return new CirclesRequest("/users/__SELF__/circles", RequestMethod.GET);
    }
}
