package com.etsy.android.lib.requests;

import com.etsy.android.lib.models.Category;
import com.etsy.android.lib.requests.EtsyRequest.RequestMethod;

public class TaxonomyRequest extends EtsyRequest<Category> {
    private static final long serialVersionUID = -3963093237063702726L;

    public TaxonomyRequest(String str, RequestMethod requestMethod) {
        super(str, requestMethod, Category.class);
    }

    public static TaxonomyRequest findAllTopCategory() {
        return new TaxonomyRequest("/taxonomy/categories", RequestMethod.GET);
    }

    public static TaxonomyRequest findAllTopCategoryChildren(String str) {
        return new TaxonomyRequest("/taxonomy/categories/" + str, RequestMethod.GET);
    }
}
