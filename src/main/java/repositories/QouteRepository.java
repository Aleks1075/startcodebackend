package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QouteRepository
{
        private List<String> quotes;
        private List<String> authors;

        public QouteRepository() {
            quotes = new ArrayList<>();
            authors = new ArrayList<>();

            quotes.add("The only way to do great work is to love what you do.");
            authors.add("Steve Jobs");
            quotes.add("Life is 10% what happens to you and 90% how you react to it.");
            authors.add("Charles R. Swindoll");
        }

        public String getRandomQuote() {
            Random random = new Random();
            int index = random.nextInt(quotes.size());
            return quotes.get(index);
        }

        public String getAuthorForQuote(String quote) {
            int index = quotes.indexOf(quote);
            return authors.get(index);
        }
}
