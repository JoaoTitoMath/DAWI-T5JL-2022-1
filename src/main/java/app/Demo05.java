package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo05 {
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//Acciones 
		Usuario u = em.find(Usuario.class,55);
		
		if(u != null) {
			em.getTransaction().begin();
			em.remove(u);
			em.getTransaction().commit();
			System.out.println("Se elimino correctamente el usuario");
		}else { 
			System.out.println("Codigo no existe!!");
		
		em.close();
	}
}
}
