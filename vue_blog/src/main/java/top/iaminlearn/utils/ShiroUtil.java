package top.iaminlearn.utils;

import org.apache.shiro.SecurityUtils;
import top.iaminlearn.shiro.AccountProfile;

public class ShiroUtil {

    public static AccountProfile getProfile(){
        return (AccountProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
