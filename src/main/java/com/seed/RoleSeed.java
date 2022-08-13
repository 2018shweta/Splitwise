package com.seed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.entity.RoleEntity;
import com.repository.RoleRepository;
@Component
public class RoleSeed {

	
	@Autowired
	RoleRepository roleRepo;
	@PostConstruct
	void roleCreate()
	{
		//RoleBean role=new RoleBean();
		RoleEntity role=roleRepo.findByRoleType("admin");
		RoleEntity roleC=roleRepo.findByRoleType("customer");
		if(role==null || roleC==null)
		{
			RoleEntity role1=new RoleEntity();
			role1.setRoleType("customer");
			roleRepo.save(role1);
			RoleEntity role2=new RoleEntity();
			role2.setRoleType("admin");
			roleRepo.save(role2);
			System.out.println("new admin and customer ADDED");
			
		}
		else {
			System.out.println("Allready added");
		}
	}
}
