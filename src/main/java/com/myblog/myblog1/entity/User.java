package com.myblog.myblog1.entity;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;
@Data
@Entity
@Table(name="users",uniqueConstraints={@UniqueConstraint(columnNames={"username"}),
                                       @UniqueConstraint(columnNames={"email"})})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    @ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    /* Here i provide EAGER as all the tables are loaded into hibernate "cash A" memory as execution is Faster  */
    @JoinTable(name="user_roles",
            joinColumns=@JoinColumn(name="users_id",referencedColumnName="id"),
            inverseJoinColumns=@JoinColumn(name="roles_id",referencedColumnName="id"))
    private Set<Role> roles;

}
