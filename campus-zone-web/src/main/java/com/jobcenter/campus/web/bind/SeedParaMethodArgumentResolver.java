package com.jobcenter.campus.web.bind;

import com.google.common.collect.Maps;
import com.jobcenter.campus.domin.page.Seed;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Iterator;
import java.util.Map;

/**
 *
 * Created by xiayun on 28/3/17.
 */
public class SeedParaMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        if (parameter.getGenericParameterType().equals(Seed.class)){
            return true;
        }
        return false;
    }

    /**
     * The arguments could be "page", "pagesize", "order" or other query data.
     */
    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        Seed seed = new Seed();
        Map<String,String> map = Maps.newHashMap();

        for(Iterator<String> paramNameItr = webRequest.getParameterNames(); paramNameItr.hasNext();){
            String paramName = paramNameItr.next();
            if(paramName.equals(Seed.PAGE_NUMBER)){
                seed.setPageNum(Integer.parseInt(webRequest.getParameter(paramName)));
            }else if(paramName.equals(Seed.PAGE_SIZE)){
                seed.setPageSize(Integer.parseInt(webRequest.getParameter(paramName)));
            }else if(paramName.equals(Seed.ORDER)){
                seed.setOrderBy(webRequest.getParameter(paramName));
            }else{
                map.put(paramName,webRequest.getParameter(paramName));
            }
        }
        seed.setQueryData(map);
        return seed;
    }
}
