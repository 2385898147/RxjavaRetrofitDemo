package tznrxjavaretrofitdemo.http;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import tznrxjavaretrofitdemo.entity.MovieEntity;
import tznrxjavaretrofitdemo.entity.Subject;

/**
 * Created by n-373 on 2018/1/24.
 */

public interface MovieService {
    //    @GET("top250")
    //    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);

  /*  @GET("top250")
    Observable<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);*/

  @GET("top250")
    Observable<HttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);
}
