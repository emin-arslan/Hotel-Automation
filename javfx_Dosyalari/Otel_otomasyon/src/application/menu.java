package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class menu  extends anaSahne{


	static public  kullanici_bilgileri anaKullanici=new kullanici_bilgileri();
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_menu;

    @FXML
    private Button btn_odalar;

    @FXML
    private Tooltip tooltip_calisanlar;

    
    @FXML
    private Button btn_musteri;

    @FXML
    private Button btn_calisanlar;

    @FXML
    private Button btn_yekilidegis;

    @FXML
    private Button btn_cikis;



    @FXML
    void btnCikis_Click(ActionEvent event) {
    	UserSession.cleanUserSession();
    	btn_cikis.getScene().getWindow().hide();
    }

    @FXML
    void btnMusteri_Click(ActionEvent event) {
    	
    	AnchorPane odalar;
		try {
			odalar = (AnchorPane)FXMLLoader.load(getClass().getResource("musteri_tablosu.fxml"));
			degisken.getChildren().setAll(odalar);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
    	
    }


    
    
    @FXML
    void btnOdalar_Click(ActionEvent event) {
    	try {
    		AnchorPane odalar=(AnchorPane)FXMLLoader.load(getClass().getResource("odalar.fxml"));		
        	degisken.getChildren().setAll(odalar);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }


    
    void kullanici_cikis()
    {
    	UserSession.cleanUserSession();
    }

    @FXML
    void initialize() {
    	
    	anaKullanici=kullanici_Getir();
    	if(anaKullanici.k_yetki==0)
    	{
    		btn_musteri.setDisable(true);
    		
    	}
    
    	
    }
}


















