package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.*;

public class TipoBancoDAO {

	public Conexao conexao = new Conexao();

	public Msg_banco incluiTipoBanco(TipoBanco c) throws SQLException {
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
			String desc = c.getTipoBanco_sigla().trim();
			sql = "select * from tipos_bd where tip_sigla ='" + desc + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false) {// nao tem cadastro
				sql = "insert into tipos_bd (tip_sigla) values (";
				sql += "'" + c.getTipoBanco_sigla() + "')";
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

	public TipoBanco buscaTipoBancoPorId(int codigo) throws SQLException {
		TipoBanco a = new TipoBanco();
		String sql = null;
		sql = "select * from tipos_bd where tip_id = " + codigo;
		Connection conn = conexao.abreConexaoBD();
		ResultSet rs = null;
		Statement st = null;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {
			a = new TipoBanco();
			a.setTipoBanco_id(rs.getInt("tip_id"));
			a.setTipoBanco_sigla(rs.getString("tip_sigla"));
		} else
			a = null;
		return a;
	}

	public TipoBanco buscaTipoBancoPorSigla(String sigla) throws SQLException {
		TipoBanco a = new TipoBanco();
		String sql = null;
		sql = "select * from tipos_bd where tip_sigla = '" + sigla + "'";
		Connection conn = conexao.abreConexaoBD();
		ResultSet rs = null;
		Statement st = null;
		st = conn.createStatement();
		rs = st.executeQuery(sql);
		if (rs.next()) {
			a = new TipoBanco();
			a.setTipoBanco_id(rs.getInt("tip_id"));
			a.setTipoBanco_sigla(rs.getString("tip_sigla"));
		} else
			a = null;
		return a;
	}

	public Msg_banco ExcluiTipoBanco(TipoBanco c) throws SQLException {
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
			// 5=> Há um Banco cadastrado neste tipo, sendo assim nao é possivel exclui-lo
			String sql = null;
			Connection conn = conexao.abreConexaoBD();
			ResultSet rs;
			Statement st = conn.createStatement();
			int ID = c.getTipoBanco_id();
			sql = "select * from tipos_bd where tip_id ='" + ID + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false) {
				// nao possui cadastro
				msg.setId(4);
			} else {
				sql = "select * from banco_de_dados where banco_TipoBanco ='" + c.getTipoBanco_id() + "'";
				rs = st.executeQuery(sql);
				if (rs.next() == false) {
					sql = "delete from tipos_bd where tip_id = ";
					sql += "'" + c.getTipoBanco_id() + "'";
					int qRs = st.executeUpdate(sql);
					if (qRs == 0)
						msg.setId(2);// inclui na msg_banco operaçao nao efetuada
					else
						msg.setId(1);
				} else {
					msg.setId(5);
				}

			}
		}

		return msg;
	}

	public Msg_banco AlteraTipoBanco(TipoBanco c) throws SQLException {
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
			String desc = Integer.toString(c.getTipoBanco_id());
			sql = "select * from tipos_bd where tip_id ='" + desc + "'";
			rs = st.executeQuery(sql);
			if (rs.next() == false) {
				// nao possui cadastro
				msg.setId(4);
			} else {
				sql = "update tipos_bd set tip_sigla ='" + c.getTipoBanco_sigla() + "'where tip_id = ";
				sql += "'" + c.getTipoBanco_id() + "'";
				int qRs = st.executeUpdate(sql);
				if (qRs == 0)
					msg.setId(2);// inclui na msg_banco operaçao nao efetuada
				else
					msg.setId(1);
			}
			return msg;
		}
	}

}
