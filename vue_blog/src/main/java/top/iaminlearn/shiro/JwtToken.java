package top.iaminlearn.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {

    private String token;
    public JwtToken(String jwt) {
        this.token = jwt;
    }

     // 显示用户身份信息  认证的实体信息
    @Override
    public Object getPrincipal() {
        return token;
    }

    // 证书 密码
    @Override
    public Object getCredentials() {
        return token;
    }
}
