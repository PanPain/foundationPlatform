package edu.xd.ridelab.receiver.util;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author PanTeng
 * @Description
 * @file Tuple2.java
 * @date 2018/5/11
 * @attention Copyright (C),2004-2018,RIDELab, SEI, XiDian University
 */
public class Tuple2<A, B> {

    public final A _1;
    public final B _2;

    public Tuple2(A _1, B _2) {
        this._1 = _1;
        this._2 = _2;
    }

    public static <A, B> Tuple2<A, B> of(A _1, B _2){
        return new Tuple2<>(_1, _2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tuple2<?, ?> tuple2 = (Tuple2<?, ?>) o;
        return Objects.equals(_1, tuple2._1) &&
            Objects.equals(_2, tuple2._2);
    }

    @Override
    public int hashCode(){
        return Arrays.hashCode(new int[]{
            _1.hashCode(),
            _2.hashCode()
        });
    }
    @Override
    public String toString() {
        return "Tuple2{" +
            "_1=" + _1 +
            ", _2=" + _2 +
            '}';
    }
}
