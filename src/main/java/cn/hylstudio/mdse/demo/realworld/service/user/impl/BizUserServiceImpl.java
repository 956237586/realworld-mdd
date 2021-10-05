package cn.hylstudio.mdse.demo.realworld.service.user.impl;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.service.user.IBaseUserService;
import cn.hylstudio.mdse.demo.realworld.service.user.IBizUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BizUserServiceImpl implements IBizUserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BizUserServiceImpl.class);
    @Autowired
    private IBaseUserService baseUserService;

    @Override
    public RealWorldUser query(String loginEmail, String passwordHash) {
        RealWorldUser user = baseUserService.findByEmailAndPassword(loginEmail, passwordHash);
        return user;
    }
}
