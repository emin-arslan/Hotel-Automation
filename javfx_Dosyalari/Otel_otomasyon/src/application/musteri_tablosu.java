package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.MySql.Util.DataBaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class musteri_tablosu {
	
	CustomAlertButton alert=new CustomAlertButton();
	ObservableList<musteri_bilgileri> musteri_list;
	ObservableList<musteri_bilgileri> temp_musteri_list;
	public musteri_tablosu() {
		baglanti=DataBaseUtil.Connect();
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<musteri_bilgileri> tw_musteriler;

    @FXML
    private TableColumn<musteri_bilgileri,Integer> tc_id;

    @FXML
    private TableColumn<musteri_bilgileri,String> tc_ad;

    @FXML
    private TableColumn<musteri_bilgileri, String> tc_soyad;

    @FXML
    private TableColumn<musteri_bilgileri, String> tc_tc;

    @FXML
    private TableColumn<musteri_bilgileri, String> tc_telno;

    @FXML
    private TextField txt_guncelle_id;

    @FXML
    private TextField txt_guncell_ad;

    @FXML
    private TextField txt_guncelle_soyad;

    @FXML
    private TextField txt_guncelle_tc;

    @FXML
    private TextField txt_guncelle_tel;

    @FXML
    private Button btn_guncelle;
    
    @FXML
    private TextField txt_filtrele;


    public void txt_changed()
    {
       	temp_musteri_list=FXCollections.observableArrayList();
    	if(!txt_filtrele.getText().isEmpty())
    	{
    		
    		for(musteri_bilgileri musteri:musteri_list)
    		{
    			if(txt_filtrele.getText().equals(String.valueOf(musteri.m_tc)))
    			{
    				temp_musteri_list.add(musteri);
    			}
    		}
    		tc_id.setCellValueFactory(new PropertyValueFactory<>("m_id"));
    		tc_ad.setCellValueFactory(new PropertyValueFactory<>("m_ad"));
    		tc_soyad.setCellValueFactory(new PropertyValueFactory<>("m_soyad"));
    		tc_tc.setCellValueFactory(new PropertyValueFactory<>("m_tc"));
    		tc_telno.setCellValueFactory(new PropertyValueFactory<>("m_tel"));
    		tw_musteriler.setItems(temp_musteri_list);
    	}
    	else musteri_getir();
    }
    
    public void secilen_deger()
    {
    	if(tw_musteriler.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		txt_guncelle_id.setText(String.valueOf( tc_id.getCellData(tw_musteriler.getSelectionModel().getSelectedIndex())));
    		txt_guncell_ad.setText(tc_ad.getCellData(tw_musteriler.getSelectionModel().getSelectedIndex()));
    		txt_guncelle_soyad.setText(String.valueOf(tc_soyad.getCellData(tw_musteriler.getSelectionModel().getSelectedIndex())));
    		txt_guncelle_tc.setText(String.valueOf(tc_tc.getCellData(tw_musteriler.getSelectionModel().getSelectedIndex())));
    		txt_guncelle_tel.setText(String.valueOf(tc_telno.getCellData(tw_musteriler.getSelectionModel().getSelectedIndex())));
    	}
    	
    	
    	
    	
    }
    public void musteri_guncelle()
    {
    	if(!txt_guncell_ad.getText().isEmpty() && !txt_guncelle_id.getText().isEmpty() && !txt_guncelle_soyad.getText().isEmpty() && !txt_guncelle_tc.getText().isEmpty() && !txt_guncelle_tel.getText().isEmpty() && tw_musteriler.getSelectionModel().getSelectedIndex()!=-1 )
    	{
    		try
    		{
    		sorgu=baglanti.prepareStatement("update musteriler set m_ad=? , m_soyad=?, m_tc=?,m_tel=? where m_id=?");
    		sorgu.setString(1, txt_guncell_ad.getText());
    		sorgu.setString(2, txt_guncelle_soyad.getText());
    		sorgu.setString(3, txt_guncelle_tc.getText());
    		sorgu.setString(4, txt_guncelle_tel.getText());
    		sorgu.setInt(5,Integer.parseInt(txt_guncelle_id.getText()));
    		sorgu.executeUpdate();
    		alert.InformationAlert("Güncelleme","Güncelleme Baþarýlý", "Güncellediðiniz deðerleri tablodan kontrol ediniz..!");
    		musteri_getir();
    		}
    		catch (Exception e) {
					alert.WarningAlert("Beklenmiyen Hata","Hata kodu:9" ,"Lütfen yöneticiye baþvurun:"+e.getMessage());
			}
    	}
    	else
    	{
    		alert.WarningAlert("Boþ Deðer Giriþi","Boþ Býrakýlmaz.","Lütfen tablodan deðer seçin veya boþ deðer girmeyin.!");
    	}
    }

    public void musteri_getir()
    {
    	musteri_list=FXCollections.observableArrayList();
    	try
    	{
    	sorgu=baglanti.prepareStatement("Select * from musteriler");
    	cevap=sorgu.executeQuery();
    	while(cevap.next())
    	{
    		musteri_list.add(new musteri_bilgileri(cevap.getInt("m_id"), cevap.getString("m_ad"), cevap.getString("m_soyad"), cevap.getString("m_tc"), cevap.getString("m_tel")));
    	}
    	tc_id.setCellValueFactory(new PropertyValueFactory<>("m_id"));
    	tc_ad.setCellValueFactory(new PropertyValueFactory<>("m_ad"));
    	tc_soyad.setCellValueFactory(new PropertyValueFactory<>("m_soyad"));
    	tc_tc.setCellValueFactory(new PropertyValueFactory<>("m_tc"));
    	tc_telno.setCellValueFactory(new PropertyValueFactory<>("m_tel"));
    	tw_musteriler.setItems(musteri_list);
    	}
    	catch (Exception e) {
			System.out.println(e.getMessage());
		}
    }
    
    Connection baglanti=null;
    PreparedStatement sorgu=null;
    ResultSet cevap=null;

    
    @FXML
    void initialize() {
    	musteri_getir();
    }
}
