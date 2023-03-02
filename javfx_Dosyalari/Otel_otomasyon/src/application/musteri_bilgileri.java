package application;

public class musteri_bilgileri {

	public int m_id;
	public String m_ad;
	public String m_soyad;
	public String m_tc;
	public String m_tel;
	public musteri_bilgileri(int t_m_id,String t_m_ad,String t_m_soyad,String t_m_tc,String t_m_tel) {
		this.m_id=t_m_id;
		this.m_ad=t_m_ad;
		this.m_soyad=t_m_soyad;
		this.m_tc=t_m_tc;
		this.m_tel=t_m_tel;
	}
	public int getM_id() {
		return m_id;
	}
	public void setM_id(int m_id) {
		this.m_id = m_id;
	}
	public String getM_ad() {
		return m_ad;
	}
	public void setM_ad(String m_ad) {
		this.m_ad = m_ad;
	}
	public String getM_soyad() {
		return m_soyad;
	}
	public void setM_soyad(String m_soyad) {
		this.m_soyad = m_soyad;
	}
	public String getM_tc() {
		return m_tc;
	}
	public void setM_tc(String m_tc) {
		this.m_tc = m_tc;
	}
	public String getM_tel() {
		return m_tel;
	}
	public void setM_tel(String m_tel) {
		this.m_tel = m_tel;
	}
	
}
