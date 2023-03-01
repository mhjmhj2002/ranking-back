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
import com.mhj.ranking.model.JogoModel;
import com.mhj.ranking.service.JogoService;
import com.mhj.ranking.util.AppConstants;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/jogo")
//@Slf4j
public class JogoController {

	@Autowired
	private JogoService service;

	@ApiOperation(value = "Retorna uma lista de jogos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de jogos"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/jogos")
	@ResponseBody
	public ResponseEntity<Page<JogoModel>> getAll(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			Page<JogoModel> jogos = service.findAll(pageNo, pageSize, sortBy, sortDir);

			if (jogos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(jogos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@ApiOperation(value = "Retorna uma lista de jogos")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Retorna a lista de jogos"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping("/jogos/last")
	@ResponseBody
	public ResponseEntity<List<JogoModel>> getLast(
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
			) {
		try {

			List<JogoModel> jogos = service.findLast(pageNo, pageSize, sortBy, sortDir);

			if (jogos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(jogos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/jogos/{id}")
	public ResponseEntity<JogoModel> getJogoById(@PathVariable("id") Long id) {
		Optional<JogoModel> jogoData = service.findById(id);

		if (jogoData.isPresent()) {
			return new ResponseEntity<>(jogoData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/jogos")
	public ResponseEntity<JogoModel> save(@RequestBody JogoModel jogo) {
		try {
			JogoModel jogoModel = service.save(jogo);
			return new ResponseEntity<>(jogoModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/jogos/{id}")
	public ResponseEntity<JogoModel> updateJogo(@PathVariable("id") long id, @RequestBody JogoModel jogoModel) {
		JogoModel response;
		try {
			response = service.update(id, jogoModel);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/jogos/{id}")
	public ResponseEntity<HttpStatus> deleteJogo(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/*@GetMapping("/jogos")
	@ResponseBody
	public ResponseEntity<List<JogoModel>> findByNome(@RequestParam("nome") String nome) {
		try {

			List<JogoModel> jogos = service.findByNome(nome);

			if (jogos.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(jogos, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}*/

	/*
	 * @DeleteMapping("/jogos") public ResponseEntity<HttpStatus> deleteAllJogos()
	 * { try { service.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 * 
	 * @GetMapping("/jogos/published") public ResponseEntity<List<JogoModel>>
	 * findByPublished() { try { List<JogoModel> jogos =
	 * service.findByPublished(true);
	 * 
	 * if (jogos.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT); }
	 * return new ResponseEntity<>(jogos, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
