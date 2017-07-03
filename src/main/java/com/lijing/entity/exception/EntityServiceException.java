package com.lijing.entity.exception;

import lombok.Getter;
import lombok.Setter;


/**
 *
 */
@Getter
@Setter
public class EntityServiceException extends RuntimeException {

    private static final long serialVersionUID = -1952521610582354826L;

    private String code;

    public EntityServiceException(String code) {
        this.code = code;
    }

    public EntityServiceException(String code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public EntityServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public EntityServiceException(String code, String message, Throwable throwable) {
        super(message, throwable);
        this.code = code;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + code + '\'' +
                "errMsg='" + getMessage() + '\'' +
                '}';
    }
}
