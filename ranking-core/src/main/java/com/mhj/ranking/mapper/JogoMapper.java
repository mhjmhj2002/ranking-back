package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Jogo;
import com.mhj.ranking.model.JogoModel;

@Mapper(componentModel = "spring")
public interface JogoMapper {

	public JogoModel toModel(final Jogo entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Jogo toEntity(JogoModel model);

	List<JogoModel> toModel(List<Jogo> models);

}
