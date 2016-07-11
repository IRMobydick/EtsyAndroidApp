package com.appboy.ui.support;

import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.appboy.Constants;
import com.appboy.models.IInAppMessageHtml;
import com.appboy.support.AppboyLogger;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WebContentUtils {
    private static final String FILE_URI_SCHEME_PREFIX = "file://";
    static final String HTML_INAPP_MESSAGES_FOLDER_NAME = "appboy-html-inapp-messages";
    private static final String TAG;
    private static final int ZIP_UNPACK_BYTE_BUFFER_LENGTH = 8192;

    static {
        TAG = String.format("%s.%s", new Object[]{Constants.APPBOY_LOG_TAG_PREFIX, WebContentUtils.class.getName()});
    }

    public static String getLocalHtmlUrlFromRemoteUrl(File file, String str) {
        if (file == null) {
            AppboyLogger.m670w(TAG, "Internal cache directory is null. No local URL will be created.");
            return null;
        } else if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m670w(TAG, "Remote zip url is null or empty. No local URL will be created.");
            return null;
        } else {
            clearEntireHtmlInAppMessageCache(file);
            String str2 = file.getAbsolutePath() + "/" + HTML_INAPP_MESSAGES_FOLDER_NAME;
            String valueOf = String.valueOf(System.currentTimeMillis());
            str2 = str2 + "/" + valueOf;
            AppboyLogger.m662d(TAG, "Starting download of url: " + str);
            File downloadFileToPath = downloadFileToPath(str2, str, valueOf);
            if (downloadFileToPath == null) {
                AppboyLogger.m662d(TAG, "Could not download zip file to local storage.");
                deleteFileOrDirectory(new File(str2));
                return null;
            }
            AppboyLogger.m662d(TAG, "Html content zip downloaded.");
            if (unpackZipIntoDirectory(str2, downloadFileToPath)) {
                AppboyLogger.m662d(TAG, "Html content zip unpacked.");
                return FILE_URI_SCHEME_PREFIX + str2 + "/";
            }
            AppboyLogger.m670w(TAG, "Error during the zip unpack.");
            deleteFileOrDirectory(new File(str2));
            return null;
        }
    }

    static File downloadFileToPath(String str, String str2, String str3) {
        Throwable e;
        HttpURLConnection httpURLConnection;
        BufferedOutputStream bufferedOutputStream;
        HttpURLConnection httpURLConnection2;
        Throwable th;
        BufferedOutputStream bufferedOutputStream2 = null;
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m666i(TAG, "Download directory null or blank. Zip file not downloaded.");
            return null;
        } else if (StringUtils.isNullOrBlank(str2)) {
            AppboyLogger.m666i(TAG, "Zip file url null or blank. Zip file not downloaded.");
            return null;
        } else if (StringUtils.isNullOrBlank(str3)) {
            AppboyLogger.m666i(TAG, "Output filename null or blank. Zip file not downloaded.");
            return null;
        } else {
            new File(str).mkdirs();
            File file = new File(str, str3 + ".zip");
            DataInputStream dataInputStream = null;
            BufferedOutputStream bufferedOutputStream3 = null;
            DataInputStream dataInputStream2;
            BufferedOutputStream bufferedOutputStream4;
            try {
                URL url = new URL(str2);
                HttpURLConnection httpURLConnection3 = (HttpURLConnection) url.openConnection();
                try {
                    int contentLength;
                    if (httpURLConnection3.getResponseCode() != Callback.DEFAULT_DRAG_ANIMATION_DURATION) {
                        AppboyLogger.m662d(TAG, String.format("HTTP response code was %s. Zip file with url %s could not be downloaded.", new Object[]{Integer.valueOf(contentLength), str2}));
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (null != null) {
                            try {
                                dataInputStream.close();
                            } catch (Throwable e2) {
                                AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e2);
                            }
                        }
                        if (null != null) {
                            bufferedOutputStream3.close();
                        }
                        return null;
                    }
                    contentLength = httpURLConnection3.getContentLength();
                    if (contentLength == -1) {
                        AppboyLogger.m662d(TAG, String.format("Content length to the zip file could not be set. Zip file with url %s could not be downloaded.", new Object[]{str2}));
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (null != null) {
                            try {
                                dataInputStream.close();
                            } catch (Throwable e22) {
                                AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e22);
                            }
                        }
                        if (null != null) {
                            bufferedOutputStream3.close();
                        }
                        return null;
                    }
                    byte[] bArr = new byte[contentLength];
                    dataInputStream2 = new DataInputStream(url.openStream());
                    try {
                        dataInputStream2.readFully(bArr);
                        dataInputStream2.close();
                        httpURLConnection3.disconnect();
                        bufferedOutputStream4 = new BufferedOutputStream(new FileOutputStream(file));
                    } catch (Throwable e3) {
                        dataInputStream = dataInputStream2;
                        httpURLConnection = httpURLConnection3;
                        e22 = e3;
                        bufferedOutputStream = null;
                        try {
                            AppboyLogger.m665e(TAG, "MalformedURLException during download of zip file from url.", e22);
                            if (httpURLConnection != null) {
                                httpURLConnection.disconnect();
                            }
                            if (dataInputStream != null) {
                                try {
                                    dataInputStream.close();
                                } catch (Throwable e222) {
                                    AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e222);
                                    return null;
                                }
                            }
                            if (bufferedOutputStream != null) {
                                bufferedOutputStream.close();
                            }
                            return null;
                        } catch (Throwable th2) {
                            e222 = th2;
                            bufferedOutputStream2 = bufferedOutputStream;
                            httpURLConnection2 = httpURLConnection;
                            dataInputStream2 = dataInputStream;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (Throwable e4) {
                                    AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e4);
                                    throw e222;
                                }
                            }
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            throw e222;
                        }
                    } catch (Throwable e32) {
                        bufferedOutputStream4 = null;
                        th = e32;
                        httpURLConnection2 = httpURLConnection3;
                        e222 = th;
                        try {
                            AppboyLogger.m665e(TAG, "IOException during download of zip file from url.", e222);
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (dataInputStream2 != null) {
                                try {
                                    dataInputStream2.close();
                                } catch (Throwable e2222) {
                                    AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e2222);
                                    return null;
                                }
                            }
                            if (bufferedOutputStream4 != null) {
                                bufferedOutputStream4.close();
                            }
                            return null;
                        } catch (Throwable th3) {
                            e2222 = th3;
                            bufferedOutputStream2 = bufferedOutputStream4;
                            if (httpURLConnection2 != null) {
                                httpURLConnection2.disconnect();
                            }
                            if (dataInputStream2 != null) {
                                dataInputStream2.close();
                            }
                            if (bufferedOutputStream2 != null) {
                                bufferedOutputStream2.close();
                            }
                            throw e2222;
                        }
                    } catch (Throwable e322) {
                        bufferedOutputStream4 = null;
                        th = e322;
                        httpURLConnection2 = httpURLConnection3;
                        e2222 = th;
                        AppboyLogger.m665e(TAG, "Exception during download of zip file from url.", e2222);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            try {
                                dataInputStream2.close();
                            } catch (Throwable e22222) {
                                AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e22222);
                                return null;
                            }
                        }
                        if (bufferedOutputStream4 != null) {
                            bufferedOutputStream4.close();
                        }
                        return null;
                    } catch (Throwable e3222) {
                        th = e3222;
                        httpURLConnection2 = httpURLConnection3;
                        e22222 = th;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        throw e22222;
                    }
                    try {
                        bufferedOutputStream4.write(bArr);
                        bufferedOutputStream4.close();
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            try {
                                dataInputStream2.close();
                            } catch (Throwable e222222) {
                                AppboyLogger.m665e(TAG, "IOException during closing of zip file download streams.", e222222);
                            }
                        }
                        if (bufferedOutputStream4 != null) {
                            bufferedOutputStream4.close();
                        }
                        return file;
                    } catch (Throwable e32222) {
                        th = e32222;
                        bufferedOutputStream = bufferedOutputStream4;
                        dataInputStream = dataInputStream2;
                        httpURLConnection = httpURLConnection3;
                        e222222 = th;
                        AppboyLogger.m665e(TAG, "MalformedURLException during download of zip file from url.", e222222);
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        if (dataInputStream != null) {
                            dataInputStream.close();
                        }
                        if (bufferedOutputStream != null) {
                            bufferedOutputStream.close();
                        }
                        return null;
                    } catch (Throwable e322222) {
                        th = e322222;
                        httpURLConnection2 = httpURLConnection3;
                        e222222 = th;
                        AppboyLogger.m665e(TAG, "IOException during download of zip file from url.", e222222);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        if (bufferedOutputStream4 != null) {
                            bufferedOutputStream4.close();
                        }
                        return null;
                    } catch (Throwable e3222222) {
                        th = e3222222;
                        httpURLConnection2 = httpURLConnection3;
                        e222222 = th;
                        AppboyLogger.m665e(TAG, "Exception during download of zip file from url.", e222222);
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        if (bufferedOutputStream4 != null) {
                            bufferedOutputStream4.close();
                        }
                        return null;
                    } catch (Throwable e42) {
                        httpURLConnection2 = httpURLConnection3;
                        e222222 = e42;
                        bufferedOutputStream2 = bufferedOutputStream4;
                        if (httpURLConnection2 != null) {
                            httpURLConnection2.disconnect();
                        }
                        if (dataInputStream2 != null) {
                            dataInputStream2.close();
                        }
                        if (bufferedOutputStream2 != null) {
                            bufferedOutputStream2.close();
                        }
                        throw e222222;
                    }
                } catch (Throwable e32222222) {
                    dataInputStream = null;
                    httpURLConnection = httpURLConnection3;
                    e222222 = e32222222;
                    bufferedOutputStream = null;
                    AppboyLogger.m665e(TAG, "MalformedURLException during download of zip file from url.", e222222);
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    if (dataInputStream != null) {
                        dataInputStream.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return null;
                } catch (Throwable e322222222) {
                    bufferedOutputStream4 = null;
                    dataInputStream2 = null;
                    th = e322222222;
                    httpURLConnection2 = httpURLConnection3;
                    e222222 = th;
                    AppboyLogger.m665e(TAG, "IOException during download of zip file from url.", e222222);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    if (bufferedOutputStream4 != null) {
                        bufferedOutputStream4.close();
                    }
                    return null;
                } catch (Throwable e3222222222) {
                    bufferedOutputStream4 = null;
                    dataInputStream2 = null;
                    th = e3222222222;
                    httpURLConnection2 = httpURLConnection3;
                    e222222 = th;
                    AppboyLogger.m665e(TAG, "Exception during download of zip file from url.", e222222);
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    if (bufferedOutputStream4 != null) {
                        bufferedOutputStream4.close();
                    }
                    return null;
                } catch (Throwable e32222222222) {
                    dataInputStream2 = null;
                    th = e32222222222;
                    httpURLConnection2 = httpURLConnection3;
                    e222222 = th;
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                    if (bufferedOutputStream2 != null) {
                        bufferedOutputStream2.close();
                    }
                    throw e222222;
                }
            } catch (MalformedURLException e5) {
                e222222 = e5;
                bufferedOutputStream = null;
                dataInputStream = null;
                httpURLConnection = null;
                AppboyLogger.m665e(TAG, "MalformedURLException during download of zip file from url.", e222222);
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                if (dataInputStream != null) {
                    dataInputStream.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return null;
            } catch (IOException e6) {
                e222222 = e6;
                bufferedOutputStream4 = null;
                dataInputStream2 = null;
                httpURLConnection2 = null;
                AppboyLogger.m665e(TAG, "IOException during download of zip file from url.", e222222);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                if (bufferedOutputStream4 != null) {
                    bufferedOutputStream4.close();
                }
                return null;
            } catch (Exception e7) {
                e222222 = e7;
                bufferedOutputStream4 = null;
                dataInputStream2 = null;
                httpURLConnection2 = null;
                AppboyLogger.m665e(TAG, "Exception during download of zip file from url.", e222222);
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                if (bufferedOutputStream4 != null) {
                    bufferedOutputStream4.close();
                }
                return null;
            } catch (Throwable th4) {
                e222222 = th4;
                dataInputStream2 = null;
                httpURLConnection2 = null;
                if (httpURLConnection2 != null) {
                    httpURLConnection2.disconnect();
                }
                if (dataInputStream2 != null) {
                    dataInputStream2.close();
                }
                if (bufferedOutputStream2 != null) {
                    bufferedOutputStream2.close();
                }
                throw e222222;
            }
        }
    }

    static boolean unpackZipIntoDirectory(String str, File file) {
        Throwable e;
        ZipInputStream zipInputStream;
        Throwable th;
        BufferedOutputStream bufferedOutputStream = null;
        boolean z = false;
        if (StringUtils.isNullOrBlank(str)) {
            AppboyLogger.m666i(TAG, "Unpack directory null or blank. Zip file not unpacked.");
        } else if (file == null) {
            AppboyLogger.m666i(TAG, "Zip file is null. Zip file not unpacked.");
        } else {
            new File(str).mkdirs();
            ZipInputStream zipInputStream2;
            try {
                zipInputStream2 = new ZipInputStream(new BufferedInputStream(new FileInputStream(file)));
                try {
                    byte[] bArr = new byte[ZIP_UNPACK_BYTE_BUFFER_LENGTH];
                    while (true) {
                        ZipEntry nextEntry = zipInputStream2.getNextEntry();
                        if (nextEntry == null) {
                            break;
                        }
                        String name = nextEntry.getName();
                        if (!name.toLowerCase().startsWith("__macosx")) {
                            name = str + "/" + name;
                            if (nextEntry.isDirectory()) {
                                new File(name).mkdirs();
                            } else {
                                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(new FileOutputStream(name));
                                while (true) {
                                    try {
                                        int read = zipInputStream2.read(bArr);
                                        if (read == -1) {
                                            break;
                                        }
                                        bufferedOutputStream2.write(bArr, 0, read);
                                    } catch (FileNotFoundException e2) {
                                        e = e2;
                                        bufferedOutputStream = bufferedOutputStream2;
                                        zipInputStream = zipInputStream2;
                                    } catch (IOException e3) {
                                        e = e3;
                                        bufferedOutputStream = bufferedOutputStream2;
                                    } catch (Exception e4) {
                                        e = e4;
                                        bufferedOutputStream = bufferedOutputStream2;
                                    } catch (Throwable th2) {
                                        th = th2;
                                        bufferedOutputStream = bufferedOutputStream2;
                                    }
                                }
                                bufferedOutputStream2.close();
                                zipInputStream2.closeEntry();
                                bufferedOutputStream = bufferedOutputStream2;
                            }
                        }
                    }
                    zipInputStream2.close();
                    z = true;
                    if (zipInputStream2 != null) {
                        try {
                            zipInputStream2.close();
                        } catch (Throwable e5) {
                            AppboyLogger.m665e(TAG, "IOException during closing of zip file unpacking streams.", e5);
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                } catch (FileNotFoundException e6) {
                    e5 = e6;
                    zipInputStream = zipInputStream2;
                } catch (IOException e7) {
                    e5 = e7;
                } catch (Exception e8) {
                    e5 = e8;
                }
            } catch (FileNotFoundException e9) {
                e5 = e9;
                zipInputStream = null;
                try {
                    AppboyLogger.m665e(TAG, "FileNotFoundException during unpack of zip file.", e5);
                    if (zipInputStream != null) {
                        try {
                            zipInputStream.close();
                        } catch (Throwable e52) {
                            AppboyLogger.m665e(TAG, "IOException during closing of zip file unpacking streams.", e52);
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    zipInputStream2 = zipInputStream;
                    if (zipInputStream2 != null) {
                        try {
                            zipInputStream2.close();
                        } catch (Throwable e522) {
                            AppboyLogger.m665e(TAG, "IOException during closing of zip file unpacking streams.", e522);
                            throw th;
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e10) {
                e522 = e10;
                zipInputStream2 = null;
                try {
                    AppboyLogger.m665e(TAG, "IOException during unpack of zip file.", e522);
                    if (zipInputStream2 != null) {
                        try {
                            zipInputStream2.close();
                        } catch (Throwable e5222) {
                            AppboyLogger.m665e(TAG, "IOException during closing of zip file unpacking streams.", e5222);
                        }
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    return z;
                } catch (Throwable th4) {
                    th = th4;
                    if (zipInputStream2 != null) {
                        zipInputStream2.close();
                    }
                    if (bufferedOutputStream != null) {
                        bufferedOutputStream.close();
                    }
                    throw th;
                }
            } catch (Exception e11) {
                e5222 = e11;
                zipInputStream2 = null;
                AppboyLogger.m665e(TAG, "Exception during unpack of zip file.", e5222);
                if (zipInputStream2 != null) {
                    try {
                        zipInputStream2.close();
                    } catch (Throwable e52222) {
                        AppboyLogger.m665e(TAG, "IOException during closing of zip file unpacking streams.", e52222);
                    }
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                return z;
            } catch (Throwable th5) {
                th = th5;
                zipInputStream2 = null;
                if (zipInputStream2 != null) {
                    zipInputStream2.close();
                }
                if (bufferedOutputStream != null) {
                    bufferedOutputStream.close();
                }
                throw th;
            }
        }
        return z;
    }

    public static void clearEntireHtmlInAppMessageCache(File file) {
        AppboyLogger.m662d(TAG, "Deleting Html In App Messages folder");
        deleteFileOrDirectory(new File(file, HTML_INAPP_MESSAGES_FOLDER_NAME));
    }

    public static void clearInAppMessageLocalAssets(IInAppMessageHtml iInAppMessageHtml) {
        if (iInAppMessageHtml == null) {
            AppboyLogger.m662d(TAG, "Could not clear InAppMessage Local Assets for a null IInAppMessageHtml. Doing nothing.");
            return;
        }
        String localAssetsDirectoryUrl = iInAppMessageHtml.getLocalAssetsDirectoryUrl();
        if (StringUtils.isNullOrBlank(localAssetsDirectoryUrl)) {
            AppboyLogger.m662d(TAG, "IInAppMessageHtml has null local assets file url. Doing nothing.");
            return;
        }
        localAssetsDirectoryUrl = localAssetsDirectoryUrl.substring(FILE_URI_SCHEME_PREFIX.length(), localAssetsDirectoryUrl.length() - 1);
        AppboyLogger.m662d(TAG, "Deleting local html assets with path: " + localAssetsDirectoryUrl);
        deleteFileOrDirectory(new File(localAssetsDirectoryUrl));
    }

    static void deleteFileOrDirectory(File file) {
        if (file != null && file.exists()) {
            if (file.isDirectory()) {
                for (String file2 : file.list()) {
                    deleteFileOrDirectory(new File(file, file2));
                }
            }
            file.delete();
        }
    }
}
