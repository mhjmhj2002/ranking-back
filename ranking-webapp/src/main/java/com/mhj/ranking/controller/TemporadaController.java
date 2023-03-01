package com.mhj.ranking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhj.ranking.config.NotFoundException;
import com.mhj.ranking.model.TemporadaModel;
import com.mhj.ranking.service.TemporadaService;
import com.mhj.ranking.util.AppConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/temporada")
//@Slf4j
public class TemporadaController {

	@Autowired
	private TemporadaService service;

	@ApiOperation(value = "Retorna uma lista de temporadas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de temporadas"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/temporadas")
	@ResponseBody
	public ResponseEntity<Page<TemporadaModel>> getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			Page<TemporadaModel> temporadas = service.findAll(pageNo, pageSize, sortBy, sortDir);

			if (temporadas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(temporadas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retorna uma lista de temporadas")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de temporadas"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/temporadas/last")
	@ResponseBody
	public ResponseEntity<List<TemporadaModel>> getLast(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			List<TemporadaModel> temporadas = service.findLast(pageNo, pageSize, sortBy, sortDir);

			if (temporadas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(temporadas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/temporadas/{id}")
	public ResponseEntity<TemporadaModel> getTemporadaById(@PathVariable("id") Long id) {
		Optional<TemporadaModel> temporadaData = service.findById(id);

		if (temporadaData.isPresent()) {
			return new ResponseEntity<>(temporadaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/temporadas")
	public ResponseEntity<TemporadaModel> save(@RequestBody TemporadaModel temporada) {
		try {
			TemporadaModel temporadaModel = service.save(temporada);
			return new ResponseEntity<>(temporadaModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/temporadas/{id}")
	public ResponseEntity<TemporadaModel> updateTemporada(@PathVariable("id") long id, @RequestBody TemporadaModel temporadaModel) {
		TemporadaModel response;
		try {
			response = service.update(id, temporadaModel);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/temporadas/{id}")
	public ResponseEntity<HttpStatus> deleteTemporada(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@GetMapping("/temporadas")
	@ResponseBody
	public ResponseEntity<List<TemporadaModel>> findByNome(@RequestParam("nome") String nome) {
		try {

			List<TemporadaModel> temporadas = service.findByNome(nome);

			if (temporadas.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(temporadas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	/*
	 * @DeleteMapping("/temporadas") public ResponseEntity<HttpStatus> deleteAllTemporadas()
	 * { try { service.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 * 
	 * @GetMapping("/temporadas/published") public ResponseEntity<List<TemporadaModel>>
	 * findByPublished() { try { List<TemporadaModel> temporadas =
	 * service.findByPublished(true);
	 * 
	 * if (temporadas.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * return new ResponseEntity<>(temporadas, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
