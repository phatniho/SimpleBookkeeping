package com.ph.simplebookkeeping.util;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理
 *
 * @author 夜雨妖惜
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * 在controller里面内容执行之前，校验一些参数不匹配啊，Get post方法不对啊之类的
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(Json.knownFail("请求参数好像有问题哦", ""), HttpStatus.OK);
    }

    /**
     * 在controller进入之后,到实际逻辑执行了，然后发生了异常
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Json jsonHandler(HttpServletRequest request, Exception ex) throws Exception {
        if (ex.getClass().isAssignableFrom(SysException.class)) {
            return Json.unKnownFail(ex.getMessage(), "请求发生了一个小小的错误");
        }
        return Json.knownFail("访问接口不行了，请联系接口开发商吧", "在处理过程中发生了一个未知错误：" + ex.getMessage());
    }
}
