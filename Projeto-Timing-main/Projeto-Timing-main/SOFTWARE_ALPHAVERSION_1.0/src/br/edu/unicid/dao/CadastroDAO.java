package br.edu.unicid.dao;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import br.edu.unicid.bean.Cadastro;
import br.edu.unicid.util.Conexao;
import br.edu.unicid.util.*;

public class CadastroDAO {
	Connection conn;
	PreparedStatement pstm;
	ResultSet rs;
	ArrayList<Cadastro> list = new ArrayList<>();

	
	//LIGADO A FUNÇÃO DE INSERIR DADOS NO BD (Localizada na aba Tela1)
	public void cadastrar(Cadastro objCadastro) {
		String sql = "insert into cadastro(nome, telefone, email, escala) values (?,?,?,?)";

		try {
			conn = new Conexao().conectaBD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, objCadastro.getNome());
			pstm.setString(2, objCadastro.getTelefone());
			pstm.setString(3, objCadastro.getEmail());
			pstm.setString(4, objCadastro.getEscala());

			pstm.execute();
			pstm.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "CadastroDAO Adicionar" + erro);
		}
	}

	
	//Está ligado a função listarValores (localizado na aba Tela1)
	public ArrayList<Cadastro> Listar() {
		String sql = "SELECT * FROM DB.CADASTRO";
		try {
			conn = new Conexao().conectaBD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			while (rs.next()) {
				Cadastro objcadastro = new Cadastro();
				objcadastro.setId(rs.getInt("id"));
				objcadastro.setNome(rs.getString("nome"));
				objcadastro.setTelefone(rs.getString("telefone"));
				objcadastro.setEmail(rs.getString("email"));
				objcadastro.setEscala(rs.getString("escala"));

				list.add(objcadastro);
			}

		} catch (Exception erro) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "CadastroDAO Listar" + erro);
		}
		return list;
	}

	
	//Está ligado a função SalvarDado (localizado na aba Tela1)
	public ArrayList<Cadastro> salvar() {
		ArrayList<Cadastro> cadastro = new ArrayList<Cadastro>();

		String sql = "select * from db.cadastro";

		try {
			conn = new Conexao().conectaBD();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();

			Cadastro bean = null;

			while (rs.next()) {
				bean = new Cadastro();

				bean.setId(rs.getInt("id"));
				bean.setNome(rs.getString("nome"));
				bean.setTelefone(rs.getString("telefone"));
				bean.setEmail(rs.getString("email"));
				bean.setEscala(rs.getString("escala"));

				cadastro.add(bean);

			}
			pstm.close();
			rs.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "CadastroDAO Salvar" + erro);
		}
		try {
			FileWriter fw = new FileWriter("arquivo.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cadastro;
	}

	public String processaObjeto(Cadastro bean) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <= 80; i++) {
			sb.append("-");
		}
		sb.append("\n");
		sb.append("NOME: " + bean.getNome());
		sb.append("\n");
		sb.append("Data: " + bean.getTelefone());
		sb.append("\n");
		sb.append("Hora: " + bean.getEmail());
		sb.append("\n");
		sb.append("Info: " + bean.getEscala());
		sb.append("\n");
		for (int i = 0; i <= 80; i++) {
			sb.append("-");
		}
		return sb.toString();
	}
	
	
	//Ligado a função CarregarDeletarCampo (localizado na aba Tela1)
	public void ExcluirFuncionario (Cadastro objFuncionarioExcluir) {
		String Sql = "Delete from cadastro where id = ?";
		
		try {
			conn = new Conexao().conectaBD();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			pstm = conn.prepareStatement(Sql);
			
			pstm.setInt(1, objFuncionarioExcluir.getId());

			pstm.execute();
			pstm.close();

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "CadastroDAO EXCLUIRFUNCIONARIO" + erro);
		}
	}
}