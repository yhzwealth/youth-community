package com.chuang.bootplus.config.exception;

import cn.hutool.core.util.StrUtil;
import com.chuang.bootplus.base.utils.ApiResponse;
import com.chuang.bootplus.base.constant.Constant;
import com.chuang.bootplus.base.exception.BusException;
import com.chuang.bootplus.base.enumerate.HttpStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

/**
 * @author lcc
 * @create 2021-05-28
 * @注意 本内容仅限于dev414内部传阅，禁止外泄以及用于其他的商业目的
 */

@RestControllerAdvice
@Slf4j
public class ExceptionCatch {

    public ExceptionCatch() {}

    /**
     * 统一处理 <code>BusException</code>
     *
     * @param busException BusException
     * @return ApiResponse 统一返回体
     * @see ApiResponse
     * @see BusException
     */
    @ResponseBody
    @ExceptionHandler({BusException.class})
    public ApiResponse customException(BusException busException) {
        log.error("catch exception : {}\r\nexception: ", busException.getMessage(), busException);
        return new ApiResponse(busException.getCode(), busException.getMessage());
    }


    /**
     * 统一处理
     * <code>MethodArgumentNotValidException</code><br>
     * 处理参数校验返回的异常
     *
     * @param e MethodArgumentNotValidException
     * @return ApiResponse 统一返回体
     * @see ApiResponse
     * @see MethodArgumentNotValidException
     */
    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ApiResponse methodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldError();
        return fieldError == null ? new ApiResponse(HttpStatusEnum.RE_CODE_DATABASE_ERROR) : new ApiResponse(HttpStatusEnum.RE_CODE_DATABASE_ERROR, fieldError.getDefaultMessage());
    }

    /**
     * 文件大小限制 统一处理
     * <code>MaxUploadSizeExceededException</code><br>
     * 处理参数校验返回的异常
     *
     * @param e MaxUploadSizeExceededException
     * @return ApiResponse 统一返回体
     * @see ApiResponse
     * @see MaxUploadSizeExceededException
     */
    @ResponseBody
    @ExceptionHandler({MaxUploadSizeExceededException.class})
    public ApiResponse maxUploadSizeExceededExceptionException(MaxUploadSizeExceededException e) {
        try {
            return new ApiResponse(
                    HttpStatusEnum.RE_CODE_FILE_TO_BIG,
                    StrUtil.format(Constant.FILE_TO_BIG, (int) e.getMaxUploadSize()/1048576)
            );
        }catch (Exception ignore) {
            return new ApiResponse(HttpStatusEnum.RE_CODE_FILE_TO_BIG);
        }
    }

    /**
     * 统一处理 <code>Exception</code>
     *
     * @param exception Exception
     * @return ApiResponse 统一返回体
     * @see ApiResponse
     * @see BusException
     */
    @ResponseBody
    @ExceptionHandler({Exception.class})
    public ApiResponse errorHandler(Exception exception) {
        log.error("接口出现严重异常：{}", exception.getMessage(), exception);
        return new ApiResponse(HttpStatusEnum.RE_CODE_DATABASE_ERROR);
    }

}
