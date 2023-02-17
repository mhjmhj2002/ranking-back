package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Grupo;
import com.mhj.ranking.model.GrupoModel;

@Mapper(componentModel = "spring")
public interface GrupoMapper {

	public GrupoModel toModel(final Grupo entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Grupo toEntity(GrupoModel model);

	List<GrupoModel> toModel(List<Grupo> models);

}
