package com.leyifu.weitoutiao;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

/**
 * Created by hahaha on 2017/12/21 0021.
 */

public class RxBus {

    private ConcurrentHashMap<Object, List<Subject>> map = new ConcurrentHashMap<>();

    private RxBus() {

    }

    public static RxBus getInstance() {
        return Holder.instance;
    }

    public <T> Observable<T> register(Class<T> tClass) {
        return register(tClass.getName());
    }

    public <T> Observable<T> register(Object tag) {

        List<Subject> subjectList = map.get(tag);

        if (subjectList == null) {

            subjectList = new ArrayList<>();
            map.put(tag, subjectList);
        }

        Subject<T> subject = PublishSubject.create();

        subjectList.add(subject);

        return subject;
    }

    public void post(Object tag, Object content) {
        List<Subject> subjectList = map.get(tag);

        if (!subjectList.isEmpty()) {

            for (Subject subject : subjectList) {

                subject.onNext(content);
            }
        }
    }

    public void unRegister(Object tag, Observable observable) {

        List<Subject> subjectList = map.get(tag);

        if (subjectList != null) {
            subjectList.remove(observable);
            if (subjectList.isEmpty()) {
                map.remove(tag);
            }
        }
    }


    private static class Holder {
        public static RxBus instance = new RxBus();
    }

}
