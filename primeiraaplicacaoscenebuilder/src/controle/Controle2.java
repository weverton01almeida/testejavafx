package controle;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;

import dao.BancoDeDadosDAO;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import modelo.BancoDeDados;
import modelo.Msg_banco;

public class Controle2 implements Initializable {

	@FXML
	private Button btnBancoLimpartela;

	@FXML
	private Button btnBancoIncluir;

	@FXML
	private Button btnBancoAtualizar;

	@FXML
	private Button btnBancoAlterar;

	@FXML
	private Button btnBancoExcluir;

	@FXML
	private Button btnBancoBuscarCod;

	@FXML
	private Button btnBancoBuscarSigla;

	@FXML
	private TextField txtBancoCodBusca;

	@FXML
	private TextField txtBancoCodigo;

	@FXML
	private TextField txtBancoSigla;

	@FXML
	private ComboBox<String> comboBancoTipo;

	@FXML
	void btnBancoLimpartelaOnAction(ActionEvent event) {
		this.limpaTela();
	}

	@FXML
	void btnBancoAtualizarOnAction(ActionEvent event) throws SQLException {
		this.comboBancoTipo.getItems().clear();
		this.alimentaComboTipo();
	}

	@FXML
	void btnBancoBuscarCodOnAction(ActionEvent event) throws NumberFormatException, SQLException {
		if (util.Util.stringVaziaOuNula(this.txtBancoCodBusca.getText())) {
			util.Util.mensagemErro("Informe o codigo do banco de dados");
		} else {
			if (!util.Util.validaConteudoNumerico(this.txtBancoCodBusca.getText())) {
				util.Util.mensagemErro("Informe o codigo do banco de dados(somente valores numericos)");

			} else {
				BancoDeDados bd = new BancoDeDados();
				BancoDeDadosDAO bdDAO = new BancoDeDadosDAO();
				bd = bdDAO.buscaBancoDeDadosPorId(Integer.parseInt(this.txtBancoCodBusca.getText()));
				if (bd == null) {
					util.Util.mensagemErro("Nao ha registros");
					this.limpaTela();
				} else {
					this.txtBancoCodigo.setText(Integer.toString(bd.getCodigo()));
					this.txtBancoSigla.setText(bd.getNomeBancoDados());
					this.comboBancoTipo.setValue(String.valueOf(bd.getTipoBanco()));
				}
			}
		}
	}

	@FXML
	void btnBancoBuscarSiglaOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtBancoSigla.getText())) {
			util.Util.mensagemErro("Informe a sigla do tipo de banco de dados");
		} else {
			BancoDeDados bd = new BancoDeDados();
			BancoDeDadosDAO bdDAO = new BancoDeDadosDAO();
			bd = bdDAO.buscaBancoDeDadosPorNome((this.txtBancoSigla.getText()));
			if (bd == null) {
				util.Util.mensagemErro("Nao ha registros");
				this.limpaTela();
			} else {
				this.txtBancoCodigo.setText(Integer.toString(bd.getCodigo()));
				this.txtBancoSigla.setText(bd.getNomeBancoDados());
				this.txtBancoCodBusca.setText("");
				this.comboBancoTipo.setValue(String.valueOf(bd.getTipoBanco()));
			}
		}
	}

	@FXML
	void btnBancoAlterarOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtBancoCodigo.getText())) {
			util.Util.mensagemErro("Faça a busca antes de tentar alterar algo");
		} else {
			if (util.Util.stringVaziaOuNula(this.txtBancoSigla.getText())) {
				util.Util.mensagemErro("A sigla esta vazia!");
			} else {
				BancoDeDados bd = new BancoDeDados();
				BancoDeDadosDAO bdDAO = new BancoDeDadosDAO();
				bd.setCodigo(Integer.parseInt(this.txtBancoCodigo.getText()));
				bd.setNomeBancoDados((this.txtBancoSigla.getText()));
				bd.setTipoBanco(Integer.valueOf(this.comboBancoTipo.getValue()));
				Msg_banco retorno = bdDAO.AlteraBancoDeDados(bd);
				if (retorno.getId() == 1)
					util.Util.mensagemInformacao(retorno.getAlteracao());
				if (retorno.getId() != 1)
					util.Util.mensagemErro(retorno.getAlteracao());
			}
			this.limpaTela();
		}
	}

	@FXML
	void btnBancoExcluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtBancoCodigo.getText())) {
			util.Util.mensagemErro("Faça a busca antes de tentar deletar algo");
		} else {
			BancoDeDados bd = new BancoDeDados();
			BancoDeDadosDAO bdDAO = new BancoDeDadosDAO();
			if (!util.Util.stringVaziaOuNula(this.txtBancoCodigo.getText()))
				bd.setCodigo(Integer.parseInt(this.txtBancoCodigo.getText()));
			bd.setNomeBancoDados(this.txtBancoSigla.getText());
			bd.setTipoBanco(Integer.valueOf(this.comboBancoTipo.getValue()));
			Msg_banco retorno = bdDAO.ExcluiBancoDeDados(bd);
			if (retorno.getId() == 1)
				util.Util.mensagemInformacao(retorno.getExclusao());
			if (retorno.getId() != 1)
				util.Util.mensagemErro(retorno.getExclusao());
		}
		this.limpaTela();
	}

	@FXML
	void btnBancoIncluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtBancoSigla.getText())) {
			util.Util.mensagemErro("Informe a sigla do banco de dados");
		} else {
			BancoDeDados bd = new BancoDeDados();
			BancoDeDadosDAO bdDAO = new BancoDeDadosDAO();

			if (!util.Util.stringVaziaOuNula(this.txtBancoCodigo.getText()))
				bd.setCodigo(Integer.parseInt(this.txtBancoCodigo.getText()));
			bd.setNomeBancoDados(this.txtBancoSigla.getText());
			bd.setTipoBanco(Integer.valueOf(this.comboBancoTipo.getValue()));
			Msg_banco retorno = bdDAO.incluiBancoDeDados(bd);
			if (retorno.getId() == 1)
				util.Util.mensagemInformacao(retorno.getInclusao());
			else
				util.Util.mensagemErro(retorno.getInclusao());
		}
		this.limpaTela();
	}

	public void limpaTela() {
		this.txtBancoCodigo.setText("");
		this.txtBancoSigla.setText("");
		this.txtBancoCodBusca.setText("");
		this.comboBancoTipo.setValue("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		/*
		 * comboBancoTipo.getSelectionModel().selectedItemProperty().addListener(new
		 * ChangeListener<String>() { public void changed(ObservableValue<? extends
		 * String> ov, final String oldvalue, final String newvalue) {
		 * util.Util.mensagemInformacao("Você selecionou o item " + newvalue);
		 * 
		 * } });
		 */

		txtBancoSigla.focusedProperty().addListener((v, vv, nv) -> {
			if (!nv) { // perde o foco
						// util.Util.mensagemErro("desfocou");
			} else {
				// System.out.println("*");
			}

		});
		try {
			alimentaComboTipo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public void alimentaComboTipo() throws SQLException {
		BancoDeDadosDAO bd = new BancoDeDadosDAO();
		ArrayList<String> combobox = new ArrayList<String>();
		combobox = bd.AtualizaComboBox();
		comboBancoTipo.getItems().clear();// limpando a combobox
		for (String tipoBanco : combobox) {
			comboBancoTipo.getItems().add(tipoBanco);
		}
	}
}
