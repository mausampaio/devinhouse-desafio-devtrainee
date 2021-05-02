package br.com.izy.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.izy.dto.ClienteDTO;
import br.com.izy.entity.Cliente;
import br.com.izy.service.ClienteService;

@CrossOrigin
@RestController
@RequestMapping(value = "/v1" + "/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService service;
	
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public List<ClienteDTO> findAll(@RequestParam(required = false) String busca) {
		List<Cliente> clientes = service.findAll(busca);
		
		List<ClienteDTO> result = new ArrayList<ClienteDTO>();
		
		for (Cliente cliente : clientes) {
			result.add(new ClienteDTO(cliente));
		}
		
		return result;
	}
	
	@GetMapping(value = "/{cpf}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.OK)
	public ClienteDTO find(@Valid @PathVariable String cpf) {
		Cliente cliente = service.find(null, cpf);
		
		return new ClienteDTO(cliente);
	}
	
	@PostMapping(produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.CREATED)
	public ClienteDTO create(@RequestBody ClienteDTO body) {
		Cliente cliente = new Cliente();
		
		cliente = cliente.converteClienteDTO(body);
		
		Cliente result = service.create(cliente);
		
		return new ClienteDTO(result);
	}
	
	@PutMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void update(@PathVariable Long id, @RequestBody ClienteDTO body) {
		Cliente cliente = new Cliente();
		
		cliente = cliente.converteClienteDTO(body);
		
		
		service.update(id, cliente);
	}
	
	@DeleteMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}
