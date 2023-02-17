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

	public FaseModel save(FaseModel paisModel) {
		Fase pais = mapper.toEntity(paisModel);
		pais = repository.saveAndFlush(pais);
		return mapper.toModel(pais);
	}

	public void edit(Fase current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Fase> paisOptional = repository.findById(id);
		repository.delete(paisOptional.get());

	}

	public Page<FaseModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		List<FaseModel> paisesModel = new ArrayList<>();
		
		Page<Fase> findAll = repository.findAll(pageable);
		
		findAll.stream().forEach(p -> {
			FaseModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		PageImpl<FaseModel> pageImpl = new PageImpl<>(paisesModel, pageable, findAll.getTotalElements());
		
		return pageImpl;
	}

	public List<FaseModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		PageImpl<FaseModel> pageImpl = (PageImpl<FaseModel>) findAll(pageNo, pageSize, sortBy, sortDir);
		
		return pageImpl.getContent();
	}

	public Optional<FaseModel> findById(Long key) {
		Optional<Fase> paisOptional = repository.findById(key.longValue());
		FaseModel model = mapper.toModel(paisOptional.get());
		return Optional.of(model);
	}

	public FaseModel update(long id, FaseModel paisModel) throws NotFoundException {
		Optional<Fase> paisOptional = repository.findById(id);

		if (paisOptional.isPresent()) {
			Fase pais = paisOptional.get();
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

	public List<FaseModel> findByNome(String nome) {
		Fase pais = new Fase();
		pais.setNome(nome);
		
		Example<Fase> example = Example.of(pais);
		
		List<FaseModel> paisesModel = new ArrayList<>();
		
		repository.findAll(example).stream().forEach(p -> {
			FaseModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		return paisesModel;
	}

}
