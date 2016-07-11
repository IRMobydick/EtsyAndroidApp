package com.foresee.mobileReplay.data;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.util.Log;
import com.etsy.android.lib.models.apiv3.ActivityFeedEntity;
import com.foresee.mobileReplay.domain.DiffSet;
import com.foresee.mobileReplay.domain.Session;
import com.foresee.mobileReplay.domain.SessionEvent;
import com.foresee.mobileReplay.domain.SessionEvent.SessionEventDeserializer;
import com.foresee.mobileReplay.domain.SessionEventData;
import com.foresee.mobileReplay.domain.SessionEvents;
import com.foresee.mobileReplay.domain.SessionEvents.SessionEventsDeserializer;
import com.foresee.mobileReplay.domain.SessionGroup;
import com.foresee.mobileReplay.session.Pending;
import com.foresee.mobileReplay.session.ReplaySessionState;
import com.foresee.mobileReplay.tasks.SessionTask;
import com.foresee.mobileReplay.tasks.SessionTaskQueue;
import com.foresee.mobileReplay.util.LogUtil;
import com.foresee.sdk.events.LifecycleEvent.EventType;
import com.foresee.sdk.json.CustomJsonProcessor;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.e;
import com.google.gson.g;
import com.google.inject.Inject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class SessionRepositoryImpl implements SessionRepository {
    static final /* synthetic */ boolean $assertionsDisabled;
    private static final int BUFFER_SIZE = 2048;
    private static e customGson;
    private static e plainGson;
    private CapacityMonitor capacityMonitor;
    private Application context;

    static {
        $assertionsDisabled = !SessionRepositoryImpl.class.desiredAssertionStatus() ? true : $assertionsDisabled;
        customGson = new g().a(SessionTask.class, new CustomJsonProcessor()).a(ReplaySessionState.class, new CustomJsonProcessor()).a(SessionEvent.class, new SessionEventDeserializer()).a(SessionEventData.class, new CustomJsonProcessor()).a(SessionEvents.class, new SessionEventsDeserializer()).a();
        plainGson = new e();
    }

    @Inject
    public SessionRepositoryImpl(Application application, CapacityMonitor capacityMonitor) {
        this.context = application;
        this.capacityMonitor = capacityMonitor;
    }

    public void initApplicationContext(Application application) {
        this.context = application;
    }

    public Bitmap retrieveImage(String str, String str2, String str3) {
        FileInputStream fileInputStream;
        Throwable e;
        Throwable th;
        Bitmap bitmap = null;
        FileInputStream fileInputStream2 = null;
        try {
            File ensureDirectory = FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures");
            if (!$assertionsDisabled && (ensureDirectory == null || !ensureDirectory.exists())) {
                throw new AssertionError();
            } else if ($assertionsDisabled || str3 != null) {
                File file = new File(ensureDirectory, str3);
                if (file.exists()) {
                    fileInputStream = new FileInputStream(file);
                    try {
                        Options options = new Options();
                        options.inPreferredConfig = Config.ARGB_8888;
                        bitmap = BitmapFactory.decodeStream(fileInputStream, new Rect(), options);
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (Throwable e2) {
                                Log.e("FORESEE_CAPTURE", e2.getMessage(), e2);
                            }
                        }
                    } catch (FileNotFoundException e3) {
                        e2 = e3;
                        try {
                            Log.e("FORESEE_CAPTURE", e2.getMessage(), e2);
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable e22) {
                                    Log.e("FORESEE_CAPTURE", e22.getMessage(), e22);
                                }
                            }
                            return bitmap;
                        } catch (Throwable th2) {
                            th = th2;
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (Throwable e222) {
                                    Log.e("FORESEE_CAPTURE", e222.getMessage(), e222);
                                }
                            }
                            throw th;
                        }
                    }
                }
                Log.w("FORESEE_CAPTURE", String.format("%s doesn't exist", new Object[]{file.getPath()}));
                if (bitmap != null) {
                    try {
                        fileInputStream2.close();
                    } catch (Throwable e2222) {
                        Log.e("FORESEE_CAPTURE", e2222.getMessage(), e2222);
                    }
                }
                return bitmap;
            } else {
                throw new AssertionError();
            }
        } catch (FileNotFoundException e4) {
            e2222 = e4;
            fileInputStream = bitmap;
            Log.e("FORESEE_CAPTURE", e2222.getMessage(), e2222);
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            return bitmap;
        } catch (Throwable e22222) {
            fileInputStream = bitmap;
            th = e22222;
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            throw th;
        }
    }

    public void saveImage(String str, String str2, Bitmap bitmap, String str3) {
        if (bitmap != null) {
            try {
                File file = new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures"), str3);
                OutputStream fileOutputStream = new FileOutputStream(file);
                bitmap.compress(CompressFormat.JPEG, 80, fileOutputStream);
                adjustUsedSpaceEstimate(file.length(), true);
                fileOutputStream.flush();
                fileOutputStream.close();
                Log.d("FORESEE_CAPTURE", String.format("Screenshot saved to %s", new Object[]{file.getPath()}));
                if (file.getFreeSpace() == 0) {
                    Log.e("FORESEE_DATA_CAPS", "Error saving image. Session will be deleted");
                    storageError();
                }
            } catch (Throwable e) {
                Log.e("FORESEE_CAPTURE", e.getMessage(), e);
                e.printStackTrace();
            } catch (IOException e2) {
                Log.e("FORESEE_DATA_CAPS", "Error saving image. Session will be deleted");
                storageError();
            }
        }
    }

    public void deleteImage(String str, String str2, String str3) {
        try {
            File ensureDirectory = FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures");
            if (ensureDirectory.exists()) {
                File[] listFiles = ensureDirectory.listFiles(new 1(this, str3));
                if (listFiles.length > 0) {
                    adjustUsedSpaceEstimate(-listFiles[0].length(), $assertionsDisabled);
                    listFiles[0].delete();
                    return;
                }
                Log.w("FORESEE_CAPTURE", String.format("Unable to delete image: %s", new Object[]{str3}));
            }
        } catch (Throwable e) {
            Log.e("FORESEE_CAPTURE", e.getMessage(), e);
        }
    }

    public List<DiffSet> retrieveDiffSets(String str, String str2) {
        try {
            File file = new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures"), "metadata.json");
            if (!file.exists()) {
                return new ArrayList();
            }
            return (List) customGson.a(FileSystemHelper.readAllTextInFile(file), new 2(this).getType());
        } catch (Throwable e) {
            Log.e("FORESEE_CAPTURE", e.getMessage(), e);
            return null;
        } catch (JsonSyntaxException e2) {
            Log.e("FORESEE_CAPTURE", e2.getMessage(), e2);
            return null;
        } catch (JsonIOException e3) {
            Log.e("FORESEE_CAPTURE", e3.getMessage(), e3);
            return null;
        } catch (Throwable e4) {
            Log.e("FORESEE_CAPTURE", e4.getMessage(), e4);
            return null;
        }
    }

    public void persistDiffSets(String str, String str2, List<DiffSet> list) {
        try {
            String a = customGson.a(list, new 3(this).getType());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures"), "metadata.json"));
            fileOutputStream.write(a.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e("FORESEE_DATA_CAPS", "Error persisting diffs. Session will be deleted.");
            storageError();
        }
    }

    public SessionEvents retrieveSessionEvents(String str, String str2, String str3) {
        try {
            File file = new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/events"), str3);
            if (file.exists()) {
                return (SessionEvents) customGson.a(FileSystemHelper.readAllTextInFile(file), SessionEvents.class);
            }
        } catch (JsonSyntaxException e) {
            Log.w("FORESEE_CAPTURE", e.getMessage(), e);
        } catch (JsonIOException e2) {
            Log.w("FORESEE_CAPTURE", e2.getMessage(), e2);
        } catch (IOException e3) {
            Log.e("FORESEE_DATA_CAPS", "Error retrieving session events. Session will be deleted.");
            storageError();
        }
        return new SessionEvents();
    }

    public void persistSessionEvents(String str, String str2, String str3, SessionEvents sessionEvents) {
        try {
            String a = customGson.a(sessionEvents, SessionEvents.class);
            File file = new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/events"), str3);
            long length = file.length();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(a.getBytes());
            fileOutputStream.close();
            adjustUsedSpaceEstimate(file.length() - length, $assertionsDisabled);
        } catch (Exception e) {
            Log.e("FORESEE_CAPTURE", "There was a problem writing the session events. Session data will be deleted");
            storageError();
        }
    }

    public SessionEvents mergeSessionEvents(String str, String str2, String... strArr) {
        SessionEvents sessionEvents = new SessionEvents();
        for (String retrieveSessionEvents : strArr) {
            sessionEvents.getEvents(str, str2).addAll(retrieveSessionEvents(str, str2, retrieveSessionEvents).getEvents(str, str2));
        }
        return sessionEvents;
    }

    public boolean requestSessionStart(boolean z) {
        return requestMoreSpace(z);
    }

    boolean requestMoreSpace(boolean z) {
        boolean isCapacityExceededAccurate = this.capacityMonitor.isCapacityExceededAccurate(this.context);
        if (isCapacityExceededAccurate) {
            Log.w("FORESEE_DATA_CAPS", "Capacity exceeded");
            if (z) {
                Log.d("FORESEE_DATA_CAPS", "Beginning session purges...");
                while (isCapacityExceededAccurate) {
                    if (purgeSession(true)) {
                        Log.d("FORESEE_DATA_CAPS", "Successfully purged session");
                        isCapacityExceededAccurate = this.capacityMonitor.isCapacityExceededAccurate(this.context);
                    } else {
                        Log.w("FORESEE_DATA_CAPS", "No more sessions to purge. Session recording cannot continue");
                        return $assertionsDisabled;
                    }
                }
                Log.d("FORESEE_DATA_CAPS", "Capacity is no longer exceeded. Proceeding with session recording");
                return true;
            }
            Log.w("FORESEE_DATA_CAPS", "No sessions available for purging. Session recording cannot continue");
            return $assertionsDisabled;
        }
        Log.d("FORESEE_DATA_CAPS", "Capacity not exceeded");
        return true;
    }

    private boolean purgeSession(boolean z) {
        List sessionsOrderedByDate = getSessionsOrderedByDate();
        if (sessionsOrderedByDate == null) {
            return $assertionsDisabled;
        }
        int i;
        int size = sessionsOrderedByDate.size();
        if (z) {
            i = 1;
        } else {
            i = 2;
        }
        if (size <= i) {
            return $assertionsDisabled;
        }
        int i2;
        i = (int) Math.floor(((double) size) / 2.0d);
        if (z) {
            i2 = i;
        } else {
            i2 = (int) Math.ceil(((double) (size - 1)) / 2.0d);
        }
        Session session = (Session) sessionsOrderedByDate.get(i2);
        Log.w("FORESEE_DATA_CAPS", String.format("Removing session %d of %d due to storage limitations (%s-%s)", new Object[]{Integer.valueOf(i2 + 1), Integer.valueOf(size), session.getGroupId(), session.getSessionId()}));
        removeCapturesDir(session.getGroupId(), session.getSessionId());
        removeEventsDir(session.getGroupId(), session.getSessionId());
        removeSessionFromGroup(session);
        sessionsOrderedByDate.remove(i2);
        return true;
    }

    public void persistTaskQueue(String str, SessionTaskQueue sessionTaskQueue) {
        IOException e;
        Throwable th;
        String a = customGson.a(sessionTaskQueue, SessionTaskQueue.class);
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(new File(FileSystemHelper.ensureDirectory(this.context, "session_replay/submission"), String.format("%s.json", new Object[]{str})));
            try {
                fileOutputStream.write(a.getBytes());
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                        throw new RuntimeException(e2.getMessage());
                    }
                }
            } catch (IOException e3) {
                e2 = e3;
                try {
                    e2.printStackTrace();
                    throw new RuntimeException(e2.getMessage());
                } catch (Throwable th2) {
                    th = th2;
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e22) {
                            e22.printStackTrace();
                            throw new RuntimeException(e22.getMessage());
                        }
                    }
                    throw th;
                }
            }
        } catch (IOException e4) {
            e22 = e4;
            fileOutputStream = null;
            e22.printStackTrace();
            throw new RuntimeException(e22.getMessage());
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public SessionTaskQueue loadTaskQueue(String str) {
        File file = new File(FileSystemHelper.ensureDirectory(this.context, "session_replay/submission"), String.format("%s.json", new Object[]{str}));
        if (file.exists()) {
            Log.d("FORESEE_CAPTURE", String.format("Task queue found. %d tasks to run", new Object[]{Integer.valueOf(((SessionTaskQueue) customGson.a(FileSystemHelper.readAllTextInFile(file), SessionTaskQueue.class)).getTasks().size())}));
            return (SessionTaskQueue) customGson.a(FileSystemHelper.readAllTextInFile(file), SessionTaskQueue.class);
        }
        Log.d("FORESEE_CAPTURE", "No task queue found at " + file.toString());
        return null;
    }

    public void deleteSessionData() {
        new File(FileSystemHelper.ensureDirectoryForGroup(this.context, retrieveSessionGroupId(), "session_replay/session_json"), "sessionGroupData.json").delete();
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("sessionGroupData.json", 0);
        Log.d("FORESEE_CAPTURE", "Deleting session data: " + sharedPreferences.getString(ActivityFeedEntity.DATA, null));
        Editor edit = sharedPreferences.edit();
        edit.remove("groupId");
        edit.commit();
    }

    public void prepareSessionCaptures(String str, String str2) {
        Log.d("FORESEE_CAPTURE", String.format("Preparing captures for %s/%s", new Object[]{str.toString(), str2}));
        File ensureDirectory = FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures");
        File file = new File(ensureDirectory, "captures.zip");
        byte[] retrieveCaptureStream = retrieveCaptureStream(str, str2);
        OutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(retrieveCaptureStream);
        fileOutputStream.flush();
        fileOutputStream.close();
        adjustUsedSpaceEstimate(file.length(), $assertionsDisabled);
        if (file.length() < ((long) retrieveCaptureStream.length)) {
            Log.e("FORESEE_DATA_CAPS", "There was not enough space to store the capture file. Session will be deleted");
            storageError();
            return;
        }
        adjustUsedSpaceEstimate(-FileSystemHelper.removeAllExceptZipInDirectory(ensureDirectory), $assertionsDisabled);
    }

    public File retrieveCaptureFile(String str, String str2) {
        return new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures"), "captures.zip");
    }

    public void removeCapturesDir(String str, String str2) {
        adjustUsedSpaceEstimate(-FileSystemHelper.deleteCaptureDirectory(this.context, str, str2), $assertionsDisabled);
    }

    public String persistSessionEvents(String str, String str2, SessionEvents sessionEvents) {
        try {
            String a = customGson.a(sessionEvents.getEvents(str, str2).toArray(), Object[].class);
            File ensureDirectory = FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/events");
            String createEventFileName = createEventFileName(str, str2);
            File file = new File(ensureDirectory, createEventFileName);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long length = file.length();
            fileOutputStream.write(a.getBytes());
            adjustUsedSpaceEstimate(file.length() - length, $assertionsDisabled);
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.d("FORESEE_CAPTURE", String.format("Session events written to %s", new Object[]{createEventFileName}));
            return createEventFileName;
        } catch (Throwable e) {
            Log.e("FORESEE_DATA_CAPS", "Error persisting session events. Session will be deleted.");
            storageError();
            throw new PersistenceException(e.getMessage(), e);
        }
    }

    private String createEventFileName(String str, String str2) {
        return String.format("%s_%s_events.json", new Object[]{str, str2});
    }

    public String retrieveSessionEventJson(String str, String str2) {
        try {
            File file = new File(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/events"), createEventFileName(str, str2));
            if (file.exists()) {
                return FileSystemHelper.readAllTextInFile(file);
            }
            Log.w("FORESEE_CAPTURE", String.format("JSON file (%s) not found", new Object[]{r2}));
            return null;
        } catch (Throwable e) {
            Log.e("FORESEE_CAPTURE", e.getMessage(), e);
            return null;
        }
    }

    public String retrieveSessionEventJsonForTransmission(String str, String str2) {
        String retrieveSessionEventJson = retrieveSessionEventJson(str, str2);
        Type type = new 4(this).getType();
        return plainGson.a((List) customGson.a(retrieveSessionEventJson, type), type);
    }

    public void removeEventsDir(String str, String str2) {
        adjustUsedSpaceEstimate(-FileSystemHelper.deleteEventDirectory(this.context, str, str2), $assertionsDisabled);
    }

    public void deleteTaskQueue(String str) {
        File file = new File(FileSystemHelper.ensureDirectory(this.context, "session_replay/submission"), String.format("%s.json", new Object[]{str}));
        if (file.exists()) {
            file.delete();
        }
    }

    private ByteArrayOutputStream createZip(File file) {
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        File[] listFiles = file.listFiles(new 5(this, Pattern.compile("(_([0-9]+).jpg)|([\\w].json)")));
        if (listFiles.length > 0) {
            for (File file2 : listFiles) {
                FileInputStream fileInputStream = new FileInputStream(file2);
                zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                byte[] bArr = new byte[BUFFER_SIZE];
                while (true) {
                    int read = fileInputStream.read(bArr, 0, BUFFER_SIZE);
                    if (read == -1) {
                        break;
                    }
                    zipOutputStream.write(bArr, 0, read);
                }
                fileInputStream.close();
                zipOutputStream.closeEntry();
            }
            zipOutputStream.flush();
            zipOutputStream.close();
            LogUtil.d("Wrote %d bytes to zip stream", new Object[]{Integer.valueOf(byteArrayOutputStream.size())});
        }
        return byteArrayOutputStream;
    }

    private void adjustUsedSpaceEstimate(long j, boolean z) {
        this.capacityMonitor.adjustUsedSpaceEstimate(j);
        if (this.capacityMonitor.isCapacityExceededFast() && z && !purgeSession($assertionsDisabled)) {
            this.context.sendBroadcast(new Intent(EventType.STORAGE_EXCEEDED.value()));
        }
    }

    public void updateSessionGroup(Session session) {
        SessionGroup retrieveSessionGroup = retrieveSessionGroup();
        retrieveSessionGroup.getSessions().add(session);
        try {
            String a = customGson.a(retrieveSessionGroup, SessionGroup.class);
            saveSessionGroupJSON(retrieveSessionGroup.getGroupId(), a);
            LogUtil.d("Saving session group: " + a, new Object[0]);
        } catch (JsonParseException e) {
            LogUtil.err(e.getMessage(), e, new Object[0]);
        }
    }

    public boolean saveSessionGroupJSON(String str, String str2) {
        try {
            File file = new File(FileSystemHelper.ensureDirectoryForGroup(this.context, str, "session_replay/session_json"), "sessionGroupData.json");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            long length = file.length();
            fileOutputStream.write(str2.getBytes());
            adjustUsedSpaceEstimate(file.length() - length, $assertionsDisabled);
            fileOutputStream.flush();
            fileOutputStream.close();
            Log.d("FORESEE_CAPTURE", String.format("Session group json written to %s", new Object[]{"sessionGroupData.json"}));
            return true;
        } catch (Throwable e) {
            LogUtil.err(e.getMessage(), e, new Object[0]);
            return $assertionsDisabled;
        }
    }

    public boolean removeSessionFromGroup(Session session) {
        SessionGroup retrieveSessionGroup = retrieveSessionGroup();
        List<Session> sessions = retrieveSessionGroup.getSessions();
        for (Session session2 : sessions) {
            if (session2.getSessionId().compareTo(session.getSessionId()) == 0) {
                sessions.remove(session2);
                return saveSessionGroupJSON(retrieveSessionGroup.getGroupId(), customGson.a(retrieveSessionGroup, SessionGroup.class));
            }
        }
        return $assertionsDisabled;
    }

    public void removeSessionGroup(String str) {
        FileSystemHelper.deleteDirectory(this.context, "session_replay/session_json");
    }

    public String retrieveSessionGroupId() {
        SharedPreferences sharedPreferences = this.context.getSharedPreferences("sessionGroupData.json", 0);
        String string = sharedPreferences.getString("groupId", null);
        if (string != null) {
            return string;
        }
        string = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString("groupId", string);
        edit.commit();
        return string;
    }

    public SessionGroup retrieveSessionGroup() {
        File file = new File(FileSystemHelper.ensureDirectoryForGroup(this.context, retrieveSessionGroupId(), "session_replay/session_json"), "sessionGroupData.json");
        if (file.exists()) {
            try {
                Reader fileReader = new FileReader(file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    stringBuffer.append(readLine);
                }
                String stringBuffer2 = stringBuffer.toString();
                LogUtil.d("Retrieved json = %s", new Object[]{stringBuffer2});
                if (stringBuffer2 != null) {
                    return (SessionGroup) customGson.a(stringBuffer2, SessionGroup.class);
                }
                fileReader.close();
            } catch (Throwable e) {
                LogUtil.err(e.getMessage(), e, new Object[0]);
            }
        }
        return new SessionGroup(retrieveSessionGroupId());
    }

    public ReplaySessionState retrieveSessionState() {
        String string = this.context.getSharedPreferences("FS_REPLAY_LIB", 0).getString("SESSION_STATE", null);
        if (string != null) {
            try {
                return (ReplaySessionState) customGson.a(string, ReplaySessionState.class);
            } catch (JsonParseException e) {
                LogUtil.err(e.getMessage(), e, new Object[0]);
            }
        }
        return new Pending();
    }

    public void persistSessionState(ReplaySessionState replaySessionState) {
        Editor edit = this.context.getSharedPreferences("FS_REPLAY_LIB", 0).edit();
        try {
            edit.putString("SESSION_STATE", customGson.a(replaySessionState));
            edit.commit();
        } catch (JsonParseException e) {
            Log.e("FORESEE_CAPTURE", e.getMessage(), e);
        }
    }

    private void serializeDiffSets(Application application, String str, String str2, List<DiffSet> list) {
        try {
            String a = customGson.a(list, new 6(this).getType());
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileSystemHelper.ensureDirectory(application, str, str2, "session_replay/captures"), "metadata.json"));
            fileOutputStream.write(a.getBytes());
            fileOutputStream.close();
        } catch (Throwable e) {
            Log.e("FORESEE_CAPTURE", e.getMessage(), e);
        } catch (JsonParseException e2) {
            Log.e("FORESEE_CAPTURE", e2.getMessage(), e2);
        } catch (Throwable e3) {
            Log.e("FORESEE_CAPTURE", e3.getMessage(), e3);
        }
    }

    private byte[] retrieveCaptureStream(String str, String str2) {
        LogUtil.d("Session (%s/%s) - contains %d captures", new Object[]{str.toString(), str2, Integer.valueOf(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures").list().length)});
        return createZip(FileSystemHelper.ensureDirectory(this.context, str, str2, "session_replay/captures")).toByteArray();
    }

    public List<Session> getSessionsOrderedByDate() {
        File file = new File(this.context.getFilesDir(), "session_replay/events");
        if (file == null) {
            return null;
        }
        List<Session> arrayList = new ArrayList();
        File[] listFiles = file.listFiles();
        if (listFiles == null) {
            return null;
        }
        for (File file2 : listFiles) {
            for (File file3 : file2.listFiles()) {
                arrayList.add(new Session(file3.getName(), file3.lastModified(), file2.getName()));
            }
        }
        Collections.sort(arrayList, new SessionComparator(this));
        return arrayList;
    }

    public void deleteData() {
        FileSystemHelper.deleteDirectory(this.context, "session_replay/events");
        FileSystemHelper.deleteDirectory(this.context, "session_replay/captures");
        deleteSessionData();
    }

    public void storageError() {
        this.context.sendBroadcast(new Intent(EventType.STORAGE_ERROR.value()));
    }

    public void saveStringToFile(String str, String str2, String str3, String str4) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(FileSystemHelper.ensureDirectory(this.context, str2, str3, "session_replay/events"), str4));
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
