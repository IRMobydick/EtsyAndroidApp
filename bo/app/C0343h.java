package bo.app;

import com.android.volley.toolbox.BasicNetwork;
import com.appboy.Constants;
import com.appboy.models.InAppMessageBase;
import com.appboy.support.AppboyLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: bo.app.h */
public final class C0343h implements C0342i {
    private static final String f686a;
    private final int f687b;

    static {
        f686a = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, C0343h.class.getName()});
    }

    public C0343h() {
        this.f687b = InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS;
    }

    public final JSONObject m495a(URI uri, Map<String, String> map) {
        return m492a(uri, null, (Map) map, ag.GET);
    }

    public final JSONObject m496a(URI uri, Map<String, String> map, JSONObject jSONObject) {
        return m492a(uri, jSONObject, (Map) map, ag.POST);
    }

    private JSONObject m492a(URI uri, JSONObject jSONObject, Map<String, String> map, ag agVar) {
        JSONObject jSONObject2 = null;
        URL a = ff.m337a(uri);
        if (a != null) {
            try {
                jSONObject2 = m493a(a, jSONObject, (Map) map, agVar);
            } catch (IOException e) {
                try {
                    jSONObject2 = m493a(a, jSONObject, (Map) map, agVar);
                } catch (Throwable e2) {
                    throw new bn(String.format("Experienced IOException twice during request to [%s], failing: [%s]", new Object[]{a.toString(), e2.getMessage()}), e2);
                }
            }
        }
        return jSONObject2;
    }

    private JSONObject m493a(URL url, JSONObject jSONObject, Map<String, String> map, ag agVar) {
        HttpURLConnection httpURLConnection;
        InputStream gZIPInputStream;
        InputStream inputStream = null;
        if (url != null) {
            try {
                HttpURLConnection b = m494b(url, jSONObject, map, agVar);
            } catch (Throwable th) {
                Throwable th2 = th;
                httpURLConnection = null;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e) {
                        AppboyLogger.m665e(f686a, "Caught an error trying to close the inputStream in getResult", e);
                    }
                }
                throw th2;
            }
        }
        b = null;
        if (b != null) {
            try {
                b.connect();
                if (b.getResponseCode() / 100 == 2) {
                    if (BasicNetwork.ENCODING_GZIP.equalsIgnoreCase(b.getContentEncoding())) {
                        gZIPInputStream = new GZIPInputStream(b.getInputStream());
                    } else {
                        gZIPInputStream = new BufferedInputStream(b.getInputStream());
                    }
                    try {
                        JSONObject jSONObject2 = new JSONObject(C0343h.m491a(new BufferedReader(new InputStreamReader(gZIPInputStream, com.adjust.sdk.Constants.ENCODING))));
                        if (b != null) {
                            b.disconnect();
                        }
                        try {
                            gZIPInputStream.close();
                            return jSONObject2;
                        } catch (Throwable e2) {
                            AppboyLogger.m665e(f686a, "Caught an error trying to close the inputStream in getResult", e2);
                            return jSONObject2;
                        }
                    } catch (IOException e3) {
                        AppboyLogger.m664e(f686a, String.format("Could not read from response stream [%s]", new Object[]{e3.getMessage()}));
                    } catch (JSONException e4) {
                        AppboyLogger.m664e(f686a, String.format("Unable to parse response [%s]", new Object[]{e4}));
                    } catch (Throwable th3) {
                        th2 = th3;
                        inputStream = gZIPInputStream;
                        httpURLConnection = b;
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        throw th2;
                    }
                }
                throw new bn(String.format("Bad Http response code from Appboy: [%d]", new Object[]{Integer.valueOf(b.getResponseCode())}));
            } catch (Throwable th4) {
                th2 = th4;
                httpURLConnection = b;
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th2;
            }
        }
        gZIPInputStream = null;
        if (b != null) {
            b.disconnect();
        }
        if (gZIPInputStream != null) {
            try {
                gZIPInputStream.close();
            } catch (Throwable th22) {
                AppboyLogger.m665e(f686a, "Caught an error trying to close the inputStream in getResult", th22);
            }
        }
        AppboyLogger.m670w(f686a, String.format("Failed to get result from [%s]. Returning null.", new Object[]{String.valueOf(url)}));
        return null;
    }

    private HttpURLConnection m494b(URL url, JSONObject jSONObject, Map<String, String> map, ag agVar) {
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(InAppMessageBase.INAPP_MESSAGE_DURATION_DEFAULT_MILLIS);
            httpURLConnection.setReadTimeout(this.f687b);
            httpURLConnection.setInstanceFollowRedirects(false);
            httpURLConnection.setRequestMethod(agVar.toString());
            for (Entry entry : map.entrySet()) {
                httpURLConnection.setRequestProperty((String) entry.getKey(), (String) entry.getValue());
            }
            if (agVar == ag.POST) {
                httpURLConnection.setDoOutput(true);
                OutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                bufferedOutputStream.write(jSONObject.toString().getBytes(com.adjust.sdk.Constants.ENCODING));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            }
            return httpURLConnection;
        } catch (Throwable e) {
            throw new bn(String.format("Could not setup connection [%s] [%s].  Appboy will try to reconnect periodically.", new Object[]{url.toString(), e.getMessage()}), e);
        }
    }

    private static String m491a(BufferedReader bufferedReader) {
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            stringBuilder.append(readLine);
        }
    }
}
