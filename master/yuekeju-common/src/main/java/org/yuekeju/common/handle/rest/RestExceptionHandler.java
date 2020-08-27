package org.yuekeju.common.handle.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.yuekeju.common.constants.CommonConstants;
import org.yuekeju.common.handle.custom.ApplicationException;
import org.yuekeju.common.vo.ResultEnum;
import org.yuekeju.common.vo.ResultVO;

import java.io.IOException;

/**
 * 异常处理类
 *
 * @author szk
 * @date 2020/8/27
 */
@SuppressWarnings("all")
@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {
    /**
     * 自定义异常
     */
    @ExceptionHandler(ApplicationException.class)
    @ResponseBody
    public ResultVO handleServiceException(ApplicationException e) {
        printLog(e);
        return new ResultVO(e.getCode(), CommonConstants.FALSE, e.getMsg(), null);
    }

    /**
     * 参数校验
     */
    // TODO 怎么只返回注解上的value
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResultVO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseBody
    public ResultVO handleDuplicateKeyException(DuplicateKeyException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    // shiro 权限
    //     @ExceptionHandler(AuthorizationException.class)
    //     @ResponseBody
    //     public ReturnResult handleAuthorizationException(AuthorizationException e){
    //     logger.error(e.getMessage(), e);
    //     return ReturnResult.createSuccessReturn();
    //
    //     }

    /**
     * 运行时异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public ResultVO runtimeExceptionHandler(RuntimeException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 空指针异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public ResultVO nullPointerExceptionHandler(NullPointerException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 类型转换异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ClassCastException.class)
    @ResponseBody
    public ResultVO classCastExceptionHandler(ClassCastException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * IO异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IOException.class)
    @ResponseBody
    public ResultVO iOExceptionHandler(IOException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 未知方法异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoSuchMethodException.class)
    @ResponseBody
    public ResultVO noSuchMethodExceptionHandler(NoSuchMethodException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 数组越界异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseBody
    public ResultVO indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 400错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public ResultVO requestNotReadable(HttpMessageNotReadableException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 400错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public ResultVO requestTypeMismatch(TypeMismatchException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 400错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public ResultVO requestMissingServletRequest(MissingServletRequestParameterException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 405错误
     *
     * @return
     */
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public ResultVO request405() {
        return new ResultVO(ResultEnum.STATUS405.getCode(), CommonConstants.FALSE, ResultEnum.STATUS405.getMessage(), null);
    }

    /**
     * 406错误
     *
     * @return
     */
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public ResultVO request406() {
        return new ResultVO(ResultEnum.STATUS406.getCode(), CommonConstants.FALSE, ResultEnum.STATUS406.getMessage(), null);
    }

    /**
     * 500错误
     *
     * @param e
     * @return
     */
    @ExceptionHandler({ConversionNotSupportedException.class, HttpMessageNotWritableException.class})
    @ResponseBody
    public ResultVO server500(RuntimeException e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResultVO handleException(Exception e) {
        printLog(e);
        return new ResultVO(CommonConstants.ERROR_CODE, CommonConstants.FALSE, e.getMessage(), null);
    }

    /**
     * 异常信息打印日志
     *
     * @param e
     */
    private void printLog(Exception e) {
        log.error("error >>> ", e);
    }
}
