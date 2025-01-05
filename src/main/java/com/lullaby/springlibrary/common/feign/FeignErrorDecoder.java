package com.lullaby.springlibrary.common.feign;

import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        return new FeignClientException(response);
    }

}
