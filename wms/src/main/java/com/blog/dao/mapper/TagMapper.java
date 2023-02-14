package com.blog.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.dao.pojo.Tag;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TagMapper extends BaseMapper<Tag> {

    List<Tag> findTagByArticleId(Long articleId);
}
