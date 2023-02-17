package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Equipe;
import com.mhj.ranking.model.EquipeModel;

@Mapper(componentModel = "spring")
public interface EquipeMapper {

	public EquipeModel toModel(final Equipe entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Equipe toEntity(EquipeModel model);

	List<EquipeModel> toModel(List<Equipe> models);

}
