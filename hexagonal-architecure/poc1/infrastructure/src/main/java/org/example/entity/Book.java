package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.data.BookDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private Double price;

    public static Book toEntity(BookDto bookDto) {
        Book book = Book
                .builder()
                .id(bookDto.getId())
                .title(bookDto.getTitle())
                .description(bookDto.getDescription())
                .price(bookDto.getPrice())
                .build();
        return book;
    }

    public static BookDto toDto(Book book) {
        return BookDto
                .builder()
                .id(book.getId())
                .title(book.getTitle())
                .description(book.getDescription())
                .price(book.getPrice())
                .build();
    }

    public static List<BookDto> toDtoList(List<Book> bookList) {
        return bookList
                .stream()
                .map(Book::toDto)
                .collect(Collectors.toList());
    }

    public static List<Book> toEntityList(List<BookDto> bookDtoList) {
        return bookDtoList
                .stream()
                .map(Book::toEntity)
                .collect(Collectors.toList());
    }
}