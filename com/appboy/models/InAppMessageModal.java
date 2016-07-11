package com.appboy.models;

import bo.app.ce;
import org.json.JSONObject;

public class InAppMessageModal extends InAppMessageImmersiveBase {
    private Integer f937e;

    public InAppMessageModal() {
        this.f937e = null;
    }

    public InAppMessageModal(JSONObject jSONObject, ce ceVar) {
        super(jSONObject, ceVar);
        this.f937e = null;
    }

    public Integer getModalFrameColor() {
        return this.f937e;
    }

    public void setModalFrameColor(Integer num) {
        this.f937e = num;
    }
}
