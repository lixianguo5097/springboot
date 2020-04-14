package com.lxg.config;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author LXG
 * @date 2020-2-21
 */
public class JWTToken implements AuthenticationToken {

    /**
     * 密钥
     */
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
