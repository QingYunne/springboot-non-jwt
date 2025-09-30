package com.arstialmq.javaweb.repository.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "renttype")
public class RentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @ManyToMany
    private List<BuildingEntity> buildings;
}
