package guis;

import clases.*;
import arreglos.*;
import libreria.*;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class DlgInternamientos extends JDialog implements ActionListener, MouseListener {
	
	private JLabel lblCodigoInternamiento;
	private JComboBox <String> cboCodigoInternamiento;
	private JButton btnPagar;
	private JScrollPane scrollPane;
	private JTextArea txtS;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgInternamientos dialog = new DlgInternamientos();
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
	public DlgInternamientos() {
		setResizable(false);
		setTitle("Pago | Internamientos");
		setBounds(100, 100, 710, 410);
		getContentPane().setLayout(null);
		
		lblCodigoInternamiento = new JLabel("Internamiento");
		lblCodigoInternamiento.setBounds(10, 10, 110, 23);
		getContentPane().add(lblCodigoInternamiento);
		
		btnPagar = new JButton("Pagar");
		btnPagar.addActionListener(this);
		btnPagar.addMouseListener(this);
		btnPagar.setForeground(Color.BLUE);
		btnPagar.setBounds(535, 10, 150, 23);
		getContentPane().add(btnPagar);

		cboCodigoInternamiento = new JComboBox <String> ();
		cboCodigoInternamiento.addActionListener(this);
		cboCodigoInternamiento.addMouseListener(this);
		cboCodigoInternamiento.setBounds(100, 10, 100, 23);
		getContentPane().add(cboCodigoInternamiento);
	
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 50, 675, 310);
		getContentPane().add(scrollPane);
		
		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		colocarCodigosInternamientos();
	}
	
	//  Declaración global
	ArregloInternamientos ai = new ArregloInternamientos();
	ArregloPacientes ap = new ArregloPacientes();
	String fechaSalida, horaSalida;
	double totalPagar;
	
	public void actionPerformed(ActionEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			actionPerformedBtnPagar(arg0);
		}
		if (arg0.getSource() == cboCodigoInternamiento) {
			actionPerformedCboCodigoInternamiento(arg0);
		}
	}
	protected void actionPerformedCboCodigoInternamiento(ActionEvent arg0) {
		txtS.setText("");
		if (cboCodigoInternamiento.getSelectedIndex() >= 0)
			listar();
	}
	protected void actionPerformedBtnPagar(ActionEvent arg0) {
		if (cboCodigoInternamiento.getSelectedIndex() >= 0) { 
			int ok = confirmar("¿ Desea pagar Internamiento ?");
			if (ok == 0) {
				Internamiento i = ai.buscar(leerCodigoInternamiento());
				i.setEstado(1);
				i.setFechaSalida(fechaSalida);
				i.setHoraSalida(horaSalida);
				i.setTotalPagar(totalPagar);
				ai.actualizarArchivo();
				ArregloCamas ac = new ArregloCamas();
				Cama c = ac.buscar(i.getNumeroCama());
				c.setEstado(0);
				ac.actualizarArchivo();
				cboCodigoInternamiento.removeItem(cboCodigoInternamiento.getSelectedItem());
			}
		}
		else
			mensaje("No existen internamientos pendientes de pago");
	}	
	public void mouseClicked(MouseEvent arg0) {
	}
	public void mouseEntered(MouseEvent arg0) {
		if (arg0.getSource() == btnPagar) {
			mouseEnteredBtnPagar(arg0);
		}
	}
	public void mouseExited(MouseEvent arg0) {
	}
	public void mousePressed(MouseEvent arg0) {
	}
	public void mouseReleased(MouseEvent arg0) {
	}
	protected void mouseEnteredBtnPagar(MouseEvent arg0) {
		btnPagar.setCursor(new Cursor(Cursor.HAND_CURSOR));
	}
	//  Métodos tipo void (sin parámetros)
	void colocarCodigosInternamientos() {
		Internamiento i;
		for (int j=0; j<ai.tamaño(); j++) {
			i = ai.obtener(j);
			if (i.getEstado() == 0)
				cboCodigoInternamiento.addItem("" + i.getCodigoInternamiento());
		}	
	}
	void imprimir() {
		imprimir("");
	}
	void listar() {
		Internamiento i = ai.buscar(leerCodigoInternamiento());
		Paciente p = ap.buscar(i.getCodigoPaciente());
		imprimir("Paciente  :  " + p.getCodigoPaciente());
		imprimir("Nombres   :  " + p.getNombres());
		imprimir("Apellidos :  " + p.getApellidos());
		imprimir();
		imprimir("Cama      :  " + i.getNumeroCama());
		ArregloCamas ac = new ArregloCamas();
		Cama c = ac.buscar(i.getNumeroCama());
		imprimir("Categoría :  " + Lib.categoriasCama[c.getCategoria()]);
		imprimir();
		imprimir("Ingreso   :  " + i.getFechaIngreso()
		                         + " - " + i.getHoraIngreso());
		fechaSalida = Fecha.fechaActual();
		horaSalida = Fecha.horaActual();
		imprimir("Salida    :  " + fechaSalida
                                 + " - " + horaSalida);
		int dias = Fecha.diasTranscurridos(i.getFechaIngreso(), fechaSalida);
		totalPagar = i.getTotalPagar() + dias * c.getPrecioDia();
		imprimir();
		imprimir("1) COSTO DE ALOJAMIENTO S/ " + Formato.ajusteB(i.getTotalPagar()));
		imprimir();
		imprimir("2) PRECIO POR DÍA S/ " + Formato.ajusteB(c.getPrecioDia()));
		imprimir();
		imprimir("3) DÍAS TRANSCURRIDOS " + dias);
		imprimir();
		imprimir("4) TOTAL A PAGAR S/ " + Formato.ajusteB(totalPagar));
	}
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	void mensaje(String s) {
		JOptionPane.showMessageDialog(this, s, "Información", 1);
	}
	//  Métodos que retornan valor (sin parámetros)
	int leerCodigoInternamiento() {
		return Integer.parseInt(cboCodigoInternamiento.getSelectedItem().toString());
	}
	//  Métodos que retornan valor (con parámetros)
	int confirmar(String s) {
		return JOptionPane.showConfirmDialog(this, s, "Alerta", 0, 1, null);
	}

}