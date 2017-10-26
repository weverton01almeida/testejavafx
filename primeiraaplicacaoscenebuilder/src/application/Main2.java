//não esquecer:
//01. verificar a conexão com o banco para efetuar login
//02. inserir label nos campos do login...

package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main2 extends Application {

	// public Stage stage;
	@Override
	public void start(Stage primaryStage) {
		try {
			// setUserAgentStylesheet(STYLESHEET_MODENA);
			// AquaFx.style();
			// stage = primaryStage;
			AnchorPane root = new AnchorPane();
			root = FXMLLoader.load(getClass().getResource("/view/Cadastro02.fxml"));

			// javafx.geometry.Rectangle2D screenBounds =
			// Screen.getPrimary().getVisualBounds();
			Scene scene = new Scene(root);// ,screenBounds.getWidth(),screenBounds.getHeight());

			primaryStage.setScene(scene);
			primaryStage.setTitle("Bancos de Dados");
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
