package vista;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import arrays.ArrayAlumno;
import arrays.ArrayCurso;
import arrays.ArrayMatricula;
import arrays.ArrayRetiro;

import entidad.Curso;
import entidad.Matricula;
import entidad.Retiro;

import mantenimiento.GestionRetiroDAO;

import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;

public class RegistroRetiroMod extends JInternalFrame {
	private JTable table;
	private JScrollPane scrollPane;
	private JRadioButton rdbtn_Consultar;
	private JRadioButton rdbtn_Modificar;
	private DefaultTableModel modelo;
	private JButton btn_Limpiar;
	private JTextField text_Retiro;
	private JLabel lblNewLabel;
	private JTextField text_Alumno;
	private JLabel lblNewLabel_1;
	private JTextField text_Matricula;
	private JLabel lblNewLabel_2;
	private JTextField text_Fecha;
	private JTextField text_Hora;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JButton btn_Consultar;
	private JButton btn_Modificar;
	private JButton btn_Eliminar;
	private JTextField text_Curso;
	private JLabel lblNewLabel_5;

		// Button Groups
	ButtonGroup group = new ButtonGroup();
	
		// ArrayList
	ArrayMatricula AM = new ArrayMatricula();
	ArrayAlumno AA = new ArrayAlumno();
	ArrayCurso AC = new ArrayCurso();
	ArrayRetiro AR = new ArrayRetiro(); 
	
		// GestionDAO
	GestionRetiroDAO gRetiro = new GestionRetiroDAO();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroRetiroMod frame = new RegistroRetiroMod();
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
	public RegistroRetiroMod() {
		setTitle("Registro Matricula Consultar, Modificar, Eliminar");
		setBounds(100, 100, 450, 300);
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBorder(null);
		setBounds(100, 100, 620, 600);
		getContentPane().setLayout(null);
		
		rdbtn_Consultar = new JRadioButton("Consultar");
		rdbtn_Consultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedRdbtn_Consultar(e);
			}
		});
		rdbtn_Consultar.setSelected(true);
		rdbtn_Consultar.setBounds(51, 38, 103, 21);
		getContentPane().add(rdbtn_Consultar);
		
		rdbtn_Modificar = new JRadioButton("Modificar");
		rdbtn_Modificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn_Modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedRdbtn_Modificar(e);
			}
		});
		rdbtn_Modificar.setBounds(156, 38, 103, 21);
		getContentPane().add(rdbtn_Modificar);
		JRadioButton rdbtn_Eliminar = new JRadioButton("Eliminar");
		rdbtn_Eliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		rdbtn_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedRdbtn_Eliminar(e);
			}
		});
		rdbtn_Eliminar.setBounds(261, 38, 90, 21);
		getContentPane().add(rdbtn_Eliminar);
		group.add(rdbtn_Consultar);
		group.add(rdbtn_Eliminar);
		group.add(rdbtn_Modificar);

		
		
		
		// Hacemos el Default de la Tabla
		//	Mostramos la tabla
		modelo = new DefaultTableModel();
		modelo.addColumn("N� Retiro");
		modelo.addColumn("N� Matricula");
		modelo.addColumn("Cod. Alumno");
		modelo.addColumn("Cod. Curso");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		MostramosTabla();
		
		btn_Limpiar = new JButton("");
		btn_Limpiar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Limpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtn_Limpiar(e);
			}
		});
		btn_Limpiar.setIcon(new ImageIcon(RegistroRetiroMod.class.getResource("/imagenes/basura.png")));
		btn_Limpiar.setBounds(393, 31, 37, 41);
		getContentPane().add(btn_Limpiar);
		
		text_Retiro = new JTextField();
		text_Retiro.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedText_Retiro(e);
			}
		});
		text_Retiro.setFont(new Font("Tahoma", Font.PLAIN, 12));
		text_Retiro.setBounds(109, 110, 96, 19);
		getContentPane().add(text_Retiro);
		text_Retiro.setColumns(10);
		
		lblNewLabel = new JLabel("N\u00B0 Retiro");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(30, 113, 69, 13);
		getContentPane().add(lblNewLabel);
		
		text_Alumno = new JTextField();
		text_Alumno.setEditable(false);
		text_Alumno.setFont(new Font("Tahoma", Font.PLAIN, 12));
		text_Alumno.setBounds(109, 187, 96, 19);
		getContentPane().add(text_Alumno);
		text_Alumno.setColumns(10);
		
		lblNewLabel_1 = new JLabel("N\u00B0 Matr\u00EDcula");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(30, 151, 75, 13);
		getContentPane().add(lblNewLabel_1);
		
		text_Matricula = new JTextField();
		text_Matricula.setEditable(false);
		text_Matricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
		text_Matricula.setBounds(109, 148, 96, 19);
		getContentPane().add(text_Matricula);
		text_Matricula.setColumns(10);
		
		lblNewLabel_2 = new JLabel("Cod. Alumno");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(30, 190, 75, 13);
		getContentPane().add(lblNewLabel_2);
		
		text_Fecha = new JTextField();
		text_Fecha.setEditable(false);
		text_Fecha.setFont(new Font("Tahoma", Font.PLAIN, 12));
		text_Fecha.setBounds(334, 110, 96, 19);
		getContentPane().add(text_Fecha);
		text_Fecha.setColumns(10);
		
		text_Hora = new JTextField();
		text_Hora.setEditable(false);
		text_Hora.setFont(new Font("Tahoma", Font.PLAIN, 12));
		text_Hora.setBounds(334, 148, 96, 19);
		getContentPane().add(text_Hora);
		text_Hora.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Fecha");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(261, 112, 60, 13);
		getContentPane().add(lblNewLabel_3);
		
		lblNewLabel_4 = new JLabel("Hora");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(261, 151, 60, 13);
		getContentPane().add(lblNewLabel_4);
		
		btn_Consultar = new JButton("Consultar");
		btn_Consultar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtn_Consultar(e);
			}
		});
		btn_Consultar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Consultar.setBounds(480, 51, 96, 21);
		getContentPane().add(btn_Consultar);
		
		btn_Modificar = new JButton("Modificar");
		btn_Modificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtn_Modificar(e);
			}
		});
		btn_Modificar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Modificar.setEnabled(false);
		btn_Modificar.setBounds(480, 120, 96, 21);
		getContentPane().add(btn_Modificar);
		
		btn_Eliminar = new JButton("Eliminar");
		btn_Eliminar.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btn_Eliminar.setEnabled(false);
		btn_Eliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnNewButton_1(e);
			}
		});
		btn_Eliminar.setBounds(480, 185, 96, 21);
		getContentPane().add(btn_Eliminar);
		
		text_Curso = new JTextField();
		text_Curso.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				keyTypedText_Curso(e);
			}
		});
		text_Curso.setEditable(false);
		text_Curso.setBounds(334, 188, 96, 19);
		getContentPane().add(text_Curso);
		text_Curso.setColumns(10);
		
		lblNewLabel_5 = new JLabel("Cod. Curso");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setBounds(261, 191, 112, 13);
		getContentPane().add(lblNewLabel_5);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 266, 588, 260);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setFillsViewportHeight(true);
		scrollPane.setViewportView(table);
		table.setModel(modelo);
		MostramosTabla();
	}
	
	void NoEspeciales (KeyEvent e) {
		char caracter = e.getKeyChar();
		if (!(Character.isLetter(caracter) || Character.isDigit(caracter))) {
			getToolkit();
			e.consume();
		}
	}
	
	//	Metodo para no exceder los digitos
	void BorrandoDigitos (KeyEvent e,int field) {
		switch (field) {
			case 1: 
				if (text_Retiro.getText().length() >= 7)
					e.consume();
						break;
			case 2: 
				if(text_Curso.getText().length() >= 6)
			        e.consume();
						break;
		}
	}
	
		//	Validar Retiro
	protected void keyTypedText_Retiro(KeyEvent e) {
		BorrandoDigitos(e, 1);
		NoEspeciales(e);
		
	}
		//	Validar Curso
	protected void keyTypedText_Curso(KeyEvent e) {
		BorrandoDigitos(e, 2);
		NoEspeciales(e);
	}
	
	//	Metodo Leer String
	String LeerString(JTextField text) {
		return text.getText().trim().toString();
	}

	//	Metodo Error
	void Error(String x, JTextField text) {
		JOptionPane.showMessageDialog(this, "El campo " + x + " no ha sido llenado", "ERROR", 0);
		text.setText("");
		text.requestFocus();
	}
	
	//	Metodo No Existe
	void NoExiste(String x, JTextField text) {
		JOptionPane.showMessageDialog(this, "No existe el " + x , "ERROR", 0);
		text.requestFocus();
	}
	
	//	M�todo Limpiar
	void Limpiar() {
		text_Retiro.setText("");
		text_Matricula.setText("");
		text_Alumno.setText("");
		text_Curso.setText("");
		text_Fecha.setText("");
		text_Hora.setText("");
	}
	
	//	M�todo Mostrar Tabla
	void MostramosTabla() {
		modelo.setRowCount(0);
		for (int i = 0; i < AR.tamanio(); i++) {
			Object [] fila = {
					AR.obtener(i).getNumRetiro(),
					AR.obtener(i).getNumMatricula(),
					AM.buscar(AR.obtener(i).getNumMatricula()).getCodAlumno(),
					AM.buscar(AR.obtener(i).getNumMatricula()).getCodCurso(),
					AR.obtener(i).getFecha(),
					AR.obtener(i).getHora(),
			};
			modelo.addRow(fila);
		}
	}
	//Metodo Obtener Retiro
	Retiro getRetiro() {
		for(int i = 0; i < AR.tamanio(); i++) {
			if(AR.obtener(i).getNumRetiro().equals(LeerString(text_Retiro))) {
				return AR.obtener(i);
			}
		}
		return null;
	}
	
	

	//Radious button Modificar
	protected void actionPerformedRdbtn_Modificar(ActionEvent e) {
		btn_Modificar.setEnabled(true);
		btn_Eliminar.setEnabled(false);
		btn_Consultar.setEnabled(false);
		text_Retiro.setEditable(false);
		text_Matricula.setEditable(false);
		text_Alumno.setEditable(false);
		text_Curso.setEditable(true);
		text_Fecha.setEditable(false);
		text_Hora.setEditable(false);
	}
	
	//Radious button Consultar
	protected void actionPerformedRdbtn_Consultar(ActionEvent e) {
		btn_Modificar.setEnabled(false);
		btn_Eliminar.setEnabled(false);
		btn_Consultar.setEnabled(true);
		text_Retiro.setEditable(true);
		text_Matricula.setEditable(false);
		text_Alumno.setEditable(false);
		text_Curso.setEditable(false);
		text_Fecha.setEditable(false);
		text_Hora.setEditable(false);
	}
	
	//Radious button Eliminar
	protected void actionPerformedRdbtn_Eliminar(ActionEvent e) {
		btn_Modificar.setEnabled(false);
		btn_Eliminar.setEnabled(true);
		btn_Consultar.setEnabled(false);
		text_Retiro.setEditable(false);
		text_Matricula.setEditable(false);
		text_Alumno.setEditable(false);
		text_Curso.setEditable(false);
		text_Fecha.setEditable(false);
		text_Hora.setEditable(false);
	}
	
	// Bot�n limpiar
	protected void actionPerformedBtn_Limpiar(ActionEvent e) {
		Limpiar();
	}
	
	//Boton Eliminar
	protected void actionPerformedBtnNewButton_1(ActionEvent e) {
		/*String num = LeerString(text_Retiro);
		Retiro ret = getRetiro();
		if(num.length() != 0) {
			if(ret != null){
				AR.eliminar(ret);
				MostramosTabla();
				Limpiar();	
			}
			else {
				NoExiste("N�mero de Retiro", text_Retiro);
			}
		}
		else {
			Error("N� Retiro", text_Retiro);
		}*/
		
		String numRetiro = LeerString(text_Retiro);
		if(numRetiro.length() != 0) {
			Retiro retiro = gRetiro.leer(numRetiro);
			if(retiro != null){
				int confirmacion = JOptionPane.showConfirmDialog(this, "�Desea eliminar el Retiro?", "James School", JOptionPane.YES_NO_OPTION);
				
				if (confirmacion == 0) {
					
				}
				
				MostramosTabla();
				Limpiar();
			}
			else {
				NoExiste("N�mero de Retiro", text_Retiro);
			}
		}
		else {
			Error("N� Retiro", text_Retiro);
		}
	}
	Curso ObtenerCurso() {
		for (int i = 0; i < AC.tamanio(); i++) {
			if(AC.obtener(i).getCodCurso().equals(LeerString(text_Curso))) {
				return AC.obtener(i);
			}
		}
		return null;
	}
	
	//Boton Consultar
	protected void actionPerformedBtn_Consultar(ActionEvent e) {
		/*String Retiro = LeerString(text_Retiro);
		if(Retiro.length() != 0) {
			if(AR.buscar(Retiro) != null) {
				text_Matricula.setText(AR.buscar(Retiro).getNumMatricula());
				String Matricula = AR.buscar(Retiro).getNumMatricula();
				if(Matricula.length() != 0) {
					text_Curso.setText(AM.buscar(Matricula).getCodCurso());
					text_Alumno.setText(AM.buscar(Matricula).getCodAlumno());
					text_Fecha.setText(AR.buscar(Retiro).getFecha());
					text_Hora.setText(AR.buscar(Retiro).getHora());
				}
			}
			else {
				NoExiste("N� MATRICULA", text_Matricula);
			}
		}
		else {
			Error("MATRICULA", text_Retiro);
		}*/
		
		String codRetiro = LeerString(text_Retiro);
		if(codRetiro.length() != 0) {
			if(gRetiro.leer(codRetiro) != null) {
				Retiro retiro = new Retiro();
				String matricula = retiro.getNumMatricula();
				text_Matricula.setText(retiro.getNumMatricula());
				if(matricula.length() != 0) {
					text_Curso.setText(AM.buscar(matricula).getCodCurso());
					text_Alumno.setText(AM.buscar(matricula).getCodAlumno());
					text_Fecha.setText(retiro.getFecha());
					text_Hora.setText(retiro.getHora());
				}
			}
			else {
				NoExiste("N� MATRICULA", text_Matricula);
			}
		}
		else {
			Error("MATRICULA", text_Retiro);
		}
	}	
	//Bot�n Modificar
	protected void actionPerformedBtn_Modificar(ActionEvent e) {
		String Matricula = LeerString(text_Matricula);
		String codCurso = LeerString(text_Curso);
		if(codCurso.length() != 0) {
			//	Curso curso = ObtenerCurso();
			Curso curso = AC.buscar(codCurso);
			if(curso != null) {
				//curso.setCodCurso(codCurso);
				// AA.buscar(curso.getCodAlumno());
				AM.buscar(Matricula).setCodCurso(codCurso);
				AM.actualizarArchivo();
				MostramosTabla();
			}
		}
	}
}
