package com.foresee.mobileReplay.data;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileLock;
import org.apache.commons.lang3.StringUtils;

public class CapacityMonitorImpl implements CapacityMonitor {
    private Application context;
    private long currentUsageEstimate;
    private float minFreeSpacePercentage;
    private float storageLimit;

    @Inject
    public CapacityMonitorImpl(Application application, @Named("STORAGE_LIMIT_ABSOLUTE") float f, @Named("MIN_FREE_SPACE_PERCENTAGE") float f2) {
        this.storageLimit = STORAGE_LIMIT_ABSOLUTE;
        this.minFreeSpacePercentage = MIN_FREE_SPACE_PERCENTAGE;
        this.currentUsageEstimate = 0;
        this.context = application;
        if (f <= 0.0f) {
            f = STORAGE_LIMIT_ABSOLUTE;
        }
        this.storageLimit = f;
        if (f2 <= 0.0f) {
            f2 = MIN_FREE_SPACE_PERCENTAGE;
        }
        this.minFreeSpacePercentage = f2;
        Log.v("FORESEE_DATA_CAPS", String.format("Storage limit set at %.2f MB, %.2f%% free space", new Object[]{Float.valueOf(this.storageLimit), Float.valueOf(this.minFreeSpacePercentage / 100.0f)}));
    }

    public boolean isCapacityExceededAccurate(Application application) {
        double freeSpace = getFreeSpace();
        Log.d("FORESEE_DATA_CAPS", String.format("Used space: %.2f, Free Space: %.2f (%.3f%%)", new Object[]{Double.valueOf(((double) getStorageUsedAccurate(application)) / 1048576.0d), Double.valueOf(freeSpace), Double.valueOf(100.0d * getFreeSpaceRatio())}));
        if (((double) getStorageUsedAccurate(application)) / 1048576.0d > ((double) this.storageLimit)) {
            Log.w("FORESEE_DATA_CAPS", "Storage limit exceeded (accurate check)");
            return true;
        } else if (r6 >= ((double) this.minFreeSpacePercentage)) {
            return false;
        } else {
            Log.w("FORESEE_DATA_CAPS", "Free space limit exceeded (accurate check)");
            return true;
        }
    }

    public boolean isCapacityExceededFast() {
        getFreeSpace();
        double freeSpaceRatio = getFreeSpaceRatio();
        if (((double) this.currentUsageEstimate) / 1048576.0d > ((double) this.storageLimit)) {
            Log.w("FORESEE_DATA_CAPS", "Storage limit exceeded (quick check)");
            return true;
        } else if (freeSpaceRatio >= ((double) this.minFreeSpacePercentage)) {
            return false;
        } else {
            Log.w("FORESEE_DATA_CAPS", "Free space limit exceeded (quick check)");
            return true;
        }
    }

    private double getFreeSpace() {
        return ((double) Environment.getDataDirectory().getFreeSpace()) / 1048576.0d;
    }

    private double getFreeSpaceRatio() {
        return getFreeSpace() / (((double) Environment.getDataDirectory().getTotalSpace()) / 1048576.0d);
    }

    private long getStorageUsedAccurate(Context context) {
        long storageUsedForDirectory = FileSystemHelper.getStorageUsedForDirectory(new File(context.getFilesDir(), "session_replay"));
        long j = 0;
        try {
            j = getStorageUsedEstimate();
        } catch (Exception e) {
            Log.w("FORESEE_DATA_CAPS", "Failed to find/create storage usage file in getStorageUsedEstimate(): " + e);
        }
        if (j != storageUsedForDirectory) {
            Log.w("FORESEE_DATA_CAPS", String.format("Reported space used is incorrect (reported:%d/used:%d/delta:%d)", new Object[]{Long.valueOf(j), Long.valueOf(storageUsedForDirectory), Long.valueOf(j - storageUsedForDirectory)}));
        }
        setStorageUsedEstimate(storageUsedForDirectory);
        return storageUsedForDirectory;
    }

    public void setStorageUsedEstimate(long j) {
        try {
            saveStorageUsedEstimate(j);
            Log.d("FORESEE_DATA_CAPS", String.format("New storage usage:%d", new Object[]{Long.valueOf(j)}));
        } catch (Exception e) {
            Log.w("FORESEE_DATA_CAPS", "Failed to save storage usage file in setStorageUsedEstimate(): " + e);
        }
        this.currentUsageEstimate = j;
    }

    public void adjustUsedSpaceEstimate(long j) {
        try {
            this.currentUsageEstimate = getStorageUsedEstimate();
        } catch (Exception e) {
            Log.w("FORESEE_DATA_CAPS", "Failed to read storage usage file in adjustUsedSpaceEstimate(): " + e);
        }
        if (this.currentUsageEstimate < 0) {
            this.currentUsageEstimate = getStorageUsedAccurate(this.context);
        }
        this.currentUsageEstimate += j;
        try {
            saveStorageUsedEstimate(this.currentUsageEstimate);
            Log.d("FORESEE_DATA_CAPS", String.format("New storage usage:%d (delta:%d)", new Object[]{Long.valueOf(this.currentUsageEstimate), Long.valueOf(j)}));
        } catch (Exception e2) {
            Log.w("FORESEE_DATA_CAPS", "Failed to save storage usage file in adjustUsedSpaceEstimate(): " + e2);
        }
    }

    private long getStorageUsedEstimate() {
        long parseLong;
        File file = new File(FileSystemHelper.ensureDirectory(this.context, "session_replay"), "storage_usage");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileLock lock = randomAccessFile.getChannel().lock();
        FileInputStream fileInputStream = new FileInputStream(file);
        String str = StringUtils.EMPTY;
        while (fileInputStream.available() > 0) {
            str = str + String.valueOf((char) fileInputStream.read());
        }
        fileInputStream.close();
        if (str.length() > 0) {
            parseLong = Long.parseLong(str);
        } else {
            parseLong = -1;
        }
        lock.release();
        randomAccessFile.close();
        fileInputStream.close();
        return parseLong;
    }

    private void saveStorageUsedEstimate(long j) {
        File file = new File(FileSystemHelper.ensureDirectory(this.context, "session_replay"), "storage_usage");
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        FileLock lock = randomAccessFile.getChannel().lock();
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(String.format("%d", new Object[]{Long.valueOf(j)}).getBytes());
        lock.release();
        randomAccessFile.close();
        fileOutputStream.close();
    }
}
