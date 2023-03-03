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
import com.mhj.ranking.entity.Torneio;
import com.mhj.ranking.mapper.TorneioMapper;
import com.mhj.ranking.model.TorneioModel;
import com.mhj.ranking.repository.TorneioRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TorneioService {

	@Autowired
	private TorneioRepository repository;

	@Autowired
	private TorneioMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Torneio> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Torneio> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public TorneioModel save(TorneioModel entityModel) {
		Torneio entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(Torneio current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Torneio> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<TorneioModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		List<TorneioModel> models = new ArrayList<>();

		Page<Torneio> findAll = repository.findAll(pageable);

		findAll.stream().forEach(p -> {
			TorneioModel model = mapper.toModel(p);
			models.add(model);
		});

		PageImpl<TorneioModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());

		return pageImpl;
	}

	public List<TorneioModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {

		PageImpl<TorneioModel> pageImpl = (PageImpl<TorneioModel>) findAll(pageNo, pageSize, sortBy, sortDir);

		return pageImpl.getContent();
	}

	public Optional<TorneioModel> findById(Long key) {
		Optional<Torneio> entityOptional = repository.findById(key.longValue());
		TorneioModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public TorneioModel update(long id, TorneioModel entityModel) throws NotFoundException {
		Optional<Torneio> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			Torneio entity = entityOptional.get();
//			entity.setNome(entityModel.getNome());
			entity = repository.save(entity);
			return mapper.toModel(entity);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public List<TorneioModel> findByNome(String nome) {
		Torneio entity = new Torneio();
//		entity.setNome(nome);

		Example<Torneio> example = Example.of(entity);

		List<TorneioModel> models = new ArrayList<>();

		repository.findAll(example).stream().forEach(p -> {
			TorneioModel model = mapper.toModel(p);
			models.add(model);
		});

		return models;
	}

}
