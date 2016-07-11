package com.etsy.android.lib.core.p005a;

/* renamed from: com.etsy.android.lib.core.a.b */
public class PageLinksParser {
    private String f1404a;
    private String f1405b;
    private String f1406c;
    private String f1407d;

    public PageLinksParser(String str) {
        if (str != null) {
            for (String split : str.split(",")) {
                String split2;
                String[] split3 = split2.split(";");
                if (split3.length >= 2) {
                    split2 = split3[0].trim();
                    if (split2.startsWith("<") && split2.endsWith(">")) {
                        String substring = split2.substring(1, split2.length() - 1);
                        for (int i = 1; i < split3.length; i++) {
                            String[] split4 = split3[i].trim().split("=");
                            if (split4.length >= 2 && "rel".equals(split4[0])) {
                                Object obj = split4[1];
                                if (obj.startsWith("\"") && obj.endsWith("\"")) {
                                    obj = obj.substring(1, obj.length() - 1);
                                }
                                if ("first".equals(obj)) {
                                    this.f1404a = substring;
                                } else if ("last".equals(obj)) {
                                    this.f1405b = substring;
                                } else if ("next".equals(obj)) {
                                    this.f1406c = substring;
                                } else if ("prev".equals(obj)) {
                                    this.f1407d = substring;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public String m1071a() {
        return this.f1406c;
    }
}
