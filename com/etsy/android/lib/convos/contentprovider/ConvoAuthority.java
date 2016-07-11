package com.etsy.android.lib.convos.contentprovider;

import android.content.UriMatcher;
import android.net.Uri;
import com.etsy.android.lib.core.EtsyApplication;

/* renamed from: com.etsy.android.lib.convos.contentprovider.a */
public class ConvoAuthority {
    public static Uri m961a() {
        return Uri.parse("content://" + EtsyApplication.get().getConvoAuthority());
    }

    public static Uri m962a(ConvoPaths convoPaths) {
        if (convoPaths == ConvoPaths.CONVO) {
            return ConvoAuthority.m961a().buildUpon().appendPath(convoPaths.getPath()).build();
        }
        return ConvoAuthority.m961a().buildUpon().appendPath(ConvoPaths.CONVO.getPath()).appendPath(convoPaths.getPath()).build();
    }

    public static Uri m963a(ConvoPaths convoPaths, long j) {
        return ConvoAuthority.m962a(convoPaths).buildUpon().appendPath(String.valueOf(j)).build();
    }

    public static UriMatcher m960a(String str) {
        UriMatcher uriMatcher = new UriMatcher(-1);
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.CONTACTS.getPath() + "/" + "*", ConvoPaths.CONTACTS.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.CONTACT.getPath(), ConvoPaths.CONTACT.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.DRAFTS.getPath() + "/" + "*", ConvoPaths.DRAFTS.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.DRAFT.getPath(), ConvoPaths.DRAFT.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.ERASE.getPath(), ConvoPaths.ERASE.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.SNIPPETS.getPath() + "/" + "*", ConvoPaths.SNIPPETS.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath() + "/" + ConvoPaths.SNIPPET.getPath(), ConvoPaths.SNIPPET.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVO.getPath(), ConvoPaths.CONVO.getMatch());
        uriMatcher.addURI(str, ConvoPaths.CONVOS.getPath() + "/" + "*", ConvoPaths.CONVOS.getMatch());
        return uriMatcher;
    }
}
