package guis;

import clases.*;
import arreglos.*;
import libreria.Formato;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DlgAtencionesPagadas extends JDialog {
	
	private JScrollPane scrollPane;
	private JTextArea txtS;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtencionesPagadas dialog = new DlgAtencionesPagadas();
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
	public DlgAtencionesPagadas() {
		setResizable(false);
		setTitle("Reporte | Atenciones pagadas");
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
	
	//  M�todos tipo void (sin par�metros)
	void imprimir() {
		imprimir("");
	}
	void listar() {
		ArregloAtenciones aa = new ArregloAtenciones();
		ArregloPacientes ap = new ArregloPacientes();
		Atencion a;
		Paciente p;
		txtS.setText("");
		for (int i=0; i<aa.tama�o(); i++) {
			a = aa.obtener(i);
			if (a.getEstado() == 1) {
				p = ap.buscar(a.getCodigoPaciente());
				imprimir("C�digo de atenci�n :  " + a.getCodigoAtencion());
				imprimir("C�digo de paciente :  " + a.getCodigoPaciente());
				imprimir("Nombres            :  " + p.getNombres());
				imprimir("Apellidos          :  " + p.getApellidos());
				imprimir("Fecha de atenci�n  :  " + a.getFechaAtencion()
				                                  + " - " + a.getHoraAtencion());
				imprimir("Costo atenci�n     :  S/ " + Formato.ajusteB(a.getTotalPagar()));
				double costoMedicinas = 0.0, importe;
				ArregloRecetas ar = new ArregloRecetas("" + a.getCodigoAtencion());
				Receta r;
				for (int j=0; j<ar.tama�o(); j++) {
					r = ar.obtener(j);
					importe = r.getPrecioUnitario() * r.getCantidad();
					costoMedicinas += importe;
				}
				imprimir("Costo medicinas    :  S/ " + Formato.ajusteB(costoMedicinas));
				imprimir("Total              :  S/ " + Formato.ajusteB(a.getTotalPagar() + costoMedicinas));
				imprimir();
			}
		}
	}
	//  M�todos tipo void (con par�metros)
	void imprimir(String s) {
		txtS.append(s + "\n");
	}
	
}