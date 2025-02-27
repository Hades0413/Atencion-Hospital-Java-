package guis;

import clases.Cama;
import arreglos.ArregloCamas;
import libreria.Lib;
import libreria.Formato;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
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

public class DlgCama extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgCama;
	private JLabel lblNumeroCama;
	private JLabel lblCategoria;
	private JLabel lblPrecioDia;
	private JLabel lblEstado;
	private JTextField txtNumeroCama;
	private JTextField txtPrecioDia;
	private JComboBox <String> cboCategoria;
	private JComboBox <String> cboEstado;
	private JScrollPane scrollPane;
	private JButton btnAceptar;
	private JButton btnAdicionar;
	private JButton btnModificar;
	private JButton btnEliminar;
	private JTable tblCama;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgCama dialog = new DlgCama();
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
	public DlgCama() {
		setResizable(false);
		setTitle("Mantenimiento | Cama");
		setBounds(100, 100, 710, 410);
		getContentPane().setLayout(null);
		
		lblImgCama = new JLabel();
		lblImgCama.setIcon(new ImageIcon("imagenes/dlgCama.png"));
		lblImgCama.setBounds(400, 10, 89, 89);
		getContentPane().add(lblImgCama);
		
		lblNumeroCama = new JLabel("N\u00FAmero");
		lblNumeroCama.setBounds(10, 10, 110, 23);
		getContentPane().add(lblNumeroCama);
		
		lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setBounds(10, 40, 70, 23);
		getContentPane().add(lblCategoria);
	
		lblPrecioDia = new JLabel("Precio S/");
		lblPrecioDia.setBounds(10, 70, 70, 23);
		getContentPane().add(lblPrecioDia);
		
		lblEstado = new JLabel("Estado");
		lblEstado.setBounds(10, 100, 70, 23);
		getContentPane().add(lblEstado);
		
		txtNumeroCama = new JTextField();
		txtNumeroCama.setBounds(90, 10, 85, 23);
		getContentPane().add(txtNumeroCama);
		txtNumeroCama.setColumns(10);

		cboCategoria = new JComboBox <String> ();
		cboCategoria.addActionListener(this);
		cboCategoria.setModel(new DefaultComboBoxModel <String> (Lib.categoriasCama));
		cboCategoria.setBounds(90, 40, 85, 23);
		getContentPane().add(cboCategoria);

		txtPrecioDia = new JTextField();
		txtPrecioDia.setBounds(90, 70, 85, 23);
		getContentPane().add(txtPrecioDia);
		txtPrecioDia.setEditable(false);
		txtPrecioDia.setColumns(10);
		
		cboEstado = new JComboBox <String> ();
		cboEstado.setEnabled(false);
		cboEstado.setModel(new DefaultComboBoxModel <String> (Lib.estadosCama));
		cboEstado.setBounds(90, 100, 85, 23);
		getContentPane().add(cboEstado);

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
		scrollPane.setBounds(10, 140, 675, 220);
		getContentPane().add(scrollPane);

		tblCama = new JTable();
		tblCama.addKeyListener(this);
		tblCama.addMouseListener(this);
		tblCama.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblCama);

		modelo = new DefaultTableModel();
		modelo.addColumn("N�MERO");
		modelo.addColumn("CATEGOR�A");
		modelo.addColumn("PRECIO POR D�A");
		modelo.addColumn("ESTADO");
		
		tblCama.setModel(modelo);
		
		txtNumeroCama.setEditable(false);
		
		ajustarAnchoColumnas();
		listar();
		editarFila();
		habilitarEntradas(false);
	}
	
	//  Declaraci�n global
	ArregloCamas ac = new ArregloCamas();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboCategoria) {
			actionPerformedCboCategoria(arg0);
		}
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
	}
	protected void actionPerformedBtnModificar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(false);
		if (ac.tama�o() == 0) {
			btnAceptar.setEnabled(false);
			habilitarEntradas(false);
			mensaje("No existen camas");	
		}
		else {
			editarFila();
			int numeroCama = leerNumeroCama();
			Cama x = ac.buscar(numeroCama);
			if (x.getEstado() == 0) {
				btnAceptar.setEnabled(true);
				habilitarEntradas(true);
			}
			else {
				btnModificar.setEnabled(true);
				mensaje("No se puede modificar el n�mero de cama " + numeroCama + " porque est� Ocupada");
			}
		}
	}
	protected void actionPerformedBtnEliminar(ActionEvent arg0) {
		btnAdicionar.setEnabled(true);
		btnModificar.setEnabled(true);
		btnAceptar.setEnabled(false);
		if (ac.tama�o() == 0)
			mensaje("No existen camas");
		else {
			editarFila();
			habilitarEntradas(false);
			int numeroCama = leerNumeroCama();
			Cama x = ac.buscar(numeroCama);
			if (x.getEstado() == 0) {
				int ok = confirmar("� Desea eliminar el registro ?");
				if (ok == 0) {
					ac.eliminar(ac.buscar(leerNumeroCama()));
					listar();
					editarFila();
				}
			}
			else
				mensaje("No se puede eliminar el n�mero de cama " + numeroCama + " porque est� Ocupada");
		}
	}
	protected void actionPerformedBtnAceptar(ActionEvent arg0) {
		int numeroCama = leerNumeroCama();
		int categoria = leerPosCategoria();
		double precioDia = leerPrecioDia();
		int estado = leerPosEstado();
		if (btnAdicionar.isEnabled() == false) {
			Cama nueva = new Cama(numeroCama, categoria, precioDia, estado);
			ac.adicionar(nueva);
			btnAdicionar.setEnabled(true);
		}
		if (btnModificar.isEnabled() == false) {
			Cama x = ac.buscar(numeroCama);
			x.setCategoria(categoria);
			x.setPrecioDia(precioDia);
			x.setEstado(estado);
			ac.actualizarArchivo();
			btnModificar.setEnabled(true);
		}
		listar();
		habilitarEntradas(false);
	}
	protected void actionPerformedCboCategoria(ActionEvent arg0) {
		txtPrecioDia.setText("" + establecerPrecio());
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
		if (arg0.getSource() == tblCama) {
			mouseClickedTblCama(arg0);
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
		if (arg0.getSource() == tblCama) {
			mouseEnteredTblCama(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblCama(MouseEvent arg0) {
		habilitarEntradas(false);
		habilitarBotones(true);
		editarFila();
	}
	protected void mouseEnteredTblCama(MouseEvent arg0) {
		tblCama.setCursor(new Cursor(Cursor.HAND_CURSOR));
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
		TableColumnModel tcm = tblCama.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(25));  // numeroCama
		tcm.getColumn(1).setPreferredWidth(anchoColumna(25));  // categoria
		tcm.getColumn(2).setPreferredWidth(anchoColumna(25));  // precioDia
		tcm.getColumn(3).setPreferredWidth(anchoColumna(25));  // estado
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblCama.getSelectedRow();
		if (modelo.getRowCount() == ac.tama�o() - 1)
			posFila = ac.tama�o() - 1;
		if (posFila == ac.tama�o())
			posFila --;
		modelo.setRowCount(0);
		Cama x;
		for (int i=0; i<ac.tama�o(); i++) {
			x = ac.obtener(i);
			Object[] fila = { x.getNumeroCama(),
			          		  enTextoCategoria(x.getCategoria()),
			                  "S/ " + Formato.ajusteA(x.getPrecioDia()),
			                  enTextoEstado(x.getEstado()) };
			modelo.addRow(fila);
		}
		if (ac.tama�o() > 0)
			tblCama.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (ac.tama�o() == 0)
			limpieza();
		else {
			Cama x = ac.obtener(tblCama.getSelectedRow());
			txtNumeroCama.setText("" + x.getNumeroCama());
			cboCategoria.setSelectedIndex(x.getCategoria());
			txtPrecioDia.setText("" + Formato.ajusteA(x.getPrecioDia()));
			cboEstado.setSelectedIndex(x.getEstado());
		}
	}
	void limpieza() {
		txtNumeroCama.setText("" + ac.numeroCorrelativo());
		cboCategoria.setSelectedIndex(0);
		cboEstado.setSelectedIndex(0);
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
		cboCategoria.setEnabled(sino);
	}
	void habilitarBotones(boolean sino) {
		btnAdicionar.setEnabled(sino);
		btnModificar.setEnabled(sino);
	}
	//  M�todos que retornan valor (sin par�metros)
	int leerNumeroCama() {
		return Integer.parseInt(txtNumeroCama.getText().trim());
	}
	int leerPosCategoria() {
		return cboCategoria.getSelectedIndex();
	}
	double leerPrecioDia() {
		return Double.parseDouble(txtPrecioDia.getText().trim());
	}
	int leerPosEstado() {
		return cboEstado.getSelectedIndex();
	}
	double establecerPrecio() {
		switch (leerPosCategoria()) {
			case 0:
				return 50.0;
			case 1:
				return 100.0;
			default:
				return 150.0;	
		}
	}
	//  M�todos que retornan valor (con par�metros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	String enTextoCategoria(int i) {
		return cboCategoria.getItemAt(i);
	}
	String enTextoEstado(int i) {
		return cboEstado.getItemAt(i);
	}

}