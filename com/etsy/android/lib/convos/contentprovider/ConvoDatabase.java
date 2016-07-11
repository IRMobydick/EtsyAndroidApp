package com.etsy.android.lib.convos.contentprovider;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.etsy.android.lib.logger.EtsyDebug;

public class ConvoDatabase extends SQLiteOpenHelper {
    public static final String DB_NAME = "convos_database";
    private static final int DB_VERSION = 6;
    private static final String TAG;

    static {
        TAG = EtsyDebug.m1891a(ConvoDatabase.class);
    }

    public ConvoDatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        setJournalModeToTruncate(sQLiteDatabase);
        createTables(sQLiteDatabase);
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        EtsyDebug.m1906b(TAG, "Upgrading database. Existing contents will be lost. [" + i + "]->[" + i2 + "]");
        sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS convos");
        if (i < 5) {
            sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS convo_draft");
        }
        onCreate(sQLiteDatabase);
    }

    public void setJournalModeToTruncate(SQLiteDatabase sQLiteDatabase) {
        Cursor rawQuery;
        try {
            rawQuery = sQLiteDatabase.rawQuery("PRAGMA journal_mode=TRUNCATE", null);
            if (rawQuery != null) {
                rawQuery.moveToFirst();
                rawQuery.close();
            }
        } catch (Throwable e) {
            EtsyDebug.m1897a(TAG, "Problem setting journal mode to truncate", e);
        } catch (Throwable th) {
            rawQuery.close();
        }
    }

    public void recreateTables() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        deleteTables(writableDatabase);
        createTables(writableDatabase);
    }

    private void createTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE  TABLE  IF  not  EXISTS convos ( _id integer  primary key autoincrement , conversation_id integer  not  null , message_count integer , is_read boolean  not  null , has_attachment boolean , title text  not  null , last_message integer  not  null , last_updated_tsz integer , is_custom_shop boolean , other_user_id integer  not  null , other_user_name_user text  not  null , other_user_name_full text  not  null , other_user_avatar_url text );");
        sQLiteDatabase.execSQL("CREATE  TABLE  IF  not  EXISTS convo_draft ( _id integer  primary key autoincrement , conversation_id integer  not  null , addressee text  not  null , subject text  not  null , message text  not  null , has_images boolean  not  null , image_filenames text , send_status text , unique( conversation_id) ON  CONFLICT REPLACE );");
        sQLiteDatabase.execSQL("CREATE  TABLE  IF  not  EXISTS snippets ( _id integer  primary key autoincrement , snippet_id text , title text , content text );");
    }

    private void deleteTables(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS convos");
        sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS convo_draft");
        sQLiteDatabase.execSQL("DROP  TABLE  IF  EXISTS snippets");
    }
}
