package com.billdiary.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.billdiary.entities.Product;
import com.billdiary.model.User;
@Repository
public class ProductDAO extends AbstractJpaDAO< Product >{

	@PersistenceContext
	EntityManager entityManager;
	
	public ProductDAO()
	{
		setClazz(Product.class );
	}
	public void fetchProducts()
	{
		//Query q=entityManager.createNativeQuery("select * from product");
		//setClazz(Product.class);
		
		List<Product> p=findAll();
		for(Product w:p)
		{
			System.out.println(w.getName()+" "+w.getDescription());
		}
		//Object[] o=(Object[]) q.getSingleResult();
		
		
	}
	
}
