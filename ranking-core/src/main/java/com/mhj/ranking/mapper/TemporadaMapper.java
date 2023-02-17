package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Temporada;
import com.mhj.ranking.model.TemporadaModel;

@Mapper(componentModel = "spring")
public interface TemporadaMapper {

	public TemporadaModel toModel(final Temporada entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Temporada toEntity(TemporadaModel model);

	List<TemporadaModel> toModel(List<Temporada> models);

}
