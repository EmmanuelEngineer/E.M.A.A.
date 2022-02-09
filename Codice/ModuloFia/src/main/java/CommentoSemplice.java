package src.main.java;

public class CommentoSemplice {
    public String getContenuto() {
        return contenuto;
    }

    public void setCommento(String contenuto) {
        this.contenuto = contenuto;
    }

    @Override
    public String toString() {
        return "CommentoSemplice{" +
                "commento='" + contenuto + '\'' +
                ", utente='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String contenuto;
    private String username;

}
