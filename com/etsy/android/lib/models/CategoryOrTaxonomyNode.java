package com.etsy.android.lib.models;

public interface CategoryOrTaxonomyNode {
    Category asCategory();

    TaxonomyNode asTaxononmyNode();

    boolean hasChildren();
}
