package com.example.academicdraft.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "USERS_ACTIONS")
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String log;


    @Column(name = "date_actions", nullable = false)
    private Date ts;

    public void setMessage(String message) {
        log = message;
    }

}
