package com.blog.service;

import com.blog.dao.pojo.SysUser;

public interface SysUserService {
    SysUser findUserById(Long id);
}
