package it.unisa.emaa.www.sito.Application.Utente.ModuloFIA;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.unisa.emaa.www.sito.Data.dao.CommentoDao;
import it.unisa.emaa.www.sito.Data.entity.Commento;
import it.unisa.emaa.www.sito.Data.entity.Utente;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Questa classe viene utilizzata come adattatore per la comunicazione tra un modulo di intelligenza artificiale e il database della piattaforma.
 * Presenta un solo metodo statico che riceve la lista di tutti i commenti presenti sulla piattaforma.
 * Invia i commenti al modulo in formato JSON e riceve una lista commenti in formato JSON, restituita poi dal metodo.
 * La comunicazione avviene tramite l'utilizzo di una socket.
 * @author Alessandro Marigliano
 */
public class AdapterFia{

    public static ArrayList<String> utentiSpammer(ArrayList<Commento> commenti) throws IOException, ClassNotFoundException {
        Gson gson = new Gson();
        String jsonCommenti = gson.toJson(commenti);
        Socket socket = new Socket("localhost",2020);
        if(!socket.isConnected()){
            throw new RuntimeException("Connessione alla socket non riuscita");
        }
        System.out.println("Connessione riuscita");
        PrintWriter objectOutputStream = new PrintWriter(socket.getOutputStream(),true);
        objectOutputStream.println(jsonCommenti);
        Scanner objectInputStream = new Scanner(socket.getInputStream());
        String jsonUtenti = objectInputStream.nextLine();
        socket.close();
        return gson.fromJson(jsonUtenti,new TypeToken<ArrayList<String>>(){}.getType());
    }
}
