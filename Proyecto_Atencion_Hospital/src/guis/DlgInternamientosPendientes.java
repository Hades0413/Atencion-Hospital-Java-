package guis;

import clases.*;
import arreglos.*;
import libreria.*;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgInternamientosPendientes extends JDialog {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgInternamientosPendientes dialog = new DlgInternamientosPendientes();
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
	public DlgInternamientosPendientes() {
		setResizable(false);
		setTitle("Reporte | Internamientos pendientes");
		setBounds(100, 100, 710, 410);
		getContentPane().setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 15, 675, 345);
		getContentPane().add(scrollPane);
	
		txtS = new JTextArea();
		txtS.setFont(new Font("Monospaced", Font.PLAIN, 15));
		scrollPane.setViewportView(txtS);
		
		listar();
	}
	
	//  Métodos tipo void (sin parámetros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloInternamientos ai = new ArregloInternamientos();
		ArregloPacientes ap = new ArregloPacientes();
		ArregloCamas ac = new ArregloCamas();
		Internamiento i;
		Paciente p;
		Cama c;
		txtS.setText("");
		for (int j=0; j<ai.tamaño(); j++) {
			i = ai.obtener(j);
			if (i.getEstado() == 0) {
				p = ap.buscar(i.getCodigoPaciente());
				c = ac.buscar(i.getNumeroCama());
				imprimir("Código de internamiento :  " + i.getCodigoInternamiento());
				imprimir("Código de paciente      :  " + p.getCodigoPaciente());
				imprimir("Nombres                 :  " + p.getNombres());
				imprimir("Apellidos               :  " + p.getApellidos());
				imprimir("Número de cama          :  " + i.getNumeroCama());
				imprimir("Categoría               :  " + Lib.categoriasCama[c.getCategoria()]);
				imprimir("Precio por día          :  S/ " + Formato.ajusteB(c.getPrecioDia()));
				imprimir("Fecha de ingreso        :  " + i.getFechaIngreso()
				                                       + " - " + i.getHoraIngreso());
				imprimir();
			}
		}
	}
	//  Métodos tipo void (con parámetros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}

}