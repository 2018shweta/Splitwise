package com.controller.publicApi;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.entity.LoginEntity;
import com.entity.ResponseBean;
import com.entity.RoleEntity;
import com.entity.UserEntity;
import com.repository.RoleRepository;
import com.repository.UserRepository;
import com.service.TokenService;

@RestController
@RequestMapping("/publicApi")
public class UserController {

	
	@Autowired
	UserRepository userRepo;
	@Autowired
	TokenService tokenService;
	@Autowired
	RoleRepository roleRepo;
	@Autowired
	BCryptPasswordEncoder bCp;
	
	@PostMapping("/signUp")
	public ResponseEntity<?> adduser(@RequestBody UserEntity user)
	{
		
		UserEntity users=userRepo.findByEmail(user.getEmail());
		if(users==null)
		{
		  Optional<RoleEntity> role= roleRepo.findById(4);
		user.setRole(role.get());
		try {
			user.setPassword(bCp.encode(user.getPassword()));
			userRepo.save(user);	
		}catch(Exception e)
		{
			e.printStackTrace();
			return ResponseEntity.unprocessableEntity().build();
		}
		return ResponseEntity.ok(user);
		}else {
			return ResponseEntity.ok("allready available");
		}
	}
	
	@GetMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginEntity login)
	{UserEntity users=userRepo.findByEmail(login.getEmail());
		if(users==null || !bCp.matches(login.getPassword(), users.getPassword()))
		{
			
			ResponseBean<LoginEntity> res = new ResponseBean<LoginEntity>();
			
			res.setData(login);
			res.setMsg("Invalid Credentials");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
		}
		else {
			ResponseBean<UserEntity> res = new ResponseBean<>();
			res.setData(users);
			res.setMsg("Login done...");
			
			String authTokens = tokenService.generateToken(16);
			users.setAuthToken(authTokens);
			userRepo.save(users);
			return ResponseEntity.ok(res);
			
		}
	}
	 
	@DeleteMapping("/del/{userId}")
	public ResponseEntity<?> delUser(@RequestBody @PathVariable("userId") Integer userId)
	{
		//List<UserBean> user=userRepo.findAll();
		UserEntity u2=userRepo.findByUserId(userId);
		ResponseBean<UserEntity> res=new ResponseBean<>();
		res.setData(u2);
		userRepo.deleteById(userId);
		res.setMsg("deleted user");
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(res);
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> updateUser(@RequestBody UserEntity user)
	{Optional<RoleEntity> role= roleRepo.findById(4);
	user.setRole(role.get());
		userRepo.save(user);
		return ResponseEntity.ok(user);
	}
	
	@GetMapping("/allUser")
public ResponseEntity<?> allUser( UserEntity user){
		//ResponseBean<UserEntity> res=new ResponseBean<>();
		//res.setData()
		return ResponseEntity.ok( userRepo.findAll());
	}	
	
}
