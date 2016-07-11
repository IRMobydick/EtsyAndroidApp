package com.appboy.models;

import bo.app.ce;
import bo.app.fg;
import com.appboy.enums.inappmessage.SlideFrom;
import org.json.JSONObject;

public class InAppMessageSlideup extends InAppMessageBase {
    private SlideFrom f938e;
    private int f939f;

    public InAppMessageSlideup() {
        this.f938e = SlideFrom.BOTTOM;
    }

    public InAppMessageSlideup(JSONObject jSONObject, ce ceVar) {
        this(jSONObject, ceVar, (SlideFrom) fg.m339a(jSONObject, "slide_from", SlideFrom.class, SlideFrom.BOTTOM), jSONObject.optInt("close_btn_color"));
    }

    private InAppMessageSlideup(JSONObject jSONObject, ce ceVar, SlideFrom slideFrom, int i) {
        super(jSONObject, ceVar);
        this.f938e = SlideFrom.BOTTOM;
        this.f938e = slideFrom;
        if (this.f938e == null) {
            this.f938e = SlideFrom.BOTTOM;
        }
        this.f939f = i;
    }

    public SlideFrom getSlideFrom() {
        return this.f938e;
    }

    public int getChevronColor() {
        return this.f939f;
    }

    public void setChevronColor(int i) {
        this.f939f = i;
    }

    public void setSlideFrom(SlideFrom slideFrom) {
        this.f938e = slideFrom;
    }
}
