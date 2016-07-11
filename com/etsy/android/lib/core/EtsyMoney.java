package com.etsy.android.lib.core;

import com.etsy.android.lib.logger.EtsyDebug;
import com.etsy.android.lib.models.GiftCard;
import com.etsy.android.lib.util.CurrencyUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;
import org.parceler.Parcel;

@Parcel
public class EtsyMoney implements Serializable {
    private static boolean f1384a;
    private static final String f1385b;
    protected final BigDecimal mAmount;
    protected final Currency mCurrency;
    protected Integer mMaximumFractionDigits;

    static {
        f1384a = false;
        f1385b = EtsyDebug.m1891a(EtsyMoney.class);
    }

    public static void setConditionalCurrencyCodesHidden(boolean z) {
        f1384a = z;
    }

    public EtsyMoney() {
        this(new BigDecimal(0), CurrencyUtil.m3082d());
    }

    public EtsyMoney(BigDecimal bigDecimal) {
        this(bigDecimal, CurrencyUtil.m3082d());
    }

    public EtsyMoney(int i) {
        this(new BigDecimal(i));
    }

    public EtsyMoney(BigDecimal bigDecimal, Currency currency) {
        this.mAmount = bigDecimal;
        this.mCurrency = currency;
    }

    public EtsyMoney(double d, Currency currency) {
        this(new BigDecimal(d), currency);
    }

    public EtsyMoney(String str, Currency currency, Locale locale) {
        this(CurrencyUtil.m3076b(str, locale), currency);
    }

    public EtsyMoney(String str, Currency currency) {
        this(str, currency, Locale.getDefault());
    }

    public EtsyMoney(double d, String str) {
        this(new BigDecimal(d), Currency.getInstance(str));
    }

    public EtsyMoney(float f, String str) {
        this(new BigDecimal((double) f), Currency.getInstance(str));
    }

    public EtsyMoney(String str, String str2, Locale locale) {
        this(str, Currency.getInstance(str2), locale);
    }

    public EtsyMoney(String str, String str2) {
        this(str, Currency.getInstance(str2), Locale.getDefault());
    }

    public EtsyMoney withAmount(BigDecimal bigDecimal) {
        return new EtsyMoney(bigDecimal, this.mCurrency);
    }

    public EtsyMoney withAmount(String str, Locale locale) {
        return new EtsyMoney(CurrencyUtil.m3076b(str, locale), this.mCurrency);
    }

    public EtsyMoney withAmount(String str) {
        return withAmount(str, Locale.getDefault());
    }

    public EtsyMoney withAmount(double d) {
        return new EtsyMoney(new BigDecimal(d), this.mCurrency);
    }

    public EtsyMoney withCurrency(Currency currency) {
        return new EtsyMoney(this.mAmount, currency);
    }

    public EtsyMoney withCurrency(String str) {
        Currency instance;
        try {
            instance = Currency.getInstance(str);
        } catch (IllegalArgumentException e) {
            instance = Currency.getInstance(GiftCard.CURRENCY_USD);
        }
        return withCurrency(instance);
    }

    public Currency getCurrency() {
        return this.mCurrency;
    }

    public BigDecimal getAmount() {
        return this.mAmount;
    }

    public String getCurrencyCodeIfNotEqualToSupplied(String str) {
        return str.equalsIgnoreCase(this.mCurrency.getCurrencyCode()) ? StringUtils.EMPTY : this.mCurrency.getCurrencyCode();
    }

    public String getConditionalCurrencyCodeIfNotEqualToSupplied(String str) {
        if (f1384a) {
            return StringUtils.EMPTY;
        }
        return str.equalsIgnoreCase(this.mCurrency.getCurrencyCode()) ? StringUtils.EMPTY : this.mCurrency.getCurrencyCode();
    }

    public EtsyMoney setMaximumFractionDigits(Integer num) {
        this.mMaximumFractionDigits = num;
        return this;
    }

    protected NumberFormat getFormat(Locale locale) {
        NumberFormat a = CurrencyUtil.m3065a(this.mCurrency, locale);
        if (this.mMaximumFractionDigits != null) {
            a.setMaximumFractionDigits(this.mMaximumFractionDigits.intValue());
        }
        return a;
    }

    protected NumberFormat getFormat() {
        NumberFormat a = CurrencyUtil.m3064a(this.mCurrency);
        if (this.mMaximumFractionDigits != null) {
            a.setMaximumFractionDigits(this.mMaximumFractionDigits.intValue());
        }
        return a;
    }

    public static EtsyMoney getInstanceWithAmountOfFraction(Currency currency, int i, int i2) {
        return new EtsyMoney(new BigDecimal(i).divide(new BigDecimal(i2)), currency);
    }

    public static EtsyMoney getInstanceWithAmountOfFraction(Currency currency, long j, int i) {
        return new EtsyMoney(new BigDecimal(j).divide(new BigDecimal(i)), currency);
    }

    public static EtsyMoney getInstanceWithAmountOfFraction(Currency currency, String str, int i) {
        return new EtsyMoney(new BigDecimal(str).divide(new BigDecimal(i)), currency);
    }

    public String getCurrencyCode() {
        return this.mCurrency.getCurrencyCode();
    }

    public String getCurrencySymbol() {
        return this.mCurrency.getSymbol();
    }

    public String getCurrencySymbol(Locale locale) {
        return this.mCurrency.getSymbol(locale);
    }

    protected boolean isNewIntlSafeImplActive() {
        return CurrencyUtil.m3072a();
    }

    public String format(Locale locale) {
        if (isNewIntlSafeImplActive()) {
            return getFormat(locale).format(this.mAmount);
        }
        return CurrencyUtil.m3075b(this.mAmount.toString(), this.mCurrency.getCurrencyCode());
    }

    public String format() {
        return format(Locale.getDefault());
    }

    public String formatWithCurrencyCode(Locale locale) {
        return format(locale) + this.mCurrency.getCurrencyCode();
    }

    public String formatWithCurrencyCode() {
        return format() + " " + this.mCurrency.getCurrencyCode();
    }

    public String formatWithConditionalCurrencyCode(Locale locale) {
        if (f1384a) {
            return format(locale);
        }
        return formatWithCurrencyCode(locale);
    }

    public String formatWithConditionalCurrencyCode() {
        if (f1384a) {
            return format();
        }
        return formatWithCurrencyCode();
    }

    public String formatAsNonCurrencyWithDefaultCurrencyFractionDigits(Locale locale) {
        return CurrencyUtil.m3062a(this.mAmount, this.mCurrency, locale);
    }

    public String formatAsNonCurrencyWithDefaultCurrencyFractionDigits() {
        return formatAsNonCurrencyWithDefaultCurrencyFractionDigits(Locale.getDefault());
    }

    private void m1030a(EtsyMoney etsyMoney, String str) {
        if (!this.mCurrency.equals(etsyMoney.getCurrency())) {
            throw new EtsyMoneyException("tried to " + str + " an EtsyMoney amount in currency (" + this.mCurrency.getCurrencyCode() + ") with another EtsyMoney in currency (" + etsyMoney.getCurrency().getCurrencyCode() + ")");
        }
    }

    public EtsyMoney multiply(EtsyMoney etsyMoney) {
        m1030a(etsyMoney, "multiply");
        return new EtsyMoney(this.mAmount.multiply(etsyMoney.mAmount), this.mCurrency);
    }

    public EtsyMoney multiply(BigDecimal bigDecimal) {
        try {
            return multiply(new EtsyMoney(bigDecimal, this.mCurrency));
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1385b, "EtsyMoney objects currency mismatch with non EtsyMoney operand method", e);
            return null;
        }
    }

    public EtsyMoney multiply(int i) {
        return multiply(new BigDecimal(i));
    }

    public EtsyMoney divide(EtsyMoney etsyMoney) {
        m1030a(etsyMoney, "divide");
        return new EtsyMoney(this.mAmount.divide(etsyMoney.mAmount), this.mCurrency);
    }

    public EtsyMoney divide(BigDecimal bigDecimal) {
        try {
            return divide(new EtsyMoney(bigDecimal, this.mCurrency));
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1385b, "EtsyMoney objects currency mismatch with non EtsyMoney operand method", e);
            return null;
        }
    }

    public EtsyMoney divide(int i) {
        return divide(new BigDecimal(i));
    }

    public EtsyMoney add(EtsyMoney etsyMoney) {
        m1030a(etsyMoney, "add");
        return new EtsyMoney(this.mAmount.add(etsyMoney.mAmount), this.mCurrency);
    }

    public EtsyMoney add(BigDecimal bigDecimal) {
        try {
            return add(new EtsyMoney(bigDecimal, this.mCurrency));
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1385b, "EtsyMoney objects currency mismatch with non EtsyMoney operand method", e);
            return null;
        }
    }

    public EtsyMoney add(int i) {
        return add(new BigDecimal(i));
    }

    public EtsyMoney subtract(EtsyMoney etsyMoney) {
        m1030a(etsyMoney, "subtract");
        return new EtsyMoney(this.mAmount.subtract(etsyMoney.mAmount), this.mCurrency);
    }

    public EtsyMoney subtract(BigDecimal bigDecimal) {
        try {
            return subtract(new EtsyMoney(bigDecimal, this.mCurrency));
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1385b, "EtsyMoney objects currency mismatch with non EtsyMoney operand method", e);
            return null;
        }
    }

    public EtsyMoney subtract(int i) {
        return subtract(new BigDecimal(i));
    }

    public int compareTo(EtsyMoney etsyMoney) {
        m1030a(etsyMoney, "compare");
        return this.mAmount.compareTo(etsyMoney.mAmount);
    }

    public int compareTo(BigDecimal bigDecimal) {
        try {
            return compareTo(new EtsyMoney(bigDecimal, this.mCurrency));
        } catch (Throwable e) {
            EtsyDebug.m1917d(f1385b, "EtsyMoney objects currency mismatch with non EtsyMoney operand method", e);
            return 0;
        }
    }

    public int compareTo(int i) {
        return compareTo(new BigDecimal(i));
    }

    public boolean equals(EtsyMoney etsyMoney) {
        return etsyMoney.getAmount().equals(this.mAmount) && etsyMoney.getCurrency().equals(this.mCurrency);
    }
}
