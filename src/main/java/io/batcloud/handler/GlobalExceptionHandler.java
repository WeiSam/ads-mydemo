package io.batcloud.handler;


import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zz.common.log.LogService;

import io.batcloud.dto.common.BaseResponse;
import io.batcloud.exception.BaseException;

/**
 * 全局异常处理器
 * 避免直接将错误堆栈信息抛给前端
 * @author gaolinlou
 * Date: Created in 9:58 2018/8/9
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
    
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.OK)
    public BaseResponse<?> handle(BaseException ex) {

        LogService.error(ex.getMessage(),ex);
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage(ex.getMessage(), null,ex.getMessage(),locale);
        return new BaseResponse<Object>(ex.getCode(),msg,null);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public BaseResponse<?> handle(Exception ex) {
    	LogService.error(ex.getMessage(),ex);
        Locale locale = LocaleContextHolder.getLocale();
        String msg = messageSource.getMessage("server.internal.error", null,"Server internal error!",locale);
        return new BaseResponse<Object>(500, msg,null);
    }
}