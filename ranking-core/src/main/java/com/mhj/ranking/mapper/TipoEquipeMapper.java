package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.TipoEquipe;
import com.mhj.ranking.model.TipoEquipeModel;

@Mapper(componentModel = "spring")
public interface TipoEquipeMapper {

	public TipoEquipeModel toModel(final TipoEquipe entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public TipoEquipe toEntity(TipoEquipeModel model);

	List<TipoEquipeModel> toModel(List<TipoEquipe> models);

}
