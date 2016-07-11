package bo.app;

public enum ab {
    SHORT("yyyy-MM-dd"),
    LONG("yyyy-MM-dd kk:mm:ss");
    
    public final String f14c;

    private ab(String str) {
        this.f14c = str;
    }
}
