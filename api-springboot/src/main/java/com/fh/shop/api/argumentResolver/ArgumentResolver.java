package com.fh.shop.api.argumentResolver;

import com.fh.shop.api.member.vo.MemberVo;
import com.fh.shop.api.util.SystemConst;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

//这个类 技术 是spring支持的   需要在进行springMVC 配置注解驱动才会生效
public class ArgumentResolver implements HandlerMethodArgumentResolver {

    //此方法 会根据 return 的值 进行判断是否执行第二个方法
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //获取控制层参数类型
        Class<?> parameterType = parameter.getParameterType();
        //判断要访问的控制层 方法上参数是否和我们定义的参数类型相符
        return parameterType == MemberVo.class;
    }

    //只有第一个方法返回true 才会进入这个方法
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //获取  请求
        HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        //从请求中获取数据   并强转数据类型
        MemberVo memberVo = (MemberVo) request.getAttribute(SystemConst.CURRENT_MEMBERNAME);
        //将数据 传递到控制层
        return memberVo;
    }
}
