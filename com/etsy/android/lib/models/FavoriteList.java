package com.etsy.android.lib.models;

import com.etsy.android.lib.models.datatypes.EtsyId;
import com.fasterxml.jackson.core.JsonParser;
import java.util.List;

public class FavoriteList extends BaseModel {
    private List<FavoriteListing> mListings;
    private String mLoginName;
    private int mNumFavorites;
    private EtsyId mUserId;

    public FavoriteList(EtsyId etsyId, String str, int i, List<FavoriteListing> list) {
        this.mUserId = etsyId;
        this.mListings = list;
        this.mLoginName = str;
        this.mNumFavorites = i;
    }

    public List<FavoriteListing> getListings() {
        return this.mListings;
    }

    public EtsyId getUserId() {
        return this.mUserId;
    }

    public String getLoginName() {
        return this.mLoginName;
    }

    public int getNumFavorites() {
        return this.mNumFavorites;
    }

    public void parseData(JsonParser jsonParser) {
    }
}
