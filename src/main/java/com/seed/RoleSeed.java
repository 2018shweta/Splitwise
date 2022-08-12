package com.seed;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;


import com.entity.RoleEntity;
import com.repository.RoleRepository;

public class RoleSeed {

	
	@Autowired
	RoleRepository roleRepo;
	@PostConstruct
	void roleCreate()
	{
		//RoleBean role=new RoleBean();
		RoleEntity role=roleRepo.findByRoleName("admin");
		if(role==null)
		{
			RoleEntity role1=new RoleEntity();
			role1.setRoleType("user");
			roleRepo.save(role1);
			RoleEntity role2=new RoleEntity();
			role1.setRoleType("admin");
			roleRepo.save(role2);
			
		}
		else {
			System.out.println("Allready added");
		}
	}
}
