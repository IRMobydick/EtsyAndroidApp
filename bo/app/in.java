package bo.app;

import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

public final class in {
    public static boolean m562a(InputStream inputStream, OutputStream outputStream, io ioVar, int i) {
        int available = inputStream.available();
        if (available <= 0) {
            available = 512000;
        }
        byte[] bArr = new byte[i];
        if (m561a(ioVar, 0, available)) {
            return false;
        }
        int i2 = 0;
        do {
            int read = inputStream.read(bArr, 0, i);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
                i2 += read;
            } else {
                outputStream.flush();
                return true;
            }
        } while (!m561a(ioVar, i2, available));
        return false;
    }

    private static boolean m561a(io ioVar, int i, int i2) {
        if (ioVar == null || ioVar.m472a(i, i2) || (i * 100) / i2 >= 75) {
            return false;
        }
        return true;
    }

    public static void m560a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }
}
