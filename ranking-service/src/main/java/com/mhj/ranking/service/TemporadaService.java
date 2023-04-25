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
import com.mhj.ranking.entity.Temporada;
import com.mhj.ranking.mapper.TemporadaMapper;
import com.mhj.ranking.model.TemporadaModel;
import com.mhj.ranking.repository.TemporadaRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TemporadaService {

	@Autowired
	private TemporadaRepository repository;

	@Autowired
	private TemporadaMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Temporada> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Temporada> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public TemporadaModel save(TemporadaModel entityModel) {
		Temporada entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(Temporada current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Temporada> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<TemporadaModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		List<TemporadaModel> models = new ArrayList<>();

		Page<Temporada> findAll = repository.findAll(pageable);

		findAll.stream().forEach(p -> {
			TemporadaModel model = mapper.toModel(p);
			models.add(model);
		});

		PageImpl<TemporadaModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());

		return pageImpl;
	}

	public List<TemporadaModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {

		PageImpl<TemporadaModel> pageImpl = (PageImpl<TemporadaModel>) findAll(pageNo, pageSize, sortBy, sortDir);

		return pageImpl.getContent();
	}

	public Optional<TemporadaModel> findById(Long key) {
		Optional<Temporada> entityOptional = repository.findById(key.longValue());
		TemporadaModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public TemporadaModel update(long id, TemporadaModel entityModel) throws NotFoundException {
		Optional<Temporada> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			Temporada entity = entityOptional.get();
//			entity.setNome(entityModel.getNome());
			entity = repository.save(entity);
			return mapper.toModel(entity);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public List<TemporadaModel> findByNome(String nome) {
		Temporada entity = new Temporada();
//		entity.setNome(nome);

		Example<Temporada> example = Example.of(entity);

		List<TemporadaModel> models = new ArrayList<>();

		repository.findAll(example).stream().forEach(p -> {
			TemporadaModel model = mapper.toModel(p);
			models.add(model);
		});

		return models;
	}

}
