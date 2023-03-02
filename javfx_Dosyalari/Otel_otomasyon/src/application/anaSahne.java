package application;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class anaSahne {

	public static kullanici_bilgileri kullanici=new kullanici_bilgileri();
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane menu_pane;
    
    @FXML
    private AnchorPane degisken_pane;

    public static AnchorPane degisken;
    
    
    @FXML
    private Label lbl_id;
    
    public kullanici_bilgileri kullanici_Getir()
    {
    	return kullanici;
    }
   
    void yetkiliAd(kullanici_bilgileri temp_kullanici)
    {
    	kullanici=temp_kullanici;
    
    	lbl_id.setText("Merhaba "+temp_kullanici.kullanici_id+"\nlütfen yapmak istediðiniz iþlemi menüden seçiniz..");
    	degisken=degisken_pane;
        try {
     	   AnchorPane pane1=(AnchorPane)FXMLLoader.load(getClass().getResource("menu.fxml"));
     	   menu_pane.getChildren().setAll(pane1);
     	   
     	    
 		
 	} catch (Exception e) {
 		// TODO: handle exception
 		System.out.println(e.getMessage());
 	}
    }
    
   
    
    @FXML
    void initialize() 
    {
    	
    	

    }
}
