package com.blog.controller;

import com.blog.pagparms.PageParams;
import com.blog.pagparms.Result;
import com.blog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
     public ArticleService articleService;
/*
首页列表
 */
  @PostMapping
      public Result listArticle(@RequestBody PageParams pageParams){
          return articleService.listArticle(pageParams);
      }
}
