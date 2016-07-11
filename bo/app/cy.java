package bo.app;

import com.appboy.models.IPutIntoJson;
import java.util.UUID;

public final class cy implements IPutIntoJson<String> {
    final UUID f279a;

    public final /* synthetic */ Object forJsonPut() {
        return this.f279a.toString();
    }

    private cy(UUID uuid) {
        this.f279a = uuid;
    }

    public static cy m144a() {
        return new cy(UUID.randomUUID());
    }

    public static cy m145a(String str) {
        return new cy(UUID.fromString(str));
    }

    public final String toString() {
        return this.f279a.toString();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f279a.equals(((cy) obj).f279a);
    }

    public final int hashCode() {
        return this.f279a.hashCode();
    }
}
