package cn.hylstudio.mdse.demo.realworld.service.user;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;

public interface IBizUserService {
    RealWorldUser query(String loginEmail, String passwordHash);
}
