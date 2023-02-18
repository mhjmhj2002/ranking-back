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
import com.mhj.ranking.entity.Fase;
import com.mhj.ranking.mapper.FaseMapper;
import com.mhj.ranking.model.FaseModel;
import com.mhj.ranking.repository.FaseRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FaseService {

	@Autowired
	private FaseRepository repository;

	@Autowired
	private FaseMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Fase> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Fase> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public FaseModel save(FaseModel entityModel) {
		Fase entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(Fase current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Fase> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<FaseModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		List<FaseModel> models = new ArrayList<>();
		
		Page<Fase> findAll = repository.findAll(pageable);
		
		findAll.stream().forEach(p -> {
			FaseModel model = mapper.toModel(p);
			models.add(model);
		});
		
		PageImpl<FaseModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());
		
		return pageImpl;
	}

	public List<FaseModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		PageImpl<FaseModel> pageImpl = (PageImpl<FaseModel>) findAll(pageNo, pageSize, sortBy, sortDir);
		
		return pageImpl.getContent();
	}

	public Optional<FaseModel> findById(Long key) {
		Optional<Fase> entityOptional = repository.findById(key.longValue());
		FaseModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public FaseModel update(long id, FaseModel entityModel) throws NotFoundException {
		Optional<Fase> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			Fase entity = entityOptional.get();
//			entity.setNome(entityModel.getNome());
			entity = repository.save(entity);
			return mapper.toModel(entity);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public void teste(final Pageable pageable) {
		Page<Object> entites = repository.findAll(pageable).map(mapper::toModel);

	}

	public List<FaseModel> findByNome(String nome) {
		Fase entity = new Fase();
		entity.setNome(nome);
		
		Example<Fase> example = Example.of(entity);
		
		List<FaseModel> models = new ArrayList<>();
		
		repository.findAll(example).stream().forEach(p -> {
			FaseModel model = mapper.toModel(p);
			models.add(model);
		});
		
		return models;
	}

}
