package org.bs.rental.util.naver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bs.rental.dto.book.BookDTO;
import org.bs.rental.dto.naver.NaverApiResponseDTO;
import org.bs.rental.service.Book.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NaverBookAPIUtil {

    @Value("${naver.api.url}")
    private String naverApiUrl;

    @Value("${naver.api.key}")
    private String naverApiKey;

    @Value("${naver.api.secret}")
    private String naverApiSecret;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private BookService bookService;

        public List<BookDTO> searchBooks(String query, int start, int display) {
        String apiUrl = naverApiUrl + "?query=" + query + "&start=" + start + "&display=" + display;
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverApiKey);
        headers.set("X-Naver-Client-Secret", naverApiSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<NaverApiResponseDTO> response = restTemplate.exchange(apiUrl, HttpMethod.GET, entity, NaverApiResponseDTO.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getItems();
        } else {
            // Handle error
            return Collections.emptyList();
        }
    }

    public List<BookDTO> searchAllBooks(String query, int pageSize) {
        List<BookDTO> allBooks = new ArrayList<>();
        int start = 1;

        while (true) {
            List<BookDTO> books = searchBooks(query, start, pageSize);

            if (books.isEmpty()) {
                break;
            }

            allBooks.addAll(books);
            start += pageSize;
        }

        return allBooks;
    }

    public void saveAllBooksToDatabase(String query, int pageSize) {
        List<BookDTO> allBooks = searchAllBooks(query, pageSize);
        bookService.booksCreate(allBooks);
    }
}
    
