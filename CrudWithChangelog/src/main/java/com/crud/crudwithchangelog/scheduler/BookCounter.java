package com.crud.crudwithchangelog.scheduler;

import com.crud.crudwithchangelog.book.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BookCounter {
    private final BookRepository bookRepository;


    @Scheduled(fixedRate = 5000)
    public void counter(){
        final Long count = bookRepository.count();
        log.info("Book counter: {}", count);
    }

}
