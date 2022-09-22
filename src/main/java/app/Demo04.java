package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo04 {
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//Acciones 
		Usuario u = em.find(Usuario.class,20);
		
		if(u != null)
			System.out.println(u);
		else 
			System.out.println("Codigo no existe!!");
		
		em.close();
	}
}
