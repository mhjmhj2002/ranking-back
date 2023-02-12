package com.mhj.ranking.controller;

import java.util.ArrayList;
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
@RequestMapping("/api/employee")
//@Slf4j
public class EmployeController {

	@Autowired
	private PaisService service;

	@ApiOperation(value = "Retorna uma lista de employes")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de employes"),
			@ApiResponse(code = 403, message = "Você não tem permissão para acessar este recurso"),
			@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping("/getAllEmployee")
	@ResponseBody
	public ResponseEntity<List<EmployeeDto>> getAll(
			@RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir) {
		try {

//			Page<PaisModel> employes = service.findAll(pageNo, pageSize, sortBy, sortDir);

			List<EmployeeDto> employees = new ArrayList<>();

			EmployeeDto dto = EmployeeDto.builder()
					.id(1L)
					.address("teste")
					.email("teste")
					.firstName("teste")
					.lastName("teste")
					.phone("teste").build();
			employees.add(dto);

			dto = EmployeeDto.builder()
					.id(2L)
					.address("teste2")
					.email("teste2")
					.firstName("teste2")
					.lastName("teste2")
					.phone("teste2").build();
			employees.add(dto);

			if (employees.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/getEmployeeDetailById/{id}")
	public ResponseEntity<EmployeeDto> getPaisById(@PathVariable("id") Long id) {
		EmployeeDto dto = EmployeeDto.builder()
				.id(1L)
				.address("teste")
				.email("teste")
				.firstName("teste")
				.lastName("teste")
				.phone("teste").build();
		
//		Optional<PaisModel> paisData = service.findById(id);

//		if (paisData.isPresent()) {
			return new ResponseEntity<>(dto, HttpStatus.OK);
//		} else {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
	}

	@PostMapping("/employes")
	public ResponseEntity<PaisModel> save(@RequestBody PaisModel pais) {
		try {
			PaisModel paisModel = service.save(pais);
			return new ResponseEntity<>(paisModel, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/employes/{id}")
	public ResponseEntity<PaisModel> updatePais(@PathVariable("id") long id, @RequestBody PaisModel paisModel) {
		PaisModel response;
		try {
			response = service.update(id, paisModel);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (NotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employes/{id}")
	public ResponseEntity<HttpStatus> deletePais(@PathVariable("id") long id) {
		try {
			service.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/*
	 * @GetMapping("/employes")
	 * 
	 * @ResponseBody public ResponseEntity<List<PaisModel>>
	 * findByNome(@RequestParam("nome") String nome) { try {
	 * 
	 * List<PaisModel> employes = service.findByNome(nome);
	 * 
	 * if (employes.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 * }
	 * 
	 * return new ResponseEntity<>(employes, HttpStatus.OK); } catch (Exception e) {
	 * return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

	/*
	 * @DeleteMapping("/employes") public ResponseEntity<HttpStatus>
	 * deleteAllPaises() { try { service.deleteAll(); return new
	 * ResponseEntity<>(HttpStatus.NO_CONTENT); } catch (Exception e) { return new
	 * ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); }
	 * 
	 * }
	 * 
	 * @GetMapping("/employes/published") public ResponseEntity<List<PaisModel>>
	 * findByPublished() { try { List<PaisModel> employes =
	 * service.findByPublished(true);
	 * 
	 * if (employes.isEmpty()) { return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	 * } return new ResponseEntity<>(employes, HttpStatus.OK); } catch (Exception e)
	 * { return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */

}
