package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapRunner implements CommandLineRunner {
    private final BookRepository bookRepository;
    private  final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapRunner(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author("aa", "bb");
        Book book1 = new Book("book1", "111");
        Book book2 = new Book("book2", "222");

        author1.getBooks().add(book1);
        author1.getBooks().add(book2);
        book1.getAuthors().add(author1);
        book2.getAuthors().add(author1);



        Publisher publisher1 = new Publisher("publisher1", "address1");
        publisherRepository.save(publisher1);

        publisher1.getBooks().add(book1);
        book1.setPublisher(publisher1);
        book2.setPublisher(publisher1);

        authorRepository.save(author1);
        bookRepository.save(book1);
        bookRepository.save(book2);
        publisherRepository.save(publisher1);

        System.out.println("started in bootstrap");
        System.out.println("number of books: " + bookRepository.count());
        System.out.println("number of publishers: " + publisherRepository.count());
        System.out.println("number of books by publisher " + publisher1.getBooks().size());

    }
}
