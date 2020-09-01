package org.yuekeju.common.intercepter.userintercepter;

import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.yuekeju.common.entity.user.UserEntity;
import org.yuekeju.common.util.JwtUtil;
import org.yuekeju.common.util.UserInfoContext;

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
public class TransmitUserInfoFeighClientIntercepter implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        //从应用上下文中取出user信息，放入Feign的请求头中
        UserEntity user = UserInfoContext.getUser();
        if (user != null) {
            try {
                String userJson = JSON.toJSONString(user);
                String jwt = JwtUtil.createJWT(System.currentTimeMillis() + "", userJson, null);
                requestTemplate.header("Authorization", new String[]{URLDecoder.decode(jwt, "UTF-8")});
            } catch (UnsupportedEncodingException e) {
                log.error("用户信息设置错误", e);
            } catch (Exception e) {
                log.error("用户信息获取失败", e);
            }
        }
    }
}
