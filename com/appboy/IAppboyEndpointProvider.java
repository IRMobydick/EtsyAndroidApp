package com.appboy;

import android.net.Uri;

public interface IAppboyEndpointProvider {
    Uri getApiEndpoint(Uri uri);

    Uri getResourceEndpoint(Uri uri);
}
