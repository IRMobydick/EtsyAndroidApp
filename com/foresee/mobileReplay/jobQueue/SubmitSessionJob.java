package com.foresee.mobileReplay.jobQueue;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import com.foresee.mobileReplay.data.BandwidthMonitor;
import com.foresee.mobileReplay.data.FileSystemHelper;
import com.foresee.mobileReplay.data.SessionRepository;
import com.foresee.mobileReplay.domain.Session;
import com.foresee.mobileReplay.domain.SessionGroup;
import com.foresee.mobileReplay.http.FsServiceClient;
import com.foresee.mobileReplay.session.SessionManager;
import com.foresee.mobileReplay.tasks.AbstractSessionTask;
import com.foresee.mobileReplay.tasks.CaptureSubmissionTask;
import com.foresee.mobileReplay.tasks.EventSubmissionTask;
import com.foresee.mobileReplay.tasks.SessionTask;
import com.foresee.mobileReplay.tasks.SessionTaskQueue;
import com.google.inject.Inject;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class SubmitSessionJob implements QueueableJob {
    @Inject
    BandwidthMonitor bandwidthMonitor;
    private String cid;
    private String groupId;
    @Inject
    private FsServiceClient serviceClient;
    private SessionGroup sessionGroup;

    public SubmitSessionJob(SessionGroup sessionGroup, String str) {
        this.sessionGroup = sessionGroup;
        this.cid = str;
        this.groupId = sessionGroup.getGroupId();
    }

    public SubmitSessionJob(String str, String str2) {
        this.groupId = str;
        this.cid = str2;
    }

    public void setServiceClient(FsServiceClient fsServiceClient) {
        this.serviceClient = fsServiceClient;
    }

    public void setBandwidthMonitor(BandwidthMonitor bandwidthMonitor) {
        this.bandwidthMonitor = bandwidthMonitor;
    }

    public String getGroupId() {
        return this.groupId;
    }

    public void setGroupId(String str) {
        this.groupId = str;
    }

    public String getCid() {
        return this.cid;
    }

    public void setCid(String str) {
        this.cid = str;
    }

    public String getDescription() {
        return String.format("[groupId => %s]", new Object[]{this.groupId});
    }

    public void execute(Application application, SessionRepository sessionRepository) {
        SessionTaskQueue sessionTaskQueue;
        this.bandwidthMonitor.startMobileDataLogging();
        this.serviceClient.setConnectivityManager((ConnectivityManager) application.getSystemService("connectivity"));
        SessionTaskQueue loadTaskQueue = sessionRepository.loadTaskQueue(this.groupId);
        if (loadTaskQueue == null) {
            sessionTaskQueue = new SessionTaskQueue();
        } else {
            sessionTaskQueue = loadTaskQueue;
        }
        if (this.sessionGroup != null) {
            for (Session session : this.sessionGroup.getSessions()) {
                sessionTaskQueue.add(new CaptureSubmissionTask(this.groupId, session.getSessionId(), this.cid));
                sessionTaskQueue.add(new EventSubmissionTask(this.groupId, session.getSessionId()));
            }
        }
        submitSessionGroup(application, this.groupId, sessionTaskQueue.getTasks(), sessionRepository);
        this.bandwidthMonitor.stopMobileDataLogging();
    }

    public String getShortDescription() {
        return "Submit session";
    }

    public String getIdentifier() {
        return this.groupId;
    }

    private List<SessionTask> getTasksAssociatedWithSessionId(String str, Stack<SessionTask> stack) {
        List<SessionTask> arrayList = new ArrayList();
        Iterator it = stack.iterator();
        while (it.hasNext()) {
            SessionTask sessionTask = (SessionTask) it.next();
            if (((AbstractSessionTask) sessionTask).getSessionId().equals(str)) {
                arrayList.add(sessionTask);
            }
        }
        return arrayList;
    }

    private boolean areSessionTasksTooLarge(List<SessionTask> list, String str, Context context) {
        File filesDir = context.getFilesDir();
        List<File> arrayList = new ArrayList();
        arrayList.add(new File(filesDir, "session_replay/captures"));
        arrayList.add(new File(filesDir, "session_replay/events"));
        long j = 0;
        for (File file : arrayList) {
            File file2;
            long storageUsedForDirectory;
            File file3 = new File(file2, this.groupId);
            if (file3.exists()) {
                file2 = new File(file3, str);
                if (file2.exists()) {
                    storageUsedForDirectory = j + FileSystemHelper.getStorageUsedForDirectory(file2);
                    j = storageUsedForDirectory;
                }
            }
            storageUsedForDirectory = j;
            j = storageUsedForDirectory;
        }
        Log.d("FORESEE_DATA_CAPS", String.format("Session %s requires %.2fMB", new Object[]{str, Double.valueOf(((double) j) / 1048576.0d)}));
        return this.bandwidthMonitor.willExceedMobileDataLimit(j);
    }

    private void submitSessionGroup(Context context, String str, List<SessionTask> list, SessionRepository sessionRepository) {
        Stack stack = new Stack();
        List arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        stack.addAll(list);
        while (!stack.empty()) {
            String sessionId = ((AbstractSessionTask) ((SessionTask) stack.peek())).getSessionId();
            List<SessionTask> tasksAssociatedWithSessionId = getTasksAssociatedWithSessionId(sessionId, stack);
            if (areSessionTasksTooLarge(tasksAssociatedWithSessionId, sessionId, context)) {
                for (SessionTask sessionTask : tasksAssociatedWithSessionId) {
                    stack.remove(sessionTask);
                    arrayList.add(sessionTask);
                }
            } else {
                for (SessionTask sessionTask2 : tasksAssociatedWithSessionId) {
                    sessionTask2.execute(new 1(this, stack, sessionTask2, arrayList), this.serviceClient, sessionRepository);
                }
            }
        }
        if (arrayList.isEmpty()) {
            sessionRepository.deleteTaskQueue(str);
            broadcastSubmissionComplete(context);
            return;
        }
        sessionRepository.persistTaskQueue(str, new SessionTaskQueue(arrayList));
    }

    public SessionGroup getSessionGroup() {
        return this.sessionGroup;
    }

    public void setSessionGroup(SessionGroup sessionGroup) {
        this.sessionGroup = sessionGroup;
    }

    private void broadcastSubmissionComplete(Context context) {
        context.sendBroadcast(new Intent(SessionManager.EVENT_SUBMISSION_COMPLETE));
    }
}
