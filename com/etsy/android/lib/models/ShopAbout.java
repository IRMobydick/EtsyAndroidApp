package com.etsy.android.lib.models;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import com.etsy.android.iconsy.AbstractFontIcon;
import com.etsy.android.lib.R;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.util.fonts.EtsyFontIcons;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.gcm.Task;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.parceler.Parcel;

@Parcel
public class ShopAbout extends BaseModel {
    private static final long serialVersionUID = 5450494864709068115L;
    protected List<ShopAboutImage> mImages;
    protected RelatedLinks mLinks;
    protected List<ShopAboutMember> mMembers;
    protected String mStory;
    protected String mStoryHeadline;
    protected String mUrl;
    protected List<ShopAboutVideo> mVideos;

    @Parcel
    public class Link extends BaseModel {
        public static final String FACEBOOK_TITLE = "facebook";
        public static final String INSTAGRAM_TITLE = "instagram";
        public static final String PINTEREST_TITLE = "pinterest";
        public static final String SHOP_BLOG_TITLE = "shop-blog";
        public static final String SHOP_WEBSITE_TITLE = "shop-website";
        public static final String TWITTER_TITLE = "twitter";
        protected String mTitle;
        protected String mUrl;

        public enum LinkType {
            FACEBOOK(R.facebook, Link.FACEBOOK_TITLE, EtsyFontIcons.FACEBOOK, "facebook.com/"),
            TWITTER(R.twitter, Link.TWITTER_TITLE, EtsyFontIcons.TWITTER, "twitter.com/"),
            INSTAGRAM(R.instagram, Link.INSTAGRAM_TITLE, EtsyFontIcons.INSTAGRAM, "instagram.com/"),
            PINTEREST(R.pinterest, Link.PINTEREST_TITLE, EtsyFontIcons.PINTEREST, "pinterest.com/"),
            BLOG(R.shop_blog, Link.SHOP_BLOG_TITLE, EtsyFontIcons.LINK, StringUtils.EMPTY),
            WEBSITE(R.shop_website, Link.SHOP_WEBSITE_TITLE, EtsyFontIcons.LINK, StringUtils.EMPTY);
            
            public String baseUrl;
            public String fieldTitle;
            public AbstractFontIcon icon;
            @StringRes
            public int name;

            private LinkType(int i, String str, @StringRes AbstractFontIcon abstractFontIcon, String str2) {
                this.fieldTitle = StringUtils.EMPTY;
                this.baseUrl = StringUtils.EMPTY;
                this.name = i;
                this.fieldTitle = str;
                this.icon = abstractFontIcon;
                this.baseUrl = str2;
            }

            public static LinkType fromTitleField(String str) {
                if (TextUtils.isEmpty(str)) {
                    return null;
                }
                for (LinkType linkType : values()) {
                    if (linkType.fieldTitle.equalsIgnoreCase(str)) {
                        return linkType;
                    }
                }
                return null;
            }

            public static boolean isBaseUrlOrEmpty(String str) {
                if (TextUtils.isEmpty(str)) {
                    return true;
                }
                for (LinkType linkType : values()) {
                    if (linkType.baseUrl.equalsIgnoreCase(str)) {
                        return true;
                    }
                }
                return false;
            }

            public static int displayTitleResFromFieldName(String str) {
                LinkType fromTitleField = fromTitleField(str);
                if (fromTitleField != null) {
                    return fromTitleField.name;
                }
                return -1;
            }
        }

        public Link() {
            this.mTitle = StringUtils.EMPTY;
            this.mUrl = StringUtils.EMPTY;
        }

        public Link(@NonNull String str, @NonNull String str2) {
            this.mTitle = StringUtils.EMPTY;
            this.mUrl = StringUtils.EMPTY;
            this.mTitle = str;
            this.mUrl = str2;
        }

        public String getTitle() {
            return this.mTitle;
        }

        public String getUrl() {
            return this.mUrl;
        }

        public AbstractFontIcon getTypeIcon() {
            String str = this.mTitle;
            Object obj = -1;
            switch (str.hashCode()) {
                case -916346253:
                    if (str.equals(TWITTER_TITLE)) {
                        obj = 3;
                        break;
                    }
                    break;
                case -1034342:
                    if (str.equals(PINTEREST_TITLE)) {
                        obj = 1;
                        break;
                    }
                    break;
                case 28903346:
                    if (str.equals(INSTAGRAM_TITLE)) {
                        obj = 2;
                        break;
                    }
                    break;
                case 497130182:
                    if (str.equals(FACEBOOK_TITLE)) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case Task.NETWORK_STATE_CONNECTED /*0*/:
                    return EtsyFontIcons.FACEBOOK;
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    return EtsyFontIcons.PINTEREST;
                case Task.NETWORK_STATE_ANY /*2*/:
                    return EtsyFontIcons.INSTAGRAM;
                case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                    return EtsyFontIcons.TWITTER;
                default:
                    return EtsyFontIcons.LINK;
            }
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    if (FindsModule.FIELD_TITLE.equals(currentName)) {
                        this.mTitle = BaseModel.parseString(jsonParser);
                    } else if (ResponseConstants.URL.equals(currentName)) {
                        this.mUrl = BaseModel.parseStringURL(jsonParser);
                    } else {
                        jsonParser.skipChildren();
                    }
                }
            }
        }

        public boolean equals(Object obj) {
            if (obj instanceof Link) {
                return new EqualsBuilder().append(this.mTitle, ((Link) obj).getTitle()).append(this.mUrl, ((Link) obj).getUrl()).isEquals();
            }
            return super.equals(obj);
        }

        public int hashCode() {
            return new HashCodeBuilder().append(this.mTitle).append(this.mUrl).toHashCode();
        }

        public boolean isValid() {
            String str = this.mTitle;
            for (LinkType linkType : LinkType.values()) {
                if (linkType.fieldTitle.equals(str)) {
                    return true;
                }
            }
            return false;
        }
    }

    public class RelatedLinks extends BaseModel {
        private ArrayList<Link> mLinks;

        public RelatedLinks() {
            this.mLinks = new ArrayList();
        }

        public RelatedLinks(List<Link> list) {
            this.mLinks = new ArrayList(list);
        }

        public List<Link> getLinks() {
            return this.mLinks;
        }

        public void parseData(JsonParser jsonParser) {
            while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
                String currentName = jsonParser.getCurrentName();
                jsonParser.nextToken();
                if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                    if (currentName.contains("link-")) {
                        this.mLinks.add(BaseModel.parseObject(jsonParser, Link.class));
                    } else {
                        jsonParser.skipChildren();
                    }
                }
            }
        }
    }

    public ShopAbout() {
        this.mStoryHeadline = StringUtils.EMPTY;
        this.mStory = StringUtils.EMPTY;
        this.mUrl = StringUtils.EMPTY;
        this.mImages = new ArrayList(0);
        this.mMembers = new ArrayList(0);
    }

    public String getStoryHeadline() {
        return this.mStoryHeadline;
    }

    public String getStory() {
        return this.mStory;
    }

    public List<ShopAboutImage> getImages() {
        return this.mImages;
    }

    public List<ShopAboutMember> getMembers() {
        return this.mMembers;
    }

    public List<Link> getLinks() {
        if (this.mLinks != null) {
            return this.mLinks.getLinks();
        }
        return new ArrayList();
    }

    public List<ShopAboutVideo> getVideos() {
        return this.mVideos;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void parseData(JsonParser jsonParser) {
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
            String currentName = jsonParser.getCurrentName();
            jsonParser.nextToken();
            if (jsonParser.getCurrentToken() != JsonToken.VALUE_NULL) {
                Object obj = -1;
                switch (currentName.hashCode()) {
                    case -2101383528:
                        if (currentName.equals(Includes.IMAGES)) {
                            obj = 3;
                            break;
                        }
                        break;
                    case -1732810888:
                        if (currentName.equals(Includes.VIDEOS)) {
                            obj = 7;
                            break;
                        }
                        break;
                    case -1681432327:
                        if (currentName.equals(Includes.MEMBERS)) {
                            obj = 4;
                            break;
                        }
                        break;
                    case -1185250696:
                        if (currentName.equals(FindsModule.FIELD_IMAGES)) {
                            obj = 2;
                            break;
                        }
                        break;
                    case -816678056:
                        if (currentName.equals(ResponseConstants.VIDEOS)) {
                            obj = 8;
                            break;
                        }
                        break;
                    case 116079:
                        if (currentName.equals(ResponseConstants.URL)) {
                            obj = 9;
                            break;
                        }
                        break;
                    case 109770997:
                        if (currentName.equals(ResponseConstants.STORY)) {
                            obj = 1;
                            break;
                        }
                        break;
                    case 482755237:
                        if (currentName.equals(ResponseConstants.RELATED_LINKS)) {
                            obj = 6;
                            break;
                        }
                        break;
                    case 948881689:
                        if (currentName.equals(ResponseConstants.MEMBERS)) {
                            obj = 5;
                            break;
                        }
                        break;
                    case 1736706526:
                        if (currentName.equals(ResponseConstants.STORY_HEADLINE)) {
                            obj = null;
                            break;
                        }
                        break;
                }
                switch (obj) {
                    case Task.NETWORK_STATE_CONNECTED /*0*/:
                        this.mStoryHeadline = BaseModel.parseString(jsonParser).trim();
                        break;
                    case Task.NETWORK_STATE_UNMETERED /*1*/:
                        this.mStory = BaseModel.parseString(jsonParser).trim();
                        break;
                    case Task.NETWORK_STATE_ANY /*2*/:
                    case CommonStatusCodes.SERVICE_DISABLED /*3*/:
                        this.mImages = BaseModel.parseArray(jsonParser, ShopAboutImage.class);
                        break;
                    case CommonStatusCodes.SIGN_IN_REQUIRED /*4*/:
                    case CommonStatusCodes.INVALID_ACCOUNT /*5*/:
                        this.mMembers = BaseModel.parseArray(jsonParser, ShopAboutMember.class);
                        break;
                    case CommonStatusCodes.RESOLUTION_REQUIRED /*6*/:
                        if (jsonParser.getCurrentToken() != JsonToken.START_ARRAY) {
                            this.mLinks = (RelatedLinks) BaseModel.parseObject(jsonParser, RelatedLinks.class);
                            break;
                        } else {
                            this.mLinks = new RelatedLinks(BaseModel.parseArray(jsonParser, Link.class));
                            break;
                        }
                    case CommonStatusCodes.NETWORK_ERROR /*7*/:
                    case CommonStatusCodes.INTERNAL_ERROR /*8*/:
                        this.mVideos = BaseModel.parseArray(jsonParser, ShopAboutVideo.class);
                        break;
                    case CommonStatusCodes.SERVICE_INVALID /*9*/:
                        this.mUrl = BaseModel.parseStringPreserveHTMLEscapeEncoding(jsonParser);
                        break;
                    default:
                        jsonParser.skipChildren();
                        break;
                }
            }
        }
    }

    @Nullable
    public ShopAboutVideo getFirstReadyVideo() {
        if (this.mVideos != null) {
            for (ShopAboutVideo shopAboutVideo : this.mVideos) {
                if (shopAboutVideo.videoIsReady()) {
                    return shopAboutVideo;
                }
            }
        }
        return null;
    }

    public boolean hasProcessingVideo() {
        return (this.mVideos == null || this.mVideos.isEmpty() || this.mVideos.get(0) == null || ((ShopAboutVideo) this.mVideos.get(0)).videoIsReady() || ((ShopAboutVideo) this.mVideos.get(0)).hasError()) ? false : true;
    }
}
