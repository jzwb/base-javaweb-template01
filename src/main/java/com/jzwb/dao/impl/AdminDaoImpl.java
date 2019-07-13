package com.jzwb.dao.impl;

import com.jzwb.dao.AdminDao;
import com.jzwb.entity.Admin;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

/**
 * Dao - 管理员
 */
@Repository("adminDaoImpl")
public class AdminDaoImpl extends BaseDaoImpl<Admin, Long> implements AdminDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdminDaoImpl.class);

    @Override
    public Admin findByUsername(String username) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        try {
            String jpql = "FROM Admin WHERE username = :username";
            return entityManager.createQuery(jpql, Admin.class).setFlushMode(FlushModeType.COMMIT).setParameter("username", username).getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error("当前用户名存在多个账号,参数(username:[{}])", username);
            return null;
        }
    }
}