package bo.app;

public enum iw {
    STRICT(false, true),
    LENIENT(true, false),
    NON_EXTENSIBLE(false, false),
    STRICT_ORDER(true, true);
    
    public final boolean f781e;
    public final boolean f782f;

    private iw(boolean z, boolean z2) {
        this.f781e = z;
        this.f782f = z2;
    }
}
