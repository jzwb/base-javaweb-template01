package com.jzwb.dao;

import com.jzwb.entity.Admin;

/**
 * Dao - 管理员
 */
public interface AdminDao extends BaseDao<Admin, Long> {

    /**
     * 根据用户名查询管理员
     *
     * @param username 用户名
     * @return
     */
    Admin findByUsername(String username);
}