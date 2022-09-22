package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Usuario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmLogin extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField txtClave;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmLogin frame = new FrmLogin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FrmLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 474, 161);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Usuario :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(10, 39, 68, 22);
		contentPane.add(lblNewLabel);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(88, 41, 178, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblClave = new JLabel("Clave    : ");
		lblClave.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblClave.setBounds(10, 72, 68, 22);
		contentPane.add(lblClave);
		
		txtClave = new JPasswordField();
		txtClave.setBounds(88, 72, 178, 20);
		contentPane.add(txtClave);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ingresar();
			}
		});
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIngresar.setBounds(333, 40, 89, 23);
		contentPane.add(btnIngresar);
	}
	
	void ingresar() {
		// leer los campos
		String usuario = leerUsuario();
		String clave = leerClave();
		
		
		
		// Obtener un Usuario segun el usuario y clave
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		Usuario u;
		
		try {
		TypedQuery<Usuario> consulta = em.createQuery("select u from Usuario u where u.usuario = :xusr and u.clave =: xpas",Usuario.class);
		consulta.setParameter("xusr",usuario);
		consulta.setParameter("xpas", clave);
		 u = consulta.getSingleResult();
		 aviso("Bienvenido","Mensaje del Sistema", JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e) {
			
			aviso("Usuario no existe","Mensaje del Sistema", JOptionPane.ERROR_MESSAGE);
		}
		
		// mostrar los mensajes de exito o error respectivo
		em.close();
	}

	private String leerClave() {
		// TODO Auto-generated method stub
		return String.valueOf(txtClave.getPassword());
	}

	private String leerUsuario() {
		// TODO Auto-generated method stub
		return String.valueOf(txtUsuario.getText());
	}
	
	void aviso(String msg, String tit, int icono) {

		JOptionPane.showMessageDialog(this, msg, tit,icono);

	}
}
