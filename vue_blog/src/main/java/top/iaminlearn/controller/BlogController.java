package top.iaminlearn.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.Assert;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import top.iaminlearn.common.lang.Result;
import top.iaminlearn.entity.Blog;
import top.iaminlearn.service.BlogService;
import top.iaminlearn.utils.ShiroUtil;

import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author anonymous
 * @since 2020-07-15
 */
@RestController
//@RequestMapping("/blog")
public class BlogController {


    @Autowired
    BlogService blogService;
    /**
     * RequestParam(defaultValue = "1") 默认值
     */
    @GetMapping("/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage){

//        分页接口
        Page page = new Page(currentPage,5);
        IPage pageDate = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));

        return Result.succ(pageDate);
    }

    @GetMapping("/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id){
        Blog blog = blogService.getById(id);

        Assert.notNull(blog,"该博客已被删除");
        return Result.succ(blog);
    }

    @RequiresAuthentication   // 登录认证
    @PostMapping("/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog){

        Blog temp = null;
//        不为空是编辑状态
        if (blog.getId() != null){
            temp = blogService.getById(blog.getId());
//            只能编辑自己的文章
//            Assert.isTrue(temp.getUserId() == ShiroUtil.getProfile().getId(),"没有权限编辑");
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(),"没有权限编辑");
        }else {
//            为空时，添加
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }

        BeanUtil.copyProperties(blog,temp,"id","userId","created","status");
        blogService.saveOrUpdate(temp);
        return Result.succ(null);
    }

    @GetMapping("/blog/{id}/del")
    public Result delete(@PathVariable(name = "id") Long id){
//
        boolean remove = blogService.removeById(id);
//        Blog blog = blogService.getById(id.intValue());
        System.out.println(remove);
//        System.out.println(blog);
//        Assert.notNull(blog,"该博客已被删除");
        return Result.succ("删除成功");
    }
}
