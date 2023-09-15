package com.upc.acpractica.controller;

import com.upc.acpractica.dtos.PartnerDTO;
import com.upc.acpractica.entities.Partner;
import com.upc.acpractica.interfaceservice.PartnerService;
import com.upc.acpractica.service.PartnerServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PartnerController {
    @Autowired
    private PartnerService partnerService;

    //registrar partner
    @PostMapping("/partner")
    public ResponseEntity<PartnerDTO> registerPartner(@RequestBody PartnerDTO partnerDTO){
        Partner partner;
        PartnerDTO dto;
        try{
            partner = convertToEntity(partnerDTO);
            partner = partnerService.registerPartner(partner);
            dto = convertToDTO(partner);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se ha podido registrar");
        }
        return new ResponseEntity<PartnerDTO>(dto, HttpStatus.OK);
    }

    @GetMapping("/partner/{dni}")
    public double getAverage_EligibleApplicants(@PathVariable(value = "dni") String dni){
        double proof;
        try {
            proof = partnerService.getRegistrationFeeByDNI(dni);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.EXPECTATION_FAILED, "No se encontró el partner");
        }
        return proof;
    }

    @GetMapping("/partners/withfee")
    public ResponseEntity<List<PartnerDTO>> listAllPartnerWithRegistrationFee(){
        List<Partner> list;
        List<PartnerDTO> listDTO=null;
        try {
            list = partnerService.getAllPartnersWithRegistrationFee();
            listDTO = convertToListDTO(list);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Lista no disponible");
        }
        return new ResponseEntity<>(listDTO,HttpStatus.OK);
    }

    @PutMapping("/partner/update")
    public ResponseEntity<PartnerDTO> updatePartner(@RequestBody PartnerDTO partnerDTO){
        Partner partner;
        PartnerDTO dto;
        try{
            partner = convertToEntity(partnerDTO);
            partner = partnerService.updatePartner(partner);
            dto = convertToDTO(partner);
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se puede actualizar");
        }
        return new ResponseEntity<PartnerDTO>(dto,HttpStatus.OK);
    }


    private Partner convertToEntity(PartnerDTO partnerDTO){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(partnerDTO, Partner.class);
    }

    private PartnerDTO convertToDTO(Partner partner){
        ModelMapper modelMapper =new ModelMapper();
        return modelMapper.map(partner, PartnerDTO.class);
    }

    private List<PartnerDTO> convertToListDTO(List<Partner> list){
        return list.stream().map(this::convertToDTO).collect(Collectors.toList());
    }
}
