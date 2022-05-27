package ru.pycak.todolistapp.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="taskstatus")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TaskStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="statusid")
    private int id;

    @Column
    private String value;
}
