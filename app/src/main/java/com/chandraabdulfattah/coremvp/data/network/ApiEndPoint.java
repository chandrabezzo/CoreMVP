package com.chandraabdulfattah.coremvp.data.network;

import com.chandraabdulfattah.coremvp.BuildConfig;

/**
 * Created by bezzo on 25/09/17.
 */

public final class ApiEndPoint {
    public static final String USER = BuildConfig.BASE_URL + "user";

    private ApiEndPoint() {
        // This class is not publicly instantiable
    }
}
