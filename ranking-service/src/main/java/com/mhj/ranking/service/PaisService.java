package com.mhj.ranking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mhj.ranking.entity.Pais;
import com.mhj.ranking.mapper.PaisMapper;
import com.mhj.ranking.model.PaisModel;
import com.mhj.ranking.repository.PaisRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaisService {

	@Autowired
	private PaisRepository repository;

	@Autowired
	private PaisMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Pais> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Pais> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public void create(Pais current) {
		repository.saveAndFlush(current);
	}

	public void edit(Pais current) {
		repository.saveAndFlush(current);
	}

	public void remove(Pais current) {
		repository.delete(current);

	}

	public List<Pais> findAll() {
		return repository.findAll();
	}

	public PaisModel findById(Long key) {
		Optional<Pais> paisOptional = repository.findById(key.longValue());
		return mapper.toModel(paisOptional.get());
	}

}
