package info.krushik.android.jsonretrofit.retrofit;

import java.util.ArrayList;

import retrofit2.http.GET;
import info.krushik.android.jsonretrofit.model.ModelVideo;
import rx.Observable;

public interface GetModels {
    @GET("TheKrushik/JsonRetrofit/master/Video.txt")
    Observable<ArrayList<ModelVideo>> getModelsList();
}