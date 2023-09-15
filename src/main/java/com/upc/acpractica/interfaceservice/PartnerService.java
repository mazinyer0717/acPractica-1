package com.upc.acpractica.interfaceservice;

import com.upc.acpractica.entities.Partner;

import java.util.List;

public interface PartnerService {

    public Partner updatePartner(Partner partner) throws Exception;
    public List<Partner> getListWithRegistration();
    public List<Partner> getAllPartnersWithRegistrationFee();
    public double getRegistrationFeeByDNI(String DNI);
    public Partner setRegistrationFee(Partner partner);
    public Partner registerPartner(Partner partner);
}
