package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

import com.MySql.Util.DataBaseUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;




public class SampleController extends menu
{
	public kullanici_bilgileri kullanici=new kullanici_bilgileri();
	public SampleController()
	{
		this.kullanici=kullanici_Getir();
		baglanti=DataBaseUtil.Connect();
	}


	public ObservableList<oda_ozellikleri> odalar=FXCollections.observableArrayList();
	CustomAlertButton alert=new CustomAlertButton();
	ObservableList<Label> labels=FXCollections.observableArrayList();
	ObservableList<Label> temp_labels=FXCollections.observableArrayList();
	ObservableList<ImageView> temp_resimler=FXCollections.observableArrayList();
	ObservableList<ImageView> oda_resimleri;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ComboBox<String> cb_kat;
    
    @FXML
    private ComboBox<String> cb_odatip;

    @FXML
    private ImageView image0;

    @FXML
    private Label lbl0;

    @FXML
    private ImageView image1;

    @FXML
    private Label lbl1;

    @FXML
    private ImageView image2;

    @FXML
    private Label lbl2;

    @FXML
    private ImageView image3;

    @FXML
    private Label lbl3;

    @FXML
    private ImageView image4;

    @FXML
    private Label lbl4;

    @FXML
    private ImageView image5;

    @FXML
    private Label lbl5;

    @FXML
    private ImageView image6;

    @FXML
    private Label lbl6;

    @FXML
    private ImageView image7;

    @FXML
    private Label lbl7;

    @FXML
    private ImageView image8;

    @FXML
    private Label lbl8;
    
    
    


    @FXML
    void oda1_Click() {	oda_Ac(image0.getAccessibleText()); }
    @FXML
    void oda2_Click() {	oda_Ac(image1.getAccessibleText());}
    @FXML
    void oda3_Click() {	oda_Ac(image2.getAccessibleText());}
    @FXML
    void oda4_Click() {	oda_Ac(image3.getAccessibleText()); }
    @FXML
    void oda5_Click() {	oda_Ac(image4.getAccessibleText()); }
    @FXML
    void oda6_Click() {	oda_Ac(image5.getAccessibleText()); }
    @FXML
    void oda7_Click() {	oda_Ac(image6.getAccessibleText()); }
    @FXML
    void oda8_Click() {	oda_Ac(image7.getAccessibleText()); }
    @FXML
    void oda9_Click() {	oda_Ac(image8.getAccessibleText());}
    
    
    
    //ikinci combobox aktif etme fonksiyonu
    public void oda_tip_Click()
    {
    	if (!cb_kat.isDisable())
    	{
    		Odalari_Getir();
    	}
    	else if(cb_odatip.getSelectionModel().getSelectedIndex()!=-1)
    	{
    		cb_kat.setDisable(false);
    	}
    }
    
    public void oda_Ac(String a)
    {
    	
    	if(!cb_kat.getSelectionModel().isEmpty())
    	{
    	
    	
    	//System.out.println(a);
    	try {
    		 
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("oda_kayit.fxml"));
        	BorderPane oda_kayit=(BorderPane)loader.load();
        	oda_kayit odabilgi=loader.getController();
        	Scene scene=new Scene(oda_kayit);
        	Stage stage=new Stage();
			stage.setScene(scene);
			stage.getIcons().add(new Image(getClass().getResourceAsStream("e.png")));
		
			
			for(oda_ozellikleri oda:odalar)
			{
				if(oda.oda_id==Integer.parseInt(a))
				{
					
					odabilgi.bilgiler(oda,stage,anaKullanici);

					cb_kat.getSelectionModel().select(-1);
					
					cb_odatip.getSelectionModel().select(-1);
					cb_odatip.setPromptText("Lütfen Tip Seçin.");
					cb_kat.setDisable(true);
					label_Gizleme(labeller);
					oda_resimGizleme(oda_resimleri);
					break;
				}
			}
			
		
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setResizable(false);
			stage.show();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Oda Ac Fonksiyonu :"+e.getMessage());
		}
    	}
    	else
    	{
    		alert.WarningAlert("Oda Seçimi", "Boþ seçim", "Lütfen kat seçiniz");
    	}
    }
    
    Connection baglanti=null;
    PreparedStatement sorgu=null;
    ResultSet cevap;
    String sql;
    
    
    
    //Oda numaralarýný label'e yazma ve gizleme veya gösterme iþlemleri , oda adýndaki objeyi oluþturup verilerini doldurma.
    public void oda_kontrol(int max , int min)
    {
    	oda_resimGizleme(oda_resimleri);
    	label_Gizleme(labeller);
    	odalar.clear();
    	try {
    		 sql="select * from odalar where oda_id=?";
       		 sorgu=baglanti.prepareStatement(sql);
       		 
       		for (Label label : temp_labels) 
       		{
       			 		
       			 		int oda_id=Integer.parseInt(label.getText());
       			 		sorgu.setInt(1,oda_id);
       			 		oda_ozellikleri oda=new oda_ozellikleri();
       			 		cevap=sorgu.executeQuery();
       			 		cevap.next();
       			 		oda.oda_durum=cevap.getInt("oda_durum");
       			 		oda.oda_id=cevap.getInt("oda_id");
       			 		oda.oda_sorumlu=cevap.getString("oda_sorumlu");
       			 		oda.oda_ks=cevap.getInt("oda_ks");
       			 		oda.oda_ucret=cevap.getInt("oda_ucret");
       			 		//System.out.println("labeller fonksiyon" +label.getText());
       			 		label_duzenle(label, oda.oda_durum);
       			 		label.setVisible(true);
       			 		odalar.add(oda);
       			 		/*
       			 		System.out.println(oda.oda_durum);
       			 		System.out.println(oda.oda_id);
       			 		System.out.println(oda.oda_sorumlu);
       			 		System.out.println(oda.oda_ks);*/
       			
    		}
       		for (ImageView resim : temp_resimler) 
       		{
       			 		
       			 		resim.setVisible(true);
       			
    		}
    	}
    		catch (Exception e) {
    			
    			System.out.println(e.getMessage());
				
			
    		
		}
    	
    	
    	
    }
    ObservableList<Label> labeller;
    public void label_duzenle(Label duzeltilceklabel,int durum)
    {
    	 if(durum==0) duzeltilceklabel.setTextFill(Color.web("green"));
			 else if (durum==1)
				 duzeltilceklabel.setTextFill(Color.web("black"));
			 else if (durum==2)
				duzeltilceklabel.setTextFill(Color.web("yellow"));
			 else if (durum==2)
				 duzeltilceklabel.setTextFill(Color.web("blue"));
			 else if (durum==2)
				 duzeltilceklabel.setTextFill(Color.web("red"));
    }
    
    public void Odalari_Getir()
    {
    	temp_resimler.clear();
    	temp_labels.clear();
    	int cb_katno=cb_kat.getSelectionModel().getSelectedIndex();
    	int cb_odatipno=cb_odatip.getSelectionModel().getSelectedIndex();
    	int oda_maxid;
    	int oda_minid;
    	int oda_tipno;
    	int oda_sayisi;
    	if(cb_katno!=-1 && cb_odatipno!=-1)
    	{
    		 oda_minid=((cb_katno+1)*100);
    		 oda_maxid=oda_minid+10;
    	     if(cb_odatipno!=0)
    	     {
    	    	 oda_tipno=cb_odatipno-1;
    			 try {
    		   		 sql="select oda_id,oda_durum from odalar where oda_durum=? and oda_id between ? and ?";
    		      		 sorgu=baglanti.prepareStatement(sql);
    		      		sorgu.setInt(1, oda_tipno);
    		      		 sorgu.setInt(2, oda_minid);
    		      		 sorgu.setInt(3, oda_maxid);
    		      		 cevap=sorgu.executeQuery();
    		      		
    		      		for(Label odano:labeller )
    		       		{
    		       			if(cevap.next())
    		       			{
    		       				
    		       				odano.setText(String.valueOf(cevap.getString(1)));
    		       				//System.out.println(" odalari getir fonksiyon :"+odano.getText());
    		       				
    		       				temp_labels.add(odano);
    		       			}
    		       			
    		       			else break;
    		       			
    		       		}
    		      		cevap=sorgu.executeQuery();
    		      		for(ImageView resim:oda_resimleri )
    		       		{
    		      			
    		       			if(cevap.next())
    		       			{
    		       				
    		       				resim.setAccessibleText(String.valueOf(cevap.getString(1)));
    		       				
    		       				
    		       				temp_resimler.add(resim);
    		       				//System.out.println("ekleme:"+resim.getAccessibleText());
    		       			}
    		       			
    		       			else break;
    		       			
    		       		}
    		      		 oda_sayisi=cevap.getInt(1);
    		      		
    		    	}
    		    	catch (Exception e) {
    					// TODO: handle exception
    				}
    			 
    	     }
    	     else
    	     {
    	    	 try {
    		   		 sql="select oda_id from odalar where oda_id between ? and ?";
    		      		 sorgu=baglanti.prepareStatement(sql);
    		      		 sorgu.setInt(1, oda_minid);
    		      		 sorgu.setInt(2, oda_maxid);
    		      		 cevap=sorgu.executeQuery();
    		      		for(Label odano:labeller )
    		       		{
    		       			if(cevap.next())
    		       			{
    		       				
    		       				odano.setText(String.valueOf(cevap.getString(1)));
    		       				//System.out.println(" odalari getir fonksiyon :"+odano.getText());
    		       				
    		       				temp_labels.add(odano);
    		       			}
    		       			
    		       			else break;
    		       			
    		       		}
    		      		cevap=sorgu.executeQuery(); 
    		      		for(ImageView resim:oda_resimleri )
    		       		{
    		      			
    		       			if(cevap.next())
    		       			{
    		       				
    		       				resim.setAccessibleText(String.valueOf(cevap.getString(1)));
    		       				
    		       				
    		       				temp_resimler.add(resim);
    		       				//System.out.println("ekleme:"+resim.getAccessibleText());
    		       			}
    		       			
    		       			else break;
    		       			
    		       		}
    		      		 oda_sayisi=cevap.getInt(1);
    		    	}
    		    	catch (Exception e) {
    					// TODO: handle exception
    				}
    	    	 
    	     }
    	     Oda_Resimleriekle(cb_katno);
    	     oda_kontrol(oda_maxid,oda_minid);
    	     
    	}
    	
    	

    }
    
    public void Oda_Resimleriekle(int kat)
    {

        
         Image kingbed=new Image(getClass().getResourceAsStream("king.png"));
      	 Image singlebed=new Image(getClass().getResourceAsStream("single.png"));
      	 Image doublebed=new Image(getClass().getResourceAsStream("double.png"));
      	 Image triplebed=new Image(getClass().getResourceAsStream("triple.png"));
      	 
       	if(kat==0)     		
       		for(ImageView resim:temp_resimler )
       			resim.setImage(singlebed);	
       	else if(kat==1)     		
       		for(ImageView resim:temp_resimler )
       			resim.setImage(doublebed);	
       	else if(kat==2)     		
       		for(ImageView resim:temp_resimler )
       			resim.setImage(triplebed);	
       	else if(kat==3)     		
       		for(ImageView resim:temp_resimler )
       			resim.setImage(kingbed);	

    	
       	
    }
    
    
    @FXML
    void cb_kat_Selected(ActionEvent event) 
    {
    	Odalari_Getir();
   

    }
    
    public void label_Gizleme(ObservableList<Label> gizlenecek_labellist)
    {
    	for(Label label:gizlenecek_labellist) label.setVisible(false);
    }
    public void oda_resimGizleme(ObservableList<ImageView> oda_resimleri)
    {
    	for(ImageView resim:oda_resimleri) resim.setVisible(false);
    }

    @FXML
    void initialize() {
    	
    	
    	labeller=FXCollections.observableArrayList(lbl0,lbl1,lbl2,lbl3,lbl4,lbl5,lbl6,lbl7,lbl8);
    	oda_resimleri=FXCollections.observableArrayList(image0,image1,image2,image3,image4,image5,image6,image7,image8);
    	
    	
    	
    	
    	
      
       cb_kat.getItems().add("1.KAT");
       cb_kat.getItems().add("2.KAT");
       cb_kat.getItems().add("3.KAT");
       cb_kat.getItems().add("4.KAT");
       cb_odatip.getItems().add("Tüm odalar");
       cb_odatip.getItems().add("Boþ Odalar");
       cb_odatip.getItems().add("Dolu Odalar");
      // cb_odatip.getItems().add("Rezervasyonlu Odalar");
       //cb_odatip.getItems().add("Bakým Yapýlan Odalar");
       //cb_odatip.getItems().add("Arýzalý Odalar");
       cb_kat.setDisable(true);

    }
}

 




/*	 
     */
