package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo01 {
	
	// registrar los datos  de un usuario
	public static void main(String[] args) {
		//establecer conexion
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		// crear el manejador de entidades
		EntityManager em = fabrica.createEntityManager();
		
		//proceso --> registrar , actualizar , eliminar --> transacciones
		em.getTransaction().begin();
		
		//registrar - objeto a grabar 
		//Usuario u = new Usuario(1, "Juan", "Perez", "jperez", "123", "2003/01/15", 1, 1);
		Usuario u = new Usuario(2, "Maria", "Cruz", "mcruz", "888", "2008/05/25", 1, 1, null);
		em.persist(u);
		
		System.out.println("Grabacion OK");
		
		//CONFIRMAR LA TRANSACCION
		em.getTransaction().commit();
		
		//Cerrar Conexion
		em.close();
		
		
		
	}

}
