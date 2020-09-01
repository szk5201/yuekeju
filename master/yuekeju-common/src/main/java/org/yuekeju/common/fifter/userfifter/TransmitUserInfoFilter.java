package org.yuekeju.common.fifter.userfifter;

import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.util.JwtUtil;
import org.yuekeju.common.util.UserInfoContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @author szk
 * @description
 * @date 2020/8/27
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "myDatasources.enabled", havingValue = "true")
public class TransmitUserInfoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        initUserInfo((HttpServletRequest) request);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private void initUserInfo(HttpServletRequest request) {
        String userJson = request.getHeader("Authorization");
        if (StringUtils.isNotBlank(userJson)) {
            try {
                String substring = userJson;
                if (userJson.contains("Bearer")) {
                    substring = userJson.substring(7);
                }
                userJson = URLDecoder.decode(substring, "UTF-8");
                Claims claims = JwtUtil.parseJWT(userJson);
                UserEntity userInfo = JSON.parseObject(claims.getSubject(), UserEntity.class);
                //将UserInfo放入上下文中
                UserInfoContext.setUser(userInfo);
            } catch (UnsupportedEncodingException e) {
                log.error("init userInfo error", e);
            } catch (Exception e) {
                e.printStackTrace();
                log.error("登录失败", e);
            }
        }
    }
}
