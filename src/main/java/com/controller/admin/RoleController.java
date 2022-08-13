package com.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RoleEntity;
import com.repository.RoleRepository;

@RestController
@RequestMapping("/admin")
public class RoleController {

	@Autowired
	RoleRepository roleRepo;
	
	
	
	
	@PostMapping("/addRole")
	public ResponseEntity<?> addRole(@RequestBody RoleEntity role)
	{
		roleRepo.save(role);
		return ResponseEntity.ok(role);
	}
	
	
	
	
}
