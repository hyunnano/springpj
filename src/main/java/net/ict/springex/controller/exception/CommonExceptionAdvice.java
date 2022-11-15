package net.ict.springex.controller.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.Arrays;

@ControllerAdvice // 공통 기능, 우선순위가 가장 높은 Advice
@Log4j2
public class CommonExceptionAdvice { // exception만 처리하는 클래스

    @ResponseBody // view 페이지없이 리턴값을 그대로 출력하겠다 라는 의미
    @ExceptionHandler(NumberFormatException.class)
    public String exceptNumber(NumberFormatException numberFormatException){
        log.error("eeeeeeee~~~~~~~");
        log.error(numberFormatException.getMessage());
        return "NUMBER FORMAT EXCEPTION";
    }


    @ResponseBody
    @ExceptionHandler(Exception.class) // 범용적인 예외처리, 예외처리의 최상위 타입인 Exception타입을 처리하도록 구성
    public String exceptionCommon(Exception exception){
        log.error("----------error---------");
        log.error(exception.getMessage());

        StringBuffer buffer = new StringBuffer("<ul>");
        buffer.append("<li>"+exception.getMessage()+"</li>");
        Arrays.stream(exception.getStackTrace()).forEach(stackTraceElement -> {
            buffer.append("<li>"+stackTraceElement+"</li>");
        });
        buffer.append("</ul>");
        return buffer.toString();
    }

    // 404에러 처리
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 응답 상태 코드 값 지정
    public String notFound(){ // 페이지 요청시 해당 페이지가 없을때
        return "custom404";
    }




}
