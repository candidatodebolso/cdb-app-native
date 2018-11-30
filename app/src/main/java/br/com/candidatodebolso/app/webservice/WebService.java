package br.com.candidatodebolso.app.webservice;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WebService {

    private final static Retrofit retrofit;

    static {
        //"http://web-service-candidato-de-bolso-web-service.193b.starter-ca-central-1.openshiftapps.com/voter/"
        retrofit = new Retrofit.Builder()
                .baseUrl("http://web-service-candidato-de-bolso-web-service.193b.starter-ca-central-1.openshiftapps.com/voter/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static Retrofit getInstance() {
        return retrofit;
    }
}
