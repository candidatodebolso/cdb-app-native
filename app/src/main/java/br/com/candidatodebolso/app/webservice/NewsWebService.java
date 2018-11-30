package br.com.candidatodebolso.app.webservice;

import java.util.List;

import br.com.candidatodebolso.app.model.News;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NewsWebService {

    @GET("lasted-news")
    Call<List<News>> listAll();

    @GET("lasted-news/{id}")
    Call<News> getById(@Path("id") Long id);
}
