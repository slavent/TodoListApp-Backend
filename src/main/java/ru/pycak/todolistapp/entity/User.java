package ru.pycak.todolistapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="userid")
    private Long id;

    @Column
    private String name;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String avatarUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Task> tasks;
}
