package com.c332030.util.comm;

import java.util.concurrent.TimeUnit;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;

/**
 * <p>
 * Description: OkHttpUtils
 * </p>
 *
 * @author c332030
 * @version 1.0
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OkHttpUtils {

    public static final int CONNECT_TIMEOUT = 3 * 1000;

    public static final int READ_TIMEOUT = 60 * 1000;

    public static final int WRITE_TIMEOUT = 60 * 1000;

    public static final int KEEP_ALIVE_TIMEOUT = 10 * 1000;

    public static final int MAX_TOTAL_CONNECTIONS = 50;

    public static final ConnectionPool CONNECTION_POOL = new ConnectionPool(
        MAX_TOTAL_CONNECTIONS, KEEP_ALIVE_TIMEOUT, TimeUnit.SECONDS
    );

    public static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
        .retryOnConnectionFailure(false)
        .connectionPool(CONNECTION_POOL)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .build();

}
