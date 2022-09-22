package app;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Usuario;

public class Demo02 {
	//Actualizar los datos de la clase ingresada
	public static void main(String[] args) {
		
		//establecer conexion
				EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
				// crear el manejador de entidades
				EntityManager em = fabrica.createEntityManager();
				
				em.getTransaction().begin();
				Usuario u = new Usuario(1, "Juan Carlos", "Perez Lopez", "jperezl", "555", "2003/01/15", 2, 1,null);
				em.merge(u); // si existe el codigo --> lo actualiza / si el codigo no existe lo crea
				System.out.println("Usuario Actualizado");
				em.getTransaction().commit();
				em.close();
				
				
				
	}

}
