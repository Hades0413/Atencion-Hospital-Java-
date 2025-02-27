package guis;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.UIManager;

public class DlgAtencionHospital extends JFrame implements ActionListener {

	private JLabel lblFondo;
	private JMenuBar menuProyecto;
	private JMenu mnArchivo;
	private JMenu mnMantenimiento;
	private JMenu mnRegistro;
	private JMenu mnPago;
	private JMenu mnReporte;
	private JMenuItem mntmSalir;
	private JMenuItem mntmCama;
	private JMenuItem mntmPaciente;
	private JMenuItem mntmMedicina;
	private JMenuItem mntmAtencion;
	private JMenuItem mntmInternamiento;
	private JMenuItem mntmAtenciones;
	private JMenuItem mntmInternamientos;
	private JMenuItem mntmAtencionesPendientes;
	private JMenuItem mntmAtencionesPagadas;
	private JMenuItem mntmInternamientosPendientes;
	private JMenuItem mntmInternamientosPagados;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DlgAtencionHospital frame = new DlgAtencionHospital();
					frame.setVisible(true);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public DlgAtencionHospital() {
		int ANCHO = 750, ALTO = 500,
			DX = 16, DY = 62;
		
		setResizable(false);
		setTitle("Atencion Hospital");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\migve\\OneDrive\\Escritorio\\Repositorio\\Atencion Hospital\\Proyecto_Atencion_Hospital\\imagenes\\icono.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(ANCHO + DX, ALTO + DY);
		this.setLocationRelativeTo(null);
		
		menuProyecto = new JMenuBar();
		setJMenuBar(menuProyecto);
		
		mnArchivo = new JMenu("Archivo");
		menuProyecto.add(mnArchivo);
		
		mntmSalir = new JMenuItem("Salir");  
		mntmSalir.addActionListener(this);
		mnArchivo.add(mntmSalir);
		
		mnMantenimiento = new JMenu("Mantenimiento");
		menuProyecto.add(mnMantenimiento);
		
		mntmCama = new JMenuItem("Cama");
		mntmCama.addActionListener(this);
		mnMantenimiento.add(mntmCama);
		
		mntmPaciente = new JMenuItem("Paciente");
		mntmPaciente.addActionListener(this);
		mnMantenimiento.add(mntmPaciente);
		
		mntmMedicina = new JMenuItem("Medicina");
		mntmMedicina.addActionListener(this);
		mnMantenimiento.add(mntmMedicina);
		
		mnRegistro = new JMenu("Registro");
		menuProyecto.add(mnRegistro);
		
		mntmAtencion = new JMenuItem("Atenci�n");
		mntmAtencion.addActionListener(this);
		mnRegistro.add(mntmAtencion);
		
		mntmInternamiento = new JMenuItem("Internamiento");
		mntmInternamiento.addActionListener(this);
		mnRegistro.add(mntmInternamiento);
		
		mnPago = new JMenu("Pago");
		menuProyecto.add(mnPago);
		
		mntmAtenciones = new JMenuItem("Atenciones");
		mntmAtenciones.addActionListener(this);
		mnPago.add(mntmAtenciones);
	
		mntmInternamientos = new JMenuItem("Internamientos");
		mntmInternamientos.addActionListener(this);
		mnPago.add(mntmInternamientos);
		
		mnReporte = new JMenu("Reporte");
		menuProyecto.add(mnReporte);
	
		mntmAtencionesPendientes = new JMenuItem("Atenciones pendientes");
		mntmAtencionesPendientes.addActionListener(this);
		mnReporte.add(mntmAtencionesPendientes);
		
		mntmAtencionesPagadas = new JMenuItem("Atenciones pagadas");
		mntmAtencionesPagadas.addActionListener(this);
		mnReporte.add(mntmAtencionesPagadas);
		
		mntmInternamientosPendientes = new JMenuItem("Internamientos pendientes");
		mntmInternamientosPendientes.addActionListener(this);
		mnReporte.add(mntmInternamientosPendientes);
		
		mntmInternamientosPagados = new JMenuItem("Internamientos pagados");
		mntmInternamientosPagados.addActionListener(this);
		mnReporte.add(mntmInternamientosPagados);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblFondo = new JLabel(new ImageIcon("C:\\Users\\migve\\OneDrive\\Escritorio\\Repositorio\\Atencion Hospital\\Proyecto_Atencion_Hospital\\imagenes\\itachi.jpg"));
		lblFondo.setBounds(0, 0, 750, 688);
		getContentPane().add(lblFondo);	
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mntmInternamientosPagados) {
			actionPerformedMntmInternamientosPagados(e);
		}
		if (e.getSource() == mntmInternamientosPendientes) {
			actionPerformedMntmInternamientosPendientes(e);
		}
		if (e.getSource() == mntmAtencionesPagadas) {
			actionPerformedMntmAtencionesPagadas(e);
		}
		if (e.getSource() == mntmAtencionesPendientes) {
			actionPerformedMntmAtencionesPendientes(e);
		}
		if (e.getSource() == mntmInternamientos) {
			actionPerformedMntmInternamientos(e);
		}
		if (e.getSource() == mntmAtenciones) {
			actionPerformedMntmAtenciones(e);
		}	
		if (e.getSource() == mntmInternamiento) {
			actionPerformedMntmInternamiento(e);
		}
		if (e.getSource() == mntmAtencion) {
			actionPerformedMntmAtencion(e);
		}
		if (e.getSource() == mntmMedicina) {
			actionPerformedMntmMedicina(e);
		}
		if (e.getSource() == mntmPaciente) {
			actionPerformedMntmPaciente(e);
		}
		if (e.getSource() == mntmCama) {
			actionPerformedMntmCama(e);
		}
		if (e.getSource() == mntmSalir) {
			actionPerformedMntmSalir(e);
		}
	}
	protected void actionPerformedMntmSalir(ActionEvent arg0) {
		System.exit(0);
	}
	protected void actionPerformedMntmCama(ActionEvent arg0) {
		DlgCama dc = new DlgCama();
		dc.setLocationRelativeTo(this);
		dc.setVisible(true);
	}
	protected void actionPerformedMntmPaciente(ActionEvent arg0) {
		DlgPaciente dp = new DlgPaciente();
		dp.setLocationRelativeTo(this);
		dp.setVisible(true);
	}
	protected void actionPerformedMntmMedicina(ActionEvent arg0) {
		DlgMedicina dm = new DlgMedicina();
		dm.setLocationRelativeTo(this);
		dm.setVisible(true);
	}
	protected void actionPerformedMntmAtencion(ActionEvent arg0) {
		DlgAtencion da = new DlgAtencion();
		da.setLocationRelativeTo(this);
		da.setVisible(true);
	}
	protected void actionPerformedMntmInternamiento(ActionEvent arg0) {
		DlgInternamiento di = new DlgInternamiento();
		di.setLocationRelativeTo(this);
		di.setVisible(true);
	}
	protected void actionPerformedMntmAtenciones(ActionEvent arg0) {
		DlgAtenciones da = new DlgAtenciones();
		da.setLocationRelativeTo(this);
		da.setVisible(true);
	}
	protected void actionPerformedMntmInternamientos(ActionEvent arg0) {
		DlgInternamientos di = new DlgInternamientos();
		di.setLocationRelativeTo(this);
		di.setVisible(true);
	}
	protected void actionPerformedMntmAtencionesPendientes(ActionEvent arg0) {
		DlgAtencionesPendientes dap = new DlgAtencionesPendientes();
		dap.setLocationRelativeTo(this);
		dap.setVisible(true);
	}
	protected void actionPerformedMntmAtencionesPagadas(ActionEvent arg0) {
		DlgAtencionesPagadas dap = new DlgAtencionesPagadas();
		dap.setLocationRelativeTo(this);
		dap.setVisible(true);
	}
	protected void actionPerformedMntmInternamientosPendientes(ActionEvent arg0) {
		DlgInternamientosPendientes dip = new DlgInternamientosPendientes();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	protected void actionPerformedMntmInternamientosPagados(ActionEvent arg0) {
		DlgInternamientosPagados dip = new DlgInternamientosPagados();
		dip.setLocationRelativeTo(this);
		dip.setVisible(true);
	}
	
}