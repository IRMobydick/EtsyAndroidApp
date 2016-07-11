package bo.app;

import com.appboy.models.IPutIntoJson;

public final class cz implements IPutIntoJson<String> {
    private final String f280a;

    public final /* bridge */ /* synthetic */ Object forJsonPut() {
        return this.f280a;
    }

    public cz(String str) {
        this.f280a = str;
    }

    public final String toString() {
        return this.f280a;
    }
}
