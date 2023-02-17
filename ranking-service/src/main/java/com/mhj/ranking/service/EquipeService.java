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
import com.mhj.ranking.entity.Equipe;
import com.mhj.ranking.mapper.EquipeMapper;
import com.mhj.ranking.model.EquipeModel;
import com.mhj.ranking.repository.EquipeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EquipeService {

	@Autowired
	private EquipeRepository repository;

	@Autowired
	private EquipeMapper mapper;

	public int count() {
		Long count = repository.count();
		log.info("count: {}", count);
		return count.intValue();
	}

	public List<Equipe> findRange(int pageNo, int pageSize) {
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<Equipe> pagedResult = repository.findAll(paging);
		log.info("findRange: {}", pagedResult.getContent());
//		return pagedResult.toList();
		return repository.findAll();
	}

	public EquipeModel save(EquipeModel paisModel) {
		Equipe pais = mapper.toEntity(paisModel);
		pais = repository.saveAndFlush(pais);
		return mapper.toModel(pais);
	}

	public void edit(Equipe current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Equipe> paisOptional = repository.findById(id);
		repository.delete(paisOptional.get());

	}

	public Page<EquipeModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		List<EquipeModel> paisesModel = new ArrayList<>();
		
		Page<Equipe> findAll = repository.findAll(pageable);
		
		findAll.stream().forEach(p -> {
			EquipeModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		PageImpl<EquipeModel> pageImpl = new PageImpl<>(paisesModel, pageable, findAll.getTotalElements());
		
		return pageImpl;
	}

	public List<EquipeModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		PageImpl<EquipeModel> pageImpl = (PageImpl<EquipeModel>) findAll(pageNo, pageSize, sortBy, sortDir);
		
		return pageImpl.getContent();
	}

	public Optional<EquipeModel> findById(Long key) {
		Optional<Equipe> paisOptional = repository.findById(key.longValue());
		EquipeModel model = mapper.toModel(paisOptional.get());
		return Optional.of(model);
	}

	public EquipeModel update(long id, EquipeModel paisModel) throws NotFoundException {
		Optional<Equipe> paisOptional = repository.findById(id);

		if (paisOptional.isPresent()) {
			Equipe pais = paisOptional.get();
//			pais.setNome(paisModel.getNome());
			pais = repository.save(pais);
			return mapper.toModel(pais);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public void teste(final Pageable pageable) {
		Page<Object> paises = repository.findAll(pageable).map(mapper::toModel);

	}

	public List<EquipeModel> findByNome(String nome) {
		Equipe pais = new Equipe();
		pais.setNome(nome);
		
		Example<Equipe> example = Example.of(pais);
		
		List<EquipeModel> paisesModel = new ArrayList<>();
		
		repository.findAll(example).stream().forEach(p -> {
			EquipeModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		return paisesModel;
	}

}
