package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;

public class Util {
	private static String nomeAplicacao = "Teste";

	public static String getNomeAplicacao() {
		return nomeAplicacao;
	}

	public static void mensagemInformacao(String msg) {
		Alert alert;
		alert = new Alert(AlertType.INFORMATION, msg, ButtonType.OK);
		alert.setTitle(nomeAplicacao);
		alert.setHeaderText("Informação");
		alert.showAndWait();
	}

	public static void mensagemErro(String msg) {
		Alert alert;
		alert = new Alert(AlertType.ERROR, msg, ButtonType.OK);
		alert.setTitle(nomeAplicacao);
		alert.setHeaderText("Erro");
		alert.showAndWait();
	}

	public static boolean mensagemCustomizada(String msg) {
		boolean retorno = false;
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(nomeAplicacao);
		alert.setHeaderText(msg);
		alert.setContentText("Escolha sua opção");
		ButtonType buttonTypeConfirmar = new ButtonType("Confirmar", ButtonData.OK_DONE);
		ButtonType buttonTypeCancelar = new ButtonType("Cancelar", ButtonData.CANCEL_CLOSE);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK) {
			// ... escolheu "Confirmar"
			retorno = true;
		} else {
			// ... escolheu Cancelar ou closed the dialog
			retorno = false;
		}
		return retorno;
	}

	public static String mensagemComBotoesMaiorMenor() {
		String retorno = "";
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Seleção do Maior ou Menor Caminho");
		alert.setHeaderText("Selecione Maior para o maior caminho e Menor para o menor Caminho");
		alert.setContentText("Escolha sua opção. O padrão é igual a Menor");

		ButtonType buttonTypeOne = new ButtonType("Menor");
		ButtonType buttonTypeTwo = new ButtonType("Maior");

		alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == buttonTypeOne) {
			retorno = "MENOR";
			// ... user chose "One"
		} else if (result.get() == buttonTypeTwo) {
			retorno = "MAIOR";
		} else {
			retorno = "MENOR";
		}

		return retorno;
	}

	public static boolean validaConteudoNumerico(String s) {
		int a = 0;
		boolean retorno = true;
		try {
			a = Integer.parseInt(s);
		} catch (Exception e) {
			retorno = false;
		}
		return retorno;
	}

	public static boolean stringVaziaOuNula(String s) {
		// retorna true se a string for nula ou vazia
		boolean retorno = false;
		if (s.equals("") || s.isEmpty() || s.length() == 0 || s == null)
			retorno = true;

		return retorno;
	}

	public static boolean validaData(String s) {
		boolean retorno = false;
		Date data = null;
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			format.setLenient(false);
			data = format.parse(s);
			retorno = true;
		} catch (ParseException e) {
			retorno = false;
		}
		if (s.contains(":")) {
			retorno = false;
		}

		return retorno;
	}

	public static String converteDataParayyymmdd(String s) {
		// y y y y / m m / y y
		// 0 1 2 3 4 5 6 7 8 9
		String ano = s.substring(0, 4);
		String mes = s.substring(5, 7);
		String dia = s.substring(8);
		String resultado = ano + "-" + mes + "-" + dia;
		return resultado;
	}
}
