package com.example.rxjava;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeObserver;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;
import io.reactivex.rxjava3.observers.ResourceObserver;

import static java.lang.Thread.sleep;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    int start = 5, count = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        createObservableWithJust();
//        createObservableFromIterable();
//        createObservableUsingCreate();
//        introductionToObserver();
//        coldObservable();
//        hotObservableAndConnectibleObservable();
//        throwException();
//        throwExceptionUsingCallable();
//        createObservableUsingEmpty();
//        createObservableUsingNever();
//        observableRange();
//        observableDefer();
//        observableFromCallable();
//        observableInterVal();
//        createSingle();
//        createMaybe();
//        createCompletable();
//        handleDisposable();
//        handleDisposableInObserver();
//        handleDisposableOutsideObserver();
//        compositeDisposable();
//        mapOperator();
//        mapOperatorReturnsDifferentData();
//        filterOperator();
//        combineMapAndFilter();
//        takeOperator();
//        takeWhileInterval();
//        takeWhileOperator();
//        skipOperator();
//        skipWhileOperator();
//        distinctOperator();
//        distinctWithKeySelector();
//        distinctUtilChangeOperator();
//        distinctUtilChangeWithKeySelector();
//        userDefaultIfEmpty();
//        userSwitchIfEmpty();
//        useReport();
//        useScan();
//        useScanWithInitialValue();
//        useSorted();
//       useSortedWithOwnComparator();
//        useSortedOnNonComparator();
    }

    private void useSortedOnNonComparator() {
        Observable.just("foo", "john", "bar")
                .sorted((first, second) -> Integer.compare(first.length(), second.length()))
                .subscribe(integer -> Log.i(TAG, "useSorted: " + integer));
    }

    @SuppressLint("NewApi")
    private void useSortedWithOwnComparator() {
        Observable.just(3, 5, 2, 4, 1)
                .sorted(Comparator.reverseOrder())
                .subscribe(integer -> Log.i(TAG, "useSorted: " + integer));
    }

    private void useSorted() {
        Observable.just(3, 5, 2, 4, 1)
                .sorted()
                .subscribe(integer -> Log.i(TAG, "useSorted: " + integer));
    }

    private void useScanWithInitialValue() {
        Observable.just(1, 2, 3, 4, 5)
                .scan(10, (accumulator, next) -> accumulator + next)
                .subscribe(integer -> Log.i(TAG, "useScanWithInitialValue: " + integer));
    }

    private void useScan() {
        Observable.just(1, 2, 3, 4, 5)
                .scan((accumulator, next) -> accumulator + next)
                .subscribe(integer -> Log.i(TAG, "useScan: " + integer));
    }

    private void useReport() {
        Observable.just(1, 2, 3, 4, 5)
                .repeat(3)
                .subscribe(integer -> Log.i(TAG, "useReport: " + integer));
    }

    private void userSwitchIfEmpty() {
        Observable.just(1, 2, 3, 4, 5, 11)
                .filter(integer -> integer > 10)
                .switchIfEmpty(Observable.just(6, 7, 8, 9, 10))
                .subscribe(integer -> Log.i(TAG, "userDefaultIfEmpty: " + integer));
    }

    private void userDefaultIfEmpty() {
        Observable.just(1, 2, 3, 4, 5)
                .filter(integer -> integer > 10)
                .defaultIfEmpty(100)
                .subscribe(integer -> Log.i(TAG, "userDefaultIfEmpty: " + integer));
    }

    private void distinctUtilChangeWithKeySelector() {
        Observable.just("foo", "fool", "super", "foss", "foil")
                .distinctUntilChanged(String::length)
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void distinctUtilChangeOperator() {
        Observable.just(1, 1, 2, 2, 3, 3, 4, 5, 1, 1)
                .distinctUntilChanged()
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void distinctWithKeySelector() {
        Observable.just("foo", "fool", "super", "foss", "foil")
                .distinct(String::length)
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void distinctOperator() {
        Observable.just(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
                .distinct()
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void skipWhileOperator() {
        Observable.just(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
                .skipWhile(integer -> integer <= 3)
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void skipOperator() {
        Observable.just(1, 2, 3, 4, 5)
                .skip(2)
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void takeWhileOperator() {
        Observable.just(1, 2, 3, 4, 5, 1, 2, 3, 4, 5)
                .filter(integer -> integer <= 3)
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong));
    }

    private void takeWhileInterval() {
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(aLong -> Log.i(TAG, "takeWhileInterval: " + aLong),
                        throwable -> Log.i(TAG, "takeWhileInterval: " + throwable.getLocalizedMessage()));
        pause(5000);
    }

    private void takeOperator() {
        Observable.just(1, 2, 3, 4, 5)
                .take(2)
                .subscribe(integer -> Log.i(TAG, "takeOperator: " + integer));
    }

    private void mapOperator() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.map(integer -> integer * 2).subscribe(integer -> Log.i(TAG, "mapOperator: " + integer));
    }

    private void combineMapAndFilter() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.filter(integer -> integer % 2 == 0).map(integer -> integer * 2).subscribe(integer -> Log.i(TAG, "mapOperator: " + integer));
    }

    private void filterOperator() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.filter(integer -> integer % 2 == 0).subscribe(integer -> Log.i(TAG, "mapOperator: " + integer));
    }

    private void mapOperatorReturnsDifferentData() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.map(integer -> "Sajad!").subscribe(integer -> Log.i(TAG, "mapOperator: " + integer));
    }

    private void compositeDisposable() {
        CompositeDisposable compositeDisposable = new CompositeDisposable();
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable1 = observable.subscribe(aLong -> Log.i(TAG, "handleDisposable 1: " + aLong));
        Disposable disposable2 = observable.subscribe(aLong -> Log.i(TAG, "handleDisposable 2: " + aLong));
        compositeDisposable.addAll(disposable1, disposable2);

        pause(3000);
        compositeDisposable.delete(disposable1);
        compositeDisposable.dispose();
        pause(3000);
    }

    private void handleDisposableOutsideObserver() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        ResourceObserver<Integer> observer = new ResourceObserver<Integer>() {

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: " + "completed");
            }
        };

        Disposable disposable = observable.subscribeWith(observer);
    }

    private void handleDisposableInObserver() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        Observer<Integer> observer = new Observer<Integer>() {
            Disposable disposable;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(Integer integer) {
                if (integer == 3)
                    disposable.dispose();
                Log.i(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: " + "completed");
            }
        };

        observable.subscribe(observer);
    }

    private void handleDisposable() {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);

        Disposable disposable = observable.subscribe(aLong -> Log.i(TAG, "handleDisposable: " + aLong));

        pause(3000);
        disposable.dispose();
        pause(3000);

    }

    private void createCompletable() {
        Completable.fromSingle(Single.just("Hello Word")).subscribe(() -> Log.i(TAG, "done"));
    }

    private void createMaybe() {
        Maybe.empty().subscribe(new MaybeObserver<Object>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Object o) {
                Log.i(TAG, "onSuccess: " + o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: ");
            }
        });
    }

    private void createSingle() {
        Single.just("Hello Word").subscribe(s -> Log.i(TAG, "createSingle: " + s));
    }

    private void observableInterVal() {
        Observable observable = Observable.interval(1, TimeUnit.SECONDS);

        observable.subscribe(integer -> Log.i(TAG, "Observer 1: " + integer));


        pause(2000);

        observable.subscribe(integer -> Log.i(TAG, "Observer 2: " + integer));


        pause(3000);
    }

    private void observableFromCallable() {
//        Observable<Integer> observable = Observable.just(getNumber());
//        observable.subscribe(integer -> Log.i(TAG, "observableFromCallable: "+integer),
//                throwable -> Log.i(TAG, "observableFromCallable: "+throwable.getLocalizedMessage()));

        Observable<Integer> observable = Observable.fromCallable(() -> {
            Log.i(TAG, "Calling Method");
            return getNumber();
        });
        observable.subscribe(integer -> Log.i(TAG, "observableFromCallable: " + integer),
                throwable -> Log.i(TAG, "error: " + throwable.getLocalizedMessage()));
    }

    private void observableDefer() {
        Observable<Integer> observable = Observable.defer(() -> {
            Log.i(TAG, "New Observable is created with start = " + start + "and count = " + count);
            return Observable.range(start, count);
        });
        observable.subscribe(integer -> Log.i(TAG, "observableDefer 1: " + integer));
        count = 3;
        observable.subscribe(integer -> Log.i(TAG, "observableDefer 2: " + integer));
    }

    private void observableRange() {
//        Observable<Integer> observable = Observable.range(0, 10);
//        observable.subscribe(integer -> Log.i(TAG, "reactiveLoop: " + integer));

        int start = 5, count = 2;
        Observable<Integer> observable = Observable.range(start, count);
        observable.subscribe(integer -> Log.i(TAG, "observableRange: " + integer));
    }

    private void createObservableUsingNever() {
        Observable observable = Observable.never();
        observable.subscribe(o -> Log.i(TAG, "createObservableUsingNever: "),
                throwable -> Log.i(TAG, "error: " + throwable.hashCode()),
                () -> Log.i(TAG, "completed: "));
        pause(3000);
    }

    private void createObservableUsingEmpty() {
        Observable observable = Observable.empty();
        observable.subscribe(o -> Log.i(TAG, "createObservableUsingNever: "),
                throwable -> Log.i(TAG, "error: " + throwable.hashCode()),
                () -> Log.i(TAG, "completed: "));
    }

    private void throwExceptionUsingCallable() {
        Observable observable = Observable.error(() -> {
            Log.i(TAG, "New Exception Create");
            return new Exception("An Exception");
        });
        observable.subscribe(o -> Log.i(TAG, "throwException: " + o), throwable -> Log.i(TAG, "throwException 1: " + throwable.hashCode()));
        observable.subscribe(o -> Log.i(TAG, "throwException: " + o), throwable -> Log.i(TAG, "throwException 2: " + throwable.hashCode()));
    }

    private void throwException() {
        Observable observable = Observable.error(new Exception("An Exception"));
        observable.subscribe(o -> Log.i(TAG, "throwException: " + o), throwable -> Log.i(TAG, "throwException 1: " + throwable.hashCode()));
        observable.subscribe(o -> Log.i(TAG, "throwException: " + o), throwable -> Log.i(TAG, "throwException 2: " + throwable.hashCode()));
    }

    private void coldObservable() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.subscribe(item -> Log.i(TAG, "coldObservable1 : " + item));

        pause(3000);

        observable.subscribe(item -> Log.i(TAG, "coldObservable2 : " + item));
    }

    private static void pause(int duration) {
        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void hotObservableAndConnectibleObservable() {
        ConnectableObservable<Integer> observable = Observable.just(1, 2, 3, 4, 5).publish();
        observable.subscribe(item -> Log.i(TAG, "hotObservableAndConnectibleObservable 1: " + item));
        observable.connect();
        observable.subscribe(item -> Log.i(TAG, "hotObservableAndConnectibleObservable 2: " + item));
    }

    private void introductionToObserver() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        Observer<Integer> observer = new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Integer integer) {
                Log.i(TAG, "onNext: " + integer);
            }

            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: " + e.getLocalizedMessage());
            }

            @Override
            public void onComplete() {
                Log.i(TAG, "onComplete: " + "completed");
            }
        };

        observable.subscribe(observer);


    }

    private void createObservableUsingCreate() {
        Observable<Integer> observable = Observable.create(emitter -> {
            emitter.onNext(1);
            emitter.onNext(2);
            emitter.onNext(3);
            emitter.onNext(4);
            emitter.onNext(null);
            emitter.onComplete();
            emitter.onNext(5);
        });
        observable.subscribe(item -> Log.i(TAG, "createObservableWithJust: " + item),
                throwable -> Log.i(TAG, "errorMessage: " + throwable.getMessage()),
                () -> Log.i(TAG, "complete: " + "complete"));
    }

    private void createObservableFromIterable() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Observable<Integer> observable = Observable.fromIterable(list);
        observable.subscribe(item -> Log.i(TAG, "createObservableFromIterable: " + item));
    }

    private void createObservableWithJust() {
        Observable<Integer> observable = Observable.just(1, 2, 3, 4, 5);
        observable.subscribe(item -> Log.i(TAG, "createObservableUsingCreate: " + item));
    }

    private static int getNumber() {
        Log.i(TAG, "Generating Value");
        return 1 / 0;
    }

    private void toast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }
}
