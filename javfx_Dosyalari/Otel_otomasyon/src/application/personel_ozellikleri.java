package application;

public class personel_ozellikleri {

	public int per_id;
	public String per_ad;
	public String per_soyad;
	public String per_gorev;
	
	public personel_ozellikleri() {
		// TODO Auto-generated constructor stub
	}
	public personel_ozellikleri(int temp_id,String temp_ad,String temp_soyad,String temp_gorev)
	{
		this.per_id=temp_id;
		this.per_ad=temp_ad;
		this.per_soyad=temp_soyad;
		this.per_gorev=temp_gorev;
		
	
	}
	public int getPer_id() {
		return per_id;
	}
	public void setPer_id(int per_id) {
		this.per_id = per_id;
	}
	public String getPer_ad() {
		return per_ad;
	}
	public void setPer_ad(String per_ad) {
		this.per_ad = per_ad;
	}
	public String getPer_soyad() {
		return per_soyad;
	}
	public void setPer_soyad(String per_soyad) {
		this.per_soyad = per_soyad;
	}
	public String getPer_gorev() {
		return per_gorev;
	}
	public void setPer_gorev(String per_gorev) {
		this.per_gorev = per_gorev;
	}
	
}
