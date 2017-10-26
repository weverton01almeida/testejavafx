package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import modelo.*;

public class BancoDeDadosDAO {

	public Conexao conexao = new Conexao();

	public Msg_banco incluiBancoDeDados(BancoDeDados bd) throws SQLException {
		Conexao conexao = new Conexao();
		Msg_banco msg = new Msg_banco();
		if (conexao.abreConexaoBD() == null) {
			msg.setId(0);// banco de dados nao conectado
			return msg;
		} else {
			// tipos id msg
			// 0=> Banco de dados nao conectado
			// 1=> Operação efetuada
			// 2=> Operação nao efetuada
			// 3=> Registro ja cadastrado
			// 4=> Registro nao cadastrado

			String sql = null;
			Connection conn = conexao.abreConexaoBD();
			ResultSet rs;
			Statement st = conn.createStatement();
			String NomeBD = bd.getNomeBancoDados();
			int TipoBD = bd.getTipoBanco();
			sql = "select * from banco_de_dados where banco_NomeBancoDados ='" + NomeBD + "' and banco_TipoBanco = '"
					+ TipoBD + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false) {// nao tem cadastro
				sql = "insert into banco_de_dados (banco_NomeBancoDados,Banco_TipoBanco) values (";
				sql += "'" + bd.getNomeBancoDados() + "','" + bd.getTipoBanco() + "')";
				int qRs = st.executeUpdate(sql);
				if (qRs == 0)
					msg.setId(2);
				else
					msg.setId(1);
			} else
				msg.setId(3); // possui cadastro
			return msg;
		}
	}

	public BancoDeDados buscaBancoDeDadosPorId(int codigo) throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		String sql = null;
		sql = "select * from banco_de_dados where Banco_Cod = " + codigo;
		Connection conn = conexao.abreConexaoBD();
		ResultSet rs = null;
		Statement st = null;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {
			bd = new BancoDeDados();
			bd.setCodigo(rs.getInt("Banco_Cod"));
			bd.setNomeBancoDados(rs.getString("banco_NomeBancoDados"));
			bd.setTipoBanco(rs.getInt("banco_TipoBanco"));
		} else
			bd = null;
		return bd;
	}

	public BancoDeDados buscaBancoDeDadosPorNome(String Nome) throws SQLException {
		BancoDeDados bd = new BancoDeDados();
		String sql = null;
		sql = "select * from banco_de_dados where banco_NomeBancoDados = '" + Nome + "'";
		Connection conn = conexao.abreConexaoBD();
		ResultSet rs = null;
		Statement st = null;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {
			bd = new BancoDeDados();
			bd.setCodigo((rs.getInt("Banco_Cod")));
			bd.setNomeBancoDados((rs.getString("banco_NomeBancoDados")));
			bd.setTipoBanco(rs.getInt("banco_TipoBanco"));
		} else
			bd = null;
		return bd;
	}

	public Msg_banco ExcluiBancoDeDados(BancoDeDados bd) throws SQLException {
		Conexao conexao = new Conexao();
		Msg_banco msg = new Msg_banco();
		if (conexao.abreConexaoBD() == null) {
			msg.setId(0);// banco de dados nao conectado
			return msg;
		} else {
			// tipos id msg
			// 0=> Banco de dados nao conectado
			// 1=> Operação efetuada
			// 2=> Operação nao efetuada
			// 3=> Registro ja cadastrado
			// 4=> Registro nao cadastrado
			String sql = null;
			Connection conn = conexao.abreConexaoBD();
			ResultSet rs;
			Statement st = conn.createStatement();
			int CodBD = bd.getCodigo();
			sql = "select * from banco_de_dados where Banco_Cod ='" + CodBD + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false) {
				// nao possui cadastro
				msg.setId(4);
			} else {
				sql = "delete from banco_de_dados where banco_NomeBancoDados = ";
				sql += "'" + bd.getNomeBancoDados() + "' and banco_TipoBanco ='" + bd.getTipoBanco() + "'";
				int qRs = st.executeUpdate(sql);
				if (qRs == 0)
					msg.setId(2);// inclui na msg_banco operaçao nao efetuada
				else
					msg.setId(1);
			}
		}
		return msg;
	}

	public Msg_banco AlteraBancoDeDados(BancoDeDados bd) throws SQLException {
		Conexao conexao = new Conexao();
		Msg_banco msg = new Msg_banco();
		if (conexao.abreConexaoBD() == null) {
			msg.setId(0);// banco de dados nao conectado
			return msg;
		} else {
			// tipos id msg
			// 0=> Banco de dados nao conectado
			// 1=> Operação efetuada
			// 2=> Operação nao efetuada
			// 3=> Registro ja cadastrado
			// 4=> Registro nao cadastrado
			String sql = null;
			Connection conn = conexao.abreConexaoBD();
			ResultSet rs;
			Statement st = conn.createStatement();
			int CodBD = bd.getCodigo();
			sql = "select * from banco_de_dados where Banco_Cod ='" + CodBD + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false) {
				// nao possui cadastro
				msg.setId(4);
			} else {
				sql = "update banco_de_dados set banco_NomeBancoDados ='" + bd.getNomeBancoDados()
						+ "',banco_TipoBanco = '" + bd.getTipoBanco() + "' where Banco_Cod = ";
				sql += "'" + bd.getCodigo() + "'";
				int qRs = st.executeUpdate(sql);
				if (qRs == 0)
					msg.setId(2);// inclui na msg_banco operaçao nao efetuada
				else
					msg.setId(1);
			}
			return msg;
		}
	}

	public ArrayList<String> AtualizaComboBox() throws SQLException {
		ArrayList<String> tiposBanco = new ArrayList<String>();
		String sql = null;
		sql = "select * from tipos_bd";
		Connection conn = conexao.abreConexaoBD();
		ResultSet rs = null;
		Statement st = null;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		while (rs.next()) {
			tiposBanco.add((String.valueOf(rs.getInt("tip_id"))));
		}
		return tiposBanco;
	}

}
