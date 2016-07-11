package bo.app;

public final class fc {
    public static <Source, Target> Target m329a(Source source, Class<Target> cls) {
        if (cls.isAssignableFrom(source.getClass())) {
            return cls.cast(source);
        }
        return null;
    }
}
