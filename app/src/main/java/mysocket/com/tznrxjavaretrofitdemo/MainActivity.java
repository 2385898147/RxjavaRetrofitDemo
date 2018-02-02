package mysocket.com.tznrxjavaretrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tznrxjavaretrofitdemo.entity.MovieEntity;
import tznrxjavaretrofitdemo.entity.Subject;
import tznrxjavaretrofitdemo.http.HttpMethods;
import tznrxjavaretrofitdemo.http.HttpResult;
import tznrxjavaretrofitdemo.http.MovieService;
import tznrxjavaretrofitdemo.subscribers.ProgressSubscriber;
import tznrxjavaretrofitdemo.subscribers.SubscriberOnNextListener;

public class MainActivity extends AppCompatActivity {

    private Button clickMeBN;
    private TextView resultTV;
    private Button cnClickBn;

    //private Subscriber<MovieEntity> subscriber;
    // private Subscriber<HttpResult<List<Subject>>> subscriber;
    private Subscriber<List<Subject>> subscriber;

    private SubscriberOnNextListener subscriberOnNextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickMeBN = findViewById(R.id.click_me_BN);
        resultTV = findViewById(R.id.result_TV);
        cnClickBn = findViewById(R.id.cn_me_BN);
        cnClickBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultTV.setText("");
            }
        });
        clickMeBN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMovie();
            }
        });

        subscriberOnNextListener = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };
    }


    //进行网络请求
    private void getMovie() {
      /*  String baseUrl = "https://api.douban.com/v2/movie/";

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        MovieService movieService = retrofit.create(MovieService.class);*/
      /*  Observable<MovieEntity> call = movieService.getTopMovie(0,10);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                resultTV.setText(response.body().toString());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                resultTV.setText(t.getMessage());
            }
        });*/
        /*movieService.getTopMovie(0,10)
                .subscribeOn(Schedulers.io())// 指定 subscribe() 发生在 IO 线程
                .observeOn(AndroidSchedulers.mainThread()) // 指定 Subscriber 的回调发生在主线程
                .subscribe(new Subscriber<MovieEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        resultTV.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MovieEntity movieEntity) {
                        resultTV.setText(movieEntity.toString());
                    }
                });*/
        /*subscriber = new Subscriber<MovieEntity>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(MovieEntity movieEntity) {
                resultTV.setText(movieEntity.toString());
            }
        };*/

       /* subscriber = new Subscriber<HttpResult<List<Subject>>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(HttpResult<List<Subject>> listHttpResult) {
                resultTV.setText(listHttpResult.toString());
            }
        };*/

        /*subscriber = new Subscriber<List<Subject>>() {
            @Override
            public void onCompleted() {
                Toast.makeText(MainActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Throwable e) {
                resultTV.setText(e.getMessage());
            }

            @Override
            public void onNext(List<Subject> subjects) {
                resultTV.setText(subjects.toString());
            }
        };*/


        // HttpMethods.getInstance().getTopMovie(subscriber, 0, 10);
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber<List<Subject>>(subscriberOnNextListener, this), 0, 10);
    }
}
