package egovframework.example.cmmn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.ModelAndView;

/**
 * 전역 예외 처리 핸들러
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 404 Not Found 예외 처리
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handle404(NoHandlerFoundException ex, Model model) {
        LOGGER.error("404 오류 발생: {}", ex.getMessage());

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "요청하신 페이지를 찾을 수 없습니다.");
        mav.setViewName("common/error_404"); // 404 에러 페이지
        return mav;
    }

    /**
     * 500 Internal Server Error 처리
     */
    @ExceptionHandler(Exception.class)
    public ModelAndView handle500(Exception ex, Model model) {
        LOGGER.error("500 서버 오류 발생: {}", ex.getMessage(), ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "서버 내부 오류가 발생했습니다.");
        mav.setViewName("common/error_500"); // 500 에러 페이지
        return mav;
    }

    /**
     * 공통 예외 처리 (기타 예외)
     */
    @ExceptionHandler(RuntimeException.class)
    public ModelAndView handleCommonException(RuntimeException ex, Model model) {
        LOGGER.error("예외 발생: {}", ex.getMessage(), ex);

        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMessage", "일시적인 오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        mav.setViewName("common/common_error"); // 기타 공통 에러 페이지
        return mav;
    }
}