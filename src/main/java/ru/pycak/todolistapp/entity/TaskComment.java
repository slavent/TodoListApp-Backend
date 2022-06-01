package ru.pycak.todolistapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="taskcomments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column
    private String text;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "taskid")
    private Task task;

    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "userid")
    private User user;
}
