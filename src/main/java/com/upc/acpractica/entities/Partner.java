package com.upc.acpractica.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "partner")
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acId;
    @Column(unique = true)
    private String acDNI;
    //poner las funciones solas sin guardar update ni eliminar ni nada
    @Column()
    private int acAge;
    @Column()
    private String acName;
    @Column()
    private int acDependents;
    @Column()
    private int acActions;
    private transient double acRegistrationfee;
}
