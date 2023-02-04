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
import com.mhj.ranking.model.PaisModel;
import com.mhj.ranking.service.PaisService;
import com.mhj.ranking.util.AppConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/pais")
//@Slf4j
public class PaisController {

	@Autowired
	private PaisService service;

	@ApiOperation(value = "Retorna uma lista de paises")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de paises"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/paises")
	@ResponseBody
	public ResponseEntity<Page<PaisModel>> getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			Page<PaisModel> paises = service.findAll(pageNo, pageSize, sortBy, sortDir);

			if (paises.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(paises, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/paises/{id}")
	public ResponseEntity<PaisModel> getPaisById(@PathVariable("id") Long id) {
		Optional<PaisModel> paisData = service.findById(id);

		if (paisData.isPresent()) {
			return new ResponseEntity<>(paisData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/paises")
	public ResponseEntity<PaisModel> save(@RequestBody PaisModel pais) {
		try {
			PaisModel paisModel = service.save(pais);
			return new ResponseEntity<>(paisModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/paises/{id}")
	public ResponseEntity<PaisModel> updatePais(@PathVariable("id") long id, @RequestBody PaisModel paisModel) {
		PaisModel response;
		try {
			response = service.update(id, paisModel);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/paises/{id}")
	public ResponseEntity<HttpStatus> deletePais(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@GetMapping("/paises")
	@ResponseBody
	public ResponseEntity<List<PaisModel>> findByNome(@RequestParam("nome") String nome) {
		try {

			List<PaisModel> paises = service.findByNome(nome);

			if (paises.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(paises, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	/*
	 * @DeleteMapping("/paises") public ResponseEntity<HttpStatus> deleteAllPaises()
	 * { try { service.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 * 
	 * @GetMapping("/paises/published") public ResponseEntity<List<PaisModel>>
	 * findByPublished() { try { List<PaisModel> paises =
	 * service.findByPublished(true);
	 * 
	 * if (paises.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * return new ResponseEntity<>(paises, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
