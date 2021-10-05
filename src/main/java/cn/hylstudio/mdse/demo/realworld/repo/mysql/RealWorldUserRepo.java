package cn.hylstudio.mdse.demo.realworld.repo.mysql;

import cn.hylstudio.mdse.demo.realworld.entity.mysql.RealWorldUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RealWorldUserRepo extends JpaRepository<RealWorldUser, Integer> {
    RealWorldUser findByEmailAndPassword(String loginEmail, String passwordHash);
}
