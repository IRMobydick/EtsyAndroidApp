package com.etsy.android.lib.models.apiv3;

import com.etsy.android.lib.config.EtsyConfig;
import com.etsy.android.lib.config.EtsyConfigKeys;
import com.etsy.android.lib.util.aj;
import com.etsy.android.lib.util.bh;
import com.etsy.android.uikit.util.MachineTranslationViewState;
import com.etsy.android.uikit.view.MachineTranslationButton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.parceler.Parcel;

@Parcel
public class FAQs extends ArrayList<FAQ> implements MachineTranslationButton {
    private static final long serialVersionUID = 4072472450694829549L;
    protected MachineTranslationViewState mTranslationState;

    public FAQs() {
        this.mTranslationState = new MachineTranslationViewState();
    }

    public void updateTranslatedFAQs(List<FAQ> list) {
        HashMap hashMap = new HashMap(list.size());
        for (FAQ faq : list) {
            FAQ faq2;
            hashMap.put(faq2.getFaqId().getId(), faq2);
        }
        Iterator it = iterator();
        while (it.hasNext()) {
            faq2 = (FAQ) it.next();
            FAQ faq3 = (FAQ) hashMap.get(faq2.getFaqId().getId());
            if (faq3 != null) {
                faq2.setTranslatedQuestion(faq3.getQuestion());
                faq2.setTranslatedAnswer(faq3.getAnswer());
                faq2.setShowTranslatedFAQ(true);
            }
        }
    }

    public boolean isTranslationEligible() {
        if (EtsyConfig.m837a().m869d().m885c(EtsyConfigKeys.f1254a)) {
            String c = aj.m3235c();
            Iterator it = iterator();
            while (it.hasNext()) {
                FAQ faq = (FAQ) it.next();
                if (bh.m3343b(faq.getLanguage()) && !faq.getLanguage().equals(c)) {
                    return true;
                }
            }
        }
        return false;
    }

    public MachineTranslationViewState getTranslationState() {
        return this.mTranslationState;
    }
}
