package com.crittercism.app;

import android.app.AlertDialog;
import android.content.Context;
import android.location.Location;
import android.os.Build.VERSION;
import com.google.android.gms.gcm.Task;
import crittercism.android.ae;
import crittercism.android.ae.1;
import crittercism.android.ae.10;
import crittercism.android.ae.7;
import crittercism.android.ah;
import crittercism.android.ai;
import crittercism.android.bg;
import crittercism.android.bn.a;
import crittercism.android.cf;
import crittercism.android.dy;
import crittercism.android.ee;
import crittercism.android.eo;
import crittercism.android.es;
import java.net.URL;
import org.json.JSONObject;

public class Crittercism {
    private Crittercism() {
    }

    public static void performRateMyAppButtonAction(CritterRateMyAppButtons critterRateMyAppButtons) {
        try {
            if (ae.A().f.b()) {
                dy.c("User has opted out of crittercism.  performRateMyAppButtonAction exiting.");
                return;
            }
            ae A = ae.A();
            if (VERSION.SDK_INT < 5) {
                dy.c("Rate my app not supported below api level 5");
                return;
            }
            String D = A.D();
            if (D == null) {
                dy.b("Cannot create proper URI to open app market.  Returning null.");
                return;
            }
            switch (7.a[critterRateMyAppButtons.ordinal()]) {
                case Task.NETWORK_STATE_UNMETERED /*1*/:
                    try {
                        A.a(D);
                    } catch (Exception e) {
                        dy.c("performRateMyAppButtonAction(CritterRateMyAppButtons.YES) failed.  Email support@crittercism.com.");
                        dy.c();
                    }
                case Task.NETWORK_STATE_ANY /*2*/:
                    try {
                        A.C();
                    } catch (Exception e2) {
                        dy.c("performRateMyAppButtonAction(CritterRateMyAppButtons.NO) failed.  Email support@crittercism.com.");
                    }
                default:
            }
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static AlertDialog generateRateMyAppAlertDialog(Context context, String str, String str2) {
        try {
            return ae.A().a(context, str, str2);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
            return null;
        }
    }

    public static AlertDialog generateRateMyAppAlertDialog(Context context) {
        AlertDialog alertDialog = null;
        try {
            String b;
            String c;
            ae A = ae.A();
            es esVar = A.A;
            if (A.A != null) {
                b = A.A.b();
                c = A.A.c();
            } else {
                c = null;
                b = null;
            }
            alertDialog = A.a(context, c, b);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
        return alertDialog;
    }

    public static synchronized void initialize(Context context, String str, CrittercismConfig crittercismConfig) {
        synchronized (Crittercism.class) {
            if (a.a(str) == null) {
                throw new IllegalArgumentException("Invalid appID '" + str + "'. Crittercism cannot be initialized");
            }
            if (str == null) {
                try {
                    m678a(String.class.getCanonicalName());
                } catch (ThreadDeath e) {
                    throw e;
                } catch (Throwable th) {
                    dy.a(th);
                }
            } else if (context == null) {
                m678a(Context.class.getCanonicalName());
            } else if (crittercismConfig == null) {
                m678a(CrittercismConfig.class.getCanonicalName());
            } else if (!ae.A().b) {
                try {
                    long nanoTime = System.nanoTime();
                    ae.A().a(context, str, crittercismConfig);
                    new StringBuilder("Crittercism finished initializing in ").append((System.nanoTime() - nanoTime) / 1000000).append("ms");
                    dy.b();
                } catch (Exception e2) {
                    new StringBuilder("Exception in init > getInstance().initialize(..): ").append(e2.getClass().getName());
                    dy.b();
                }
            }
        }
    }

    public static synchronized void initialize(Context context, String str) {
        synchronized (Crittercism.class) {
            initialize(context, str, new CrittercismConfig());
        }
    }

    private static void m678a(String str) {
        dy.b("Crittercism cannot be initialized", new NullPointerException(str + " was null"));
    }

    public static void sendAppLoadData() {
        try {
            ah ahVar = ae.A().u;
            if (ahVar == null) {
                m679b("sendAppLoadData");
            } else if (!ahVar.delaySendingAppLoad()) {
                dy.a("sendAppLoadData() will only send data to Crittercism if \"delaySendingAppLoad\" is set to true in the configuration settings you include in the init call.");
            } else if (!ae.A().f.b()) {
                ae A = ae.A();
                if (!A.u.delaySendingAppLoad()) {
                    dy.c("CrittercismConfig instance not set to delay sending app loads.");
                } else if (!A.t && !A.C) {
                    A.C = true;
                    1 1 = new 1(A);
                    if (!A.q.a(1)) {
                        A.s.execute(1);
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void logHandledException(Throwable th) {
        try {
            if (!ae.A().b) {
                m679b("logHandledException");
            } else if (!ae.A().f.b()) {
                ae.A().b(th);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th2) {
            dy.a(th2);
        }
    }

    public static void logNetworkRequest(String str, URL url, long j, long j2, long j3, int i, Exception exception) {
        try {
            long currentTimeMillis = System.currentTimeMillis() - j;
            if (!ae.A().b) {
                m679b("logEndpoint");
            } else if (!ae.A().f.b()) {
                ae.A().a(str, url, j, j2, j3, i, exception, currentTimeMillis);
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static boolean didCrashOnLastLoad() {
        boolean z = false;
        try {
            ae A = ae.A();
            if (!A.b) {
                m679b("didCrashOnLoad");
            } else if (!A.B()) {
                A.e.block();
                z = eo.a;
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
        return z;
    }

    public static boolean getOptOutStatus() {
        boolean z = false;
        try {
            ae A = ae.A();
            if (A.b) {
                z = A.B();
            } else {
                m679b("getOptOutStatus");
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
        return z;
    }

    public static void setOptOutStatus(boolean z) {
        try {
            if (ae.A().b) {
                ae A = ae.A();
                ee eeVar = new ee(A.c, A, z);
                if (!A.q.a(eeVar)) {
                    A.s.execute(eeVar);
                    return;
                }
                return;
            }
            m679b("setOptOutStatus");
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void setMetadata(JSONObject jSONObject) {
        try {
            if (ae.A().b) {
                ae.A().a(jSONObject);
            } else {
                m679b("setMetadata");
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void setUsername(String str) {
        try {
            if (!ae.A().b) {
                m679b("setUsername");
            } else if (str == null) {
                dy.c("Crittercism.setUsername() given invalid parameter: null");
            } else {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.putOpt("username", str);
                    ae.A().a(jSONObject);
                } catch (Throwable e) {
                    dy.b("Crittercism.setUsername()", e);
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable e3) {
            dy.a(e3);
        }
    }

    public static void leaveBreadcrumb(String str) {
        try {
            if (!ae.A().b) {
                m679b("leaveBreadcrumb");
            } else if (str == null) {
                dy.b("Cannot leave null breadcrumb", new NullPointerException());
            } else {
                ae A = ae.A();
                if (!A.f.b()) {
                    10 10 = new 10(A, new cf(str, cf.a.a));
                    if (!A.q.a(10)) {
                        new StringBuilder("SENDING ").append(str).append(" TO EXECUTOR");
                        dy.b();
                        A.s.execute(10);
                    }
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void beginTransaction(String str) {
        try {
            ae A = ae.A();
            if (A.t) {
                dy.c("Transactions are not supported for services. Ignoring Crittercism.beginTransaction() call for " + str + ".");
                return;
            }
            Transaction a = Transaction.m686a(str);
            if (a instanceof bg) {
                synchronized (A.z) {
                    Transaction transaction = (Transaction) A.z.remove(str);
                    if (transaction != null) {
                        ((bg) transaction).g();
                    }
                    if (A.z.size() > 50) {
                        dy.c("Crittercism only supports a maximum of 50 concurrent transactions. Ignoring Crittercism.beginTransaction() call for " + str + ".");
                        return;
                    }
                    A.z.put(str, a);
                    a.m687a();
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void endTransaction(String str) {
        try {
            ae A = ae.A();
            if (A.t) {
                dy.c("Transactions are not supported for services. Ignoring Crittercism.endTransaction() call for " + str + ".");
                return;
            }
            Transaction transaction;
            synchronized (A.z) {
                transaction = (Transaction) A.z.remove(str);
            }
            if (transaction != null) {
                transaction.m689b();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void failTransaction(String str) {
        try {
            ae A = ae.A();
            if (A.t) {
                dy.c("Transactions are not supported for services. Ignoring Crittercism.failTransaction() call for " + str + ".");
                return;
            }
            Transaction transaction;
            synchronized (A.z) {
                transaction = (Transaction) A.z.remove(str);
            }
            if (transaction != null) {
                transaction.m690c();
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static void setTransactionValue(String str, int i) {
        try {
            ae A = ae.A();
            if (A.t) {
                dy.c("Transactions are not supported for services. Ignoring Crittercism.setTransactionValue() call for " + str + ".");
                return;
            }
            synchronized (A.z) {
                Transaction transaction = (Transaction) A.z.get(str);
                if (transaction != null) {
                    transaction.m688a(i);
                }
            }
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
        }
    }

    public static int getTransactionValue(String str) {
        try {
            return ae.A().b(str);
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            dy.a(th);
            return -1;
        }
    }

    public static void updateLocation(Location location) {
        if (!ae.A().b) {
            m679b("updateLocation");
        } else if (location == null) {
            dy.b("Cannot leave null location", new NullPointerException());
        } else {
            ai.a(location);
        }
    }

    private static void m679b(String str) {
        dy.b("Must initialize Crittercism before calling " + Crittercism.class.getName() + "." + str + "().  Request is being ignored...", new IllegalStateException());
    }
}
