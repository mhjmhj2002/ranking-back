package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Pais;
import com.mhj.ranking.model.PaisModel;

@Mapper(componentModel = "spring")
public interface PaisMapper {

	public PaisModel toModel(Pais pais);
	
	@InheritInverseConfiguration
	public Pais toEntity(PaisModel paisModel);

	List<PaisModel> toModel(List<Pais> paises);

}
