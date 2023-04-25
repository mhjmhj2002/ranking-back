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
import com.mhj.ranking.entity.Jogo;
import com.mhj.ranking.mapper.JogoMapper;
import com.mhj.ranking.model.JogoModel;
import com.mhj.ranking.repository.JogoRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class JogoService {

	@Autowired
	private JogoRepository repository;

	@Autowired
	private JogoMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Jogo> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Jogo> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public JogoModel save(JogoModel entityModel) {
		Jogo entity = mapper.toEntity(entityModel);
		entity = repository.saveAndFlush(entity);
		return mapper.toModel(entity);
	}

	public void edit(Jogo current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Jogo> entityOptional = repository.findById(id);
		repository.delete(entityOptional.get());

	}

	public Page<JogoModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

		List<JogoModel> models = new ArrayList<>();

		Page<Jogo> findAll = repository.findAll(pageable);

		findAll.stream().forEach(p -> {
			JogoModel model = mapper.toModel(p);
			models.add(model);
		});

		PageImpl<JogoModel> pageImpl = new PageImpl<>(models, pageable, findAll.getTotalElements());

		return pageImpl;
	}

	public List<JogoModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {

		PageImpl<JogoModel> pageImpl = (PageImpl<JogoModel>) findAll(pageNo, pageSize, sortBy, sortDir);

		return pageImpl.getContent();
	}

	public Optional<JogoModel> findById(Long key) {
		Optional<Jogo> entityOptional = repository.findById(key.longValue());
		JogoModel model = mapper.toModel(entityOptional.get());
		return Optional.of(model);
	}

	public JogoModel update(long id, JogoModel entityModel) throws NotFoundException {
		Optional<Jogo> entityOptional = repository.findById(id);

		if (entityOptional.isPresent()) {
			Jogo entity = entityOptional.get();
//			entity.setNome(entityModel.getNome());
			entity = repository.save(entity);
			return mapper.toModel(entity);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public List<JogoModel> findByNome(String nome) {
		Jogo entity = new Jogo();
//		entity.setNome(nome);

		Example<Jogo> example = Example.of(entity);

		List<JogoModel> models = new ArrayList<>();

		repository.findAll(example).stream().forEach(p -> {
			JogoModel model = mapper.toModel(p);
			models.add(model);
		});

		return models;
	}

}
