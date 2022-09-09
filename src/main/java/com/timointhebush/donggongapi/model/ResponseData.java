package com.timointhebush.donggongapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.timointhebush.donggongapi.model.enums.ResultCode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
@EqualsAndHashCode
public class ResponseData<T> {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final ResultCode resultCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final String message;
    private final Date timestamp;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final T data;

    public ResponseData() {
        this(null, null, null);
    }

    public ResponseData(T data) {
        this(null, null, data);
    }

    public ResponseData(ResultCode resultCode) {
        this(resultCode, null, null);
    }

    public ResponseData(ResultCode resultCode, T data) {
        this(resultCode, null, data);
    }

    public ResponseData(ResultCode resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.timestamp = new Date(System.currentTimeMillis());
        this.data = data;
    }
}
