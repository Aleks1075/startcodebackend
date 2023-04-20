package facades;
//comment for actions
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.Qoute1DTO;
import dtos.Qoute2DTO;
import repositories.QouteRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class QouteFacade {

    private static QouteFacade instance;
    private static EntityManagerFactory emf;

    private QouteFacade() {
    }

    public static QouteFacade getQouteFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new QouteFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public String fetchData(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();
            }
        } catch (Exception e) {
            System.out.println("Error in fetchData");
            e.printStackTrace();
        }
        return null;
    }

    public Qoute1DTO createQoute1DTO() {
        QouteRepository quoteRepository = new QouteRepository();
        String content = quoteRepository.getRandomQuote();
        String author = quoteRepository.getAuthorForQuote(content);
        return new Qoute1DTO(content, author);
    }

    public Qoute2DTO createQoute2DTO(String input) {
        return GSON.fromJson(input, Qoute2DTO.class);
    }
}