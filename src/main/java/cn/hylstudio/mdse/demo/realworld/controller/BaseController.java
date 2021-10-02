package cn.hylstudio.mdse.demo.realworld.controller;

import cn.hylstudio.mdse.demo.realworld.exception.AuthException;
import cn.hylstudio.mdse.demo.realworld.model.response.ResponsePayload;
import cn.hylstudio.mdse.demo.realworld.model.response.common.ErrorResultPayload;
import io.netty.util.internal.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@Controller
@ControllerAdvice
public class BaseController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = AuthException.class)
    @ResponseBody
    public ResponsePayload handleAuthException(AuthException e) {
        String errMsg = e.getMessage();
        return ErrorResultPayload.build(Collections.singletonList(errMsg));
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponsePayload handleException(Exception e) {
        LOGGER.info("handle unknown exception, e = [{}]", e.getMessage(), e);
        String errMsg = e.getMessage();
        if (StringUtil.isNullOrEmpty(errMsg)) {
            errMsg = "system error";
        }
        return ErrorResultPayload.build(Collections.singletonList(errMsg));
    }
}
