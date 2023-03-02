package application;

import java.sql.Date;

public class rezervasyon_bilgileri {
	public int id;
	public String ad;
	public String soyad;
	public String tc;
	public int oda_id;
	public Date giris_tarihi;
	public Date cikis_tarihi;
	public Boolean aktif;
	public String tel;
	
	rezervasyon_bilgileri()
	{
		
	}
	rezervasyon_bilgileri(int temp_id,String temp_ad,String temp_soyad,String temp_tc,int temp_oda_id,Date temp_giris_tarihi,Date temp_cikis_tarihi)
	{
		this.id=temp_id;
		this.ad=temp_ad;
		this.soyad=temp_soyad;
		this.tc=temp_tc;
		this.oda_id=temp_oda_id;
		this.giris_tarihi=temp_giris_tarihi;
		this.cikis_tarihi=temp_cikis_tarihi;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAd() {
		return ad;
	}

	public void setAd(String ad) {
		this.ad = ad;
	}

	public String getSoyad() {
		return soyad;
	}

	public void setSoyad(String soyad) {
		this.soyad = soyad;
	}

	public String getTc() {
		return tc;
	}

	public void setTc(String tc) {
		this.tc = tc;
	}

	public int getOda_id() {
		return oda_id;
	}

	public void setOda_id(int oda_id) {
		this.oda_id = oda_id;
	}

	public Date getGiris_tarihi() {
		return giris_tarihi;
	}

	public void setGiris_tarihi(Date giris_tarihi) {
		this.giris_tarihi = giris_tarihi;
	}

	public Date getCikis_tarihi() {
		return cikis_tarihi;
	}

	public void setCikis_tarihi(Date cikis_tarihi) {
		this.cikis_tarihi = cikis_tarihi;
	}
	
}
