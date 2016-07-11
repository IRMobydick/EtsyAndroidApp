package bo.app;

import java.io.FilterInputStream;
import java.io.InputStream;

public final class hf extends FilterInputStream {
    public hf(InputStream inputStream) {
        super(inputStream);
    }

    public final long skip(long j) {
        long j2 = 0;
        while (j2 < j) {
            long skip = this.in.skip(j - j2);
            if (skip == 0) {
                if (read() < 0) {
                    break;
                }
                skip = 1;
            }
            j2 = skip + j2;
        }
        return j2;
    }
}
