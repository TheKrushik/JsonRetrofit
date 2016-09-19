package info.krushik.android.jsonretrofit.retrofit;

import java.util.ArrayList;

import retrofit2.http.GET;
import info.krushik.android.jsonretrofit.model.ModelVideo;
import rx.Observable;

public interface GetModels {
    @GET("get/855616ae65fe020259dbf39065aa6f72/272293084/Video.txt")
//    @GET("TheKrushik/JsonRetrofit/master/Video.txt")
    Observable<ArrayList<ModelVideo>> getModelsList();
}