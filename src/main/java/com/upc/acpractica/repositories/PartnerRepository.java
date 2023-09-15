package com.upc.acpractica.repositories;

import com.upc.acpractica.entities.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Long> {
    public Partner findPartnerByAcDNI(String DNI);
}
