package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Pais;
import com.mhj.ranking.model.PaisModel;

@Mapper(componentModel = "spring")
public interface PaisMapper {

	public PaisModel toModel(final Pais entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Pais toEntity(PaisModel model);

	List<PaisModel> toModel(List<Pais> models);

}
