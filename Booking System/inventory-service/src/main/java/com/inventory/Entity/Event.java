package com.inventory.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "event")
public class Event {



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "totalCapacity")
    private Long totalCapacity;

    @Column(name = "leftCapacity")
    private Long leftCapacity;


    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;








}
