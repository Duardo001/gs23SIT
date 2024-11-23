package br.com.fiap.ecommerce.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Transporte {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column(nullable = true, length = 60)
	private String nome;

	@Column(nullable = true, length = 60)
	private String tipoTransporte;
	
	@Column(nullable = true, length = 20)
    private String tipoEnergia;
    
	@Column(nullable = true, length = 60)
    private String cor;
    
	@Column(nullable = true, length = 20)
    private String placa;

}
