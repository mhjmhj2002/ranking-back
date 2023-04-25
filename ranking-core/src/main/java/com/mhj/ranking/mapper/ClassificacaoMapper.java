package com.mhj.ranking.mapper;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

import com.mhj.ranking.entity.Classificacao;
import com.mhj.ranking.model.ClassificacaoModel;

@Mapper(componentModel = "spring")
public interface ClassificacaoMapper {

	public ClassificacaoModel toModel(final Classificacao entity);
	
	@InheritInverseConfiguration(name = "toModel")
	public Classificacao toEntity(ClassificacaoModel model);

	List<ClassificacaoModel> toModel(List<Classificacao> models);

}
