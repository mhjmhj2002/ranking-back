package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Fase;
import com.mhj.ranking.model.FaseModel;

@Mapper(componentModel = "spring")
public interface FaseMapper {

	public FaseModel toModel(final Fase entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Fase toEntity(FaseModel model);

	List<FaseModel> toModel(List<Fase> models);

}
