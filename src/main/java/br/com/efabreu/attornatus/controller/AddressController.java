package br.com.efabreu.attornatus.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.efabreu.attornatus.dto.AddressDTO;
import br.com.efabreu.attornatus.exceptions.PersonNotFoundExcepion;
import br.com.efabreu.attornatus.model.Address;
import br.com.efabreu.attornatus.service.AddressService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/endereco")
public class AddressController {
	
	@Autowired 
	private AddressService addressService;
	
	@GetMapping (value="/{idPerson}")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Endereço(s) da pessoa encontrado(s).", response = Address.class),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Pessoa não encontrada.", response = PersonNotFoundExcepion.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public List<Address> buscarEnderecoPorIdPessoa(@PathVariable Long idPerson) {
		return addressService.getAddressById(idPerson);
	}
	
	@GetMapping (value = "/buscarEnderecoPrincipalPorId")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Endereço principal da pessoa encontrado.", response = Address.class),
		@ApiResponse(code = HttpServletResponse.SC_NOT_FOUND, message = "Pessoa não encontrada.", response = PersonNotFoundExcepion.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public Address buscarEnderecoPrincipal(@RequestParam Long idPerson) {
		return addressService.getMainAddressById(idPerson);
	}
	
	@PostMapping (value = "/salvarEndereco")
	@ApiResponses({
		@ApiResponse(code = HttpServletResponse.SC_CREATED, message = "Endereço cadastrado.", response = Long.class),
		@ApiResponse(code = HttpServletResponse.SC_BAD_REQUEST, message = "Campos de endereço invalidos.", response = Address.class),
		@ApiResponse(code = HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message = "Exceção não tratada.")
	})
	public ResponseEntity<Object> salvarEndereco(@RequestBody AddressDTO addressDTO, @RequestParam Long idPerson, @RequestParam Boolean mainAddress) {
		addressDTO.setMainAddress(mainAddress);
		List<Address> listAddresses = addressService.checkIfMainAddress(idPerson, addressDTO);
		return addressService.newAddress(listAddresses, idPerson);
	}
	
}
