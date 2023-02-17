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

	public GrupoModel save(GrupoModel paisModel) {
		Grupo pais = mapper.toEntity(paisModel);
		pais = repository.saveAndFlush(pais);
		return mapper.toModel(pais);
	}

	public void edit(Grupo current) {
		repository.saveAndFlush(current);
	}

	public void deleteById(Long id) {
		Optional<Grupo> paisOptional = repository.findById(id);
		repository.delete(paisOptional.get());

	}

	public Page<GrupoModel> findAll(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		
		List<GrupoModel> paisesModel = new ArrayList<>();
		
		Page<Grupo> findAll = repository.findAll(pageable);
		
		findAll.stream().forEach(p -> {
			GrupoModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		PageImpl<GrupoModel> pageImpl = new PageImpl<>(paisesModel, pageable, findAll.getTotalElements());
		
		return pageImpl;
	}

	public List<GrupoModel> findLast(int pageNo, int pageSize, String sortBy, String sortDir) {
		
		PageImpl<GrupoModel> pageImpl = (PageImpl<GrupoModel>) findAll(pageNo, pageSize, sortBy, sortDir);
		
		return pageImpl.getContent();
	}

	public Optional<GrupoModel> findById(Long key) {
		Optional<Grupo> paisOptional = repository.findById(key.longValue());
		GrupoModel model = mapper.toModel(paisOptional.get());
		return Optional.of(model);
	}

	public GrupoModel update(long id, GrupoModel paisModel) throws NotFoundException {
		Optional<Grupo> paisOptional = repository.findById(id);

		if (paisOptional.isPresent()) {
			Grupo pais = paisOptional.get();
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

	public List<GrupoModel> findByNome(String nome) {
		Grupo pais = new Grupo();
		pais.setNome(nome);
		
		Example<Grupo> example = Example.of(pais);
		
		List<GrupoModel> paisesModel = new ArrayList<>();
		
		repository.findAll(example).stream().forEach(p -> {
			GrupoModel model = mapper.toModel(p);
			paisesModel.add(model);
		});
		
		return paisesModel;
	}

}
