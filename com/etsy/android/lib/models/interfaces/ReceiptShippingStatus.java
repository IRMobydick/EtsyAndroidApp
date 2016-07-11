package com.etsy.android.lib.models.interfaces;

import android.support.annotation.Nullable;
import java.util.Date;

public interface ReceiptShippingStatus {
    int daysUntilShipped();

    @Nullable
    Date getShipDate();

    boolean isInPerson();

    boolean wasShipped();
}
