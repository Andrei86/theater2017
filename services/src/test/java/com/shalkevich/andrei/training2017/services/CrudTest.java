package com.shalkevich.andrei.training2017.services;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
@Transactional

public abstract class CrudTest<T extends IGenericService<T>> extends AbstractTest{
	
	 protected T testObject;
     
     /**
      * Tests the CRUD(create, read, update, delete) operations.First, i
      */
     @Test
     public void testCRUD() {
         insert(testObject);
         T selected = select(testObject.getId());
         compare(selected, testObject);
  
         update(testObject);
         selected = select(testObject.getId());
         compare(selected, testObject);
  
         delete(testObject);
         selected = select(testObject.getId());
         Assert.isNull(selected);
     }
  
   
     public abstract void delete(T testObject);
     
     public abstract void update(T testObject);
  
    
     public abstract T select(Integer id);
  
     public abstract void insert(T testObject);
  
    
     //public abstract void changeTestObject(T testObject);
  
   
     public void compare(T selected, T testObject) {
         assertNotNull("selected is null", selected);
         assertTrue(selected.equals(testObject));
     }
  
  
     public T getTestObject() {
         return testObject;
     }
  
  
     public void setTestObject(T testObject) {
         this.testObject = testObject;
     }

}
