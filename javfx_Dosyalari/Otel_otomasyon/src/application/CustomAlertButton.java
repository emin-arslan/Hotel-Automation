package application;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

public class CustomAlertButton {
	public Alert alert;
	public CustomAlertButton() {
		
	}
	private void alertcss()
	{
		try {
			
			Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
			stage.getIcons().add(new Image(getClass().getResourceAsStream("e.png")));
			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(
			getClass().getResource("myDialogs.css").toExternalForm());
			dialogPane.getStyleClass().add("myDialog");
			}
			catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
	}
	public void WarningAlert(String Title,String Header,String Content)
	{
		alert = new Alert(AlertType.WARNING);
		alert.setTitle(Title);
		alert.setHeaderText(Header);
		alert.setContentText(Content);
		alertcss();
		alert.showAndWait();
		
		
	}
	public void ErrorAlert(String Title,String Header,String Content)
	{
		alert = new Alert(AlertType.ERROR);
		alert.setTitle(Title);
		alert.setHeaderText(Header);
		alert.setContentText(Content);
		alertcss();
		alert.showAndWait();
	}
	public void InformationAlert(String Title,String Header,String Content)
	{
		alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(Title);
		alert.setHeaderText(Header);
		alert.setContentText(Content);
		alertcss();
		alert.showAndWait();
	}
	
	
	
	
}
