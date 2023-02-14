package com.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.dao.mapper.ArticleMapper;
import com.blog.dao.pojo.Article;
import com.blog.dao.pojo.SysUser;
import com.blog.pagparms.ArticleVo;
import com.blog.pagparms.PageParams;
import com.blog.pagparms.Result;
import com.blog.service.ArticleService;
import com.blog.service.SysUserService;
import com.blog.service.TagService;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
@Resource
private ArticleMapper articleMapper;
   @Autowired
   private TagService tagService;
   @Autowired
   private SysUserService sysUserService;
    /*
    查询
     */
    @Override

    public Result listArticle(PageParams pageParams) {
        //1. 这个是分页查询的类（代表着分离模式），要传入的是页面的页数和页面总数
        Page<Article> page= new Page<>(pageParams.getPage(), pageParams.getPageSize());
        //2. LambdaQueryWrapper是MybatisPlus提供的，需要就导入这个包就可以了
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //3. 这里是根据数据库时间,及字体排序
        queryWrapper.orderByDesc(Article::getWeight,Article::getCreateDate);
        // 因为articleMapper继承了BaseMapper了的，所有设查询的参数和查询出来的排序方式
        Page<Article> articlePage = articleMapper.selectPage(page,queryWrapper);
        //查询出来的数据的数组了
        List<Article> records = articlePage.getRecords();
        //因为页面展示出来的数据不一定和数据库一样，所有要做一个转换，
        List<ArticleVo> articleList = copylist(records,true,true);
        return Result.success(articleList);
    }
    private List<ArticleVo> copylist(List<Article> records,boolean isTag,boolean isAuthor){
        List<ArticleVo>articleVoList =new ArrayList<>();
        for (Article record:records){
            articleVoList.add(copy(record,true,true));
        }
        return articleVoList;
    }
    //这个方法是主要点是BeanUtils，又Spring提供的，专门用来拷贝的，想Article和articlevo相同属性的拷贝过来返回
    private ArticleVo copy(Article article,boolean isTag, boolean isAuthor){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if(isTag){
            Long  articleId = article.getId();
            articleVo.setTags(tagService.finTagsByArticleId(articleId));
        }
        if(isAuthor){
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        return articleVo;
    }
}
