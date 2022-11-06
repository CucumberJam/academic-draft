package com.example.academicdraft.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ECOFRIENDS")
public class Ecofriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(name = "type") // Организационно_правовая форма
    private String type;

    @Column
    private String name;

    @Column                  // ОГРН/ОГРНИП
    private String identity;

    @Column
    private String address;

    @Column
    private String phone;

    @Column                      // Статус участника
    private String partnership;

    @Column                       // Вид отходов
    private String material;

}
