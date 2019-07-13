package com.jzwb.service;

import com.jzwb.entity.Admin;

import java.util.List;

/**
 * Service - 管理员
 */
public interface AdminService extends BaseService<Admin, Long> {

    /**
     * 根据用户名查询管理员(不能用于新增判断)
     *
     * @param username 用户名
     * @return
     */
    Admin findByUsername(String username);

    /**
     * 根据id查询权限
     *
     * @param id id
     * @return
     */
    List<String> findAuthoritiesById(Long id);
}