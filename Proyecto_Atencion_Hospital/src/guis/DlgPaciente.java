package guis;

import clases.Paciente;
import arreglos.ArregloPacientes;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class DlgPaciente extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgPaciente;
	private JLabel lblCodigoPaciente;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblTelefono;
	private JLabel lblDni;
	private JTextField txtCodigoPaciente;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtTelefono;
	private JTextField txtDni;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblPaciente;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgPaciente dialog = new DlgPaciente();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public DlgPaciente() {
		setResizable(false);
		setTitle("Mantenimiento | Paciente");
		setBounds(100, 100, 710, 410);
		getContentPane().setLayout(null);
		
		lblImgPaciente = new JLabel();
		lblImgPaciente.setIcon(new ImageIcon("imagenes/dlgPaciente.png"));
		lblImgPaciente.setBounds(400, 10, 88, 89);
		getContentPane().add(lblImgPaciente);
		
		lblCodigoPaciente = new JLabel("C\u00F3digo");
		lblCodigoPaciente.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoPaciente);
		
		lblNombres = new JLabel("Nombres");
		lblNombres.setBounds(10, 40, 70, 23);
		getContentPane().add(lblNombres);
	
		lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 70, 70, 23);
		getContentPane().add(lblApellidos);
		
		lblTelefono = new JLabel("Tel�fono");
		lblTelefono.setBounds(10, 100, 70, 23);
		getContentPane().add(lblTelefono);
		
		lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 130, 70, 23);
		getContentPane().add(lblDni);
		
		txtCodigoPaciente = new JTextField();
		txtCodigoPaciente.setBounds(90, 10, 85, 23);
		getContentPane().add(txtCodigoPaciente);
		txtCodigoPaciente.setColumns(10);

		txtNombres = new JTextField();
		txtNombres.setBounds(90, 40, 200, 23);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);

		txtApellidos = new JTextField();
		txtApellidos.setBounds(90, 70, 200, 23);
		getContentPane().add(txtApellidos);
		txtApellidos.setColumns(10);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(90, 100, 85, 23);
		getContentPane().add(txtTelefono);
		txtTelefono.setColumns(10);

		txtDni = new JTextField();
		txtDni.setBounds(90, 130, 85, 23);
		getContentPane().add(txtDni);
		txtDni.setColumns(10);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
		btnAceptar.addMouseListener(this);
		btnAceptar.setForeground(Color.BLUE);
		btnAceptar.setBounds(190, 10, 100, 23);
		getContentPane().add(btnAceptar);
		
		btnAdicionar = new JButton("Adicionar");
		btnAdicionar.addActionListener(this);
		btnAdicionar.addMouseListener(this);
		btnAdicionar.setForeground(Color.BLUE);
		btnAdicionar.setBounds(535, 10, 150, 23);
		getContentPane().add(btnAdicionar);

		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(this);
		btnModificar.addMouseListener(this);
		btnModificar.setForeground(Color.BLUE);
		btnModificar.setBounds(535, 35, 150, 23);
		getContentPane().add(btnModificar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.addMouseListener(this);
		btnEliminar.setForeground(Color.BLUE);
		btnEliminar.setBounds(535, 60, 150, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 170, 675, 195);
		getContentPane().add(scrollPane);

		tblPaciente = new JTable();
		tblPaciente.addKeyListener(this);
		tblPaciente.addMouseListener(this);
		tblPaciente.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblPaciente);

		modelo = new DefaultTableModel();
		modelo.addColumn("C�DIGO");
		modelo.addColumn("NOMBRES");
		modelo.addColumn("APELLIDOS");
		modelo.addColumn("TEL�FONO");
		modelo.addColumn("DNI");
		
		tblPaciente.setModel(modelo);
		
		txtCodigoPaciente.setEditable(false);
		
		habilitarEntradas(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	
	//  Declaraci�n global
	ArregloPacientes ap = new ArregloPacientes();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			actionPerformedBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			actionPerformedBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			actionPerformedBtnAdicionar(arg0);
		}
	}
	protected void actionPerformedBtnAdicionar(ActionEvent arg0) {
		btnAdicionar.setEnabled(false);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(true);
		limpieza();
		habilitarEntradas(true);
		txtNombres.requestFocus();
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ap.tama�o() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen pacientes");	
		}
		else {
			editarFila();
			btnAceptar.setEnabled(true);
			habilitarEntradas(true);
			txtNombres.requestFocus();
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (ap.tama�o() == 0)
			mensaje("No existen pacientes");
		else {
			editarFila();
			habilitarEntradas(false);
			int ok = confirmar("� Desea eliminar el registro ?");
			if (ok == 0) {
				ap.eliminar(ap.buscar(leerCodigoPaciente()));
				listar();
				editarFila();
			}
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		int codigoPaciente = leerCodigoPaciente();
		String nombres = leerNombres();
		if (nombres.length() > 0) {
			String apellidos = leerApellidos();
			if (apellidos.length() > 0) {
				String telefono = leerTelefono();
				if (telefono.length() > 0) {
					String dni = leerDni();
					if (dni.length() > 0) {
						if (btnAdicionar.isEnabled() == false) {
							Paciente nuevo = new Paciente(codigoPaciente, nombres, apellidos, telefono, dni);
							ap.adicionar(nuevo);
							btnAdicionar.setEnabled(true);
						}
						if (btnModificar.isEnabled() == false) {
							Paciente p = ap.buscar(codigoPaciente);
							p.setNombres(nombres);
							p.setApellidos(apellidos);
							p.setTelefono(telefono);
							p.setDni(dni);
							ap.actualizarArchivo();
							btnModificar.setEnabled(true);
						}
						listar();
						habilitarEntradas(false);
					}
					else
						error("Ingrese DNI correcto", txtDni);
				}
				else
					error("Ingrese TEL�FONO correcto", txtTelefono);		
			}
			else
				error("ingrese APELLIDOS correctos", txtApellidos);
		}
		else
			error("ingrese NOMBRES correctos", txtNombres);		
	}
	public void keyPressed(KeyEvent arg0) {
	}
	public void keyReleased(KeyEvent arg0) {
		arg0.consume();
		editarFila();		
	}
	public void keyTyped(KeyEvent arg0) {
	}
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == tblPaciente) {
			mouseClickedTblPaciente(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btnAceptar) {
			mouseEnteredBtnAceptar(arg0);
		}
		if (arg0.getSource() == btnEliminar) {
			mouseEnteredBtnEliminar(arg0);
		}
		if (arg0.getSource() == btnModificar) {
			mouseEnteredBtnModificar(arg0);
		}
		if (arg0.getSource() == btnAdicionar) {
			mouseEnteredBtnAdicionar(arg0);
		}
		if (arg0.getSource() == tblPaciente) {
			mouseEnteredTblPaciente(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblPaciente(MouseEvent arg0) {
		habilitarEntradas(false);
		habilitarBotones(true);
		editarFila();
	}
	protected void mouseEnteredTblPaciente(MouseEvent arg0) {
		tblPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAdicionar(MouseEvent arg0) {
		btnAdicionar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnModificar(MouseEvent arg0) {
		btnModificar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnEliminar(MouseEvent arg0) {
		btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnAceptar(MouseEvent arg0) {
		btnAceptar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  M�todos tipo void (sin par�metros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblPaciente.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));  // codigoPaciente
		tcm.getColumn(1).setPreferredWidth(anchoColumna(30));  // nombres
		tcm.getColumn(2).setPreferredWidth(anchoColumna(30));  // apellidos
		tcm.getColumn(3).setPreferredWidth(anchoColumna(15));  // tel�fono
		tcm.getColumn(4).setPreferredWidth(anchoColumna(15));  // dni
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblPaciente.getSelectedRow();
		if (modelo.getRowCount() == ap.tama�o() - 1)
			posFila = ap.tama�o() - 1;
		if (posFila == ap.tama�o())
			posFila --;
		modelo.setRowCount(0);
		Paciente p;
		for (int i=0; i<ap.tama�o(); i++) {
			p = ap.obtener(i);
			Object[] fila = { p.getCodigoPaciente(),
					          p.getNombres(),
					          p.getApellidos(),
					          p.getTelefono(),
					          p.getDni() };
			modelo.addRow(fila);
		}
		if (ap.tama�o() > 0)
			tblPaciente.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (ap.tama�o() == 0)
			limpieza();
		else {
			Paciente p = ap.obtener(tblPaciente.getSelectedRow());
			txtCodigoPaciente.setText("" + p.getCodigoPaciente());
			txtNombres.setText(p.getNombres());
			txtApellidos.setText(p.getApellidos());
			txtTelefono.setText(p.getTelefono());
			txtDni.setText(p.getDni());
		}
	}
	void limpieza() {
		txtCodigoPaciente.setText("" + ap.codigoCorrelativo());
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDni.setText("");
	}
	//  M�todos tipo void (con par�metros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informaci�n", 0);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	void habilitarEntradas(boolean sino) {
		btnAceptar.setEnabled(sino);
		txtNombres.setEditable(sino);
		txtApellidos.setEditable(sino);
		txtTelefono.setEditable(sino);
		txtDni.setEditable(sino);
	}
	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}
	//  M�todos que retornan valor (sin par�metros)
	int leerCodigoPaciente() {
		return Integer.parseInt(txtCodigoPaciente.getText().trim());
	}
	String leerNombres() {
		return txtNombres.getText().trim();
	}
	String leerApellidos() {
		return txtApellidos.getText().trim();
	}
	String leerTelefono() {
		return txtTelefono.getText().trim();
	}
	String leerDni() {
		return txtDni.getText().trim();
	}
	//  M�todos que retornan valor (con par�metros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	
}