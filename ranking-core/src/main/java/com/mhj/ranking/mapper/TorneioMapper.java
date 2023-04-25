package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Torneio;
import com.mhj.ranking.model.TorneioModel;

@Mapper(componentModel = "spring")
public interface TorneioMapper {

	public TorneioModel toModel(final Torneio entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Torneio toEntity(TorneioModel model);

	List<TorneioModel> toModel(List<Torneio> models);

}
