package com.mhj.ranking.crud.service;

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

	public PaisModel save(PaisModel paisModel) {
		Pais pais = mapper.toEntity(paisModel);
		pais = repository.saveAndFlush(pais);
		return mapper.toModel(pais);
	}

	public void edit(Pais current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Pais> paisOptional = repository.findById(id);
		repository.delete(paisOptional.get());

	}

	public Page<PaisModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		List<PaisModel> paisesModel = new ArrayList<>();
		
		Page<Pais> findAll = repository.findAll(pageable);
		
		findAll.stream().forEach(p -> {
			PaisModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		PageImpl<PaisModel> pageImpl = new PageImpl<>(paisesModel, pageable, findAll.getTotalElements());
		
		return pageImpl;
	}

	public List<PaisModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		PageImpl<PaisModel> pageImpl = (PageImpl<PaisModel>) findAll(pageNo, pageSize, sortBy, sortDir);
		
		return pageImpl.getContent();
	}

	public Optional<PaisModel> findById(Long key) {
		Optional<Pais> paisOptional = repository.findById(key.longValue());
		PaisModel model = mapper.toModel(paisOptional.get());
		return Optional.of(model);
	}

	public PaisModel update(long id, PaisModel paisModel) throws NotFoundException {
		Optional<Pais> paisOptional = repository.findById(id);

		if (paisOptional.isPresent()) {
			Pais pais = paisOptional.get();
			pais.setNome(paisModel.getNome());
			pais = repository.save(pais);
			return mapper.toModel(pais);
		} else {
			throw new NotFoundException("Nao encontrado");
		}

	}

	public List<PaisModel> findByNome(String nome) {
		Pais pais = new Pais();
		pais.setNome(nome);
		
		Example<Pais> example = Example.of(pais);
		
		List<PaisModel> paisesModel = new ArrayList<>();
		
		repository.findAll(example).stream().forEach(p -> {
			PaisModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		return paisesModel;
	}

}
