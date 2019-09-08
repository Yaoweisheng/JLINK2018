package com.jlink.interceptor;

import com.jlink.dto.ObjectResult;
import com.jlink.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.jlink.entity.User;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();

        PrintWriter writer = null;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("text/json; charset=utf-8");
        if (token == null) {
            try {
                writer = httpServletResponse.getWriter();
                writer.print(JSONObject.fromObject(ObjectResult.noaccess("请重新登录")));
                return false;
            } catch (IOException e) {
                writer.print(e.getMessage());
            } finally {
                if (writer != null)
                    writer.close();
            }
            return false;
        }
        // 获取 token 中的 user id
        Integer userId;
        try {
            userId = Integer.valueOf(JWT.decode(token).getAudience().get(0));
        } catch (JWTDecodeException j) {
            try {
                writer = httpServletResponse.getWriter();
                writer.print(JSONObject.fromObject(ObjectResult.noaccess("请重新登录")));
                return false;

            } catch (IOException e) {
                writer.print(e.getMessage());
            } finally {
                if (writer != null)
                    writer.close();
            }
            return false;
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            try {
                writer = httpServletResponse.getWriter();
                writer.print(JSONObject.fromObject(ObjectResult.noaccess("请重新登录")));
                return false;

            } catch (IOException e) {
                writer.print(e.getMessage());
            } finally {
                if (writer != null)
                    writer.close();
            }
            return false;
        }
        // 验证 token
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            try {
                writer = httpServletResponse.getWriter();
                writer.print(JSONObject.fromObject(ObjectResult.noaccess("请重新登录")));
                return false;

            } catch (IOException ee) {
                writer.print(ee.getMessage());
            } finally {
                if (writer != null)
                    writer.close();
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }
}