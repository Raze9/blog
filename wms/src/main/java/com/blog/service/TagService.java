package com.blog.service;

import com.blog.dao.pojo.Article;
import com.blog.pagparms.TagVo;

import java.util.List;

public interface TagService {
    List<TagVo>  finTagsByArticleId(Long articleId);
}
