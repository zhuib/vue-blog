package top.iaminlearn.controller;


import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.iaminlearn.common.lang.Result;
import top.iaminlearn.entity.User;
import top.iaminlearn.service.UserService;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2020-07-15
 */


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequiresAuthentication  // 登录才能进行访问
    @GetMapping("/{id}")
    public Object test(@PathVariable("id") Long id) {
        User user = userService.getById(id);
        return Result.succ(user);
    }

    /*
    * @Validated 校验异常捕获
    * */
    @PostMapping("/save")
    public Object test(@Validated  @RequestBody  User user) {
        return Result.succ(user);
    }

}
