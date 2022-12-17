package com.mhj.ranking.mapper;

import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Pais;
import com.mhj.ranking.model.PaisModel;

@Mapper(componentModel = "spring")
public interface PaisMapper {
	
	public PaisModel toModel(Pais pais);
	
}
