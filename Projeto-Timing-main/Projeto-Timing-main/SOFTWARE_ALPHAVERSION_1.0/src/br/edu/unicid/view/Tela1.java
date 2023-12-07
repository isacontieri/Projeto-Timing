
package br.edu.unicid.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import br.edu.unicid.bean.Cadastro;
import br.edu.unicid.dao.CadastroDAO;

public class Tela1 extends JFrame {

	private JPanel contentPane;
	private JButton BTNAdicionar;
	private JButton BTNExcluir;
	private JButton BTNSalvar;
	private JLabel lblNome;
	private JLabel lblTelefone;
	private JLabel lblEmail;
	private JTextField textFieldNome;
	private JTextField textFieldTelefone;
	private JTextField textFieldEmail;
	private JList list;
	private JList list_1;
	private JTable table;
	private JScrollPane tabelaUsuario;
	private JLabel lblTrabalho;
	private JTextField textFieldTrabalho;
	private JButton BTNSair;
	private JSeparator separator_2;
	private JTextField ID;
	private JLabel lblNewLabel_1;
	private JLabel lblImage;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela1 frame = new Tela1();
					frame.setVisible(true);
					frame.listarValores();

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Tela1() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ms275\\OneDrive\\Desktop\\logo_2_134x79.jpeg"));
		setTitle("Gestão de horários");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 777, 557);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(47, 52, 57));
		contentPane.setForeground(new Color(126, 185, 242));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		BTNAdicionar = new JButton("Adicionar");
		BTNAdicionar.setFont(new Font("Arial", Font.PLAIN, 15));
		BTNAdicionar.setBackground(new Color(126, 185, 242));
		BTNAdicionar.setBounds(590, 135, 120, 50);
		BTNAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ADD();
				listarValores();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(BTNAdicionar);

		BTNExcluir = new JButton("Excluir");
		BTNExcluir.setFont(new Font("Arial", Font.PLAIN, 15));
		BTNExcluir.setBackground(new Color(126, 185, 242));
		BTNExcluir.setBounds(590, 335, 120, 50);
		BTNExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CarregarDeletarCampo();
				listarValores();
				limparCampo();
			}
		});
		contentPane.add(BTNExcluir);

		BTNSalvar = new JButton("Salvar");
		BTNSalvar.setFont(new Font("Arial", Font.PLAIN, 15));
		BTNSalvar.setBackground(new Color(126, 185, 242));
		BTNSalvar.setBounds(590, 235, 120, 50);
		BTNSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SalvarDado();
			}
		});
		contentPane.add(BTNSalvar);

		lblNome = new JLabel("Nome");
		lblNome.setForeground(new Color(126, 185, 242));
		lblNome.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNome.setBounds(60, 3, 61, 24);
		contentPane.add(lblNome);

		lblTelefone = new JLabel("Data");
		lblTelefone.setForeground(new Color(126, 185, 242));
		lblTelefone.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTelefone.setBounds(60, 30, 89, 24);
		contentPane.add(lblTelefone);

		lblEmail = new JLabel("Hora");
		lblEmail.setForeground(new Color(126, 185, 242));
		lblEmail.setFont(new Font("Arial", Font.PLAIN, 20));
		lblEmail.setBounds(60, 57, 60, 24);
		contentPane.add(lblEmail);

		textFieldNome = new JTextField();
		textFieldNome.setBounds(170, 3, 318, 25);
		contentPane.add(textFieldNome);
		textFieldNome.setColumns(10);

		textFieldTelefone = new JTextField();
		textFieldTelefone.setBounds(170, 30, 318, 25);
		contentPane.add(textFieldTelefone);
		textFieldTelefone.setColumns(10);

		textFieldEmail = new JTextField();
		textFieldEmail.setBounds(170, 57, 318, 25);
		contentPane.add(textFieldEmail);
		textFieldEmail.setColumns(10);

		list = new JList();
		list.setBounds(36, 353, 390, -189);
		contentPane.add(list);

		list_1 = new JList();
		list_1.setBounds(91, 206, 1, 1);
		contentPane.add(list_1);

		tabelaUsuario = new JScrollPane();
		tabelaUsuario.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tabelaUsuario.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		tabelaUsuario.setBounds(10, 133, 511, 209);
		contentPane.add(tabelaUsuario);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 15));
		table.setSurrendersFocusOnKeystroke(true);
		table.setForeground(new Color(0, 0, 0));
		table.setBackground(new Color(225, 228, 228));
		tabelaUsuario.setViewportView(table);
		table.setModel(new DefaultTableModel(
				new Object[][] {
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
						{ null, null, null, null, null },
				},
				new String[] {
						"COD_ID","Nome", "Data", "Hora", "Info"
				}));
		table.getColumnModel().getColumn(0).setPreferredWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(139);
		table.getColumnModel().getColumn(2).setPreferredWidth(200);
		table.getColumnModel().getColumn(3).setPreferredWidth(131);

		lblTrabalho = new JLabel("Info");
		lblTrabalho.setForeground(new Color(126, 185, 242));
		lblTrabalho.setFont(new Font("Arial", Font.PLAIN, 20));
		lblTrabalho.setBounds(60, 88, 71, 16);
		contentPane.add(lblTrabalho);

		textFieldTrabalho = new JTextField();
		textFieldTrabalho.setBounds(170, 84, 318, 25);
		contentPane.add(textFieldTrabalho);
		textFieldTrabalho.setColumns(10);

		BTNSair = new JButton("Sair");
		BTNSair.setFont(new Font("Arial", Font.PLAIN, 15));
		BTNSair.setBackground(new Color(126, 185, 242));
		BTNSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		BTNSair.setBounds(590, 435, 120, 50);
		contentPane.add(BTNSair);

		separator_2 = new JSeparator();
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setBounds(531, 0, 23, 528);
		contentPane.add(separator_2);
		
		ID = new JTextField();
		ID.setBounds(665, 496, 0, 0);
		contentPane.add(ID);
		ID.setColumns(10);
		
		lblNewLabel_1 = new JLabel("TIMING");
		lblNewLabel_1.setForeground(new Color(20, 96, 200));
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 80));
		lblNewLabel_1.setBounds(120, 389, 280, 93);
		contentPane.add(lblNewLabel_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Tela1.class.getResource("/logo.png")));
		lblNewLabel.setBounds(585, 20, 134, 79);
		contentPane.add(lblNewLabel);
		Image img = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();

	}

	
	//mostra os valores do banco de dados na tabela dentro do software
	private void listarValores() {
		try {
			CadastroDAO objcadastroDAO = new CadastroDAO();

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setNumRows(0);
			
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);

			ArrayList<Cadastro> list = objcadastroDAO.Listar();
			for (int num = 0; num < list.size(); num++) {
				model.addRow(new Object[] {
						list.get(num).getId(),
						list.get(num).getNome(),
						list.get(num).getTelefone(),
						list.get(num).getEmail(),
						list.get(num).getEscala()
				});
			}
		} catch (Exception erro) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Listar Valores VIEW:" + erro);
		}
	}

	
	//função para salvar os dados inseridos em um arquivo .txt
	//Ligado à função Salvar (localizado na aba CadastroDAO)
	private void SalvarDado() {
		CadastroDAO dao = new CadastroDAO();
		StringBuilder sb = new StringBuilder();

		for (Cadastro bean : dao.salvar()) {
			sb.append(dao.processaObjeto(bean) + "\n");
		}
		OutputStream os;
		try {
			os = new FileOutputStream("SEU RELATORIO.txt");
			try {
				os.write(sb.toString().getBytes());
				os.flush();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // ou txt

	}
	
	
	//Limpar o campo do textfield oculto (evita erros e possiveis bugs)
	private void limparCampo() {
		ID.setText ("");
		textFieldNome.setText ("");
		textFieldEmail.setText ("");
		textFieldTelefone.setText ("");
		textFieldTrabalho.setText ("");
	}
	
	
	
	// Abaixo carrega variavel ID do campo oculto da tabela para text field ID oculto e usa esse parametro para deletar linha do BD
	//Ligado ao objeto ExcluirFuncionario (localizado na aba cadastroDAO)
	private void CarregarDeletarCampo() {
		
		int setar = table.getSelectedRow();
		
		ID.setText(table.getModel().getValueAt(setar, 0).toString());
        
		int idfuncionario = Integer.parseInt(ID.getText());
		
		Cadastro objParDelSQL = new Cadastro();
		objParDelSQL.setId(idfuncionario);
		
		CadastroDAO objDelSQL = new CadastroDAO();
		objDelSQL.ExcluirFuncionario(objParDelSQL);

	}
	
	
	//Adiciona as informações dos campos de texto no banco de dados
	//Esta ligado ao objeto objCadastro (Localizado na aba CADASTRODAO)
	private void ADD() {
		String nome, telefone, email, escala;
		nome = textFieldNome.getText();
		telefone = textFieldTelefone.getText();
		email = textFieldEmail.getText();
		escala = textFieldTrabalho.getText();

		Cadastro objCadastro = new Cadastro();
		objCadastro.setNome(nome);
		objCadastro.setTelefone(telefone);
		objCadastro.setEmail(email);
		objCadastro.setEscala(escala);

		CadastroDAO objCadastroDAO = new CadastroDAO();
		objCadastroDAO.cadastrar(objCadastro);
		limparCampo();
	}
}