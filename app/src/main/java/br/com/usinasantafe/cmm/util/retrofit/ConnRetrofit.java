package br.com.usinasantafe.cmm.util.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import br.com.usinasantafe.cmm.util.conHttp.UrlsConexaoHttp;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnRetrofit {

    private static ConnRetrofit instance = null;

    public static ConnRetrofit getInstance() {
        if (instance == null) {
            instance = new ConnRetrofit();
        }
        return instance;
    }

    private Gson getGson(){
        return new GsonBuilder().setLenient().create();
    }

    public Retrofit conn(){
        return new Retrofit.Builder()
                .baseUrl(UrlsConexaoHttp.url)
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .client(getUnsafeOkHttpClient())
                .build();
    }

    public static OkHttpClient getUnsafeOkHttpClient() {
        try {

            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            // Criar um TrustManager que aceita qualquer certificado
            TrustManager[] trustAllCertificates = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

                        @Override
                        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

                        @Override
                        public X509Certificate[] getAcceptedIssuers() {
                            return new X509Certificate[]{};
                        }
                    }
            };

            // Criar um contexto SSL que ignora certificações inválidas
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new SecureRandom());

            // Criar um SSLSocketFactory personalizado
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return new OkHttpClient.Builder()
                    .sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCertificates[0])
                    .hostnameVerifier((hostname, session) -> true) // Ignorar verificação de hostname
                    .connectTimeout(1, TimeUnit.MINUTES)
                    .writeTimeout(1, TimeUnit.MINUTES)
                    .readTimeout(1, TimeUnit.MINUTES)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

        } catch (Exception e) {
            throw new RuntimeException("Erro ao configurar SSL inseguro", e);
        }
    }

}
