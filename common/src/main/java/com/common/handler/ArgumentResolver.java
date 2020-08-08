package com.common.handler;

import com.common.vo.ConditionVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ArgumentResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(ConditionVO.class);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        ConditionVO conditionVO = new ConditionVO();
        String search = request.getParameter("search");
        String extSearch = request.getParameter("extSearch");
        String ascs = request.getParameter("ascs");
        String descs = request.getParameter("descs");
        String tid = request.getParameter("tid");
        String isadmin = request.getParameter("isadmin");
        if (StringUtils.isNotEmpty(search)) {
            conditionVO.setSearch(search);
        }
        if (StringUtils.isNotEmpty(extSearch)) {
            Map map = objectMapper.readValue(extSearch, Map.class);
            conditionVO.setExtSearch(map);
        }
        if (StringUtils.isNotEmpty(ascs)) {
            conditionVO.setAscs(StringUtils.split(ascs));
        }
        if (StringUtils.isNotEmpty(descs)) {
            conditionVO.setDescs(StringUtils.split(descs));
        }
        if (StringUtils.isNotEmpty(tid)) {
            conditionVO.setTid(tid);
        }
        if (StringUtils.isNotEmpty(isadmin)) {
            conditionVO.setIsadmin(isadmin);
        }
        return conditionVO;
    }

}
