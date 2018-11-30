package br.com.candidatodebolso.app.webservice;

import java.util.List;

import br.com.candidatodebolso.app.model.Candidate;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CandidateWebService {

    @GET("candidate")
    Call<List<Candidate>> listAll();

    @GET("candidate/{id}")
    Call<Candidate> getById(@Path("id") Long id);
}
