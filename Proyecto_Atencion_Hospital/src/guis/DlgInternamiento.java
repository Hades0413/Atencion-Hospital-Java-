package guis;

import clases.*;
import arreglos.*;
import libreria.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

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
import java.awt.Font;

public class DlgInternamiento extends JDialog implements ActionListener, KeyListener, MouseListener {
	
	private JLabel lblImgInternamiento;
	private JLabel lblCodigoInternamiento;
	private JTextField txtCodigoInternamiento;
	private JTextField txtCodigoPaciente;
	private JTextField txtNumeroCama;
	private JComboBox <String> cboCodigoPaciente;
	private JComboBox <String> cboNumeroCama;
	private JButton btnCodigoPaciente;
	private JButton btnNumeroCama;
	private JButton btnInternar;
	private JButton btnProceder;
	private JScrollPane scrollPane;
	private JTable tblInternamiento;
	private DefaultTableModel modelo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgInternamiento dialog = new DlgInternamiento();
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
	public DlgInternamiento() {
		setResizable(false);
		setTitle("Registro | Internamiento");
		setBounds(100, 100, 710, 410);
		getContentPane().setLayout(null);

		lblImgInternamiento = new JLabel();
		lblImgInternamiento.setIcon(new ImageIcon("imagenes/dlgInternamiento.png"));
		lblImgInternamiento.setBounds(400, 10, 89, 88);
		getContentPane().add(lblImgInternamiento);
		
		lblCodigoInternamiento = new JLabel("C\u00F3digo");
		lblCodigoInternamiento.setBounds(10, 10, 50, 23);
		getContentPane().add(lblCodigoInternamiento);
		
		btnCodigoPaciente = new JButton("P");
		btnCodigoPaciente.addMouseListener(this);
		btnCodigoPaciente.setForeground(Color.BLUE);
		btnCodigoPaciente.setBounds(10, 40, 50, 23);
		getContentPane().add(btnCodigoPaciente);
	
		btnNumeroCama = new JButton("C");
		btnNumeroCama.addMouseListener(this);
		btnNumeroCama.setForeground(Color.BLUE);
		btnNumeroCama.setBounds(10, 70, 50, 23);
		getContentPane().add(btnNumeroCama);
		
		txtCodigoInternamiento = new JTextField();
		txtCodigoInternamiento.setBounds(70, 10, 85, 23);
		getContentPane().add(txtCodigoInternamiento);
		txtCodigoInternamiento.setColumns(10);
		
		txtCodigoPaciente = new JTextField();
		txtCodigoPaciente.setBounds(70, 40, 85, 23);
		getContentPane().add(txtCodigoPaciente);
		txtCodigoPaciente.setColumns(10);
	
		txtNumeroCama = new JTextField();
		txtNumeroCama.setBounds(70, 70, 85, 23);
		getContentPane().add(txtNumeroCama);
		txtNumeroCama.setColumns(10);
		
		cboCodigoPaciente = new JComboBox <String> ();
		cboCodigoPaciente.addActionListener(this);
		cboCodigoPaciente.addMouseListener(this);
		cboCodigoPaciente.setBounds(190, 40, 100, 23);
		getContentPane().add(cboCodigoPaciente);
		colocarCodigosPacientes();

		cboNumeroCama = new JComboBox <String> ();
		cboNumeroCama.addActionListener(this);
		cboNumeroCama.addMouseListener(this);
		cboNumeroCama.setBounds(190, 70, 100, 23);
		getContentPane().add(cboNumeroCama);
		colocarNumerosCamas();

		btnInternar = new JButton("Internar");
		btnInternar.addActionListener(this);
		btnInternar.addMouseListener(this);
		btnInternar.setForeground(Color.BLUE);
		btnInternar.setBounds(535, 10, 150, 23);
		getContentPane().add(btnInternar);
		
		btnProceder = new JButton("Proceder");
		btnProceder.addActionListener(this);
		btnProceder.addMouseListener(this);
		btnProceder.setForeground(Color.BLUE);
		btnProceder.setBounds(189, 10, 102, 23);
		getContentPane().add(btnProceder);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 110, 675, 250);
		getContentPane().add(scrollPane);

		tblInternamiento = new JTable();
		tblInternamiento.setFont(new Font("Tahoma", Font.PLAIN, 10));
		tblInternamiento.addKeyListener(this);
		tblInternamiento.addMouseListener(this);
		tblInternamiento.setFillsViewportHeight(true);
		scrollPane.setViewportView(tblInternamiento);

		modelo = new DefaultTableModel();
		modelo.addColumn("CÓDIGO");
		modelo.addColumn("PACIENTE");
		modelo.addColumn("CAMA");
		modelo.addColumn("FECHA INGRESO");
		modelo.addColumn("HORA");
		modelo.addColumn("FECHA SALIDA");
		modelo.addColumn("HORA");
		modelo.addColumn("TOTAL");
		modelo.addColumn("ESTADO");
		
		tblInternamiento.setModel(modelo);
		
		txtCodigoInternamiento.setEditable(false);
		txtCodigoPaciente.setEditable(false);
		txtNumeroCama.setEditable(false);
		
		btnProceder.setEnabled(false);
		ajustarAnchoColumnas();
		listar();
		editarFila();
	}
	
	//  Declaración global
	ArregloInternamientos ai = new ArregloInternamientos();
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == cboNumeroCama) {
			actionPerformedCboNumeroCama(arg0);
		}
		if (arg0.getSource() == cboCodigoPaciente) {
			actionPerformedCboCodigoPaciente(arg0);
		}
		if (arg0.getSource() == btnProceder) {
			actionPerformedBtnProceder(arg0);
		}
		if (arg0.getSource() == btnInternar) {
			actionPerformedBtnInternar(arg0);
		}
	}
	protected void actionPerformedBtnInternar(ActionEvent arg0) {
		if (cboCodigoPaciente.getSelectedIndex() < 0)
			mensaje("Todos los pacientes están alojados");
		else
			if (cboNumeroCama.getSelectedIndex() < 0)
				mensaje("Todas las camas están ocupadas");
			else {
				btnInternar.setEnabled(false);
				btnProceder.setEnabled(true);
				txtCodigoInternamiento.setText("" + ai.codigoCorrelativo());
				txtCodigoPaciente.setText("" + cboCodigoPaciente.getSelectedItem());
				txtNumeroCama.setText("" + cboNumeroCama.getSelectedItem());
				btnProceder.setEnabled(true);
			}
	}
	protected void actionPerformedBtnProceder(ActionEvent arg0) {
		int codigoInternamiento = leerCodigoInternamiento();
		int codigoPaciente = leerCodigoPaciente();
		int numeroCama = leerNumeroCama();
		int ok = confirmar(obtenerDatosPaciente() + "\n\n" + obtenerDatosCama(), "¿ Desea internar ?");
		if (ok == 0) {
			Internamiento nuevo = new Internamiento(codigoInternamiento, codigoPaciente, numeroCama,
                                                    Fecha.fechaActual(), Fecha.horaActual(), "", "", 200.0, 0);
			ai.adicionar(nuevo);
			ArregloCamas ac = new ArregloCamas();
			Cama c = ac.buscar(numeroCama);
			c.setEstado(1);
			ac.actualizarArchivo();
			cboCodigoPaciente.removeItem(cboCodigoPaciente.getSelectedItem());
			cboNumeroCama.removeItem(cboNumeroCama.getSelectedItem());
		}
		btnInternar.setEnabled(true);
		btnProceder.setEnabled(false);	
		listar();
		editarFila();	
	}
	protected void actionPerformedCboCodigoPaciente(ActionEvent arg0) {
		txtCodigoPaciente.setText("" + cboCodigoPaciente.getSelectedItem());
	}
	protected void actionPerformedCboNumeroCama(ActionEvent arg0) {
		txtNumeroCama.setText("" + cboNumeroCama.getSelectedItem());
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
		if (arg0.getSource() == btnNumeroCama) {
			mouseClickedBtnNumeroCama(arg0);
		}
		if (arg0.getSource() == btnCodigoPaciente) {
			mouseClickedBtnCodigoPaciente(arg0);
		}
		if (arg0.getSource() == tblInternamiento) {
			mouseClickedTblInternamiento(arg0);
		}
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == cboNumeroCama) {
			mouseEnteredCboNumeroCama(arg0);
		}	
		if (arg0.getSource() == cboCodigoPaciente) {
			mouseEnteredCboCodigoPaciente(arg0);
		}	
		if (arg0.getSource() == btnProceder) {
			mouseEnteredBtnProceder(arg0);
		}
		if (arg0.getSource() == btnInternar) {
			mouseEnteredBtnInternar(arg0);
		}
		if (arg0.getSource() == btnNumeroCama) {
			mouseEnteredBtnNumeroCama(arg0);
		}
		if (arg0.getSource() == btnCodigoPaciente) {
			mouseEnteredBtnCodigoPaciente(arg0);
		}
		if (arg0.getSource() == tblInternamiento) {
			mouseEnteredTblInternamiento(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseClickedTblInternamiento(MouseEvent arg0) {
		btnProceder.setEnabled(false);
		btnInternar.setEnabled(true);
		editarFila();
	}
	protected void mouseClickedBtnCodigoPaciente(MouseEvent arg0) {
		mensaje(obtenerDatosPaciente());
	}
	protected void mouseClickedBtnNumeroCama(MouseEvent arg0) {
		mensaje(obtenerDatosCama());
	}
	protected void mouseEnteredTblInternamiento(MouseEvent arg0) {
		tblInternamiento.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnCodigoPaciente(MouseEvent arg0) {
		btnCodigoPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnNumeroCama(MouseEvent arg0) {
		btnNumeroCama.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnInternar(MouseEvent arg0) {
		btnInternar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredBtnProceder(MouseEvent arg0) {
		btnProceder.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	protected void mouseEnteredCboCodigoPaciente(MouseEvent arg0) {
		cboCodigoPaciente.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}	
	protected void mouseEnteredCboNumeroCama(MouseEvent arg0) {
		cboNumeroCama.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  Métodos tipo void (sin parámetros)
	void ajustarAnchoColumnas() {
		TableColumnModel tcm = tblInternamiento.getColumnModel();
		tcm.getColumn(0).setPreferredWidth(anchoColumna(10));  // codigoInternamiento
		tcm.getColumn(1).setPreferredWidth(anchoColumna(11));  // codigoPaciente
		tcm.getColumn(2).setPreferredWidth(anchoColumna( 7));  // numeroCama
		tcm.getColumn(3).setPreferredWidth(anchoColumna(18));  // fechaIngreso
		tcm.getColumn(4).setPreferredWidth(anchoColumna( 8));  // horaIngreso
		tcm.getColumn(5).setPreferredWidth(anchoColumna(18));  // fechaSalida
		tcm.getColumn(6).setPreferredWidth(anchoColumna( 8));  // horaSalida
		tcm.getColumn(7).setPreferredWidth(anchoColumna(11));  // totalPagar
		tcm.getColumn(8).setPreferredWidth(anchoColumna( 9));  // estado
	}
	void colocarCodigosPacientes() {	
		ArregloPacientes ap = new ArregloPacientes();
		Paciente p;
		for (int i=0; i<ap.tamaño(); i++) {
			p = ap.obtener(i);
			if (ai.procedeCodigoPaciente(p.getCodigoPaciente()))
				cboCodigoPaciente.addItem("" + p.getCodigoPaciente());
		}
	}
	void colocarNumerosCamas() {
		ArregloCamas ac = new ArregloCamas();
		Cama c;
		for (int i=0; i<ac.tamaño(); i++) {
			c = ac.obtener(i);
			if (c.getEstado() == 0)
				cboNumeroCama.addItem("" + c.getNumeroCama());
		}
	}
	void listar() {
		int posFila = 0;
		if (modelo.getRowCount() > 0)
			posFila = tblInternamiento.getSelectedRow();
		if (modelo.getRowCount() == ai.tamaño() - 1)
			posFila = ai.tamaño() - 1;
		if (posFila == ai.tamaño())
			posFila --;
		modelo.setRowCount(0);
		Internamiento i;
		for (int j=0; j<ai.tamaño(); j++) {
			i = ai.obtener(j);
			Object[] fila = { i.getCodigoInternamiento(),
					          i.getCodigoPaciente(),
					          i.getNumeroCama(),
					          Fecha.enTextoFecha(i.getFechaIngreso()),
					          i.getHoraIngreso(),
					          Fecha.enTextoFecha(i.getFechaSalida()),
					          i.getHoraSalida(),
					          "S/ " + Formato.ajusteA(i.getTotalPagar()),
					          Lib.estadosInternamiento[i.getEstado()] };
			modelo.addRow(fila);
		}
		if (ai.tamaño() > 0)
			tblInternamiento.getSelectionModel().setSelectionInterval(posFila, posFila);
	}
	void editarFila() {
		if (ai.tamaño() == 0)
			txtCodigoInternamiento.setText("" + ai.codigoCorrelativo());
		else {
			Internamiento i = ai.obtener(tblInternamiento.getSelectedRow());
			txtCodigoInternamiento.setText("" + i.getCodigoInternamiento());
			txtCodigoPaciente.setText("" + i.getCodigoPaciente());
			txtNumeroCama.setText("" + i.getNumeroCama());
		}
	}
	//  Métodos tipo void (con parámetros)
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Informe de internamiento", 1);
	}
	void mensaje(String s1, String s2) {
		JOptionPane.showMessageDialog(this, s1, s2, 1);
	}
	void error(String s, JTextField txt) {
		mensaje(s);
		txt.setText("");
		txt.requestFocus();
	}
	//  Métodos que retornan valor (sin parámetros)
	String obtenerDatosPaciente() {
		ArregloPacientes ap = new ArregloPacientes();
	    Paciente p = ap.buscar(leerCodigoPaciente());
	    return "Paciente  :  " + p.getCodigoPaciente() + "\n" +
	    	   "Nombres :  " + p.getNombres() + "\n" +
		       "Apellidos :  " + p.getApellidos();
	}
	String obtenerDatosCama() {
		ArregloCamas ac = new ArregloCamas();
	    Cama c = ac.buscar(leerNumeroCama());
	    return "Cama  :  " + c.getNumeroCama() + "\n" +
	    	   "Categoría :  " + Lib.categoriasCama[c.getCategoria()] + "\n" +
		       "Precio por día :  S/ " + c.getPrecioDia();
	}
	int leerCodigoInternamiento() {
		return Integer.parseInt(txtCodigoInternamiento.getText().trim());
	}
	int leerCodigoPaciente() {
		return Integer.parseInt(txtCodigoPaciente.getText().trim());
	}
	int leerNumeroCama() {
		return Integer.parseInt(txtNumeroCama.getText().trim());
	}
	//  Métodos que retornan valor (con parámetros)
	int anchoColumna(int porcentaje) {
		return porcentaje * scrollPane.getWidth() / 100;
	}
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}
	int confirmar(String s1, String s2) {
		return JOptionPane.showConfirmDialog(this, s1, s2, 0, 1, null);
	}
	
}