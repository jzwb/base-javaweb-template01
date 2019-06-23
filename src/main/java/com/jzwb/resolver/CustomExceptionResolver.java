package com.jzwb.resolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 自定义异常处理器
 */
public class CustomExceptionResolver extends SimpleMappingExceptionResolver {

    private final static String ERROR_MSG = "errormsg";

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionResolver.class);

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof CustomException) {
            CustomException customException = (CustomException) ex;
            if (!(handler instanceof HandlerMethod)) {
                return super.doResolveException(request, response, handler, ex);
            }
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ResponseBody body = handlerMethod.getMethodAnnotation(ResponseBody.class);//获取返回类型
            if (body != null) {//返回json
                LOGGER.error("全局异常处理器, error catch:", ex);
                ModelAndView mv = new ModelAndView();
                /*FastJsonJsonView view = new FastJsonJsonView();
                Message message = Message.error(customException.getMessage());
                Map<String, Object> attributes = new HashMap<>();
                attributes.put("type", message.getType());
                attributes.put("content", message.getContent());
                view.setAttributesMap(attributes);
                mv.setView(view);*/
                return mv;
            }
            //普通请求
            ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
            modelAndView.addObject(ERROR_MSG, customException.getMessage());
            return modelAndView;
        }
        return super.doResolveException(request, response, handler, ex);
    }
}