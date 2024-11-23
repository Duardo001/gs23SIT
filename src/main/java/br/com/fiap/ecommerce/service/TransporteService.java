package br.com.fiap.ecommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.ecommerce.mapper.TransporteMapper;
import br.com.fiap.ecommerce.model.Transporte;
import br.com.fiap.ecommerce.repository.TransporteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransporteService {
    private final TransporteRepository transporteRepository;

    public List<Transporte> list() {
        return transporteRepository.findAll();
    }

    public Transporte save(Transporte transporte) {        
        return transporteRepository.save(transporte);
    }

    public boolean existsById(Long id) {        
        return transporteRepository.existsById(id);
    }

    public void delete(Long id) {
        transporteRepository.deleteById(id);
    }

    public Optional<Transporte> findById(Long id) {
        return transporteRepository.findById(id);
    }
   
}
