package com.example.exoplayerdemo.http.retrofit;

import android.util.Log;

import com.example.exoplayerdemo.http.GoogleApiService;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.X509TrustManager;

import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.TlsVersion;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RetrofitUtil {
    private static GoogleApiService googleService;
    private static Retrofit googleRetrofit;
    private static Retrofit retrofit;

    protected static GoogleApiService getService() {
        googleService = getRetrofit().create(GoogleApiService.class);
        return googleService;
    }



    static X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };

    public static ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TlsVersion.TLS_1_1)
            .tlsVersions(TlsVersion.TLS_1_2)
            .cipherSuites(
                    CipherSuite.TLS_RSA_WITH_NULL_MD5,
                    CipherSuite.TLS_RSA_WITH_NULL_SHA,
                    CipherSuite.TLS_RSA_EXPORT_WITH_RC4_40_MD5,
                    CipherSuite.TLS_RSA_WITH_RC4_128_MD5,
                    CipherSuite.TLS_RSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_RSA_EXPORT_WITH_DES40_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_DES_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_EXPORT_WITH_DES40_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_DES_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_EXPORT_WITH_DES40_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_DES_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_DH_anon_EXPORT_WITH_RC4_40_MD5,
                    CipherSuite.TLS_DH_anon_WITH_RC4_128_MD5,
                    CipherSuite.TLS_DH_anon_EXPORT_WITH_DES40_CBC_SHA,
                    CipherSuite.TLS_DH_anon_WITH_DES_CBC_SHA,
                    CipherSuite.TLS_KRB5_WITH_DES_CBC_MD5,
                    CipherSuite.TLS_KRB5_WITH_3DES_EDE_CBC_MD5,
                    CipherSuite.TLS_KRB5_WITH_RC4_128_MD5,
                    CipherSuite.TLS_KRB5_EXPORT_WITH_DES_CBC_40_SHA,
                    CipherSuite.TLS_KRB5_EXPORT_WITH_RC4_40_SHA,
                    CipherSuite.TLS_KRB5_EXPORT_WITH_DES_CBC_40_MD5,
                    CipherSuite.TLS_KRB5_EXPORT_WITH_RC4_40_MD5,
                    CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_NULL_SHA256,
                    CipherSuite.TLS_RSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_RSA_WITH_CAMELLIA_128_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_DHE_DSS_WITH_AES_256_CBC_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_AES_256_CBC_SHA256,
                    CipherSuite.TLS_DH_anon_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_DH_anon_WITH_AES_256_CBC_SHA256,
                    CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA,
                    CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_256_CBC_SHA,
                    CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA,
                    CipherSuite.TLS_PSK_WITH_RC4_128_SHA,
                    CipherSuite.TLS_PSK_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_PSK_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_SEED_CBC_SHA,
                    CipherSuite.TLS_RSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_EMPTY_RENEGOTIATION_INFO_SCSV,
                    CipherSuite.TLS_FALLBACK_SCSV,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_NULL_SHA,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_NULL_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDH_RSA_WITH_NULL_SHA,
                    CipherSuite.TLS_ECDH_RSA_WITH_RC4_128_SHA,
                    CipherSuite.TLS_ECDH_anon_WITH_RC4_128_SHA,
                    CipherSuite.TLS_ECDH_anon_WITH_3DES_EDE_CBC_SHA,
                    CipherSuite.TLS_ECDH_anon_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDH_anon_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_CBC_SHA384,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_AES_256_CBC_SHA384,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA384,
                    CipherSuite.TLS_ECDH_RSA_WITH_AES_128_CBC_SHA256,
                    CipherSuite.TLS_ECDH_RSA_WITH_AES_256_CBC_SHA384,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_ECDH_ECDSA_WITH_AES_128_GCM_SHA256,
                    CipherSuite.TLS_ECDH_RSA_WITH_AES_256_GCM_SHA384,
                    CipherSuite.TLS_ECDHE_PSK_WITH_AES_128_CBC_SHA,
                    CipherSuite.TLS_ECDHE_PSK_WITH_AES_256_CBC_SHA,
                    CipherSuite.TLS_ECDHE_RSA_WITH_CHACHA20_POLY1305_SHA256,
                    CipherSuite.TLS_ECDHE_ECDSA_WITH_CHACHA20_POLY1305_SHA256,
                    CipherSuite.TLS_DHE_RSA_WITH_CHACHA20_POLY1305_SHA256,
                    CipherSuite.TLS_ECDHE_PSK_WITH_CHACHA20_POLY1305_SHA256,
                    CipherSuite.TLS_AES_128_GCM_SHA256,
                    CipherSuite.TLS_AES_256_GCM_SHA384,
                    CipherSuite.TLS_CHACHA20_POLY1305_SHA256,
                    CipherSuite.TLS_AES_128_CCM_SHA256
            )
            .build();

    public static OkHttpClient client;

    private static Retrofit getRetrofit() {
        if (googleRetrofit == null) {
            Interceptor headerInterceptor = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request build = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .header("Authorization", "0123456789#0#examId")
                            .build();
                    return chain.proceed(build);
                }
            };

            client = new OkHttpClient.Builder()
                    .connectionSpecs(Collections.singletonList(spec))
                    .connectTimeout(20000, TimeUnit.MILLISECONDS)
                    .readTimeout(20000, TimeUnit.MILLISECONDS)
                    .writeTimeout(20000, TimeUnit.SECONDS)
                    .addNetworkInterceptor(headerInterceptor)
                    .addInterceptor(mRewriteCacheControlInterceptor)
                    .retryOnConnectionFailure(true)
                    .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT))
                    .build();

            googleRetrofit = new Retrofit.Builder().client(client)
                    .baseUrl("https://bas.playsee.dev/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(new ObserveOnMainCallAdapterFactory(AndroidSchedulers.mainThread()))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .build();
        }
        return googleRetrofit;
    }

    public static void cancel() {
        client.dispatcher().cancelAll();
    }

    private static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            Response response = chain.proceed(request);
            if (request.toString().contains("Canceled"))
                return chain.proceed(request);

            ResponseBody responseBody = response.body();
            assert responseBody != null;
            MediaType mediaType = responseBody.contentType();
            String content = responseBody.string();

            Log.e("RequestData", request.toString() + "\n" + content);


            return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
        }
    };


}
