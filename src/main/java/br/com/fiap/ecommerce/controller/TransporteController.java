package br.com.fiap.ecommerce.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.ecommerce.dtos.TransporteRequestCreateDto;
import br.com.fiap.ecommerce.dtos.TransporteRequestUpdateDto;
import br.com.fiap.ecommerce.dtos.TransporteResponseDto;
import br.com.fiap.ecommerce.mapper.TransporteMapper;
import br.com.fiap.ecommerce.repository.TransporteRepository;
import br.com.fiap.ecommerce.service.TransporteService;
import br.com.fiap.ecommerce.views.ProdutoFullView;
import br.com.fiap.ecommerce.views.ProdutoSimpleView;
import br.com.fiap.ecommerce.views.ProdutoViewType;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/transportes")
@RequiredArgsConstructor
public class TransporteController {    
    private final TransporteService transporteService;
    private final TransporteMapper transporteMapper;
    private final TransporteRepository transporteRepository;

    @GetMapping
    public ResponseEntity<List<TransporteResponseDto>> list() {
        List<TransporteResponseDto> dtos = transporteService.list()
            .stream()
            .map(e -> transporteMapper.toDto(e))
            .toList();
        
        return ResponseEntity.ok().body(dtos);
    }

    @PostMapping
    public ResponseEntity<TransporteResponseDto> create(@RequestBody TransporteRequestCreateDto dto) {        

        return ResponseEntity
        		.status(HttpStatus.CREATED)
        		.body(
        			transporteMapper.toDto(
                        transporteService.save(transporteMapper.toModel(dto)))
        			);
    }

    @PutMapping("{id}")
    public ResponseEntity<TransporteResponseDto> update(
                        @PathVariable Long id, 
                        @RequestBody TransporteRequestUpdateDto dto) {
        if (! transporteService.existsById(id)){
            throw new RuntimeException("Id inexistente");
        }                
        return ResponseEntity.ok()
        		.body(
        			transporteMapper.toDto(
        				transporteService.save(transporteMapper.toModel(id, dto)))
        		);
    }
    
    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) {
        if (! transporteService.existsById(id)){
        	throw new RuntimeException("Id inexistente");
        }

        transporteService.delete(id);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransporteResponseDto> findById(@PathVariable Long id) {    	
    	return ResponseEntity.ok()
    			.body(
    				transporteService
    					.findById(id)
    					.map(e -> transporteMapper.toDto(e))
    					.orElseThrow(() -> new RuntimeException("Id inexistente"))
    			);    	  		     
    }
    
    @GetMapping("/find")
    public  ResponseEntity<?> findByNome(
                @RequestParam String nome, 
                ProdutoViewType type) { 

        switch (type) {
            case FULL:
                return ResponseEntity.ok().body(transporteRepository.findAllByNomeContains(nome, ProdutoFullView.class));                
            case SIMPLE:
                return ResponseEntity.ok().body(transporteRepository.findAllByNomeContains(nome, ProdutoSimpleView.class));            
        }
        return ResponseEntity.noContent().build();
    }
}
