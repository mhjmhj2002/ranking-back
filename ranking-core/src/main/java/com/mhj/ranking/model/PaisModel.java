package com.mhj.ranking.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PaisModel {

	@ApiModelProperty(value = "Código do pais")
    private Long id;
    
	@ApiModelProperty(value = "Nome do pais")
    private String nome;

}
