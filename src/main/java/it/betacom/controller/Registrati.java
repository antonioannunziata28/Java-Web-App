package it.betacom.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.betacom.dbhandler.DbHandler;

/**
 * Servlet implementation class Registrati
 */
@WebServlet("/Registrati")
public class Registrati extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registrati() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String luogoNascita = request.getParameter("luogoNascita");
        String provincia = request.getParameter("provincia");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String dataNascitaStr = request.getParameter("dataNascita");
        String codiceFiscale = request.getParameter("codiceFiscale");
        String password = request.getParameter("password");
        String comeTrovato = request.getParameter("comeTrovato");

        Connection connection = null;

        try {
        	Date dataNascita = new Date(formato.parse(dataNascitaStr).getTime());
            // Verifica se il parametro dataNascita non è nullo o vuoto
        	if (dataNascitaStr != null && !dataNascitaStr.isEmpty()) {
                

                System.out.println("dataNascita: " + dataNascita);
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DbHandler.getConnection();

                // Verifica sul codice fiscale
                String verificaQuery = "SELECT COUNT(*) FROM utenti WHERE codice_fiscale = ?";

                try (PreparedStatement verificaStm = connection.prepareStatement(verificaQuery)) {
                    verificaStm.setString(1, codiceFiscale);

                    try (ResultSet rSt = verificaStm.executeQuery()) {
                        if (rSt.next() && rSt.getInt(1) > 0) {
                            out.println("Utente già registrato, codice fiscale già associato");
                        } else {
                            String insertQuery = "INSERT INTO utenti"
                                    + " (nome, cognome, luogo_nascita, provincia, data_nascita, codice_fiscale, password, come_trovato)"
                                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                            try (PreparedStatement insertStm = connection.prepareStatement(insertQuery)) {
                                insertStm.setString(1, nome);
                                insertStm.setString(2, cognome);
                                insertStm.setString(3, luogoNascita);
                                insertStm.setString(4, provincia);
                                insertStm.setDate(5, new java.sql.Date(dataNascita.getTime()));
                                insertStm.setString(6, codiceFiscale);
                                insertStm.setString(7, password);
                                insertStm.setString(8, comeTrovato);

                                int rowsAffected = insertStm.executeUpdate();

                                if (rowsAffected > 0) {
                                    out.println("Registrazione avvenuta con successo");
                                } else {
                                    out.println("Errore durante la registrazione");
                                }
                            }
                        }
                    }
                }
            } else {
                //controllo della data
                //out.println("Data di nascita fornita: " + dataNascitaStr);
                out.println("Errore: La data di nascita non è valida.");
            }
        } catch (ParseException e) {
            // Gestisci l'eccezione di ParseException separatamente
            out.println("Errore durante la conversione della data di nascita: " + e.getMessage());
            e.printStackTrace(); // Aggiungi questa linea per stampare il trace dell'eccezione
        } catch (SQLException e) {
            e.printStackTrace();
            out.println("Errore durante la connessione al database" + e.getMessage());
        } catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
                DbHandler.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
		
		doGet(request, response);
	}

}
