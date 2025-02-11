package vista;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import arrays.ArrayAlumno;
import arrays.ArrayCurso;
import arrays.ArrayMatricula;
import entidad.Alumno;
import entidad.Curso;
import entidad.Matricula;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

public class RegistroMatriculaAdicionar extends JInternalFrame {
	private JTextField text_Matricula;
	private JTextField text_Alumno;
	private JTextField text_Curso;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblAdicionarMatricula;
	private JLabel lbl_Imagen;
	private JScrollPane scrollPane;
	private JButton btn_Adicionar;
	private JTable table;
	private DefaultTableModel modelo;

		//	Arrays Globoterraqueos
	ArrayMatricula AM = new ArrayMatricula();
	ArrayAlumno AA = new ArrayAlumno();
	ArrayCurso AC = new ArrayCurso();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroMatriculaAdicionar frame = new RegistroMatriculaAdicionar();
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
	public RegistroMatriculaAdicionar() {
		setTitle("Registro Matricula Adicionar");
		setIconifiable(true);
		setMaximizable(true);
		setClosable(true);
		setBorder(null);
		setBounds(100, 100, 620, 600);
		getContentPane().setLayout(null);
		{
			text_Matricula = new JTextField();
			text_Matricula.setFont(new Font("Tahoma", Font.PLAIN, 12));
			text_Matricula.setText(AM.codigoCorrelativo());
			text_Matricula.setEditable(false);
			text_Matricula.setColumns(10);
			text_Matricula.setBounds(153, 130, 140, 19);
			getContentPane().add(text_Matricula);
		}
		{
			text_Alumno = new JTextField();
			text_Alumno.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedText_Alumno(e);
				}
			});
			text_Alumno.setFont(new Font("Tahoma", Font.PLAIN, 12));
			text_Alumno.setColumns(10);
			text_Alumno.setBounds(153, 164, 140, 19);
			getContentPane().add(text_Alumno);
		}
		{
			text_Curso = new JTextField();
			text_Curso.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					keyTypedText_Curso(e);
				}
			});
			text_Curso.setFont(new Font("Tahoma", Font.PLAIN, 12));
			text_Curso.setColumns(10);
			text_Curso.setBounds(432, 132, 140, 19);
			getContentPane().add(text_Curso);
		}
		{
			lblNewLabel = new JLabel("Cod. Curso");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel.setBounds(329, 130, 93, 15);
			getContentPane().add(lblNewLabel);
		}
		{
			lblNewLabel_1 = new JLabel("Cod. Alumno");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(50, 162, 93, 15);
			getContentPane().add(lblNewLabel_1);
		}
		{
			lblNewLabel_2 = new JLabel("Num. Matricula");
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			lblNewLabel_2.setBounds(50, 128, 93, 15);
			getContentPane().add(lblNewLabel_2);
		}
		{
			lblAdicionarMatricula = new JLabel("Adicionar Matricula");
			lblAdicionarMatricula.setFont(new Font("Consolas", Font.BOLD, 15));
			lblAdicionarMatricula.setBounds(113, 61, 180, 26);
			getContentPane().add(lblAdicionarMatricula);
		}
		{
			lbl_Imagen = new JLabel("");
			lbl_Imagen.setIcon(new ImageIcon(RegistroMatriculaAdicionar.class.getResource("/imagenes/group_icon-icons.com_63767.png")));
			lbl_Imagen.setHorizontalAlignment(SwingConstants.CENTER);
			lbl_Imagen.setBounds(345, 44, 150, 58);
			getContentPane().add(lbl_Imagen);
		}
		{
			scrollPane = new JScrollPane();
			scrollPane.setBounds(48, 217, 524, 311);
			getContentPane().add(scrollPane);
			{
				table = new JTable();
				table.setFillsViewportHeight(true);
				scrollPane.setViewportView(table);
			}
		}
		{
			btn_Adicionar = new JButton("Adicionar");
			btn_Adicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					actionPerformedBtn_Adicionar(e);
				}
			});
			btn_Adicionar.setBounds(395, 164, 100, 21);
			getContentPane().add(btn_Adicionar);
		}
		
		modelo = new DefaultTableModel();
		modelo.addColumn("Num. Matri");
		modelo.addColumn("Cod. Alum");
		modelo.addColumn("Cod. Curso");
		modelo.addColumn("Estado");
		modelo.addColumn("Fecha");
		modelo.addColumn("Hora");
		table.setModel(modelo);
		MostramosTabla();
	}
	
		//	Metodo Solo Numeros
	void SoloNumeros (KeyEvent e) {
		char caracter = e.getKeyChar();
		if (!(Character.isDigit(caracter))) {
			getToolkit();
			e.consume();
		}
	}
	
		//	Metodo Solo Letras
	void SoloLetras (KeyEvent e) {
		char caracter = e.getKeyChar();
		if (!(Character.isLetter(caracter) || caracter == e.VK_SPACE)) {
			getToolkit();
			e.consume();
		}
	}
		//	No caracteres especiales
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
				if(text_Alumno.getText().length() >= 9)
			        e.consume();
						break;
			case 2: 
				if(text_Curso.getText().length() >= 6)
			        e.consume();
						break;
		}
	}
	
	//	Validando Codigo Alumno
	protected void keyTypedText_Alumno(KeyEvent e) {
		NoEspeciales(e);
		BorrandoDigitos(e, 1);
	}
	
	//	Validando Codigo Curso
	protected void keyTypedText_Curso(KeyEvent e) {
		NoEspeciales(e);
		BorrandoDigitos(e, 2);
	}
	
		//	Metodo Leer String
	String LeerString(JTextField text) {
		return text.getText().trim().toString();
	}
	
		//	Metodo Error
	void Error(String x, JTextField text) {
		JOptionPane.showMessageDialog(this, "No relleno el campo " + x, "ERROR", 0);
		text.setText("");
		text.requestFocus();
	}
	
		//	Metodo No Existe
	void NoExiste(String x, JTextField text) {
		JOptionPane.showMessageDialog(this, "No existe el " + x, "ERROR", 0);
		text.requestFocus();
	}
	
		// Metodo Mostramos Tabla
	void MostramosTabla() {
		int rowcount = 0;
		modelo.setRowCount(rowcount);
		for (int i = 0; i < AM.tamanio(); i++) {
			Object [] fila = {
					AM.obtener(i).getNumMatricula(),
					AM.obtener(i).getCodAlumno(),
					AM.obtener(i).getCodCurso(),
					AA.buscarCod(AM.obtener(i).getCodAlumno()).getEstado(),
					AM.obtener(i).getFecha(),
					AM.obtener(i).getHora(),
			};
			modelo.addRow(fila);
		}
	}
	
		//	Metodo Fecha
	String Fecha() {
		return LocalDate.now() + "";
	}
	
		//	Metodo Hora
	String Hora() {
		return LocalTime.now() + "";
	}
	
	
		//	Metodo Adicionamos
	void Adicionamos (String Alumno, String Curso) {
		Matricula nuevo = new Matricula (AM.codigoCorrelativo(), Alumno, Curso, Fecha(), Hora());
		AM.adicionar(nuevo);
		MostramosTabla();
		text_Matricula.setText(AM.codigoCorrelativo());
	}
	


	//	Btn Adicionar
	protected void actionPerformedBtn_Adicionar(ActionEvent e) {
		String Alumno = LeerString(text_Alumno);
		if (Alumno.length() != 0) {
			Alumno alumno = AA.buscarCod(Alumno);
			if (alumno != null) {
				String Curso = LeerString(text_Curso);
				if (Curso.length() != 0) {
					Curso curso = AC.buscar(Curso);
					if (curso != null) {
						alumno.setEstado(1);
						AA.actulizarArchivos();
						Adicionamos(Alumno, Curso);
					}
					else {
						NoExiste("CURSO", text_Curso);
					}
				}
				else {
					Error("CURSO", text_Curso);
				}
			}
			else {
				NoExiste("ALUMNO", text_Alumno);
			}
		}
		else {
			Error("ALUMNO", text_Alumno);
		}
	}
}
