package com.customview.wl.rx;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tx = ((TextView) findViewById(R.id.tx));

       tx.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tx:
                //rx1();
                //rx2(v);
                //rx3();
                //rx4();
               //map();

                /**
                 创建操作
                 用于创建Observable的操作符
                 Create — 通过调用观察者的方法从头创建一个Observable
                 Defer — 在观察者订阅之前不创建这个Observable，为每一个观察者创建一个新的Observable
                 Empty/Never/Throw — 创建行为受限的特殊Observable
                 From — 将其它的对象或数据结构转换为Observable
                 Interval — 创建一个定时发射整数序列的Observable
                 Just — 将对象或者对象集合转换为一个会发射这些对象的Observable
                 Range — 创建发射指定范围的整数序列的Observable
                 Repeat — 创建重复发射特定的数据或数据序列的Observable
                 Start — 创建发射一个函数的返回值的Observable
                 Timer — 创建在一个指定的延迟之后发射单个数据的Observable
                 */

                Observable.interval(2,2, TimeUnit.SECONDS)
                        .subscribe(new Subscriber<Long>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(final Long aLong) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        tx.setText(aLong+"");
                                    }
                                });
                            }
                        });
                break;
        }
    }

    private void map() {
        //map  变换  将just数据遍历
        Observable.just(1, 2, 3, 4, 5)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer i) {
                        return "This is " + i;
                    }
                }).subscribe(new Action1<String>() {
            @Override
            public void call(final String s) {
                System.out.println(s);
               runOnUiThread(new Runnable() {
                   @Override
                   public void run() {
                       tx.setText(s);
                   }
               });
            }
        });
    }

    private void rx4() {
        //延迟2s 执行
        Observable.timer(2, TimeUnit.SECONDS) .map(new Func1<Long, Long>() {
            @Override
            public Long call(Long aLong) {
                Log.e("rx", "call: "+ aLong );
                return   aLong++;
            }
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(
                new Action1<Long>() {
                    @Override
                    public void call(Long aLong) {
                        tx.setText("rx"+ aLong);
                    }
                }
        );
    }

    private void rx3() {
        Observable.timer(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Long>() {
            @Override
            public void call(final Long aLong) {

                        tx.setText(aLong+"");
            }
        });
    }

    private void rx2(View v) {
        Observable.just(v).subscribe(new Action1<View>() {
            @Override
            public void call(View view) {
                ((TextView) view).setText("rx View");
            }
        });
    }

    private void rx1() {
        Observable.just("rx")
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
                    }
                });
    }
}
