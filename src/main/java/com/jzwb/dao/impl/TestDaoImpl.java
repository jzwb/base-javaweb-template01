package com.jzwb.dao.impl;

import com.jzwb.dao.TestDao;
import com.jzwb.entity.Test;
import org.springframework.stereotype.Repository;

/**
 * Dao - 测试
 */
@Repository("testDaoImpl")
public class TestDaoImpl extends BaseDaoImpl<Test, Long> implements TestDao {

}