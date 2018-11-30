package br.com.candidatodebolso.app.webservice;

import java.util.List;

import br.com.candidatodebolso.app.model.Candidate;
import br.com.candidatodebolso.app.model.Question;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface QuestionWebService {

    @GET("question")
    Call<List<Question>> listAll();

    @POST("question/send")
    Call<Candidate> send(@Body List<Question> questions);
}
