package com.xq.tmall.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.xq.tmall.dao.UserMapper;
import com.xq.tmall.entity.User;
import com.xq.tmall.service.UserService;
import com.xq.tmall.util.OrderUtil;
import com.xq.tmall.util.PageUtil;

@Service("userService")
public class UserServiceImpl implements UserService {
	private UserMapper userMapper;

	@Resource(name = "userMapper")
	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	// 获取用户ID
	@Override
	public User get(Integer user_id) {

		return userMapper.selectOne(user_id);
	}

	@Override
	public User login(String user_name, String user_password) {
		return userMapper.selectByLogin(user_name, user_password);
	}

	@Override
	public List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil) {
		return userMapper.select(user, orderUtil, pageUtil);
	}

	@Override
	public Integer getTotal(User user) {
		return userMapper.selectTotal(user);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean update(User user) {
		return userMapper.updateOne(user) > 0;
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public boolean add(User user) {
		return userMapper.insertOne(user) > 0;
	}
}
