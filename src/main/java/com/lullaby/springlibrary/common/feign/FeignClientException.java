package com.lullaby.springlibrary.common.feign;

import feign.Response;

public class FeignClientException extends RuntimeException {

    private final int status;
    private final Response.Body body;

    public FeignClientException(Response response) {
        super("FeignClientException");
        this.status = response.status();
        this.body = response.body();
    }

}
