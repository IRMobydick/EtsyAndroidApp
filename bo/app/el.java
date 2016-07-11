package bo.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build.VERSION;
import com.appboy.Constants;
import com.appboy.support.AppboyLogger;
import com.etsy.android.lib.models.ResponseConstants;
import com.foresee.mobileReplay.perfLog.PerfDb;
import java.util.HashMap;
import java.util.Map;

public final class el extends SQLiteOpenHelper {
    private static final String f391a;
    private static volatile Map<String, el> f392b;
    private static final String f393c;
    private static final String f394d;

    static {
        f391a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, el.class.getName()});
        f392b = new HashMap();
        f393c = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT UNIQUE NOT NULL, %s INTEGER NOT NULL, %s INTEGER, %s TEXT, %s TEXT, %s REAL, %s REAL, %s INTEGER NOT NULL CHECK(%s IN (%s, %s)), %s INTEGER NOT NULL CHECK(%s IN (%s, %s)), %s INTEGER NOT NULL CHECK(%s IN (%s, %s)));", new Object[]{"ab_sessions", PerfDb.COL_ID, "session_id", "start_time", "end_time", ResponseConstants.LATITUDE, ResponseConstants.LONGITUDE, "altitude", "accuracy", "new_sent", "new_sent", Integer.valueOf(1), Integer.valueOf(0), "sealed", "sealed", Integer.valueOf(1), Integer.valueOf(0), "endtime_sent", "endtime_sent", Integer.valueOf(1), Integer.valueOf(0)});
        f394d = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s TEXT NOT NULL, %s TEXT NOT NULL, %s INTEGER NOT NULL);", new Object[]{"ab_events", PerfDb.COL_ID, "session_id", "event_type", "event_data", "timestamp"});
    }

    public static el m251a(Context context, String str) {
        String str2;
        if (fj.m354c(str)) {
            str2 = "appboy.db";
        } else {
            str2 = String.format("%s.%s", new Object[]{"appboy.db", str});
        }
        if (!f392b.containsKey(str2)) {
            synchronized (el.class) {
                if (f392b.containsKey(str2)) {
                } else {
                    el elVar = new el(context, str2);
                    f392b.put(str2, elVar);
                    return elVar;
                }
            }
        }
        return (el) f392b.get(str2);
    }

    private el(Context context, String str) {
        super(context, str, null, 2);
    }

    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        AppboyLogger.m666i(f391a, String.format("Creating %s table", new Object[]{"ab_sessions"}));
        sQLiteDatabase.execSQL(f393c);
        AppboyLogger.m666i(f391a, String.format("Creating %s table", new Object[]{"ab_events"}));
        sQLiteDatabase.execSQL(f394d);
    }

    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 == 2) {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS sessions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS appboy_events");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ab_sessions");
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS ab_events");
            onCreate(sQLiteDatabase);
            return;
        }
        AppboyLogger.m664e(f391a, String.format("Received call to onUpgrade with unknown upgrade version %d.  Please contact Appboy to report this error.", new Object[]{Integer.valueOf(i2)}));
    }

    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        AppboyLogger.m666i(f391a, String.format("Downgrading storage from version %d to %d. Dropping all current tables.", new Object[]{Integer.valueOf(i), Integer.valueOf(i2)}));
        sQLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s;", new Object[]{"ab_events"}));
        sQLiteDatabase.execSQL(String.format("DROP TABLE IF EXISTS %s;", new Object[]{"ab_sessions"}));
        onCreate(sQLiteDatabase);
    }

    public final void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (VERSION.SDK_INT >= 16) {
            sQLiteDatabase.setForeignKeyConstraintsEnabled(true);
        } else if (!sQLiteDatabase.isReadOnly()) {
            sQLiteDatabase.execSQL("PRAGMA foreign_keys = ON;");
        }
    }
}
