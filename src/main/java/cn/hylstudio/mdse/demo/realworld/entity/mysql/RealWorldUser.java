package cn.hylstudio.mdse.demo.realworld.entity.mysql;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "real_world_user")
public class RealWorldUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public RealWorldUser() {
    }

    public RealWorldUser(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
