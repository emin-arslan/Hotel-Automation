package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.ResourceBundle;
import application.SampleController;
import com.MySql.Util.DataBaseUtil;


import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
public class oda_kayit {

	public Boolean hata=false;
	public Connection baglanti;
	public String oda_id2;
	public int kisi_sayisi;
	public String tutulan_id=null;
	public ArrayList<TextField> adlar;
	public ArrayList<TextField> soyadlar;
	public ArrayList<TextField> tcler;
	public CustomAlertButton alert=new CustomAlertButton();
	public String temp_tel;
	public Stage stageAna;
	public SampleController yenile=new SampleController();
	public ObservableList<rezervasyon_bilgileri> rezervasyon_list;
	public oda_ozellikleri oda;
	static public kullanici_bilgileri kullanici;
	public void bilgiler(oda_ozellikleri temp_oda,  Stage stage,kullanici_bilgileri temp_kullanici )
	{
		kullanici=temp_kullanici;

		oda=new oda_ozellikleri();
		oda=temp_oda;
		stageAna=new Stage();
		stageAna=stage;
		baglanti=DataBaseUtil.Connect();
		oda_id2=Integer.toString(oda.oda_id);
		lbl_odano.setText("Oda Numarasi:"+oda_id2);
		kisi_sayisi=oda.oda_ks;
		
		
		if(oda.oda_durum==0) { lbl_odadurum.setText("Oda Durumu:Boþ");            tab_oda.getTabs().remove(2);               }
		else if(oda.oda_durum==1) { lbl_odadurum.setText("Oda Durumu:Dolu"); tab_oda.getTabs().remove(1);     }
		else if(oda.oda_durum==2)lbl_odadurum.setText("Oda Durumu:Rezervasyonlu");
		else if(oda.oda_durum==3)lbl_odadurum.setText("Oda Durumu:Bakým");
		else if(oda.oda_durum==4)lbl_odadurum.setText("Oda Durumu:Arýzalý");
		lbl_odayataksayisi.setText("Oda Kisi Sayýsý:"+Integer.toString(kisi_sayisi));
		lbl_sorumlu.setText(oda.oda_sorumlu);
	}
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
    @FXML
    private Label lbl_odabilgi;

    @FXML
    private Label lbl_odano;

    @FXML
    private Label lbl_odadurum;

    @FXML
    private Label lbl_odayataksayisi;

    @FXML
    private Label lbl_temizliktarih;

    @FXML
    private Label lbl_sorumlu;

    @FXML
    private TabPane tab_oda;
    
    @FXML
    private Tab tb_rezgoruntule;

    @FXML
    private TableView<rezervasyon_bilgileri> rez_table;

    @FXML
    private TableColumn<rezervasyon_bilgileri,Integer> tb_rez_id;

    @FXML
    private TableColumn<rezervasyon_bilgileri, String> tb_rez_tc;

    @FXML
    private TableColumn<rezervasyon_bilgileri, String> tb_rez_ad;

    @FXML
    private TableColumn<rezervasyon_bilgileri, String> tb_rez_soyad;

    @FXML
    private TableColumn<rezervasyon_bilgileri, Date> tb_rez_gt;

    @FXML
    private TableColumn<rezervasyon_bilgileri, Date> tb_rez_ct;

    @FXML
    private TextField txt_ara;

    @FXML
    private ComboBox<String> cb_ara;
    
    @FXML
    private ComboBox<String> cb_guncelle;
    

    @FXML
    private TextField txt_guncelle;

    @FXML
    private Button btn_guncelle;


    @FXML
    private Button btn_ara;

    @FXML
    private Label lbl_rezonay1;

    @FXML
    private TextField txt_rezisim;

    @FXML
    private TextField txt_rezsoyisim;

    @FXML
    private TextField txt_reztel;

    @FXML
    private TextField txt_reztc;

    @FXML
    private DatePicker txt_rezgiris;

    @FXML
    private DatePicker txt_rezcikis;

    @FXML
    private Button btn_rezonayla;

    @FXML
    private TextField txt_rez_sil_id;

    @FXML
    private CheckBox cb_rez_sil;

    @FXML
    private Button btn_rez_sil;

    @FXML
    private TabPane tab_kisi;

    @FXML
    private Button btn_onayla;

    @FXML
    private DatePicker dt_giris;

    @FXML
    private DatePicker dt_cikis;

    @FXML
    private Label lbl_rezonay;

    @FXML
    private CheckBox cb_cikis_onay;

    @FXML
    private Button btn_cikis_onay;

    
    
    
    @FXML
    void rezervasyon_Kaydet()
    {
    	if(txt_rezisim.getText().isEmpty() || txt_rezsoyisim.getText().isEmpty() || txt_reztc.getText().isEmpty() || txt_reztel.getText().isEmpty() || txt_rezgiris.getValue()==null || txt_rezcikis.getValue()==null) 
    		alert.WarningAlert("Boþ Alan", "Boþ alan býrakmayýnýz!!", "Lütfen boþ alan olmadýðýndan emin olunuz..!");
    	else
    	{
    	java.util.Date date = java.util.Date.from(txt_rezgiris.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDateGiris = new java.sql.Date(date.getTime());
		java.util.Date date2 = java.util.Date.from(txt_rezcikis.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDateCikis = new java.sql.Date(date2.getTime());
    	if(!(sqlDateGiris.compareTo(sqlDateCikis)==1 || sqlDateGiris.compareTo(sqlDateCikis)==0)) 
    	{
    	try {
   		
			pst=baglanti.prepareStatement("insert rez_odalar(rez_odaid,rez_ad,rez_soyad,rez_tc,rez_tel,rez_gt,rez_ct,rez_aktif)values (?,?,?,?,?,?,?,?)  ");
			pst.setInt(1,oda.oda_id);
			pst.setString(2,txt_rezisim.getText());
			pst.setString(3,txt_rezsoyisim.getText());
			pst.setString(4,txt_reztc.getText());
			pst.setString(5,txt_reztel.getText());
			pst.setDate(6,sqlDateGiris);
			pst.setDate(7,sqlDateCikis);
			/*System.out.println(sqlDateCikis);
			System.out.println(sqlDateGiris);
			System.out.println(date2);
			System.out.println(date);*/
			pst.setBoolean(8,true);
			pst.executeUpdate();
			alert.InformationAlert("Rezervasyon Baþarýlý", "Oda No:"+oda.oda_id,"Giriþ Tarihi:"+sqlDateGiris+"\nÇýkýþ Tarihi:"+sqlDateCikis );
			pst=baglanti.prepareStatement("Select MAX(rez_id) as sonuncu from rez_odalar");
			
			ResultSet cevap=pst.executeQuery();
			if(cevap.next()) {
			log_ekle(kullanici.kullanici_id,String.valueOf( cevap.getInt("sonuncu")),"tüm kolonlar","rez_odalar","Yeni bir rezervasyon eklenmiþtir.");
			}
			else System.out.println("GEÇERSIZ ISLEM");
			stageAna.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    	}
    	else alert.ErrorAlert("Tutarsýz Tarih", "Girilen tarihleri kontrol ediniz", "Giriþ tarihi Çýkýþ tarihinden küçük veya eþit olamaz");
    	}
    }
    
    
    
    public void log_ekle(String temp_perid,String temp_islemid,String temp_kolonad,String temp_tabload,String temp_aciklama)
    {
    	
    	try {
			pst=baglanti.prepareStatement("insert into logs(yapilan_islem,k_id,yapilan_tarih,islem_yapilan_id,islem_yapilan_kolon,islem_yapilan_tablo) values(?,?,?,?,?,?)");
			pst.setString(1, temp_aciklama);
			pst.setString(2, temp_perid);
			pst.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			pst.setString(4, temp_islemid);
			pst.setString(5, temp_kolonad);
			pst.setString(6, temp_tabload);
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
   
    }
    
    //Eþitlik saðlanmýyor sebebini Allah bilir.
    public void rezervasyon_Ara()
    {
    	
    	rezervasyon_bilgileri temp_rez=new rezervasyon_bilgileri();
    	temp_rez.tc=txt_ara.getText();
    	if(cb_ara.getSelectionModel().getSelectedIndex()==1) {
    		
    	
    	ObservableList<rezervasyon_bilgileri> temp_rez_list=FXCollections.observableArrayList();
    	for(rezervasyon_bilgileri rezervasyon:rezervasyon_list)
    	{
    		
    		//System.out.println("real_list-tc:"+rezervasyon.tc);
    		//System.out.println("temp_list-tc:"+temp_rez.tc);
    		if(rezervasyon.tc.equals(temp_rez.tc)) 
    		{
    			temp_rez_list.add(rezervasyon);
    			//System.out.println("sea");
    			
    		}
    		//else System.out.println("Geçersiz Deðer");
    		
    	}
    	tb_rez_id.setCellValueFactory(new PropertyValueFactory<>("id"));
		tb_rez_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
		tb_rez_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
		tb_rez_tc.setCellValueFactory(new PropertyValueFactory<>("tc"));
		tb_rez_gt.setCellValueFactory(new PropertyValueFactory<>("giris_tarihi"));
		tb_rez_ct.setCellValueFactory(new PropertyValueFactory<>("cikis_tarihi"));
    	rez_table.setItems(temp_rez_list);
    	}
    	else rezervasyon_Getir();
    	
    	
    	
    } 
    
    
    public void rezervasyon_sql_Guncelle(String yeni_deger,int degiscek_kolon,int rez_id)
    {
    	String sql;
    	{
    		if(degiscek_kolon==0)
    		{
    			sql="update rez_odalar set rez_tc=? where rez_id=?";
    			
        		try {
        			pst=baglanti.prepareStatement(sql);
            		pst.setString(1, yeni_deger);
					pst.setInt(2, rez_id);
					pst.executeUpdate();
					alert.InformationAlert("Deðiþiklik Baþarýlý", "Deðiþtirilen sütun:Müþteri Tc", "Yeni deðer:"+yeni_deger);
					
					
					log_ekle(kullanici.kullanici_id,String.valueOf(rez_id),"rez_tc","rez_odalar","Eski deðer:"+txt_guncelle.getText()+" olan deðer,"+yeni_deger+" olarak güncellenmiþtir");
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Müþteri Tc hatasi"+e.getMessage());
				}
        		
    		}
    		else if(degiscek_kolon==1)
    		{
    			sql="update rez_odalar set rez_ad=? where rez_id=?";
    			try {
        			pst=baglanti.prepareStatement(sql);
            		pst.setString(1, yeni_deger);
					pst.setInt(2, rez_id);
					pst.executeUpdate();
					alert.InformationAlert("Deðiþiklik Baþarýlý", "Deðiþtirilen sütun:Müþteri adi", "Yeni deðer:"+yeni_deger);
					log_ekle(kullanici.kullanici_id,String.valueOf(rez_id),"rez_ad","rez_odalar","Eski deðer:"+txt_guncelle.getText()+" olan deðer,"+yeni_deger+" olarak güncellenmiþtir");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Müþteri Adi hatasi"+e.getMessage());
				}
    		}
    		else if(degiscek_kolon==2)
    		{
    			sql="update rez_odalar set rez_soyad=? where rez_id=?";
    			try {
        			pst=baglanti.prepareStatement(sql);
            		pst.setString(1, yeni_deger);
					pst.setInt(2, rez_id);
					pst.executeUpdate();
					alert.InformationAlert("Deðiþiklik Baþarýlý", "Deðiþtirilen sütun:Müþteri Soyadi", "Yeni deðer:"+yeni_deger);
					log_ekle(kullanici.kullanici_id,String.valueOf(rez_id),"rez_soyad","rez_odalar","Eski deðer:"+txt_guncelle.getText()+" olan deðer,"+yeni_deger+" olarak güncellenmiþtir");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Müþteri Soyadi hatasi"+e.getMessage());
				}
    		}
    		else if(degiscek_kolon==3)
    		{
    			sql="update rez_odalar set rez_gt=? where rez_id=?";
    			try {
        			pst=baglanti.prepareStatement(sql);
            		pst.setDate(1, Date.valueOf(yeni_deger));
					pst.setInt(2, rez_id);
					pst.executeUpdate();
					alert.InformationAlert("Deðiþiklik Baþarýlý", "Deðiþtirilen sütun:Giriþ Tarihi", "Yeni deðer:"+yeni_deger);
					log_ekle(kullanici.kullanici_id,String.valueOf(rez_id),"rez_gt","rez_odalar","Eski deðer:"+txt_guncelle.getText()+" olan deðer,"+yeni_deger+" olarak güncellenmiþtir");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Giriþ tarihi hatasi"+e.getMessage());
				}
    		}
    		else if(degiscek_kolon==4)
    		{
    			sql="update rez_odalar set rez_ct=? where rez_id=?";
    			try {
        			pst=baglanti.prepareStatement(sql);
            		pst.setDate(1, Date.valueOf(yeni_deger));
					pst.setInt(2, rez_id);
					pst.executeUpdate();
					alert.InformationAlert("Deðiþiklik Baþarýlý", "Deðiþtirilen sütun:Çýkýþ Tarihi", "Yeni deðer:"+yeni_deger);
					log_ekle(kullanici.kullanici_id,String.valueOf(rez_id),"rez_ct","rez_odalar","Eski deðer:"+txt_guncelle+" olan deðer,"+yeni_deger+"olarak güncellenmiþtir");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Çýkýþ tarihi hatasi"+e.getMessage());
				}
    			
    		}

    		
    		
    		
    	}
    }
    
    
    public void rezervasyon_Guncelle()
    {
    	String degisicek_icerik;
    	try {
			
		
    	if(rez_table.getSelectionModel().getSelectedItem()!=null) 
    	{
    		if(cb_guncelle.getSelectionModel().getSelectedItem()!=null)
    		{
    			rezervasyon_bilgileri rezervasyon=rez_table.getSelectionModel().getSelectedItem();
    			if(cb_guncelle.getSelectionModel().getSelectedIndex()==0)
    			{
    				
    				
    				degisicek_icerik=rezervasyon.tc;
    				if(txt_guncelle!=null && !(degisicek_icerik.equals(txt_guncelle.getText())))
    				{
    				rezervasyon_sql_Guncelle(txt_guncelle.getText(),0, rezervasyon.id);
    				rezervasyon_Getir();
    				}
    				else alert.WarningAlert("Geçersiz Deðer", "Boþ veya ayný deðer", "Lütfen Girdiðiniz Deðeri Kontrol ediniz.!");
    				/*for(rezervasyon_bilgileri rezervasyon:rezervasyon_list)
    				{
    					
    				}*/
    				
    			}
    			else if(cb_guncelle.getSelectionModel().getSelectedIndex()==1)
    			{
    				
    				degisicek_icerik=rezervasyon.ad;
    				//System.out.println(degisicek_icerik);
    				//System.out.println(txt_guncelle.getText());
    				if(txt_guncelle!=null && !(degisicek_icerik.equals(txt_guncelle.getText()))  )
    				{
    				rezervasyon_sql_Guncelle(txt_guncelle.getText(),1, rezervasyon.id);
    				rezervasyon_Getir();
    				}
    				else alert.WarningAlert("Geçersiz Deðer", "Boþ veya ayný deðer", "Lütfen Girdiðiniz Deðeri Kontrol ediniz.!");
    			}
    			else if(cb_guncelle.getSelectionModel().getSelectedIndex()==2)
    			{
    				
    				degisicek_icerik=rezervasyon.soyad;
    				if(txt_guncelle!=null && !(degisicek_icerik.equals(txt_guncelle.getText())) )
    				{
    				rezervasyon_sql_Guncelle(txt_guncelle.getText(),2, rezervasyon.id);
    				rezervasyon_Getir();
    				}
    				else alert.WarningAlert("Geçersiz Deðer", "Boþ veya ayný deðer", "Lütfen Girdiðiniz Deðeri Kontrol ediniz.!");
    			}
    			else if(cb_guncelle.getSelectionModel().getSelectedIndex()==3)
    			{
    				
    				degisicek_icerik=String.valueOf(rezervasyon.giris_tarihi);
    				Date temp_tarih=rezervasyon.cikis_tarihi;
    				if(txt_guncelle!=null && !(degisicek_icerik.equals(txt_guncelle.getText())) && temp_tarih.compareTo(Date.valueOf(txt_guncelle.getText()))==1 )
    				{
    				rezervasyon_sql_Guncelle(txt_guncelle.getText(),3, rezervasyon.id);
    				rezervasyon_Getir();
    				}
    				else alert.WarningAlert("Geçersiz Deðer", "Boþ veya ayný deðer", "Lütfen Girdiðiniz Deðeri Kontrol ediniz.!");
    			}
    			else if(cb_guncelle.getSelectionModel().getSelectedIndex()==4)
    			{
    				Date temp_tarih=rezervasyon.giris_tarihi;
    				degisicek_icerik=String.valueOf(rezervasyon.cikis_tarihi);
    				if(txt_guncelle!=null && !(degisicek_icerik.equals(txt_guncelle.getText())) && temp_tarih.compareTo(Date.valueOf(txt_guncelle.getText()))==-1)
    				{
    				rezervasyon_sql_Guncelle(txt_guncelle.getText(),4, rezervasyon.id);
    				rezervasyon_Getir();
    				}
    				else alert.WarningAlert("Geçersiz Deðer", "Boþ veya ayný deðer", "Lütfen Girdiðiniz Deðeri Kontrol ediniz.!");
    			}
    			else alert.WarningAlert("Geçersiz Ýþlem", "Tanýnmayan iþlem", "404 ERROR");
    			
    			
    			
    			
    			
    		/*	txt_guncelle.getText()=
    		rezervasyon_bilgileri rezervasyon=rez_table.getSelectionModel().getSelectedItem();
    		int id=rezervasyon.id;
    		System.out.println(id);*/
    		}
    	}
    	else alert.WarningAlert("Boþ deðer", "Seçilen bir deðer yok", "Lütfen tablodan bir deðer seçiniz..!");
    	} 
    	catch (Exception e) {
			System.out.println(e.getMessage());
		}
    	
    	
    	
    	
    }
    public void rezervasyon_Sil()
    {
    	if(cb_rez_sil.isSelected())
    	try {
    		pst=baglanti.prepareStatement("delete  from rez_odalar where rez_id=?");
    		pst.setInt(1,Integer.parseInt(txt_rez_sil_id.getText()));
    		pst.executeUpdate();
    		alert.InformationAlert("Rezervasyon Silme", "Rez id:"+txt_rez_sil_id.getText(), "Rezervasyon Baþarýyla silinmiþtir.");
			log_ekle(kullanici.kullanici_id,txt_rez_sil_id.getText(),"Tüm kolonlar","rez_odalar","Silinen rezervasyon id="+txt_rez_sil_id.getText());
    		rezervasyon_Getir();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
    public void rezervasyon_Getir()
    {
    	if(tb_rezgoruntule.isSelected())
    	{
    	baglanti=DataBaseUtil.Connect();
    	rezervasyon_list=FXCollections.observableArrayList();
    	try {
    		
    		pst=baglanti.prepareStatement("Select * from rez_odalar where rez_odaid=?");
    		pst.setInt(1, Integer.valueOf(oda_id2));
    		//System.out.println("sadassadddddddd"+oda_id2);
    		ResultSet cevap=pst.executeQuery();
    		while(cevap.next())
    		{
    			rezervasyon_list.add(new rezervasyon_bilgileri(cevap.getInt("rez_id"), cevap.getString("rez_ad"),cevap.getString("rez_soyad") , cevap.getString("rez_tc"), cevap.getInt("rez_odaid"), cevap.getDate("rez_gt"), cevap.getDate("rez_ct")));  
    		}
    		tb_rez_id.setCellValueFactory(new PropertyValueFactory<>("id"));
    		tb_rez_ad.setCellValueFactory(new PropertyValueFactory<>("ad"));
    		tb_rez_soyad.setCellValueFactory(new PropertyValueFactory<>("soyad"));
    		tb_rez_tc.setCellValueFactory(new PropertyValueFactory<>("tc"));
			tb_rez_gt.setCellValueFactory(new PropertyValueFactory<>("giris_tarihi"));
			tb_rez_ct.setCellValueFactory(new PropertyValueFactory<>("cikis_tarihi"));
			rez_table.setItems(rezervasyon_list);
		} catch (Exception e) {
			// TODO: handle exception
			//System.out.println("s111111111111d"+oda_id2);
			System.out.println(e.getMessage());
		}
    	}
    }
    
    @FXML
    void oda_cikis_Click()
    {
 	   if(cb_cikis_onay.isSelected())
 	   {
 		   	try {
				pst=baglanti.prepareStatement("update odalar set oda_durum=0,oda_girist=null,oda_musteri_tc=null,oda_cikist=null where oda_id=?  ");
				pst.setInt(1,Integer.valueOf(oda_id2));
				pst.executeUpdate();
				stageAna.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
 	   }
    	
    	
    	
    	
    }

   
    PreparedStatement pst=null;
    ResultSet veri=null;
    public void musteri_ekle(String mus_ad,String mus_soyad,String mus_tc,String mus_tel)
    {
    	try {
			pst=baglanti.prepareStatement("select * from musteriler where m_tc=?");
			pst.setString(1, mus_tc);
			veri=pst.executeQuery();
			if(!veri.next())
				try {
					pst=baglanti.prepareStatement("insert into musteriler(m_ad,m_soyad,m_tc,m_tel) values(?,?,?,?)");
					pst.setString(1, mus_ad);
					pst.setString(2, mus_soyad);
					pst.setString(3, mus_tc);
					pst.setString(4, mus_tel);
					pst.executeUpdate();
					log_ekle(kullanici.kullanici_id,mus_tc,"Tüm kolonlar","musteriler","Yeni bir müsteri eklenmiþtir.!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Musteri Ekleme Hatasi:"+e.getMessage());
				}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
    	
    	
    }
   
    @FXML
    public void kayit_onayla()
    {
    	hata=true;
    	if(dt_giris.getValue()==null || dt_cikis.getValue()==null || dt_giris.getValue().compareTo(dt_cikis.getValue())==0 || dt_giris.getValue().compareTo(dt_cikis.getValue())==1 ) alert.WarningAlert("Tarih Hatasý", "Geçersiz tarihler", "Lütfen tarihleri kontrol ediniz.Giriþ tarihi büyük veya eþit olamaz.Tarihler boþ býrakýlamaz!.");
    	else
    	{
    		try {
    	String musteri_tcleri="";
    	
    	LocalDate datenow = java.time.LocalDate.now();
    	java.sql.Date sqlDate = java.sql.Date.valueOf(datenow);
    	java.util.Date date = java.util.Date.from(dt_giris.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDateGiris = new java.sql.Date(date.getTime());
		java.util.Date date2 = java.util.Date.from(dt_giris.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
		java.sql.Date sqlDateCikis = new java.sql.Date(date2.getTime());
		
		if(hata)
    	for(int i=0;i<kisi_sayisi;i++)
		{
			
			if(adlar.get(i).getText().length()>20 || soyadlar.get(i).getText().length()>20 || tcler.get(i).getText().length()!=11 || adlar.get(i).getText().isEmpty() ||  soyadlar.get(i).getText().isEmpty() || tcler.get(i).getText().isEmpty() )
			{
				
				alert.ErrorAlert("Hatali Giriþ", (kisi_sayisi-i)+". kiþinin verileri hatalý.", "Lütfen girdiðiniz deðerleri kontrol ediniz.TC 11 haneli olmak zorunda");
				hata=true;
				break;
			}//sqldategirisleri hatalý , yanlýþ yerde hata veriyor
			else if(sqlDateGiris.compareTo(sqlDate)<1  || String.valueOf(sqlDateGiris)=="" || String.valueOf(sqlDateCikis)=="")
			{
				
				alert.ErrorAlert("Hatali Tarih","Giriþ tarihi geçmiþ zaman","Giriþ tarihi geçmiþ zaman olamaz.");
				hata=true;
				break;
			}
			else
			{
				musteri_ekle(adlar.get(i).getText(), soyadlar.get(i).getText(), tcler.get(i).getText(), temp_tel);
				hata=false;
				musteri_tcleri+=tcler.get(i).getText()+",";
			}
		}
    		
		if(!hata)
    	try {
    		
    		
				pst=baglanti.prepareStatement("update odalar set  oda_girist=?,oda_cikist=?,oda_musteri_tc=?,oda_durum=1 where oda_id=?");	
				pst.setDate(1, sqlDateGiris);
				pst.setDate(2, sqlDateCikis);
				pst.setString(3, musteri_tcleri);
				pst.setInt(4, Integer.valueOf(oda_id2));
				//System.out.println(musteri_tcleri);
				pst.executeUpdate();
				long daysBetween = ChronoUnit.DAYS.between(dt_giris.getValue(), dt_cikis.getValue());
				alert.InformationAlert("Oda Bilgileri", "Oda No:"+oda_id2,"Giriþ Tarihi:"+sqlDateGiris+"\nÇýkýþ Tarihi:"+sqlDateCikis+"\nOda Ücreti:"+oda.oda_ucret*daysBetween );
				log_ekle(kullanici.kullanici_id,oda_id2,"Tüm kolonlar","odalar","Yeni bir oda giriþi yapýlmýþtýr.");
				//yenile.Odalari_Getir();
				stageAna.close();
				
				
				
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Giris Hatasi:"+e.getMessage());
		}
    	}
    		catch (Exception e) {
				System.out.println("sa:"+e.getMessage());
			}
    	}
    }
    
    @FXML
    public void kayit_Selected()
    {
    	
    	if(tutulan_id==oda_id2)
    	{
    		
    	}
    	else
    	try {
    		adlar=new ArrayList<TextField>();
    		soyadlar=new ArrayList<TextField>();
    		tcler=new ArrayList<TextField>();
    		 if(!adlar.isEmpty()) adlar.clear();
    		 if(!soyadlar.isEmpty()) adlar.clear();
    		 if(!tcler.isEmpty()) adlar.clear();
    		for (int i = kisi_sayisi; i >0; i--)
    		{
    			AnchorPane icerik=new AnchorPane();
    			int numTabs = (kisi_sayisi+1)-tab_kisi.getTabs().size();
    			Tab tab = new Tab((numTabs)+". Kisi");
            	tab_kisi.getTabs().add(0,tab);
            	tab.setContent(icerik);
            	
            	
            	
            	
            	Label lbl_musteri = new Label();
            	lbl_musteri.setText((i)+". Müþteri Bilgilerini Girin.");
            	lbl_musteri.setLayoutX(110);
            	lbl_musteri.setLayoutY(45);
            	lbl_musteri.setTextFill(Color.web("#96b946"));
            	icerik.getChildren().add(lbl_musteri);
            	
            	Label lbl_isim = new Label();
            	lbl_isim.setText("Ýsim:");
            	lbl_isim.setLayoutX(90);
            	lbl_isim.setLayoutY(85);
            	icerik.getChildren().add(lbl_isim);
            	
            	TextField txt_isim=new TextField();
            	txt_isim.setLayoutX(140);
            	txt_isim.setLayoutY(40+(40));
            	icerik.getChildren().add(txt_isim);
            	adlar.add(txt_isim);
            	
            	
            	Label lbl_soyisim = new Label();
            	lbl_soyisim.setText("Soyisim:");
            	lbl_soyisim.setLayoutX(90);
            	lbl_soyisim.setLayoutY(125);
            	icerik.getChildren().add(lbl_soyisim);
            	
            	
            	TextField txt_soyisim=new TextField();
            	txt_soyisim.setLayoutX(140);

            	txt_soyisim.setLayoutY(40+(80));
            	icerik.getChildren().add(txt_soyisim);
            	soyadlar.add(txt_soyisim);
            	
            	Label lbl_tc = new Label();
            	lbl_tc.setText("Tc:");
            	lbl_tc.setLayoutX(90);
            	lbl_tc.setLayoutY(165);
            	icerik.getChildren().add(lbl_tc);
            	
            	
            	TextField txt_tc=new TextField();
            	txt_tc.setLayoutX(140);
            	txt_tc.setLayoutY(40+(120));
            	icerik.getChildren().add(txt_tc); 
            	tcler.add(txt_tc);
            	
            	
            	
            }
    		tab_kisi.getSelectionModel().select(0);
            	
         
            	
             	//tab_kisi.getTabs().addAll((Tab)FXMLLoader.load(this.getClass().getResource("tab.fxml")));
    		
    	}
    	catch (Exception e) {
    		System.out.println("Ýçerik Hatasi:"+e.getMessage());
		}
    	tutulan_id=oda_id2;
    }

    @FXML
    void initialize() {
    	tab_oda.getSelectionModel().select(0);
    	
    	cb_ara.getItems().add("Hepsi");
    	cb_ara.getItems().add("Tc");
    	
    	cb_guncelle.getItems().add("Seçilen Müþteri Tc");
    	cb_guncelle.getItems().add("Seçilen Müþteri Adý");
    	cb_guncelle.getItems().add("Seçilen Müþteri Soyadý");
    	cb_guncelle.getItems().add("Seçilen Giriþ Tarihi");
    	cb_guncelle.getItems().add("Seçilen Çýkýþ Tarihi");
    	
    	

    }
}
