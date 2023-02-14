package com.blog.service;

import com.blog.pagparms.PageParams;
import com.blog.pagparms.Result;

public interface ArticleService {
    /*
    分页查询
     */
    Result listArticle(PageParams pageParams);
}
