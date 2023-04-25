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
import com.mhj.ranking.entity.Grupo;
import com.mhj.ranking.mapper.GrupoMapper;
import com.mhj.ranking.model.GrupoModel;
import com.mhj.ranking.repository.GrupoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GrupoService {

	@Autowired
	private GrupoRepository repository;

	@Autowired
	private GrupoMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Grupo> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Grupo> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public GrupoModel save(GrupoModel entityModel) {
		Grupo entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(Grupo current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Grupo> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<GrupoModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		List<GrupoModel> models = new ArrayList<>();

		Page<Grupo> findAll = repository.findAll(pageable);

		findAll.stream().forEach(p -> {
			GrupoModel model = mapper.toModel(p);
			models.add(model);
		});

		PageImpl<GrupoModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());

		return pageImpl;
	}

	public List<GrupoModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {

		PageImpl<GrupoModel> pageImpl = (PageImpl<GrupoModel>) findAll(pageNo, pageSize, sortBy, sortDir);

		return pageImpl.getContent();
	}

	public Optional<GrupoModel> findById(Long key) {
		Optional<Grupo> entityOptional = repository.findById(key.longValue());
		GrupoModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public GrupoModel update(long id, GrupoModel entityModel) throws NotFoundException {
		Optional<Grupo> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			Grupo entity = entityOptional.get();
//			entity.setNome(entityModel.getNome());
			entity = repository.save(entity);
			return mapper.toModel(entity);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public List<GrupoModel> findByNome(String nome) {
		Grupo entity = new Grupo();
		entity.setNome(nome);

		Example<Grupo> example = Example.of(entity);

		List<GrupoModel> models = new ArrayList<>();

		repository.findAll(example).stream().forEach(p -> {
			GrupoModel model = mapper.toModel(p);
			models.add(model);
		});

		return models;
	}

}
