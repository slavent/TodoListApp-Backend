package ru.pycak.todolistapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name="commentid")
    private Long id;

    @Column
    private String text;

    @JsonIgnore
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "taskid")
    private Task task;

    @JsonIgnore
    @ManyToOne(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REMOVE,
            CascadeType.DETACH
    })
    @JoinColumn(name = "userid")
    private User user;
}
