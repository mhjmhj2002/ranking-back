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
import com.mhj.ranking.model.TorneioModel;
import com.mhj.ranking.service.TorneioService;
import com.mhj.ranking.util.AppConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/torneio")
//@Slf4j
public class TorneioController {

	@Autowired
	private TorneioService service;

	@ApiOperation(value = "Retorna uma lista de torneios")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de torneios"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/torneios")
	@ResponseBody
	public ResponseEntity<Page<TorneioModel>> getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			Page<TorneioModel> torneios = service.findAll(pageNo, pageSize, sortBy, sortDir);

			if (torneios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(torneios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retorna uma lista de torneios")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de torneios"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/torneios/last")
	@ResponseBody
	public ResponseEntity<List<TorneioModel>> getLast(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			List<TorneioModel> torneios = service.findLast(pageNo, pageSize, sortBy, sortDir);

			if (torneios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(torneios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/torneios/{id}")
	public ResponseEntity<TorneioModel> getTorneioById(@PathVariable("id") Long id) {
		Optional<TorneioModel> torneioData = service.findById(id);

		if (torneioData.isPresent()) {
			return new ResponseEntity<>(torneioData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/torneios")
	public ResponseEntity<TorneioModel> save(@RequestBody TorneioModel torneio) {
		try {
			TorneioModel torneioModel = service.save(torneio);
			return new ResponseEntity<>(torneioModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/torneios/{id}")
	public ResponseEntity<TorneioModel> updateTorneio(@PathVariable("id") long id, @RequestBody TorneioModel torneioModel) {
		TorneioModel response;
		try {
			response = service.update(id, torneioModel);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/torneios/{id}")
	public ResponseEntity<HttpStatus> deleteTorneio(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@GetMapping("/torneios")
	@ResponseBody
	public ResponseEntity<List<TorneioModel>> findByNome(@RequestParam("nome") String nome) {
		try {

			List<TorneioModel> torneios = service.findByNome(nome);

			if (torneios.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(torneios, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	/*
	 * @DeleteMapping("/torneios") public ResponseEntity<HttpStatus> deleteAllTorneios()
	 * { try { service.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 * 
	 * @GetMapping("/torneios/published") public ResponseEntity<List<TorneioModel>>
	 * findByPublished() { try { List<TorneioModel> torneios =
	 * service.findByPublished(true);
	 * 
	 * if (torneios.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * return new ResponseEntity<>(torneios, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
