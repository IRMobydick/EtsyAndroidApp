package bo.app;

import com.etsy.android.uikit.view.ListingFullImageView;
import com.foresee.mobileReplay.recorder.ScreenRecorder;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

final class fr implements Closeable {
    static final Pattern f476a;
    private static final OutputStream f477r;
    final ThreadPoolExecutor f478b;
    private final File f479c;
    private final File f480d;
    private final File f481e;
    private final File f482f;
    private final int f483g;
    private long f484h;
    private int f485i;
    private final int f486j;
    private long f487k;
    private int f488l;
    private Writer f489m;
    private final LinkedHashMap<String, fw> f490n;
    private int f491o;
    private long f492p;
    private final Callable<Void> f493q;

    static {
        f476a = Pattern.compile("[a-z0-9_-]{1,64}");
        f477r = new ft();
    }

    private fr(File file, long j, int i) {
        this.f487k = 0;
        this.f488l = 0;
        this.f490n = new LinkedHashMap(0, ListingFullImageView.ASPECT_RATIO_STANDARD, true);
        this.f492p = 0;
        this.f478b = new ThreadPoolExecutor(0, 1, 60, TimeUnit.SECONDS, new LinkedBlockingQueue());
        this.f493q = new fs(this);
        this.f479c = file;
        this.f483g = 1;
        this.f480d = new File(file, "journal");
        this.f481e = new File(file, "journal.tmp");
        this.f482f = new File(file, "journal.bkp");
        this.f486j = 1;
        this.f484h = j;
        this.f485i = i;
    }

    public static fr m373a(File file, long j, int i) {
        if (j <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        } else if (i <= 0) {
            throw new IllegalArgumentException("maxFileCount <= 0");
        } else {
            File file2 = new File(file, "journal.bkp");
            if (file2.exists()) {
                File file3 = new File(file, "journal");
                if (file3.exists()) {
                    file2.delete();
                } else {
                    m379a(file2, file3, false);
                }
            }
            fr frVar = new fr(file, j, i);
            if (frVar.f480d.exists()) {
                try {
                    frVar.m380b();
                    frVar.m382c();
                    frVar.f489m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(frVar.f480d, true), gb.f531a));
                    return frVar;
                } catch (IOException e) {
                    System.out.println("DiskLruCache " + file + " is corrupt: " + e.getMessage() + ", removing");
                    frVar.close();
                    gb.m418a(frVar.f479c);
                }
            }
            file.mkdirs();
            frVar = new fr(file, j, i);
            frVar.m384d();
            return frVar;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void m380b() {
        /*
        r10 = this;
        r9 = 5;
        r0 = 0;
        r8 = -1;
        r3 = new bo.app.fz;
        r1 = new java.io.FileInputStream;
        r2 = r10.f480d;
        r1.<init>(r2);
        r2 = bo.app.gb.f531a;
        r3.<init>(r1, r2);
        r1 = r3.m413a();	 Catch:{ all -> 0x008e }
        r2 = r3.m413a();	 Catch:{ all -> 0x008e }
        r4 = r3.m413a();	 Catch:{ all -> 0x008e }
        r5 = r3.m413a();	 Catch:{ all -> 0x008e }
        r6 = r3.m413a();	 Catch:{ all -> 0x008e }
        r7 = "libcore.io.DiskLruCache";
        r7 = r7.equals(r1);	 Catch:{ all -> 0x008e }
        if (r7 == 0) goto L_0x0055;
    L_0x002d:
        r7 = "1";
        r7 = r7.equals(r2);	 Catch:{ all -> 0x008e }
        if (r7 == 0) goto L_0x0055;
    L_0x0035:
        r7 = r10.f483g;	 Catch:{ all -> 0x008e }
        r7 = java.lang.Integer.toString(r7);	 Catch:{ all -> 0x008e }
        r4 = r7.equals(r4);	 Catch:{ all -> 0x008e }
        if (r4 == 0) goto L_0x0055;
    L_0x0041:
        r4 = r10.f486j;	 Catch:{ all -> 0x008e }
        r4 = java.lang.Integer.toString(r4);	 Catch:{ all -> 0x008e }
        r4 = r4.equals(r5);	 Catch:{ all -> 0x008e }
        if (r4 == 0) goto L_0x0055;
    L_0x004d:
        r4 = "";
        r4 = r4.equals(r6);	 Catch:{ all -> 0x008e }
        if (r4 != 0) goto L_0x0093;
    L_0x0055:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x008e }
        r4 = new java.lang.StringBuilder;	 Catch:{ all -> 0x008e }
        r7 = "unexpected journal header: [";
        r4.<init>(r7);	 Catch:{ all -> 0x008e }
        r1 = r4.append(r1);	 Catch:{ all -> 0x008e }
        r4 = ", ";
        r1 = r1.append(r4);	 Catch:{ all -> 0x008e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x008e }
        r2 = ", ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x008e }
        r1 = r1.append(r5);	 Catch:{ all -> 0x008e }
        r2 = ", ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x008e }
        r1 = r1.append(r6);	 Catch:{ all -> 0x008e }
        r2 = "]";
        r1 = r1.append(r2);	 Catch:{ all -> 0x008e }
        r1 = r1.toString();	 Catch:{ all -> 0x008e }
        r0.<init>(r1);	 Catch:{ all -> 0x008e }
        throw r0;	 Catch:{ all -> 0x008e }
    L_0x008e:
        r0 = move-exception;
        bo.app.gb.m417a(r3);
        throw r0;
    L_0x0093:
        r1 = r0;
    L_0x0094:
        r4 = r3.m413a();	 Catch:{ EOFException -> 0x00b5 }
        r0 = 32;
        r5 = r4.indexOf(r0);	 Catch:{ EOFException -> 0x00b5 }
        if (r5 != r8) goto L_0x00c4;
    L_0x00a0:
        r0 = new java.io.IOException;	 Catch:{ EOFException -> 0x00b5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00b5 }
        r5 = "unexpected journal line: ";
        r2.<init>(r5);	 Catch:{ EOFException -> 0x00b5 }
        r2 = r2.append(r4);	 Catch:{ EOFException -> 0x00b5 }
        r2 = r2.toString();	 Catch:{ EOFException -> 0x00b5 }
        r0.<init>(r2);	 Catch:{ EOFException -> 0x00b5 }
        throw r0;	 Catch:{ EOFException -> 0x00b5 }
    L_0x00b5:
        r0 = move-exception;
        r0 = r10.f490n;	 Catch:{ all -> 0x008e }
        r0 = r0.size();	 Catch:{ all -> 0x008e }
        r0 = r1 - r0;
        r10.f491o = r0;	 Catch:{ all -> 0x008e }
        bo.app.gb.m417a(r3);
        return;
    L_0x00c4:
        r0 = r5 + 1;
        r2 = 32;
        r6 = r4.indexOf(r2, r0);	 Catch:{ EOFException -> 0x00b5 }
        if (r6 != r8) goto L_0x00e6;
    L_0x00ce:
        r0 = r4.substring(r0);	 Catch:{ EOFException -> 0x00b5 }
        r2 = 6;
        if (r5 != r2) goto L_0x0159;
    L_0x00d5:
        r2 = "REMOVE";
        r2 = r4.startsWith(r2);	 Catch:{ EOFException -> 0x00b5 }
        if (r2 == 0) goto L_0x0159;
    L_0x00dd:
        r2 = r10.f490n;	 Catch:{ EOFException -> 0x00b5 }
        r2.remove(r0);	 Catch:{ EOFException -> 0x00b5 }
    L_0x00e2:
        r0 = r1 + 1;
        r1 = r0;
        goto L_0x0094;
    L_0x00e6:
        r0 = r4.substring(r0, r6);	 Catch:{ EOFException -> 0x00b5 }
        r2 = r0;
    L_0x00eb:
        r0 = r10.f490n;	 Catch:{ EOFException -> 0x00b5 }
        r0 = r0.get(r2);	 Catch:{ EOFException -> 0x00b5 }
        r0 = (bo.app.fw) r0;	 Catch:{ EOFException -> 0x00b5 }
        if (r0 != 0) goto L_0x0100;
    L_0x00f5:
        r0 = new bo.app.fw;	 Catch:{ EOFException -> 0x00b5 }
        r7 = 0;
        r0.<init>(r2, r7);	 Catch:{ EOFException -> 0x00b5 }
        r7 = r10.f490n;	 Catch:{ EOFException -> 0x00b5 }
        r7.put(r2, r0);	 Catch:{ EOFException -> 0x00b5 }
    L_0x0100:
        if (r6 == r8) goto L_0x0122;
    L_0x0102:
        if (r5 != r9) goto L_0x0122;
    L_0x0104:
        r2 = "CLEAN";
        r2 = r4.startsWith(r2);	 Catch:{ EOFException -> 0x00b5 }
        if (r2 == 0) goto L_0x0122;
    L_0x010c:
        r2 = r6 + 1;
        r2 = r4.substring(r2);	 Catch:{ EOFException -> 0x00b5 }
        r4 = " ";
        r2 = r2.split(r4);	 Catch:{ EOFException -> 0x00b5 }
        r4 = 1;
        r0.f503c = r4;	 Catch:{ EOFException -> 0x00b5 }
        r4 = 0;
        r0.f504d = r4;	 Catch:{ EOFException -> 0x00b5 }
        r0.m405a(r2);	 Catch:{ EOFException -> 0x00b5 }
        goto L_0x00e2;
    L_0x0122:
        if (r6 != r8) goto L_0x0137;
    L_0x0124:
        if (r5 != r9) goto L_0x0137;
    L_0x0126:
        r2 = "DIRTY";
        r2 = r4.startsWith(r2);	 Catch:{ EOFException -> 0x00b5 }
        if (r2 == 0) goto L_0x0137;
    L_0x012e:
        r2 = new bo.app.fu;	 Catch:{ EOFException -> 0x00b5 }
        r4 = 0;
        r2.<init>(r0, r4);	 Catch:{ EOFException -> 0x00b5 }
        r0.f504d = r2;	 Catch:{ EOFException -> 0x00b5 }
        goto L_0x00e2;
    L_0x0137:
        if (r6 != r8) goto L_0x0144;
    L_0x0139:
        r0 = 4;
        if (r5 != r0) goto L_0x0144;
    L_0x013c:
        r0 = "READ";
        r0 = r4.startsWith(r0);	 Catch:{ EOFException -> 0x00b5 }
        if (r0 != 0) goto L_0x00e2;
    L_0x0144:
        r0 = new java.io.IOException;	 Catch:{ EOFException -> 0x00b5 }
        r2 = new java.lang.StringBuilder;	 Catch:{ EOFException -> 0x00b5 }
        r5 = "unexpected journal line: ";
        r2.<init>(r5);	 Catch:{ EOFException -> 0x00b5 }
        r2 = r2.append(r4);	 Catch:{ EOFException -> 0x00b5 }
        r2 = r2.toString();	 Catch:{ EOFException -> 0x00b5 }
        r0.<init>(r2);	 Catch:{ EOFException -> 0x00b5 }
        throw r0;	 Catch:{ EOFException -> 0x00b5 }
    L_0x0159:
        r2 = r0;
        goto L_0x00eb;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.fr.b():void");
    }

    private void m382c() {
        m378a(this.f481e);
        Iterator it = this.f490n.values().iterator();
        while (it.hasNext()) {
            fw fwVar = (fw) it.next();
            int i;
            if (fwVar.f504d == null) {
                for (i = 0; i < this.f486j; i++) {
                    this.f487k += fwVar.f502b[i];
                    this.f488l++;
                }
            } else {
                fwVar.f504d = null;
                for (i = 0; i < this.f486j; i++) {
                    m378a(fwVar.m403a(i));
                    m378a(fwVar.m406b(i));
                }
                it.remove();
            }
        }
    }

    private synchronized void m384d() {
        if (this.f489m != null) {
            this.f489m.close();
        }
        Writer bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f481e), gb.f531a));
        try {
            bufferedWriter.write("libcore.io.DiskLruCache");
            bufferedWriter.write("\n");
            bufferedWriter.write("1");
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f483g));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(this.f486j));
            bufferedWriter.write("\n");
            bufferedWriter.write("\n");
            for (fw fwVar : this.f490n.values()) {
                if (fwVar.f504d != null) {
                    bufferedWriter.write("DIRTY " + fwVar.f501a + '\n');
                } else {
                    bufferedWriter.write("CLEAN " + fwVar.f501a + fwVar.m404a() + '\n');
                }
            }
            bufferedWriter.close();
            if (this.f480d.exists()) {
                m379a(this.f480d, this.f482f, true);
            }
            m379a(this.f481e, this.f480d, false);
            this.f482f.delete();
            this.f489m = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.f480d, true), gb.f531a));
        } catch (Throwable th) {
            bufferedWriter.close();
        }
    }

    private static void m378a(File file) {
        if (file.exists() && !file.delete()) {
            throw new IOException();
        }
    }

    private static void m379a(File file, File file2, boolean z) {
        if (z) {
            m378a(file2);
        }
        if (!file.renameTo(file2)) {
            throw new IOException();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized bo.app.fx m395a(java.lang.String r11) {
        /*
        r10 = this;
        r1 = 0;
        r2 = 0;
        monitor-enter(r10);
        r10.m390f();	 Catch:{ all -> 0x0082 }
        m385d(r11);	 Catch:{ all -> 0x0082 }
        r0 = r10.f490n;	 Catch:{ all -> 0x0082 }
        r0 = r0.get(r11);	 Catch:{ all -> 0x0082 }
        r0 = (bo.app.fw) r0;	 Catch:{ all -> 0x0082 }
        if (r0 != 0) goto L_0x0015;
    L_0x0013:
        monitor-exit(r10);
        return r1;
    L_0x0015:
        r3 = r0.f503c;	 Catch:{ all -> 0x0082 }
        if (r3 == 0) goto L_0x0013;
    L_0x0019:
        r3 = r10.f486j;	 Catch:{ all -> 0x0082 }
        r6 = new java.io.File[r3];	 Catch:{ all -> 0x0082 }
        r3 = r10.f486j;	 Catch:{ all -> 0x0082 }
        r7 = new java.io.InputStream[r3];	 Catch:{ all -> 0x0082 }
        r3 = r2;
    L_0x0022:
        r4 = r10.f486j;	 Catch:{ FileNotFoundException -> 0x0036 }
        if (r3 >= r4) goto L_0x0048;
    L_0x0026:
        r4 = r0.m403a(r3);	 Catch:{ FileNotFoundException -> 0x0036 }
        r6[r3] = r4;	 Catch:{ FileNotFoundException -> 0x0036 }
        r5 = new java.io.FileInputStream;	 Catch:{ FileNotFoundException -> 0x0036 }
        r5.<init>(r4);	 Catch:{ FileNotFoundException -> 0x0036 }
        r7[r3] = r5;	 Catch:{ FileNotFoundException -> 0x0036 }
        r3 = r3 + 1;
        goto L_0x0022;
    L_0x0036:
        r0 = move-exception;
        r0 = r2;
    L_0x0038:
        r2 = r10.f486j;	 Catch:{ all -> 0x0082 }
        if (r0 >= r2) goto L_0x0013;
    L_0x003c:
        r2 = r7[r0];	 Catch:{ all -> 0x0082 }
        if (r2 == 0) goto L_0x0013;
    L_0x0040:
        r2 = r7[r0];	 Catch:{ all -> 0x0082 }
        bo.app.gb.m417a(r2);	 Catch:{ all -> 0x0082 }
        r0 = r0 + 1;
        goto L_0x0038;
    L_0x0048:
        r1 = r10.f491o;	 Catch:{ all -> 0x0082 }
        r1 = r1 + 1;
        r10.f491o = r1;	 Catch:{ all -> 0x0082 }
        r1 = r10.f489m;	 Catch:{ all -> 0x0082 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0082 }
        r3 = "READ ";
        r2.<init>(r3);	 Catch:{ all -> 0x0082 }
        r2 = r2.append(r11);	 Catch:{ all -> 0x0082 }
        r3 = 10;
        r2 = r2.append(r3);	 Catch:{ all -> 0x0082 }
        r2 = r2.toString();	 Catch:{ all -> 0x0082 }
        r1.append(r2);	 Catch:{ all -> 0x0082 }
        r1 = r10.m388e();	 Catch:{ all -> 0x0082 }
        if (r1 == 0) goto L_0x0075;
    L_0x006e:
        r1 = r10.f478b;	 Catch:{ all -> 0x0082 }
        r2 = r10.f493q;	 Catch:{ all -> 0x0082 }
        r1.submit(r2);	 Catch:{ all -> 0x0082 }
    L_0x0075:
        r1 = new bo.app.fx;	 Catch:{ all -> 0x0082 }
        r4 = r0.f505e;	 Catch:{ all -> 0x0082 }
        r8 = r0.f502b;	 Catch:{ all -> 0x0082 }
        r9 = 0;
        r2 = r10;
        r3 = r11;
        r1.<init>(r3, r4, r6, r7, r8, r9);	 Catch:{ all -> 0x0082 }
        goto L_0x0013;
    L_0x0082:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.fr.a(java.lang.String):bo.app.fx");
    }

    final synchronized fu m396b(String str) {
        fu fuVar;
        m390f();
        m385d(str);
        fw fwVar = (fw) this.f490n.get(str);
        if (-1 == -1 || (fwVar != null && fwVar.f505e == -1)) {
            fw fwVar2;
            if (fwVar == null) {
                fwVar = new fw(str, (byte) 0);
                this.f490n.put(str, fwVar);
                fwVar2 = fwVar;
            } else if (fwVar.f504d != null) {
                fuVar = null;
            } else {
                fwVar2 = fwVar;
            }
            fuVar = new fu(fwVar2, (byte) 0);
            fwVar2.f504d = fuVar;
            this.f489m.write("DIRTY " + str + '\n');
            this.f489m.flush();
        } else {
            fuVar = null;
        }
        return fuVar;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void m377a(bo.app.fu r11, boolean r12) {
        /*
        r10 = this;
        r0 = 0;
        monitor-enter(r10);
        r2 = r11.f495a;	 Catch:{ all -> 0x000e }
        r1 = r2.f504d;	 Catch:{ all -> 0x000e }
        if (r1 == r11) goto L_0x0011;
    L_0x0008:
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x000e }
        r0.<init>();	 Catch:{ all -> 0x000e }
        throw r0;	 Catch:{ all -> 0x000e }
    L_0x000e:
        r0 = move-exception;
        monitor-exit(r10);
        throw r0;
    L_0x0011:
        if (r12 == 0) goto L_0x004c;
    L_0x0013:
        r1 = r2.f503c;	 Catch:{ all -> 0x000e }
        if (r1 != 0) goto L_0x004c;
    L_0x0017:
        r1 = r0;
    L_0x0018:
        r3 = r10.f486j;	 Catch:{ all -> 0x000e }
        if (r1 >= r3) goto L_0x004c;
    L_0x001c:
        r3 = r11.f496b;	 Catch:{ all -> 0x000e }
        r3 = r3[r1];	 Catch:{ all -> 0x000e }
        if (r3 != 0) goto L_0x003a;
    L_0x0022:
        r11.m401c();	 Catch:{ all -> 0x000e }
        r0 = new java.lang.IllegalStateException;	 Catch:{ all -> 0x000e }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x000e }
        r3 = "Newly created entry didn't create value for index ";
        r2.<init>(r3);	 Catch:{ all -> 0x000e }
        r1 = r2.append(r1);	 Catch:{ all -> 0x000e }
        r1 = r1.toString();	 Catch:{ all -> 0x000e }
        r0.<init>(r1);	 Catch:{ all -> 0x000e }
        throw r0;	 Catch:{ all -> 0x000e }
    L_0x003a:
        r3 = r2.m406b(r1);	 Catch:{ all -> 0x000e }
        r3 = r3.exists();	 Catch:{ all -> 0x000e }
        if (r3 != 0) goto L_0x0049;
    L_0x0044:
        r11.m401c();	 Catch:{ all -> 0x000e }
    L_0x0047:
        monitor-exit(r10);
        return;
    L_0x0049:
        r1 = r1 + 1;
        goto L_0x0018;
    L_0x004c:
        r1 = r10.f486j;	 Catch:{ all -> 0x000e }
        if (r0 >= r1) goto L_0x0083;
    L_0x0050:
        r1 = r2.m406b(r0);	 Catch:{ all -> 0x000e }
        if (r12 == 0) goto L_0x007f;
    L_0x0056:
        r3 = r1.exists();	 Catch:{ all -> 0x000e }
        if (r3 == 0) goto L_0x007c;
    L_0x005c:
        r3 = r2.m403a(r0);	 Catch:{ all -> 0x000e }
        r1.renameTo(r3);	 Catch:{ all -> 0x000e }
        r1 = r2.f502b;	 Catch:{ all -> 0x000e }
        r4 = r1[r0];	 Catch:{ all -> 0x000e }
        r6 = r3.length();	 Catch:{ all -> 0x000e }
        r1 = r2.f502b;	 Catch:{ all -> 0x000e }
        r1[r0] = r6;	 Catch:{ all -> 0x000e }
        r8 = r10.f487k;	 Catch:{ all -> 0x000e }
        r4 = r8 - r4;
        r4 = r4 + r6;
        r10.f487k = r4;	 Catch:{ all -> 0x000e }
        r1 = r10.f488l;	 Catch:{ all -> 0x000e }
        r1 = r1 + 1;
        r10.f488l = r1;	 Catch:{ all -> 0x000e }
    L_0x007c:
        r0 = r0 + 1;
        goto L_0x004c;
    L_0x007f:
        m378a(r1);	 Catch:{ all -> 0x000e }
        goto L_0x007c;
    L_0x0083:
        r0 = r10.f491o;	 Catch:{ all -> 0x000e }
        r0 = r0 + 1;
        r10.f491o = r0;	 Catch:{ all -> 0x000e }
        r0 = 0;
        r2.f504d = r0;	 Catch:{ all -> 0x000e }
        r0 = r2.f503c;	 Catch:{ all -> 0x000e }
        r0 = r0 | r12;
        if (r0 == 0) goto L_0x00e5;
    L_0x0091:
        r0 = 1;
        r2.f503c = r0;	 Catch:{ all -> 0x000e }
        r0 = r10.f489m;	 Catch:{ all -> 0x000e }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x000e }
        r3 = "CLEAN ";
        r1.<init>(r3);	 Catch:{ all -> 0x000e }
        r3 = r2.f501a;	 Catch:{ all -> 0x000e }
        r1 = r1.append(r3);	 Catch:{ all -> 0x000e }
        r3 = r2.m404a();	 Catch:{ all -> 0x000e }
        r1 = r1.append(r3);	 Catch:{ all -> 0x000e }
        r3 = 10;
        r1 = r1.append(r3);	 Catch:{ all -> 0x000e }
        r1 = r1.toString();	 Catch:{ all -> 0x000e }
        r0.write(r1);	 Catch:{ all -> 0x000e }
        if (r12 == 0) goto L_0x00c3;
    L_0x00ba:
        r0 = r10.f492p;	 Catch:{ all -> 0x000e }
        r4 = 1;
        r4 = r4 + r0;
        r10.f492p = r4;	 Catch:{ all -> 0x000e }
        r2.f505e = r0;	 Catch:{ all -> 0x000e }
    L_0x00c3:
        r0 = r10.f489m;	 Catch:{ all -> 0x000e }
        r0.flush();	 Catch:{ all -> 0x000e }
        r0 = r10.f487k;	 Catch:{ all -> 0x000e }
        r2 = r10.f484h;	 Catch:{ all -> 0x000e }
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 > 0) goto L_0x00dc;
    L_0x00d0:
        r0 = r10.f488l;	 Catch:{ all -> 0x000e }
        r1 = r10.f485i;	 Catch:{ all -> 0x000e }
        if (r0 > r1) goto L_0x00dc;
    L_0x00d6:
        r0 = r10.m388e();	 Catch:{ all -> 0x000e }
        if (r0 == 0) goto L_0x0047;
    L_0x00dc:
        r0 = r10.f478b;	 Catch:{ all -> 0x000e }
        r1 = r10.f493q;	 Catch:{ all -> 0x000e }
        r0.submit(r1);	 Catch:{ all -> 0x000e }
        goto L_0x0047;
    L_0x00e5:
        r0 = r10.f490n;	 Catch:{ all -> 0x000e }
        r1 = r2.f501a;	 Catch:{ all -> 0x000e }
        r0.remove(r1);	 Catch:{ all -> 0x000e }
        r0 = r10.f489m;	 Catch:{ all -> 0x000e }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x000e }
        r3 = "REMOVE ";
        r1.<init>(r3);	 Catch:{ all -> 0x000e }
        r2 = r2.f501a;	 Catch:{ all -> 0x000e }
        r1 = r1.append(r2);	 Catch:{ all -> 0x000e }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x000e }
        r1 = r1.toString();	 Catch:{ all -> 0x000e }
        r0.write(r1);	 Catch:{ all -> 0x000e }
        goto L_0x00c3;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.fr.a(bo.app.fu, boolean):void");
    }

    private boolean m388e() {
        return this.f491o >= ScreenRecorder.MAX_READY_WAIT_TIME && this.f491o >= this.f490n.size();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized boolean m397c(java.lang.String r7) {
        /*
        r6 = this;
        r1 = 0;
        monitor-enter(r6);
        r6.m390f();	 Catch:{ all -> 0x0059 }
        m385d(r7);	 Catch:{ all -> 0x0059 }
        r0 = r6.f490n;	 Catch:{ all -> 0x0059 }
        r0 = r0.get(r7);	 Catch:{ all -> 0x0059 }
        r0 = (bo.app.fw) r0;	 Catch:{ all -> 0x0059 }
        if (r0 == 0) goto L_0x0016;
    L_0x0012:
        r2 = r0.f504d;	 Catch:{ all -> 0x0059 }
        if (r2 == 0) goto L_0x0030;
    L_0x0016:
        r0 = r1;
    L_0x0017:
        monitor-exit(r6);
        return r0;
    L_0x0019:
        r2 = r6.f487k;	 Catch:{ all -> 0x0059 }
        r4 = r0.f502b;	 Catch:{ all -> 0x0059 }
        r4 = r4[r1];	 Catch:{ all -> 0x0059 }
        r2 = r2 - r4;
        r6.f487k = r2;	 Catch:{ all -> 0x0059 }
        r2 = r6.f488l;	 Catch:{ all -> 0x0059 }
        r2 = r2 + -1;
        r6.f488l = r2;	 Catch:{ all -> 0x0059 }
        r2 = r0.f502b;	 Catch:{ all -> 0x0059 }
        r4 = 0;
        r2[r1] = r4;	 Catch:{ all -> 0x0059 }
        r1 = r1 + 1;
    L_0x0030:
        r2 = r6.f486j;	 Catch:{ all -> 0x0059 }
        if (r1 >= r2) goto L_0x005c;
    L_0x0034:
        r2 = r0.m403a(r1);	 Catch:{ all -> 0x0059 }
        r3 = r2.exists();	 Catch:{ all -> 0x0059 }
        if (r3 == 0) goto L_0x0019;
    L_0x003e:
        r3 = r2.delete();	 Catch:{ all -> 0x0059 }
        if (r3 != 0) goto L_0x0019;
    L_0x0044:
        r0 = new java.io.IOException;	 Catch:{ all -> 0x0059 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0059 }
        r3 = "failed to delete ";
        r1.<init>(r3);	 Catch:{ all -> 0x0059 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0059 }
        r1 = r1.toString();	 Catch:{ all -> 0x0059 }
        r0.<init>(r1);	 Catch:{ all -> 0x0059 }
        throw r0;	 Catch:{ all -> 0x0059 }
    L_0x0059:
        r0 = move-exception;
        monitor-exit(r6);
        throw r0;
    L_0x005c:
        r0 = r6.f491o;	 Catch:{ all -> 0x0059 }
        r0 = r0 + 1;
        r6.f491o = r0;	 Catch:{ all -> 0x0059 }
        r0 = r6.f489m;	 Catch:{ all -> 0x0059 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0059 }
        r2 = "REMOVE ";
        r1.<init>(r2);	 Catch:{ all -> 0x0059 }
        r1 = r1.append(r7);	 Catch:{ all -> 0x0059 }
        r2 = 10;
        r1 = r1.append(r2);	 Catch:{ all -> 0x0059 }
        r1 = r1.toString();	 Catch:{ all -> 0x0059 }
        r0.append(r1);	 Catch:{ all -> 0x0059 }
        r0 = r6.f490n;	 Catch:{ all -> 0x0059 }
        r0.remove(r7);	 Catch:{ all -> 0x0059 }
        r0 = r6.m388e();	 Catch:{ all -> 0x0059 }
        if (r0 == 0) goto L_0x008e;
    L_0x0087:
        r0 = r6.f478b;	 Catch:{ all -> 0x0059 }
        r1 = r6.f493q;	 Catch:{ all -> 0x0059 }
        r0.submit(r1);	 Catch:{ all -> 0x0059 }
    L_0x008e:
        r0 = 1;
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: bo.app.fr.c(java.lang.String):boolean");
    }

    private void m390f() {
        if (this.f489m == null) {
            throw new IllegalStateException("cache is closed");
        }
    }

    public final synchronized void close() {
        if (this.f489m != null) {
            Iterator it = new ArrayList(this.f490n.values()).iterator();
            while (it.hasNext()) {
                fw fwVar = (fw) it.next();
                if (fwVar.f504d != null) {
                    fwVar.f504d.m401c();
                }
            }
            m392g();
            m394h();
            this.f489m.close();
            this.f489m = null;
        }
    }

    private void m392g() {
        while (this.f487k > this.f484h) {
            m397c((String) ((Entry) this.f490n.entrySet().iterator().next()).getKey());
        }
    }

    private void m394h() {
        while (this.f488l > this.f485i) {
            m397c((String) ((Entry) this.f490n.entrySet().iterator().next()).getKey());
        }
    }

    private static void m385d(String str) {
        if (!f476a.matcher(str).matches()) {
            throw new IllegalArgumentException("keys must match regex [a-z0-9_-]{1,64}: \"" + str + "\"");
        }
    }
}
