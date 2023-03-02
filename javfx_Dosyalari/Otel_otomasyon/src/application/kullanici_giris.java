package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import com.MySql.Util.DataBaseUtil;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class kullanici_giris {
	
	
	static int start=0;
	
	public kullanici_giris()
	{
		
		baglanti=DataBaseUtil.Connect();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private BorderPane borderPane_1;

    @FXML
    private AnchorPane pane_logo;

    @FXML
    private ImageView view_logo;

    @FXML
    private AnchorPane pane_giris;

    @FXML
    private ImageView view_lock;

    @FXML
    private Label lbl_id;

    @FXML
    private TextField txt_id;

    @FXML
    private Label lbl_sifre;

    @FXML
    private TextField txt_sifre;
    
    @FXML
    private Button btn_yetkili_giris;

    @FXML
    private Button btn_giris;
    
    Connection baglanti=null;
    PreparedStatement sorgu=null;
    ResultSet cevap=null;
    String sql;
    
    @FXML
    void yetkili_Giris()
    {
    	if(btn_yetkili_giris.getText().equals("Yetkili Giriþ"))
    	{
    	Image yetkili_logo=new Image(getClass().getResourceAsStream("yetkili_emin.jpg"));
    	view_logo.setImage(yetkili_logo);
    	btn_yetkili_giris.setText("Personel Giriþ");
    	}
    	else 
    	{
    		Image yetkili_logo=new Image(getClass().getResourceAsStream("logo.png"));
        	view_logo.setImage(yetkili_logo);
        	btn_yetkili_giris.setText("Yetkili Giriþ");
    	}
    }

    @FXML
    void btnGiris_Click(ActionEvent event) {
    	
    	
    		
    	CustomAlertButton alert=new CustomAlertButton();
    	if(!(txt_id.getText().isEmpty() || txt_sifre.getText().isEmpty()))
    	{
    	
    	sql="select * from kullanicilar where k_id=? and k_password=?";
    	try {
    		 sorgu=baglanti.prepareStatement(sql);
    		 sorgu.setString(1, txt_id.getText().trim());
    		 sorgu.setString(2, txt_sifre.getText().trim());
			 cevap=sorgu.executeQuery();
			 if(!cevap.next())
			 {
				 
				 alert.WarningAlert("Kullanici Giris","Giris Durumu","Eþleþmeyen kullanici adi ve þifresi");
			 }
			 else if((cevap.getInt("k_yetki")==0 ||cevap.getInt("k_yetki")==1)  && btn_yetkili_giris.getText().equals("Yetkili Giriþ"))
			 {
				  
				 try {
						FXMLLoader loader=new FXMLLoader(getClass().getResource("anaSahne.fxml"));
						AnchorPane paneAna=(AnchorPane) loader.load();
						anaSahne nesne=loader.getController();
						Scene scene1=new Scene(paneAna,750,525);
						kullanici_bilgileri kullanici=new kullanici_bilgileri(cevap.getString("k_id"), cevap.getInt("k_yetki"));
						/*System.out.println(cevap.getString("k_id"));
						System.out.println(cevap.getString("k_yetki"));*/
						nesne.yetkiliAd(kullanici);
						UserSession.getInstace("emin", "Arslan");
						Preferences userPreferences = Preferences.userRoot();
						
						userPreferences.put("ID1",kullanici.kullanici_id);
						start++;
						Stage stage1=new Stage();
						stage1.setScene(scene1);
						stage1.getIcons().add(new Image(getClass().getResourceAsStream("e.png")));
						stage1.show();
						btn_giris.getScene().getWindow().hide();
						
						
					} 	catch(Exception e) {
						
						System.out.println(e.getMessage());
					}
			 }
			 else if(cevap.getInt("k_yetki")==2 && btn_yetkili_giris.getText()=="Personel Giriþ")
			 {
				 try {
						FXMLLoader loader=new FXMLLoader(getClass().getResource("yetkili_panel.fxml"));
						AnchorPane paneAna=(AnchorPane) loader.load();
						yetkili_panel nesne=loader.getController();
						Scene scene1=new Scene(paneAna,1002,700);
						kullanici_bilgileri kullanici=new kullanici_bilgileri(cevap.getString("k_id"), cevap.getInt("k_yetki"));
						
						/*System.out.println(cevap.getString("k_id"));
						System.out.println(cevap.getString("k_yetki"));*/
						nesne.bilgiler(kullanici);
						
						
						Stage stage1=new Stage();
						stage1.setScene(scene1);
						stage1.getIcons().add(new Image(getClass().getResourceAsStream("e.png")));
						stage1.show();
						
						
						
					} 	catch(Exception e) {
						
						System.out.println(e.getMessage());
					}
			 }
			
		} catch (Exception e) {
			
		}
    	}
    	else alert.WarningAlert("Boþ Deðer Giriþi","Boþ Býrakýlmaz.","Kullanici adi veya þifresini boþ býrakmayýnýz.!");
    	
    	
    	
	
    }

    @FXML
    void initialize() {
    	
    	txt_id.setText("lyquis");
    	txt_sifre.setText("del123ete");
    }
}
