package com.myblog.myblog1.entity;

import lombok.*;
import javax.persistence.*;
@Data
@Entity
@Setter
@Getter
@Table(name="roles")
public class Role {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @Column(name="name")
    private String name;
}
