package info.krushik.android.jsonretrofit.retrofit;

import java.util.ArrayList;

import retrofit2.http.GET;
import info.krushik.android.jsonretrofit.model.Model;
import rx.Observable;

public interface GetModels {
    @GET("rx-retrofit-and-android-screen-orientation.php")
    Observable<ArrayList<Model>> getModelsList();
}