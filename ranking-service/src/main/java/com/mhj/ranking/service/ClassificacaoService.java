package com.mhj.ranking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mhj.ranking.config.NotFoundException;
import com.mhj.ranking.entity.Classificacao;
import com.mhj.ranking.mapper.ClassificacaoMapper;
import com.mhj.ranking.model.ClassificacaoModel;
import com.mhj.ranking.repository.ClassificacaoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClassificacaoService {

	@Autowired
	private ClassificacaoRepository repository;

	@Autowired
	private ClassificacaoMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Classificacao> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Classificacao> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public ClassificacaoModel save(ClassificacaoModel entityModel) {
		Classificacao entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(Classificacao current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Classificacao> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<ClassificacaoModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		List<ClassificacaoModel> models = new ArrayList<>();

		Page<Classificacao> findAll = repository.findAll(pageable);

		findAll.stream().forEach(p -> {
			ClassificacaoModel model = mapper.toModel(p);
			models.add(model);
		});

		PageImpl<ClassificacaoModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());

		return pageImpl;
	}

	public List<ClassificacaoModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {

		PageImpl<ClassificacaoModel> pageImpl = (PageImpl<ClassificacaoModel>) findAll(pageNo, pageSize, sortBy,
				sortDir);

		return pageImpl.getContent();
	}

	public Optional<ClassificacaoModel> findById(Long key) {
		Optional<Classificacao> entityOptional = repository.findById(key.longValue());
		ClassificacaoModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public ClassificacaoModel update(long id, ClassificacaoModel entityModel) throws NotFoundException {
		Optional<Classificacao> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			Classificacao entity = entityOptional.get();
//			entity.setNome(entityModel.getNome());
			entity = repository.save(entity);
			return mapper.toModel(entity);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public List<ClassificacaoModel> findByNome(String nome) {
		Classificacao entity = new Classificacao();
//		entity.setNome(nome);

		Example<Classificacao> example = Example.of(entity);

		List<ClassificacaoModel> models = new ArrayList<>();

		repository.findAll(example).stream().forEach(p -> {
			ClassificacaoModel model = mapper.toModel(p);
			models.add(model);
		});

		return models;
	}

}
