package com.jzwb.service.impl;

import com.jzwb.dao.AdminDao;
import com.jzwb.entity.Admin;
import com.jzwb.entity.Role;
import com.jzwb.service.AdminService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Service - 管理员
 */
@Service("adminServiceImpl")
public class AdminServiceImpl extends BaseServiceImpl<Admin, Long> implements AdminService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AdminServiceImpl.class);

	@Resource(name = "adminDaoImpl")
	private AdminDao adminDao;

	@Resource(name = "adminDaoImpl")
	public void setBaseDao(AdminDao adminDao) {
		super.setBaseDao(adminDao);
	}

	@Override
	@Transactional(readOnly = true)
	public Admin findByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		return adminDao.findByUsername(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<String> findAuthoritiesById(Long id) {
		if (id == null) {
			return Collections.emptyList();
		}
		Admin admin = adminDao.find(id);
		if (admin == null) {
			return Collections.emptyList();
		}
		List<String> authorities = new ArrayList<>();
		for (Role role : admin.getRoles()) {
			authorities.addAll(role.getAuthorities());
		}
		return authorities;
	}
}