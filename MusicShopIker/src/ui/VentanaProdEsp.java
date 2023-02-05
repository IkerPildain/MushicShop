package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import Main.Main;
import classes.Producto;
import config.BD;


public class VentanaProdEsp extends JFrame {
	private JTable ProductosEspecialesJTable;
	private BD dbmanager;
	private ArrayList<Producto> productos;
	private JPanel panelCarrito;
	private JPanel contentPane;
	protected final JList<Producto> listaCarrito;
	protected final DefaultListModel<Producto> modeloCarrito;

	public VentanaProdEsp() throws SQLException, IOException {
		listaCarrito = new JList<>();
		modeloCarrito = new DefaultListModel<>();
		listaCarrito.setModel(modeloCarrito);
		setResizable(false);
		add(new JScrollPane(ProductosEspecialesJTable), BorderLayout.CENTER);
		
		dbmanager = new BD();

		productos = dbmanager.getProductoEsp();
		if(productos.size() == 0) {
			dbmanager.cargaProductosEsp();
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 1012, 513);
		contentPane = new JPanel(new GridLayout(2,1));
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setForeground(new Color(0, 0, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		ProductosEspecialesJTable = new JTable();
		
		ProductosEspecialesJTable.setBounds(88, 62, 595, 223);	
		try {
			dbmanager.concon();
		} catch (Exception e) {
			
		}
		ProductosEspecialesJTable.setModel(new ProductosTableModel(productos));
		ProductosEspecialesJTable.getColumnModel().getColumn(0).setMinWidth(100);
		ProductosEspecialesJTable.getColumnModel().getColumn(1).setMinWidth(100);
		ProductosEspecialesJTable.getColumnModel().getColumn(2).setMinWidth(100);
		ProductosEspecialesJTable.getColumnModel().getColumn(3).setMinWidth(100);
		
		contentPane.add(ProductosEspecialesJTable);
		
		
		JLabel lblProductos = new JLabel("PRODUCTOS ESPECIALES");
		lblProductos.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblProductos.setForeground(new Color(192, 192, 192));
		lblProductos.setBounds(265, 11, 332, 40);
		contentPane.add(lblProductos);
				
		
		
		JButton btnAnyadirCarrito = new JButton("Añadir al carrito");
		btnAnyadirCarrito.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAnyadirCarrito.setBounds(300, 385, 178, 30);
		contentPane.add(btnAnyadirCarrito);
		
		
		
		JButton btnAtras = new JButton("ATRAS");
		btnAtras.setFont(new Font("Tahoma", Font.BOLD, 10));
		btnAtras.setBounds(884, 430, 89, 22);
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaInicio VentanaInicio = new VentanaInicio();
				VentanaInicio.setVisible(true);
			}
		});
		contentPane.add(btnAtras);
		
		btnAnyadirCarrito.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Anyadimos producto");
                Main.pedido.add(productos.get(ProductosEspecialesJTable.getSelectedRow()));
                productos.remove(productos.get(ProductosEspecialesJTable.getSelectedRow()));
                ProductosEspecialesJTable.setModel(new ProductosTableModel(productos));
               
            }
        });
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
	
	
	public static void main(String[] args) throws SQLException, IOException {
		 VentanaProdEsp vPE = new VentanaProdEsp();     
	       vPE.setVisible(true);  
	}

}
