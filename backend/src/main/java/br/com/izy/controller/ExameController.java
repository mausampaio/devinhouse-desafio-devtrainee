package br.com.izy.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.izy.dto.ExameDTO;
import br.com.izy.entity.Exame;
import br.com.izy.service.ExameService;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1" + "/exames")
public class ExameController {
	
	@Autowired
	private ExameService service;
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<ExameDTO> findAll() {
		List<Exame> exames = service.findAll();
		
		List<ExameDTO> result = new ArrayList<ExameDTO>();
		
		for (Exame exame : exames) {
			result.add(new ExameDTO(exame));
		}
		
		return result;
	}
	
	@GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ExameDTO find(@PathVariable Long id) {
		Exame exame = service.find(id);
		
		return new ExameDTO(exame);
	}
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ExameDTO create(@RequestBody ExameDTO body) {
		Exame exame = new Exame();
		
		exame = exame.converteExameDTO(body);
		
		Exame result = service.create(exame);
		
		return new ExameDTO(result);
	}
	
	@PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody ExameDTO body) {
		Exame exame = new Exame();
		
		exame = exame.converteExameDTO(body);
		
		service.update(id, exame);
	}
	
	@DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
