package cn.yan_wm.myalbum.commons.dto;

import java.io.Serializable;

/**
 * @program: MyAlbum-Boot
 * @description: 返回结果模型
 * @author: yan_zt
 * @create: 2020-01-14 11:18
 */
public class ReturnResult<T> implements Serializable {
    private static final long serialVersionUID = 8968766969795754248L;

    private String message;
    private int statusCode;
    private boolean success;
    private T object;

    public ReturnResult() {
    }

    public static <T> ReturnResult<T> success() {
        ReturnResult<T> result = new ReturnResult();
        result.setSuccess(true);
        result.setStatusCode(200);
        return result;
    }

    public static <T> ReturnResult<T> success(T data) {
        ReturnResult<T> result = new ReturnResult();
        result.setSuccess(true);
        result.setStatusCode(200);
        result.setObject(data);
        return result;
    }

    public static <T> ReturnResult<T> failure() {
        ReturnResult<T> result = new ReturnResult();
        result.setSuccess(false);
        return result;
    }

    public static <T> ReturnResult<T> failure(String message) {
        ReturnResult<T> result = new ReturnResult();
        result.setSuccess(false);
        result.setMessage(message);
        return result;
    }

    public static <T> ReturnResult<T> failure(String message, T data) {
        ReturnResult<T> result = new ReturnResult();
        result.setSuccess(false);
        result.setMessage(message);
        result.setObject(data);
        return result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public String getMessage() {
        return this.message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public T getObject() {
        return this.object;
    }

    public String toString() {
        return "ReturnResult(message=" + this.getMessage() + ", statusCode=" + this.getStatusCode() + ", success=" + this.isSuccess() + ", object=" + this.getObject() + ")";
    }
}
