package controle;

import java.net.URL;
import java.sql.SQLException;

import java.util.ResourceBundle;

import dao.TipoBancoDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Msg_banco;
import modelo.TipoBanco;

public class Controle implements Initializable {

	@FXML
	private Button btnLimpartela;

	@FXML
	private Button btnIncluir;

	@FXML
	private Button btnAlterar;

	@FXML
	private Button btnExcluir;

	@FXML
	private Button btnBuscarCod;

	@FXML
	private Button btnBuscarSigla;

	@FXML
	private TextField txtCodBusca;

	@FXML
	private TextField txtCodigo;

	@FXML
	private TextField txtSigla;

	@FXML
	void btnLimpartelaOnAction(ActionEvent event) {
		this.limpaTela();
	}

	@FXML
	void btnBuscarCodOnAction(ActionEvent event) throws NumberFormatException, SQLException {
		if (util.Util.stringVaziaOuNula(this.txtCodBusca.getText())) {
			util.Util.mensagemErro("Informe o codigo do tipo de banco de dados");
		} else {
			if (!util.Util.validaConteudoNumerico(this.txtCodBusca.getText())) {
				util.Util.mensagemErro("Informe o codigo do tipo de banco de dados(somente valores numericos)");

			} else {
				TipoBanco tbd = new TipoBanco();
				TipoBancoDAO tbdDAO = new TipoBancoDAO();
				tbd = tbdDAO.buscaTipoBancoPorId(Integer.parseInt(this.txtCodBusca.getText()));
				if (tbd == null) {
					util.Util.mensagemErro("Nao ha registros");
					this.limpaTela();
				} else {
					this.txtCodigo.setText(Integer.toString(tbd.getTipoBanco_id()));
					this.txtSigla.setText(tbd.getTipoBanco_sigla());
				}
			}
		}
	}

	@FXML
	void btnBuscarSiglaOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtSigla.getText())) {
			util.Util.mensagemErro("Informe a sigla do tipo de banco de dados");
		} else {
			TipoBanco tbd = new TipoBanco();
			TipoBancoDAO tbdDAO = new TipoBancoDAO();
			tbd = tbdDAO.buscaTipoBancoPorSigla(this.txtSigla.getText());
			if (tbd == null) {
				util.Util.mensagemErro("Nao ha registros");
				this.limpaTela();
			} else {
				this.txtCodigo.setText(Integer.toString(tbd.getTipoBanco_id()));
				this.txtSigla.setText(tbd.getTipoBanco_sigla());
				this.txtCodBusca.setText("");
			}
		}
	}

	@FXML
	void btnAlterarOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
			util.Util.mensagemErro("Faça a busca antes de tentar alterar algo");
		} else {
			if (util.Util.stringVaziaOuNula(this.txtSigla.getText())) {
				util.Util.mensagemErro("A sigla esta vazia!");
			} else {
				TipoBanco tbd = new TipoBanco();
				TipoBancoDAO tbdDAO = new TipoBancoDAO();
				tbd.setTipoBanco_id(Integer.parseInt(this.txtCodigo.getText()));
				tbd.setTipoBanco_sigla(this.txtSigla.getText());
				Msg_banco retorno = tbdDAO.AlteraTipoBanco(tbd);
				if (retorno.getId() == 1)
					util.Util.mensagemInformacao(retorno.getAlteracao());
				if (retorno.getId() != 1)
					util.Util.mensagemErro(retorno.getAlteracao());
			}
			this.limpaTela();
		}
	}

	@FXML
	void btnExcluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtCodigo.getText())) {
			util.Util.mensagemErro("Faça a busca antes de tentar deletar algo");
		} else {
			TipoBanco tbd = new TipoBanco();
			TipoBancoDAO tbdDAO = new TipoBancoDAO();
			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				tbd.setTipoBanco_id(Integer.parseInt(this.txtCodigo.getText()));
			tbd.setTipoBanco_sigla(this.txtSigla.getText());
			Msg_banco retorno = tbdDAO.ExcluiTipoBanco(tbd);
			if (retorno.getId() == 1)
				util.Util.mensagemInformacao(retorno.getExclusao());
			if (retorno.getId() != 1)
				util.Util.mensagemErro(retorno.getExclusao());
		}
		this.limpaTela();
	}

	@FXML
	void btnIncluirOnAction(ActionEvent event) throws SQLException {
		if (util.Util.stringVaziaOuNula(this.txtSigla.getText())) {
			util.Util.mensagemErro("Informe a sigla do tipo de banco de dados");
		} else {
			TipoBanco tbd = new TipoBanco();
			TipoBancoDAO tbdDAO = new TipoBancoDAO();

			if (!util.Util.stringVaziaOuNula(this.txtCodigo.getText()))
				tbd.setTipoBanco_id(Integer.parseInt(this.txtCodigo.getText()));
			tbd.setTipoBanco_sigla(this.txtSigla.getText());
			Msg_banco retorno = tbdDAO.incluiTipoBanco(tbd);
			if (retorno.getId() == 1)
				util.Util.mensagemInformacao(retorno.getInclusao());
			else
				util.Util.mensagemErro(retorno.getInclusao());
		}
		this.limpaTela();
	}

	public void limpaTela() {
		this.txtCodigo.setText("");
		this.txtSigla.setText("");
		this.txtCodBusca.setText("");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		txtSigla.focusedProperty().addListener((v, vv, nv) -> {
			if (!nv) { // perde o foco
						// util.Util.mensagemErro("desfocou");
			} else {
				// System.out.println("*");
			}

		});
	}
}
