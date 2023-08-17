package com.chuang.bootplus.base.exception;

import com.chuang.bootplus.base.enumerate.HttpStatusCodeService;
import com.chuang.bootplus.base.utils.ErrorCode;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;

/**
 * @author lcc
 * @create 2021-07-04
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */
public class BusException extends RuntimeException {
    private final String code;
    private final Object data;

    public BusException(String code, String message) {
        super(message);
        this.code = code;
        this.data = null;
    }


    public BusException(HttpStatusCodeService codeService) {
        super(codeService.getReMsg());
        this.code = codeService.getReCode();
        this.data = null;
    }

    public BusException(String message) {
        super(message);
        this.code = HttpStatusEnum.RE_CODE_DATABASE_ERROR.getCode();
        this.data = null;
    }

    public BusException(HttpStatusCodeService codeService, String message) {
        super(message);
        this.code = codeService.getReCode();
        this.data = null;
    }

    public BusException(String code, String message, Object data, Exception ex) {
        super(message, ex);
        this.code = code;
        this.data = data;
    }

    public BusException(String code, String message, Exception ex) {
        super(message, ex);
        this.code = code;
        this.data = null;
    }

    public Throwable getInnerException() {
        return super.getCause();
    }

    public Object getData() {
        return data;
    }

    public String getCode() {
        return code;
    }

    public static final BusException TokenExapiredException = new BusException(ErrorCode.RE_CODE_LOGIN_EXPIRED,ErrorCode.RE_MSG_LOGIN_EXPIRED);
}