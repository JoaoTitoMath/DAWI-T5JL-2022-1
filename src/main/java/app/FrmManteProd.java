package app;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.Categorias;
import model.Producto;
import model.Proveedor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JTable;

public class FrmManteProd extends JFrame {

	private JPanel contentPane;

	private JTextArea txtSalida;
	private JTextField txtCodigo;
	private JComboBox cboCategorias;
	private JComboBox cboProveedores;
	private JTextField txtDescripcion;
	private JTextField txtStock;
	private JTextField txtPrecio;
	
	DefaultTableModel modelo = new DefaultTableModel();
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmManteProd frame = new FrmManteProd();
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
	public FrmManteProd() {
		setTitle("Mantenimiento de Productos");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 590);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrar();
			}
		});
		btnRegistrar.setBounds(324, 29, 89, 23);
		contentPane.add(btnRegistrar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 171, 414, 143);
		contentPane.add(scrollPane);

		txtSalida = new JTextArea();
		scrollPane.setViewportView(txtSalida);

		JButton btnListado = new JButton("Listado");
		btnListado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listado();
			}
		});
		btnListado.setBounds(177, 322, 89, 23);
		contentPane.add(btnListado);

		txtCodigo = new JTextField();
		txtCodigo.setBounds(122, 11, 86, 20);
		contentPane.add(txtCodigo);
		txtCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Id. Producto :");
		lblCodigo.setBounds(10, 14, 102, 14);
		contentPane.add(lblCodigo);

		cboCategorias = new JComboBox();
		cboCategorias.setBounds(122, 70, 86, 22);
		contentPane.add(cboCategorias);

		JLabel lblCategora = new JLabel("Categor\u00EDa");
		lblCategora.setBounds(10, 74, 102, 14);
		contentPane.add(lblCategora);

		JLabel lblNomProducto = new JLabel("Nom. Producto :");
		lblNomProducto.setBounds(10, 45, 102, 14);
		contentPane.add(lblNomProducto);

		txtDescripcion = new JTextField();
		txtDescripcion.setColumns(10);
		txtDescripcion.setBounds(122, 42, 144, 20);
		contentPane.add(txtDescripcion);

		JLabel lblStock = new JLabel("Stock:");
		lblStock.setBounds(10, 106, 102, 14);
		contentPane.add(lblStock);

		txtStock = new JTextField();
		txtStock.setColumns(10);
		txtStock.setBounds(122, 103, 77, 20);
		contentPane.add(txtStock);

		JLabel lblPrecio = new JLabel("Precio:");
		lblPrecio.setBounds(10, 134, 102, 14);
		contentPane.add(lblPrecio);

		txtPrecio = new JTextField();
		txtPrecio.setColumns(10);
		txtPrecio.setBounds(122, 131, 77, 20);
		contentPane.add(txtPrecio);

		JLabel lblProveedores = new JLabel("Proveedor:");
		lblProveedores.setBounds(230, 106, 102, 14);
		contentPane.add(lblProveedores);

		cboProveedores = new JComboBox();
		cboProveedores.setBounds(300, 104, 120, 22);
		contentPane.add(cboProveedores);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar();
			}
		});
		btnBuscar.setBounds(324, 63, 89, 23);
		contentPane.add(btnBuscar);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 382, 414, 147);
		contentPane.add(scrollPane_1);
		
		JTable tblSalida = new JTable();
		scrollPane_1.setViewportView(tblSalida);
		tblSalida.setModel(modelo);
		modelo.addColumn("Codigo");
		modelo.addColumn("Nombre");
		modelo.addColumn("Categoria");
		modelo.addColumn("Proveedor");
		

		llenaCombo();
	}

	void llenaCombo() {
		// TODO Auto-generated method stub
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//Acciones 
		//Select * from tb_usuarios
		TypedQuery<Categorias> consulta = 
				em.createQuery("select c from Categorias c",Categorias.class);
		List<Categorias> lstCategorias = consulta.getResultList();
		
		cboCategorias.addItem("Seleccionar ...");
			for(Categorias c : lstCategorias) {
				cboCategorias.addItem(c.getDescripcion());
			}
			
		
		// Combo Provedor
		TypedQuery<Proveedor> consulta2 = 
				em.createQuery("select p from Proveedor p",Proveedor.class);
		List<Proveedor> lstProveedor = consulta2.getResultList();
			
		cboProveedores.addItem("Seleccionar ...");
			for(Proveedor c : lstProveedor) {
				cboProveedores.addItem(c.getNombre_rs());
			}
		
		em.close();
	}

	void registrar() {
		// TODO Auto-generated method stub
		//Entradas
		String idprod = txtCodigo.getText();
		String descripcion = txtDescripcion.getText();
		int stock = Integer.parseInt(txtStock.getText());
		double precio = Double.parseDouble(txtPrecio.getText());
		int categoria = cboCategorias.getSelectedIndex();
		int estado = 1;
		int proveedor = cboProveedores.getSelectedIndex();
		
		//VALIDACIONES
		
		
		Producto p = new Producto();
		p.setCodigo(idprod);
		p.setDescripcion(descripcion);
		p.setStock(stock);
		p.setPrecio(precio);
		p.setIdcategoria(categoria);
		p.setEstado(estado);
		p.setIdprovedor(proveedor);
		
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			aviso("Producto Registrado...","MENSAJE",JOptionPane.INFORMATION_MESSAGE);
		}
		catch(Exception e) {
			aviso("Error al registrar producto...","MENSAJE",JOptionPane.ERROR_MESSAGE);
		}
		em.close();
		
		
	}
	
	void aviso(String msg, String tit, int icono) {

		JOptionPane.showMessageDialog(this, msg, tit,icono);

	}
	
	String leerCodigo() {
		if (!txtCodigo.getText().matches("[Pp][0-9]{4}")) {
			// aviso
			return null;
		}
		return txtCodigo.getText();
	}

	void listado() {
		// TODO Auto-generated method stub
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		//Acciones 
		//Select * from tb_usuarios
		TypedQuery<Producto> consulta = 
				em.createQuery("select p from Producto p",Producto.class);
		List<Producto> lstProductos = consulta.getResultList();
		
			
		txtSalida.setText("");
		for(Producto p : lstProductos) {
			txtSalida.append("Còdigo....: " + p.getCodigo() + "\n");
			txtSalida.append("Nombre....: " + p.getDescripcion() + "\n");
			txtSalida.append("Precio....: " + p.getPrecio() + "\n");
			txtSalida.append("Categoria.: " + p.getIdcategoria() + "\n");
			txtSalida.append("Categoria.: " + p.getCategoria().getDescripcion() + "\n");
			txtSalida.append("Proveedor.: " + p.getIdprovedor() + "\n");
			txtSalida.append("Proveedor.: " + p.getProveedor().getNombre_rs() + "\n");
			txtSalida.append("**********************************************\n");
			
			//Mostrar en la tabla
			Object datos[] = {p.getCodigo(), p.getDescripcion(), 
					p.getIdcategoria() + "-" + p.getCategoria().getDescripcion(), 
					p.getIdprovedor() + "-" + p.getProveedor().getNombre_rs()};
			modelo.addRow(datos);
		}
		em.close();
	}
	
	
	
	void buscar() {
		// TODO Auto-generated method stub
		//leer codigo
		String codigo = leerCodigo();
		EntityManagerFactory fabrica = Persistence.createEntityManagerFactory("mysql");
		EntityManager em = fabrica.createEntityManager();
		
		Producto p = em.find(Producto.class, codigo);
		
		if(p != null) {
			txtDescripcion.setText(p.getDescripcion());
			txtStock.setText(p.getStock() + "");
			cboCategorias.getSelectedIndex();
			txtPrecio.setText(p.getPrecio() + "");
		}
		else {
			aviso("Codigo de producto no existe", "Aviso Sistema", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
