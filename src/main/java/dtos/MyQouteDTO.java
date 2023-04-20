package dtos;

import java.util.ArrayList;

public class MyQouteDTO {
    private ArrayList<String> quotes;

    public MyQouteDTO(Qoute1DTO q1d, Qoute2DTO q2d) {
        quotes = new ArrayList<>();
        addQuote(q1d.getContent() + " - " + q1d.getAuthor());
        addQuote(q2d.getContent() + " - " + q2d.getAuthor());
    }

    public ArrayList<String> getQuotes() {
        return quotes;
    }

    public void addQuote(String quote) {
        this.quotes.add(quote);
    }

    @Override
    public String toString() {
        return "MyQouteDTO{" +
                "quotes=" + quotes +
                '}';
    }
}