package br.com.fiap.ecommerce.dtos;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class TransporteRequestCreateDto {
    private String nome;
	private String tipoTransporte;
    private String tipoEnergia;
    private String cor;
    private String placa;

}
