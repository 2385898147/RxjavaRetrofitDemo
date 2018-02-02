package tznrxjavaretrofitdemo.subscribers;

/**
 * Created by n-373 on 2018/1/24.
 */

public interface SubscriberOnNextListener<T> {
    void onNext(T t);
}
