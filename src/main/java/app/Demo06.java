package app;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.Usuario;

public class Demo06 {
	public static void main(String[] args) {
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//Acciones 
		//Select * from tb_usuarios
		TypedQuery<Usuario> consulta = em.createQuery("select x from Usuario x",Usuario.class);
		List<Usuario> lstUsuario = consulta.getResultList();
		
		//List<Usuario> lstUsuario = em.createQuery("select x from Usuario x ", Usuario.class).getResultList();
		System.out.println("Lisado de Usuarios");
		for(Usuario u : lstUsuario) {
			System.out.println("Codigo... : " + " " +  u.getCodigo());
			System.out.println("Nombre... : " +u.getNombre() +" " + u.getApellido());
			System.out.println("Tipo..... : " + u.getTipo() +" - " + u.getObjTipo().getDescripcion());
			System.out.println("------------------------------------------------------");
		}
		
		em.close();
	}
}
