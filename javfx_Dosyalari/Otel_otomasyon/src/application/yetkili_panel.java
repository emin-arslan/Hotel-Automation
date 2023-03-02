package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import com.MySql.Util.DataBaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class yetkili_panel extends kullanici_giris {
	
	ObservableList<log_bilgileri> loglar;
	ObservableList<log_bilgileri> temp_loglar;
	ObservableList<personel_ozellikleri> personeller;
	ObservableList<personel_ozellikleri> temp_personeller;
	Connection baglanti;
	CustomAlertButton alert=new CustomAlertButton();
	int perid=0;
	
	static public kullanici_bilgileri kullanici;
	public void bilgiler(kullanici_bilgileri temp_kullanici)
	{
		baglanti=DataBaseUtil.Connect();
		
		kullanici=temp_kullanici;
		lbl_merhaba.setText("Hoþgeldiniz "+kullanici.kullanici_id);
		lbl_tarih.setText(String.valueOf(new Timestamp(System.currentTimeMillis())));
	}

	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lbl_aktif_per;

    @FXML
    private ListView<String> lw_aktif;

    @FXML
    private Label lbl_persayisi;

    @FXML
    private Label lbl_merhaba;

    @FXML
    private Label lbl_tarih;
    
    @FXML
    private ComboBox<String> cb_log_ara;

    @FXML
    private TextField txt_log_ara;

    @FXML
    private Button btn_log_ara;

    @FXML
    private Label lbl_havadurumu;

    @FXML
    private Label lbl_baslik;

    @FXML
    private TabPane tab_islemler;

    @FXML
    private TableView<log_bilgileri> tw_logs;
    
    @FXML
    private TableColumn<log_bilgileri, Integer> log_id;

    @FXML
    private TableColumn<log_bilgileri, String> per_id;

    @FXML
    private TableColumn<log_bilgileri, String> yap_id;

    @FXML
    private TableColumn<log_bilgileri, String> yap_kolon;

    @FXML
    private TableColumn<log_bilgileri, String> yap_tablo;

    @FXML
    private TableColumn<log_bilgileri, Timestamp> islem_tarih;

    @FXML
    private TableColumn<log_bilgileri, String> aciklama;

    @FXML
    private TabPane tab_logislemler;

    @FXML
    private TableView<personel_ozellikleri> tw_personel;

    @FXML
    private TableColumn<personel_ozellikleri,Integer> personel_id;

    @FXML
    private TableColumn<personel_ozellikleri, String> per_ad;

    @FXML
    private TableColumn<personel_ozellikleri, String> per_soyad;

    @FXML
    private TableColumn<personel_ozellikleri, String> per_gorev;
    
    @FXML
    private ComboBox<String> cb_per_ara;

    @FXML
    private TextField txt_per_ara;
    @FXML
    private Button btn_ara;
    
    @FXML
    private TextField txt_perad_ekle;

    @FXML
    private TextField txt_soyad_ekle;

    @FXML
    private ComboBox<String> cb_gorev_ekle;

    @FXML
    private Button btn_ekle;
    
    @FXML
    private TextField txt_sil;

    @FXML
    private CheckBox cb_per_onay;

    @FXML
    private Button btn_sil;
    
    @FXML
    private TextField txt_guncelle_ad;

    @FXML
    private TextField txt_guncelle_soyad;

    @FXML
    private ComboBox<String> cb_guncelle_gorev;

    @FXML
    private Label lbl_perid;
    @FXML
    private TabPane tab_perislemler;
    PreparedStatement pst=null;
    ResultSet veri=null;
    public void log_getir()
	{
    	baglanti=DataBaseUtil.Connect();
    	loglar=FXCollections.observableArrayList();
			try {

    		pst=baglanti.prepareStatement("Select * from logs");

    		veri=pst.executeQuery();
    		while(veri.next())
    		{

    			loglar.add(new log_bilgileri(veri.getInt("log_id"), veri.getString("yapilan_islem") ,veri.getString("k_id"),veri.getTimestamp("yapilan_tarih"),veri.getString("islem_yapilan_id"),veri.getString("islem_yapilan_kolon"),veri.getString("islem_yapilan_tablo")));  
    			
    		}

    		log_id.setCellValueFactory(new PropertyValueFactory<>("log_id"));
    		aciklama.setCellValueFactory(new PropertyValueFactory<>("yapilan_islem"));
    		per_id.setCellValueFactory(new PropertyValueFactory<>("kullanici_id"));
    		islem_tarih.setCellValueFactory(new PropertyValueFactory<>("yapilan_tarih"));
			yap_id.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_id"));
			yap_kolon.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_kolon"));
			yap_tablo.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_tablo"));
			tw_logs.setItems(loglar);
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("s111111111111d"+oda_id2);
			System.out.println("error:log:"+e.getMessage());
		}
		
		
		
	}
    public void aktif_check()
    {
    	Preferences userPreferences = Preferences.userRoot();
    	String info = userPreferences.get("ID1","deneme2");
    	lw_aktif.getItems().add(info);
    }
    
    public void personel_getir()
    {
    	
    		baglanti=DataBaseUtil.Connect();
    		personeller=FXCollections.observableArrayList();
			try {

    		pst=baglanti.prepareStatement("SELECT otel_personeli.per_soyad,otel_personeli.per_ad,otel_personeli.per_id,otel_personeli.per_ad,otel_gorev.gorev_ad  as gorev_ad FROM `otel_personeli` INNER JOIN otel_gorev ON otel_personeli.per_gorev=otel_gorev.gorev_id");

    		veri=pst.executeQuery();
    		while(veri.next())
    		{

    			personeller.add(new personel_ozellikleri(veri.getInt("per_id"),veri.getString("per_ad"),veri.getString("per_soyad"),veri.getString("gorev_ad")));
    			
    			
    		}

    		personel_id.setCellValueFactory(new PropertyValueFactory<>("per_id"));
    		per_ad.setCellValueFactory(new PropertyValueFactory<>("per_ad"));
    		per_soyad.setCellValueFactory(new PropertyValueFactory<>("per_soyad"));
			per_gorev.setCellValueFactory(new PropertyValueFactory<>("per_gorev"));
			tw_personel.setItems(personeller);
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("s111111111111d"+oda_id2);
			System.out.println("eror:per-getir:"+e.getMessage());
		}
    }
    
    public void personel_Ara()
    {
    	
    	temp_personeller=FXCollections.observableArrayList();
    	if(cb_per_ara.getSelectionModel().getSelectedIndex()!=-1 )
    	{
    	 if(cb_per_ara.getSelectionModel().getSelectedIndex()==0 )
    	 {
    		 System.out.println("sa");
    		 personel_getir();
    	 }
    	 else if(cb_per_ara.getSelectionModel().getSelectedIndex()==1 && !txt_per_ara.getText().isEmpty() )
    	 {
    		 for(personel_ozellikleri person:personeller)
    	    	{
    	    		if(person.per_id==Integer.valueOf(txt_per_ara.getText()))
    	    			temp_personeller.add(person);
    	    	}
    		 personel_id.setCellValueFactory(new PropertyValueFactory<>("per_id"));
    	  		per_ad.setCellValueFactory(new PropertyValueFactory<>("per_ad"));
    	  		per_soyad.setCellValueFactory(new PropertyValueFactory<>("per_soyad"));
    	  		per_gorev.setCellValueFactory(new PropertyValueFactory<>("per_gorev"));
    	 		tw_personel.setItems(temp_personeller);
    	 }
    	 else if(cb_per_ara.getSelectionModel().getSelectedIndex()==2 && !txt_per_ara.getText().isEmpty())
    	 {
    		 for(personel_ozellikleri person:personeller)
    	    	{
    	    		if(person.per_ad.equals( txt_per_ara.getText()))
    	    			temp_personeller.add(person);
    	    	}
    		 personel_id.setCellValueFactory(new PropertyValueFactory<>("per_id"));
    	  		per_ad.setCellValueFactory(new PropertyValueFactory<>("per_ad"));
    	  		per_soyad.setCellValueFactory(new PropertyValueFactory<>("per_soyad"));
    	  		per_gorev.setCellValueFactory(new PropertyValueFactory<>("per_gorev"));
    	 		tw_personel.setItems(temp_personeller);
    	 }
    	 else if(cb_per_ara.getSelectionModel().getSelectedIndex()==3 && !txt_per_ara.getText().isEmpty())
    	 {
    		 for(personel_ozellikleri person:personeller)
    	    	{
    	    		if(person.per_soyad.equals( txt_per_ara.getText()))
    	    			temp_personeller.add(person);
    	    	}
    		 personel_id.setCellValueFactory(new PropertyValueFactory<>("per_id"));
    	  		per_ad.setCellValueFactory(new PropertyValueFactory<>("per_ad"));
    	  		per_soyad.setCellValueFactory(new PropertyValueFactory<>("per_soyad"));
    	  		per_gorev.setCellValueFactory(new PropertyValueFactory<>("per_gorev"));
    	 		tw_personel.setItems(temp_personeller);
    	 }
    	
    	}
    	else System.out.println("ERROR MESSAGE 004");
    		
    	
 		

 		
    	 
    	
    	
    	
    }
    public void personel_ekle()
    {
    	int gorev_id=0;
    	if(!txt_perad_ekle.getText().isEmpty() && !txt_soyad_ekle.getText().isEmpty() && cb_gorev_ekle.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		if(cb_gorev_ekle.getSelectionModel().getSelectedIndex()==0) gorev_id=1;
    		else if(cb_gorev_ekle.getSelectionModel().getSelectedIndex()==1) gorev_id=2;
    		try {
				pst=baglanti.prepareStatement("insert into otel_personeli(per_ad,per_soyad,per_gorev) values(?,?,?)");
				pst.setString(1, txt_perad_ekle.getText());
				pst.setString(2, txt_soyad_ekle.getText());
				pst.setInt(3, gorev_id);
				pst.executeUpdate();
				alert.InformationAlert("Personel Ekleme Baþarýlý", txt_perad_ekle.getText()+" adlý Personel Eklendi.", "Personel eklendi.Lütfen tablodan kontrol ediniz.");
				personel_getir();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());
			}
    		
    	}
    	else alert.WarningAlert("Boþ Alan", "Geçersiz veya boþ deðer", "Lütfen boþ alan býrakmayýnýz.");
    }
    
    public void personel_sil()
    {
    	if(cb_per_onay.isSelected() && !txt_sil.getText().isEmpty())
    	{
    		try {
				pst=baglanti.prepareStatement("delete from otel_personeli where per_id=?");
				pst.setInt(1, Integer.valueOf(txt_sil.getText()));
				pst.executeUpdate();
				alert.InformationAlert("Personel Silme Baþarýlý", txt_sil.getText()+" numarali personel silindi.", "Lütfen personelin silinip silinmediðini tablodan kontrol edin.!");
				personel_getir();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("error:per:sil"+e.getMessage());
			}
    		
    				
    	}
    	else System.out.println("sa");
    }
    public void personel_guncelle()
    {
    	int gorev_id;
    	if(!txt_guncelle_ad.getText().isEmpty() && !txt_guncelle_soyad.getText().isEmpty() && perid!=0 && cb_guncelle_gorev.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		if(cb_guncelle_gorev.getSelectionModel().getSelectedItem()=="Genel Temizlik Personeli") gorev_id=1;
    		else gorev_id=2;
    		try {
				pst=baglanti.prepareStatement("update otel_personeli set per_ad=? , per_soyad=?,per_gorev=? where per_id=? ");
				pst.setString(1, txt_guncelle_ad.getText());
				pst.setString(2, txt_guncelle_soyad.getText());
				pst.setInt(3, gorev_id);
				pst.setInt(4, perid);
				pst.executeUpdate();
				alert.InformationAlert("Personel Güncelleme Baþarýlý", "Personel Bilgileri baþarýyla güncellendi.", perid+"'idli personel baþarýyla güncellendi.");
				txt_guncelle_ad.clear();
				txt_guncelle_soyad.clear();
				cb_guncelle_gorev.getItems().clear();
				lbl_perid.setText("Lütfen Tablodan istediðiniz Personeli Seçin");
				personel_getir();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("eror:per:guncelle"+e.getMessage());
			}
    		
    		
    	}
    }
    public void personel_to_textbox()
    {
    	cb_guncelle_gorev.getItems().clear();
    	if(tw_personel.getSelectionModel().getSelectedIndex()!=-1)
    	{
    	perid=personel_id.getCellData(tw_personel.getSelectionModel().getSelectedIndex());
    	txt_guncelle_ad.setText(per_ad.getCellData(tw_personel.getSelectionModel().getSelectedIndex()));
    	txt_guncelle_soyad.setText(per_soyad.getCellData(tw_personel.getSelectionModel().getSelectedIndex()));
    	lbl_perid.setText(String.valueOf(perid));
    	if(per_gorev.getCellData(tw_personel.getSelectionModel().getSelectedIndex()).equals("Genel Temizlik Personeli"))
    	{
    	cb_guncelle_gorev.getItems().add( per_gorev.getCellData(tw_personel.getSelectionModel().getSelectedIndex()));
    	cb_guncelle_gorev.getItems().add("Oda Personeli");
    	}
    	else 
    	{
    		cb_guncelle_gorev.getItems().add( per_gorev.getCellData(tw_personel.getSelectionModel().getSelectedIndex()));
    		
    		cb_guncelle_gorev.getItems().add("Genel Temizlik Personeli");
    		
    	}
    	}
    	
    	cb_guncelle_gorev.getSelectionModel().select(0);
    	
    }
    public void log_ara() {
    	temp_loglar=FXCollections.observableArrayList();
    	if(cb_log_ara.getSelectionModel().getSelectedIndex()!=-1 && !txt_log_ara.getText().isEmpty())
    	{
    		
    		if(cb_log_ara.getSelectionModel().getSelectedIndex()==0 )
    			{
       		
       		 log_getir();
    			}
       	 else if(cb_log_ara.getSelectionModel().getSelectedIndex()==1 && !txt_log_ara.getText().isEmpty() )
       	 {
       		 for(log_bilgileri log:loglar)
       	    	{
       	    		if(log.log_id==Integer.valueOf(txt_log_ara.getText()))
       	    			temp_loglar.add(log);
       	    	}
       		 	log_id.setCellValueFactory(new PropertyValueFactory<>("log_id"));
       	  		aciklama.setCellValueFactory(new PropertyValueFactory<>("yapilan_islem"));
       	  		per_id.setCellValueFactory(new PropertyValueFactory<>("kullanici_id"));
       	  		islem_tarih.setCellValueFactory(new PropertyValueFactory<>("yapilan_tarih"));
       	  		yap_id.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_id"));
       	  		yap_kolon.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_kolon"));
       	  		yap_tablo.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_tablo"));
       	 		tw_logs.setItems(temp_loglar);
       	 }
       	 else if(cb_log_ara.getSelectionModel().getSelectedIndex()==2 && !txt_log_ara.getText().isEmpty())
       	 {
       		for(log_bilgileri log:loglar)
   	    	{
   	    		if(log.kullanici_id.equals(txt_log_ara.getText()))
   	    			temp_loglar.add(log);
   	    	}
   		 	log_id.setCellValueFactory(new PropertyValueFactory<>("log_id"));
   	  		aciklama.setCellValueFactory(new PropertyValueFactory<>("yapilan_islem"));
   	  		per_id.setCellValueFactory(new PropertyValueFactory<>("kullanici_id"));
   	  		islem_tarih.setCellValueFactory(new PropertyValueFactory<>("yapilan_tarih"));
   	  		yap_id.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_id"));
   	  		yap_kolon.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_kolon"));
   	  		yap_tablo.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_tablo"));
   	 		tw_logs.setItems(temp_loglar);
       	 }
       	 else if(cb_log_ara.getSelectionModel().getSelectedIndex()==3 && !txt_log_ara.getText().isEmpty())
       	 {
       		for(log_bilgileri log:loglar)
   	    	{
   	    		if(log.islem_yapilan_id==txt_log_ara.getText())
   	    			temp_loglar.add(log);
   	    	}
   		 	log_id.setCellValueFactory(new PropertyValueFactory<>("log_id"));
   	  		aciklama.setCellValueFactory(new PropertyValueFactory<>("yapilan_islem"));
   	  		per_id.setCellValueFactory(new PropertyValueFactory<>("kullanici_id"));
   	  		islem_tarih.setCellValueFactory(new PropertyValueFactory<>("yapilan_tarih"));
   	  		yap_id.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_id"));
   	  		yap_kolon.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_kolon"));
   	  		yap_tablo.setCellValueFactory(new PropertyValueFactory<>("islem_yapilan_tablo"));
   	 		tw_logs.setItems(temp_loglar);
       	 }
    		
    	}
    	
    }
    
    @FXML
    void initialize() {
    	cb_per_ara.getItems().add("Hepsi");
    	cb_per_ara.getItems().add("Id'ye göre getir");
    	cb_per_ara.getItems().add("Ad'a göre getir");
    	cb_per_ara.getItems().add("Soyad'a göre getir");
    	cb_gorev_ekle.getItems().add("Genel Temizlik Personeli");
    	cb_gorev_ekle.getItems().add("Oda Temizligi");
    	cb_log_ara.getItems().add("Hepsi");
    	cb_log_ara.getItems().add("Ýþlem No");
    	cb_log_ara.getItems().add("Personel ID");
    	cb_log_ara.getItems().add("Yapýlan iþlem id");
    	aktif_check();
      log_getir();
      personel_getir();
    }
}
