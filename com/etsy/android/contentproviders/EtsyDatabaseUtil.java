package com.etsy.android.contentproviders;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.SearchRecentSuggestions;
import com.etsy.android.lib.convos.contentprovider.ConvoDatabaseUtil;
import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.BaseModelImage.ImageOrientation;
import com.etsy.android.lib.models.Listing;
import com.etsy.android.lib.models.ListingImage;
import com.etsy.android.lib.models.LocalMarket;
import com.etsy.android.lib.models.ResponseConstants;
import com.etsy.android.lib.models.datatypes.EtsyId;
import com.etsy.android.lib.models.finds.FindsModule;
import com.etsy.android.lib.requests.EtsyRequest;
import java.util.ArrayList;

/* renamed from: com.etsy.android.contentproviders.a */
public class EtsyDatabaseUtil {
    private static final String f1093a;

    /* renamed from: com.etsy.android.contentproviders.a.1 */
    final class EtsyDatabaseUtil extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Context f1089a;

        EtsyDatabaseUtil(Context context) {
            this.f1089a = context;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m730a((Void[]) objArr);
        }

        protected Void m730a(Void... voidArr) {
            EtsyDatabaseUtil.m755g(this.f1089a);
            return null;
        }
    }

    /* renamed from: com.etsy.android.contentproviders.a.2 */
    final class EtsyDatabaseUtil extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Context f1090a;
        final /* synthetic */ String f1091b;

        EtsyDatabaseUtil(Context context, String str) {
            this.f1090a = context;
            this.f1091b = str;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m731a((Void[]) objArr);
        }

        protected Void m731a(Void... voidArr) {
            this.f1090a.getContentResolver().delete(EtsyProvider.f1097a, "listing_id=" + this.f1091b, null);
            return null;
        }
    }

    /* renamed from: com.etsy.android.contentproviders.a.3 */
    final class EtsyDatabaseUtil extends AsyncTask<Void, Void, Void> {
        final /* synthetic */ Context f1092a;

        EtsyDatabaseUtil(Context context) {
            this.f1092a = context;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m732a((Void[]) objArr);
        }

        protected Void m732a(Void... voidArr) {
            EtsyDatabaseUtil.m736a(this.f1092a, EtsyProvider.f1094a);
            EtsyDatabaseUtil.m745b(this.f1092a, EtsyProvider.f1095a);
            EtsyDatabaseUtil.m755g(this.f1092a);
            EtsyDatabaseUtil.m751d(this.f1092a);
            ConvoDatabaseUtil.m979c(this.f1092a);
            return null;
        }
    }

    static {
        f1093a = EtsyDebug.m1891a(EtsyDatabaseUtil.class);
    }

    public static void m737a(Context context, Listing listing) {
        if (listing != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ContentProviderOperation.newInsert(EtsyProvider.f1096a).withValues(EtsyDatabaseUtil.m734a(listing, listing.isFavorite())).build());
            String id = listing.getListingId().getId();
            arrayList.add(ContentProviderOperation.newDelete(EtsyProvider.f1097a).withSelection("listing_id=" + id, null).build());
            arrayList.add(ContentProviderOperation.newInsert(EtsyProvider.f1097a).withValue(ResponseConstants.LISTING_ID, id).withValue("view_time", Long.valueOf(System.currentTimeMillis())).build());
            EtsyDatabaseUtil.m742a(context, arrayList);
        }
    }

    public static void m738a(Context context, LocalMarket localMarket) {
        if (localMarket != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(ContentProviderOperation.newInsert(EtsyProvider.f1098a).withValue(ResponseConstants.LOCAL_MARKET_ID, localMarket.getLocalMarketId().getId()).withValue("view_time", Long.valueOf(System.currentTimeMillis())).build());
            EtsyDatabaseUtil.m742a(context, arrayList);
        }
    }

    public static int m733a(Context context) {
        int i = 0;
        Cursor query = context.getContentResolver().query(EtsyProvider.f1097a, null, null, null, null);
        if (query != null) {
            int count = query.getCount();
            query.close();
            i = count;
        }
        Cursor query2 = context.getContentResolver().query(EtsyProvider.f1098a, null, null, null, null);
        if (query2 == null) {
            return i;
        }
        i += query2.getCount();
        query2.close();
        return i;
    }

    public static void m744b(Context context) {
        new EtsyDatabaseUtil(context).execute(new Void[0]);
    }

    public static void m740a(Context context, String str) {
        new EtsyDatabaseUtil(context, str).execute(new Void[0]);
    }

    private static void m755g(Context context) {
        context.getContentResolver().delete(EtsyProvider.f1097a, null, null);
        context.getContentResolver().delete(EtsyProvider.f1098a, null, null);
    }

    private static boolean m743a(Uri uri) {
        return uri == null || !uri.equals(EtsyProvider.f1094a);
    }

    public static void m736a(Context context, Uri uri) {
        if (EtsyDatabaseUtil.m743a(uri)) {
            EtsyDebug.m1918d(f1093a, "clearFeedItems - invalid feedUri(%s) - ignoring", uri);
            return;
        }
        EtsyDebug.m1914c(f1093a, "clearFeedItems - feedUri(%s)", uri);
        context.getContentResolver().delete(uri, null, null);
    }

    private static boolean m748b(Uri uri) {
        return uri == null || !uri.equals(EtsyProvider.f1095a);
    }

    public static void m745b(Context context, Uri uri) {
        if (EtsyDatabaseUtil.m748b(uri)) {
            EtsyDebug.m1918d(f1093a, "clearActivityFeedItems - invalid feedUri(%s) - ignoring", uri);
            return;
        }
        EtsyDebug.m1914c(f1093a, "clearActivityFeedItems - feedUri(%s)", uri);
        context.getContentResolver().delete(uri, null, null);
    }

    private static ContentValues m734a(Listing listing, boolean z) {
        return EtsyDatabaseUtil.m735a(listing, z, false);
    }

    private static ContentValues m735a(Listing listing, boolean z, boolean z2) {
        boolean z3 = false;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ResponseConstants.LISTING_ID, listing.getListingId().getId());
        contentValues.put("favorite", Boolean.valueOf(z));
        contentValues.put("in_collection", Boolean.valueOf(z2));
        contentValues.put(FindsModule.FIELD_TITLE, listing.getTitle());
        contentValues.put(ResponseConstants.PRICE, listing.getPrice().formatWithCurrencyCode());
        contentValues.put(EtsyRequest.PARAM_CURRENCY, listing.getCurrencyCode());
        if (listing.getImages().size() > 0) {
            ListingImage listingImage = (ListingImage) listing.getImages().get(0);
            contentValues.put(ResponseConstants.IMAGE_URL, listingImage.getUrl570xN());
            contentValues.put(ResponseConstants.FULL_WIDTH, Integer.valueOf(listingImage.getFullWidth()));
            contentValues.put(ResponseConstants.FULL_HEIGHT, Integer.valueOf(listingImage.getFullHeight()));
            String str = "is_portrait";
            if (listingImage.getImageOrientation() == ImageOrientation.PORTRAIT) {
                z3 = true;
            }
            contentValues.put(str, Boolean.valueOf(z3));
            contentValues.put("image_color", Integer.valueOf(listingImage.getImageColor()));
        }
        if (listing.getShop() != null) {
            contentValues.put(ResponseConstants.SHOP_NAME, listing.getShop().getShopName());
        }
        contentValues.put("view_time", Long.valueOf(System.currentTimeMillis()));
        return contentValues;
    }

    public static void m739a(Context context, EtsyId etsyId, boolean z) {
        EtsyDebug.m1914c(f1093a, "updateListingFavoriteState listingId:%s isFavorite:%b", etsyId.getId(), Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Boolean.valueOf(z));
        String[] strArr = new String[]{etsyId.getId()};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(EtsyProvider.m756a(etsyId)).withValues(contentValues).withSelection("listing_id = ?", strArr).build());
        EtsyDatabaseUtil.m742a(context, arrayList);
    }

    public static void m746b(Context context, EtsyId etsyId, boolean z) {
        EtsyDebug.m1914c(f1093a, "updateListingCollectionState listingId:%s isInCollection:%b", etsyId.getId(), Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("in_collection", Boolean.valueOf(z));
        String[] strArr = new String[]{etsyId.getId()};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(EtsyProvider.m756a(etsyId)).withValues(contentValues).withSelection("listing_id = ?", strArr).build());
        EtsyDatabaseUtil.m742a(context, arrayList);
    }

    public static void m750c(Context context, EtsyId etsyId, boolean z) {
        EtsyDebug.m1914c(f1093a, "updateShopFavoriteState shopUserId:%s isFavorite:%b", etsyId, Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Boolean.valueOf(z));
        String[] strArr = new String[]{String.valueOf(etsyId)};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(EtsyProvider.m757a(etsyId)).withValues(contentValues).withSelection("user_id = ?", strArr).build());
        EtsyDatabaseUtil.m742a(context, arrayList);
    }

    public static void m752d(Context context, EtsyId etsyId, boolean z) {
        EtsyDebug.m1914c(f1093a, "updateUserFollowState userId:%s isFollowed:%b", String.valueOf(etsyId), Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("followed", Boolean.valueOf(z));
        String[] strArr = new String[]{String.valueOf(etsyId)};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(EtsyProvider.f1102a).withValues(contentValues).withSelection("user_id = ?", strArr).build());
        EtsyDatabaseUtil.m742a(context, arrayList);
    }

    public static void m741a(Context context, String str, boolean z) {
        EtsyDebug.m1914c(f1093a, "updateTreasuryFavoriteState treasuryId:%s isFavorite:%b", str, Boolean.valueOf(z));
        ContentValues contentValues = new ContentValues();
        contentValues.put("favorite", Boolean.valueOf(z));
        String[] strArr = new String[]{str};
        ArrayList arrayList = new ArrayList();
        arrayList.add(ContentProviderOperation.newUpdate(EtsyProvider.f1101a).withValues(contentValues).withSelection("treasury_id = ?", strArr).build());
        EtsyDatabaseUtil.m742a(context, arrayList);
    }

    public static int m749c(Context context) {
        Cursor query = context.getContentResolver().query(EtsyProvider.f1099a, null, null, null, null);
        int count = query.getCount();
        query.close();
        return count;
    }

    public static void m747b(Context context, String str) {
        new SearchRecentSuggestions(context, EtsyProvider.AUTHORITY, 1).saveRecentQuery(str, null);
    }

    public static void m751d(Context context) {
        new SearchRecentSuggestions(context, EtsyProvider.AUTHORITY, 1).clearHistory();
    }

    public static void m753e(Context context) {
        new EtsyDatabaseUtil(context).execute(new Void[0]);
    }

    private static void m742a(Context context, ArrayList<ContentProviderOperation> arrayList) {
        try {
            context.getContentResolver().applyBatch(EtsyProvider.AUTHORITY, arrayList);
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1093a, "RemoteException on applyBatch", e);
        } catch (Throwable e2) {
            EtsyDebug.m1917d(f1093a, "OperationApplicationException on applyBatch", e2);
        }
    }
}
