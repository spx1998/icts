package com.icts.model;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response<T> implements Serializable {
    private static final long serialVersionUID = -9071260129925792016L;
    private boolean success;
    private String code;
    private T data;

    public static <T> Response<T> failed(String code) {
        return new Response<>(false, code, null);
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(true, null, data);
    }
}
