package com.jzwb.service.impl;

import com.jzwb.dao.TestDao;
import com.jzwb.entity.Test;
import com.jzwb.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Service - 测试
 */
@Service("testServiceImpl")
public class TestServiceImpl extends BaseServiceImpl<Test, Long> implements TestService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

	@Resource(name = "testDaoImpl")
	public void setBaseDao(TestDao testDao) {
		super.setBaseDao(testDao);
	}
}