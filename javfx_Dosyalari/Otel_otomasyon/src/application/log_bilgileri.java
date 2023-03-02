package application;

import java.sql.Timestamp;

public class log_bilgileri {

	public int log_id;
	public String yapilan_islem;
	public String kullanici_id;
	public Timestamp yapilan_tarih;
	public String islem_yapilan_id;
	public String islem_yapilan_kolon;
	public String islem_yapilan_tablo;
	log_bilgileri()
	{
		
	}
	log_bilgileri(int temp_id,String temp_islem,String k_id,Timestamp t_tarih,String y_id,String temp_kolon,String temp_tablo)
	{
		this.log_id=temp_id;
		this.yapilan_islem=temp_islem;
		this.kullanici_id=k_id;
		this.yapilan_tarih=t_tarih;
		this.islem_yapilan_id=y_id;
		this.islem_yapilan_kolon=temp_kolon;
		this.islem_yapilan_tablo=temp_tablo;
	}

	public int getLog_id() {
		return log_id;
	}
	public void setLog_id(int log_id) {
		this.log_id = log_id;
	}
	public String getYapilan_islem() {
		return yapilan_islem;
	}
	public void setYapilan_islem(String yapilan_islem) {
		this.yapilan_islem = yapilan_islem;
	}
	public String getKullanici_id() {
		return kullanici_id;
	}
	public void setKullanici_id(String kullanici_id) {
		this.kullanici_id = kullanici_id;
	}
	public Timestamp getYapilan_tarih() {
		return yapilan_tarih;
	}
	public void setYapilan_tarih(Timestamp yapilan_tarih) {
		this.yapilan_tarih = yapilan_tarih;
	}
	public String getIslem_yapilan_id() {
		return islem_yapilan_id;
	}
	public void setIslem_yapilan_id(String islem_yapilan_id) {
		this.islem_yapilan_id = islem_yapilan_id;
	}
	public String getIslem_yapilan_kolon() {
		return islem_yapilan_kolon;
	}
	public void setIslem_yapilan_kolon(String islem_yapilan_kolon) {
		this.islem_yapilan_kolon = islem_yapilan_kolon;
	}
	public String getIslem_yapilan_tablo() {
		return islem_yapilan_tablo;
	}
	public void setIslem_yapilan_tablo(String islem_yapilan_tablo) {
		this.islem_yapilan_tablo = islem_yapilan_tablo;
	}
	
}
