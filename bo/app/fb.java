package bo.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class fb {
    private static final BigDecimal f440a;

    static {
        f440a = new BigDecimal("100");
    }

    public static BigDecimal m327a(int i) {
        return new BigDecimal(i).divide(f440a);
    }

    public static BigDecimal m328a(BigDecimal bigDecimal) {
        return bigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}
