package top.iaminlearn.service.impl;

import top.iaminlearn.entity.Blog;
import top.iaminlearn.mapper.BlogMapper;
import top.iaminlearn.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author anonymous
 * @since 2020-07-15
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
