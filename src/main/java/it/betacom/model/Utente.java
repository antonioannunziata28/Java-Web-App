package it.betacom.model;

import java.sql.Date;

public class Utente {
	
	private String nome;
    private String cognome;
    private String luogoNascita;
    private String provincia;
    private Date dataNascita;
    private String codiceFiscale;
    private String password;
    private String comeTrovato;
    
	public Utente(String nome, String cognome, String luogoNascita, String provincia, Date dataNascita,
			String codiceFiscale, String password, String comeTrovato) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.luogoNascita = luogoNascita;
		this.provincia = provincia;
		this.dataNascita = dataNascita;
		this.codiceFiscale = codiceFiscale;
		this.password = password;
		this.comeTrovato = comeTrovato;
	}

	public String getNome() {
		return nome;
	}

	public String getCognome() {
		return cognome;
	}

	public String getLuogoNascita() {
		return luogoNascita;
	}

	public String getProvincia() {
		return provincia;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public String getPassword() {
		return password;
	}

	public String getComeTrovato() {
		return comeTrovato;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public void setLuogoNascita(String luogoNascita) {
		this.luogoNascita = luogoNascita;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setComeTrovato(String comeTrovato) {
		this.comeTrovato = comeTrovato;
	}

	@Override
	public String toString() {
		return "Utente{" +
                "nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", luogoNascita='" + luogoNascita + '\'' +
                ", provincia='" + provincia + '\'' +
                ", dataNascita=" + dataNascita +
                ", codiceFiscale='" + codiceFiscale + '\'' +
                ", password='" + password + '\'' +
                ", comeTrovato='" + comeTrovato + '\'' +
                '}';
	}
    
}
