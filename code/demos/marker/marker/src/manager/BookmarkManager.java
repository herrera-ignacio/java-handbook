package manager;

import entities.Movie;
import entities.Book;
import entities.WebLink;

public class BookmarkManager {
    private static BookmarkManager instance = new BookmarkManager();

    private BookmarkManager() {
    }

    public static BookmarkManager getInstance() {
        return instance;
    }

    public WebLink createWebLink(long id, String title, String url, String host) {
        WebLink weblink = new WebLink();

        weblink.setId(id);
        weblink.setTitle(title);
        weblink.setUrl(url);
        weblink.setHost(host);

        return weblink;
    }

    public Book createBook(long id, String title, String profileUrl, int publicationYear, String publisher, String[] authors, String genre, double amazonRating) {
        Book book = new Book();

        book.setId(id);
        book.setTitle(title);
        book.setProfileUrl(profileUrl);
        book.setAmazonRating(amazonRating);
        book.setAuthors(authors);
        book.setGenre(genre);
        book.setPublisher(publisher);

        return book;
    }

    public Movie createMovie(long id, String title, String profileUrl, int releaseYear, String[] cast, String[] directors, String genre, double imbdRating) {
        Movie movie = new Movie();

        movie.setId(id);
        movie.setTitle(title);
        movie.setProfileUrl(profileUrl);
        movie.setCast(cast);
        movie.setDirectors(directors);
        movie.setImbdRating(imbdRating);
        movie.setReleaseYear(releaseYear);

        return movie;
    }
}