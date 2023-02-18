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
import com.mhj.ranking.entity.TipoEquipe;
import com.mhj.ranking.mapper.TipoEquipeMapper;
import com.mhj.ranking.model.TipoEquipeModel;
import com.mhj.ranking.repository.TipoEquipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TipoEquipeService {

	@Autowired
	private TipoEquipeRepository repository;

	@Autowired
	private TipoEquipeMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<TipoEquipe> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<TipoEquipe> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public TipoEquipeModel save(TipoEquipeModel entityModel) {
		TipoEquipe entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(TipoEquipe current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<TipoEquipe> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<TipoEquipeModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		List<TipoEquipeModel> models = new ArrayList<>();
		
		Page<TipoEquipe> findAll = repository.findAll(pageable);
		
		findAll.stream().forEach(p -> {
			TipoEquipeModel model = mapper.toModel(p);
			models.add(model);
		});
		
		PageImpl<TipoEquipeModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());
		
		return pageImpl;
	}

	public List<TipoEquipeModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		PageImpl<TipoEquipeModel> pageImpl = (PageImpl<TipoEquipeModel>) findAll(pageNo, pageSize, sortBy, sortDir);
		
		return pageImpl.getContent();
	}

	public Optional<TipoEquipeModel> findById(Long key) {
		Optional<TipoEquipe> entityOptional = repository.findById(key.longValue());
		TipoEquipeModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public TipoEquipeModel update(long id, TipoEquipeModel entityModel) throws NotFoundException {
		Optional<TipoEquipe> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			TipoEquipe entity = entityOptional.get();
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

	public List<TipoEquipeModel> findByNome(String nome) {
		TipoEquipe entity = new TipoEquipe();
//		entity.setNome(nome);
		
		Example<TipoEquipe> example = Example.of(entity);
		
		List<TipoEquipeModel> models = new ArrayList<>();
		
		repository.findAll(example).stream().forEach(p -> {
			TipoEquipeModel model = mapper.toModel(p);
			models.add(model);
		});
		
		return models;
	}

}
