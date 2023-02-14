package com.blog.service.impl;

import com.blog.dao.mapper.TagMapper;
import com.blog.dao.pojo.Tag;
import com.blog.pagparms.TagVo;
import com.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service

public class TagServiceImpl implements TagService {
    @Resource
    private TagMapper tagMapper;
    public TagVo copy(Tag tag){
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVoList = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVoList.add(copy(tag));
        }
        return tagVoList;
    }

    @Override
    public List<TagVo> finTagsByArticleId(Long articleId) {

    List<Tag> tags = tagMapper.findTagByArticleId(articleId);
    return copyList(tags);
    }
}
