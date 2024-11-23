package br.com.fiap.ecommerce.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.fiap.ecommerce.dtos.TransporteRequestCreateDto;
import br.com.fiap.ecommerce.dtos.TransporteRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.TransporteResponseDto;
import br.com.fiap.ecommerce.model.Transporte;

@Component
public class TransporteMapper {    
    
    @Autowired
    private  ModelMapper modelMapper;

    public TransporteResponseDto toDto(Transporte transporte) {
        return modelMapper.map(transporte, TransporteResponseDto.class);
    }

    public Transporte toModel(TransporteRequestCreateDto dto) {
        return modelMapper.map(dto, Transporte.class);
    }

    public Transporte toModel(Long id, TransporteRequestUpdateDto dto) {
        Transporte result = modelMapper.map(dto, Transporte.class);
        result.setId(id);
        return result;
    }   

}
