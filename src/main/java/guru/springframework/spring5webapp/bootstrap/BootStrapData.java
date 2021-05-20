package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.Author;
import guru.springframework.spring5webapp.Book;
import guru.springframework.spring5webapp.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    public final AuthorRepository authorRepository;
    public final BookRepository bookRepository;
    public final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Publisher publisher = new Publisher("UnB", "asa norte");
        publisherRepository.save(publisher);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "23525");

        ddd.setPublisher(publisher);
        publisher.getBooks().add(ddd);

        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development withou EJB", "124124214");

        noEJB.setPublisher(publisher);
        publisher.getBooks().add(noEJB);

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        System.out.println("Started bootstrap");
        System.out.println("Number of books: " + bookRepository.count());



        System.out.println("Publisher: " + publisher.toString());
        System.out.println("Publisher count: " + publisherRepository.count());
        System.out.println("Publisher books: " + publisher.getBooks().size());

    }
}
