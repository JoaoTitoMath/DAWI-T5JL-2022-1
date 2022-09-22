package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo03 {
	public static void Main(String[] args) {
		// TODO Auto-generated method stub
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		//manejador de entidad
		EntityManager em = fabrica.createEntityManager();
				
		//Empezar los procesos --> registro , actualizacion , eliminacion
		em.getTransaction().begin();
				
		//Acciones
		//Insert Into	
		Usuario u = new Usuario();
		u.setCodigo(1);
		
		em.remove(u);
		System.out.println("Usuario Eliminado");
		// Confirmacion de procesos
		em.getTransaction().commit();
				
		//Cerro manejador
		em.close();
}
}
