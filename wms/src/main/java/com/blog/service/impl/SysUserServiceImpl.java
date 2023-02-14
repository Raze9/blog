package com.blog.service.impl;

import com.blog.dao.mapper.SysUserMapper;
import com.blog.dao.pojo.SysUser;
import com.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class SysUserServiceImpl implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;
    @Override
    public SysUser findUserById(Long id) {
         SysUser sysUser = sysUserMapper.selectById(id);
         if (sysUser==null){
             sysUser = new SysUser();
             sysUser.setNickname("");
         }
         return sysUser;
    }
}
