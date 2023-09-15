package com.upc.acpractica.dtos;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class PartnerDTO {
    private Long acId;
    private String acDNI;
    //poner las funciones solas sin guardar update ni eliminar ni nada
    private int acAge;
    private String acName;
    private int acDependents;
    private int acActions;
    private transient double registrationfee;
}
