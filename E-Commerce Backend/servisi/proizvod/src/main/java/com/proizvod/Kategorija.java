package com.proizvod;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Kategorija {


    @Id
    @GeneratedValue
    private Integer id;

    private String nazivKategorije;

    private String opisKategorije;


    @OneToMany(mappedBy = "kategorija", cascade = CascadeType.REMOVE)
    private List<Proizvod> proizvodi;



}
