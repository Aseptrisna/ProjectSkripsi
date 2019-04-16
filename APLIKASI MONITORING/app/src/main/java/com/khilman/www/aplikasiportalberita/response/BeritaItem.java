package com.khilman.www.aplikasiportalberita.response;

import com.google.gson.annotations.SerializedName;

public class BeritaItem{

	@SerializedName("username")
	private String penulis;

	@SerializedName("foto")
	private String foto;

	@SerializedName("id")
	private String id;

	@SerializedName("no_plat")
	private String judulBerita;

	@SerializedName("nama_kendaraan")
	private String tanggalPosting;

	@SerializedName("nama_pemilik")
	private String isiBerita;

	public void setPenulis(String penulis){
		this.penulis = penulis;
	}

	public String getPenulis(){
		return penulis;
	}

	public void setFoto(String foto){
		this.foto = foto;
	}

	public String getFoto(){
		return foto;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}

	public void setJudulBerita(String judulBerita){
		this.judulBerita = judulBerita;
	}

	public String getJudulBerita(){
		return judulBerita;
	}

	public void setTanggalPosting(String tanggalPosting){
		this.tanggalPosting = tanggalPosting;
	}

	public String getTanggalPosting(){
		return tanggalPosting;
	}

	public void setIsiBerita(String isiBerita){
		this.isiBerita = isiBerita;
	}

	public String getIsiBerita(){
		return isiBerita;
	}

	@Override
 	public String toString(){
		return 
			"BeritaItem{" + 
			" = '" +penulis  + '\'' +
			",foto = '" + foto + '\'' + 
			",id = '" + id + '\'' + 
			",nama_kendaraan= '" + judulBerita + '\'' +
			",no_plat= '" + tanggalPosting + '\'' +
			",nama_kendaraan= '" + isiBerita + '\'' +
			"}";
		}
}