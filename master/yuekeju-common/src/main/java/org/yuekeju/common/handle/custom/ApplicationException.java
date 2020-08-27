package org.yuekeju.common.handle.custom;


public class ApplicationException extends RuntimeException {
    private static final long serialVersionUID = -8758217088127954689L;

    private int code;
    private String msg;

    public ApplicationException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public ApplicationException(String msg) {
        code = 200;
        this.msg = msg;

    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
