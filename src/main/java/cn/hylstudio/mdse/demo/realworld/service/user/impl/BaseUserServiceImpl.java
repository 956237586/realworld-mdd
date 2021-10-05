package cn.hylstudio.mdse.demo.realworld.service.user.impl;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import cn.hylstudio.mdse.demo.realworld.repo.mysql.RealWorldUserRepo;
import cn.hylstudio.mdse.demo.realworld.service.user.IBaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseUserServiceImpl implements IBaseUserService {
    @Autowired
    private RealWorldUserRepo realWorldUserRepo;
    @Override
    public RealWorldUser findByEmailAndPassword(String loginEmail, String passwordHash) {
        return realWorldUserRepo.findByEmailAndPassword(loginEmail, passwordHash);
    }
}
