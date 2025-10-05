package com.whernandez.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.whernandez.domain.Client;
import com.whernandez.exceptions.BadRequestException;
import com.whernandez.exceptions.ResourceNotFoundException;

@RestController
@RequestMapping("/clients")
public class ClientController {
	
	private List<Client> clients = new ArrayList<>(Arrays.asList(
		new Client("arm", "1234", "Armtrong"),
		new Client("ald", "1234", "Aldrin"),
		new Client("col", "1234", "Collins")
	));
	
	@GetMapping
	public ResponseEntity<?> getClients() {
		return ResponseEntity.ok(clients);
	}
	
	@GetMapping("/{userName}")
	public ResponseEntity<?> getClientByUsername(@PathVariable String userName) {
		
		if(userName.length() != 3) {
			throw new BadRequestException("Username should container 3 characters");
		}
		
		return clients.stream()
				.filter(client -> client.getUsername().equalsIgnoreCase(userName))
				.findFirst()
				.map(ResponseEntity::ok)
				.orElseThrow(() -> new ResourceNotFoundException("Client " + userName + " no found"));
		
	}
	
	@PostMapping
	public ResponseEntity<?> addClient(@RequestBody Client client) {
		clients.add(client);
		
		// Get url de recurso
		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{userName}")
				.buildAndExpand(client.getUsername())
				.toUri();
		
		return ResponseEntity.created(location).body(client);
	}
	
	@PutMapping
	public ResponseEntity<?> updateClient(@RequestBody Client client) {
		Client clientByUsername = clients.stream()
				.filter(cli -> cli.getUsername().equalsIgnoreCase(cli.getUsername()))
				.findFirst().orElseThrow();
		
		clientByUsername.setPassword(client.getPassword());
		clientByUsername.setName(client.getName());
		
		return ResponseEntity.ok(clientByUsername);
	}
	
	@DeleteMapping("/{userName}")
	public ResponseEntity<?> deleteClient(@PathVariable String userName) {
		Client getClientByUsername = clients.stream()
				.filter(cli -> cli.getUsername().equalsIgnoreCase(userName))
				.findFirst().orElseThrow();
		
		clients.remove(getClientByUsername);
		
		return ResponseEntity.noContent().build();
	}

}
