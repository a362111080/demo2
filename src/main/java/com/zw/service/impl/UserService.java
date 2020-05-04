package com.zw.service.impl;

import com.zw.beans.User;
import com.zw.dao.UserMapper;
import com.zw.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author yzj
 * @since 2019-11-12
 */
@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IUserService {

}
