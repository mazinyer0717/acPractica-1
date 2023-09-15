package com.upc.acpractica.service;

import com.upc.acpractica.entities.Partner;
import com.upc.acpractica.interfaceservice.PartnerService;
import com.upc.acpractica.repositories.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartnerServiceImpl implements PartnerService {
    @Autowired
    private PartnerRepository partnerRepository;

    public Partner registerPartner(Partner partner){
        //primero doy valor al registration fee
        Partner partner1= setRegistrationFee(partner);
        return partnerRepository.save(partner1);
    }
    public Partner setRegistrationFee(Partner partner){
        double fee= 100.0/partner.getAcActions()*Math.sqrt(partner.getAcAge());
        if(partner.getAcDependents()>2)fee=fee*1.05;
        partner.setAcRegistrationfee(fee);
        return partner;
    }


    public double getRegistrationFeeByDNI(String DNI){
        Partner p1= partnerRepository.findPartnerByAcDNI(DNI);
        setRegistrationFee(p1);
        return p1.getAcRegistrationfee();
    }

    public List<Partner> getAllPartnersWithRegistrationFee(){
        return getListWithRegistration();
    }

    public List<Partner> getListWithRegistration(){
        List<Partner> l1=new ArrayList<>();
        for (Partner p:partnerRepository.findAll()
             ) {
            l1.add(setRegistrationFee(p));
        }
        return l1;
    }

    public Partner updatePartner(Partner partner) throws Exception{
        partnerRepository.findById(partner.getAcId()).
                orElseThrow(() -> new Exception("No se encontró la entidad"));
        return partnerRepository.save(partner);
    }


}
