package com.billdiary.DAOUtility;

import com.billdiary.model.User;
import com.billdiary.model.User1;

public class Mapper {
	
	
	
	public static User getUserEntity(User1 user)
	{
		User userEntity=new User();
		if(null!=user)
		{
			userEntity.setId(user.getId());
			userEntity.setUserName(user.getUserName());
			userEntity.setPassword(user.getPassword());
			userEntity.setRole(user.getRole());
		}
		
		return userEntity;
	}

}
