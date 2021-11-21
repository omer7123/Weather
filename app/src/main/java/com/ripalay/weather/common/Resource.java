package com.ripalay.weather.common;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Resource<T> {

    @NonNull
    public final Status status;
    public final T data;
    public final String msg;

    public Resource(@NonNull Status status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    public static <T> Resource<T> success(T data) {
        return new Resource<>(Status.SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(Status.ERROR, data, msg);
    }

    public static <T> Resource<T> loading(){
        return new Resource<>(Status.LOADING,null,null);
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}
