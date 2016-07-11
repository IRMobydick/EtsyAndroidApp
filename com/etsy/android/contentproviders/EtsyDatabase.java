package com.etsy.android.contentproviders;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.etsy.android.lib.logger.EtsyDebug;

public class EtsyDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "etsy_data";
    private static final int DB_VERSION = 44;
    private static final String TAG;
    private static final int VERSION_BEFORE_LOCAL_HISTORY = 43;

    static {
        TAG = EtsyDebug.m1891a(EtsyDatabase.class);
    }

    public EtsyDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE  TABLE history (_id integer primary key autoincrement, listing_id text  unique,view_time integer );");
        sQLiteDatabase.execSQL("CREATE  TABLE listing (_id integer primary key autoincrement, listing_id text unique, title text  not  null , price text , shop_name text , currency text , favorite boolean not null, in_collection boolean not null, image_url text  not  null , full_width integer , full_height integer , is_portrait boolean , view_time integer , image_color integer  not  null , is_sold_out boolean );");
        sQLiteDatabase.execSQL("CREATE  TABLE shop (_id integer primary key autoincrement, shop_id text unique, user_id text  not  null , title text  not  null , item_id_list text , avatar_url text , favorite boolean not null, item_img_list text , item_img_color_list text , rating integer , review_count integer );");
        sQLiteDatabase.execSQL("CREATE  TABLE user (_id integer primary key autoincrement, user_id text unique, title text  not  null , followed boolean not null, item_id_list text , avatar_url text , item_img_list text , item_img_color_list text);");
        sQLiteDatabase.execSQL("CREATE  TABLE treasury (_id integer primary key autoincrement, treasury_id text unique, title text  not  null , favorite boolean not null, item_id_list text , item_img_list text , item_img_color_list text , owner_name text );");
        sQLiteDatabase.execSQL("CREATE  TABLE activity (_id integer primary key autoincrement, listing_id text , shop_id text , user_id text , treasury_id text , item_id text , type text  not  null , title text  not  null , action text  not  null , source_user_id text  not  null , source_name text  not  null , source_image text  not  null , source_sentence text not null);");
        sQLiteDatabase.execSQL("CREATE  TABLE activity_feed (_id integer primary key autoincrement, verb_id integer  not  null , object_id integer  not  null , object_type text  not  null , title text  not  null , image1 text  not  null , image2 text , image3 text , image4 text , image_color1 text , image_color2 text , image_color3 text , image_color4 text , subject_id integer  not  null , subject_type text  not  null , subject_name text , subject_is_current_user boolean , subject_avatar_url text , item_count integer , context_sentence text , refavorite_count integer );");
        sQLiteDatabase.execSQL("CREATE  TABLE segment (_id integer primary key autoincrement, name text  not  null , path text  not  null , parent_path text , image_url text  not  null , shop_id integer not null, shop_name text  not  null , listing_id integer not null,has_children boolean not null, weight integer not null);");
        sQLiteDatabase.execSQL("CREATE  TABLE local_market_history (_id integer primary key autoincrement, local_market_id text  unique,view_time integer );");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        EtsyDebug.m1906b(TAG, "Upgrading database. Existing contents will be lost. [" + i + "]->[" + i2 + "]");
        if (i != DB_VERSION) {
            switch (i) {
                case VERSION_BEFORE_LOCAL_HISTORY /*43*/:
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS local_market_history");
                    sQLiteDatabase.execSQL("CREATE  TABLE local_market_history (_id integer primary key autoincrement, local_market_id text  unique,view_time integer );");
                default:
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS history");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS listing");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS shop");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS user");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS treasury");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS activity");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS activity_feed");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS segment");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS local_market_history");
                    sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS popular");
                    onCreate(sQLiteDatabase);
            }
        }
    }

    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 29 && i2 == 27) {
            EtsyDebug.m1912c(TAG, "Down grading database. Dropping convo tables");
            sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS convos");
            sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS convo_draft");
            return;
        }
        super.onDowngrade(sQLiteDatabase, i, i2);
    }
}
