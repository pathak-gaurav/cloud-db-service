package com.gauravpathak.controller;

import com.gauravpathak.entity.Quote;
import com.gauravpathak.entity.Quotes;
import com.gauravpathak.repository.QuoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/db")
public class QuoteController {

    private QuoteRepository quoteRepository;

    public QuoteController(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    @GetMapping("/{username}")
    public List<String> getQuotes(@PathVariable("username") String username) {

        return getQuoteByUsername(username);
    }

    private List<String> getQuoteByUsername(@PathVariable("username") String username) {
        return quoteRepository.findByUsername(username)
                .stream().map(Quote::getQuote).collect(Collectors.toList());
    }


    @PostMapping("/add")
    public List<String> add(@RequestBody Quotes quotes){

        log.info(quotes.getUsername()+"********"+quotes.getQuotes());
        quotes
                .getQuotes()
                .stream()
                .map(quote->new Quote(quotes.getUsername(),quote))
                .forEach(quote->quoteRepository.save(quote));

        return getQuoteByUsername(quotes.getUsername());
    }

    @DeleteMapping("/delete/{username}")
    public List<String> deleteByUsername(@PathVariable("username") String username){
        List<Quote> quotes = quoteRepository.findByUsername(username);
        quoteRepository.deleteAll(quotes);
        return getQuoteByUsername(username);
    }
}
