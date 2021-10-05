package cn.hylstudio.mdse.demo.realworld.service.user;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;

public interface IBaseUserService {
    RealWorldUser findByEmailAndPassword(String loginEmail, String passwordHash);
}
