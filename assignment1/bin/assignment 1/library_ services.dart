
import 'book.dart';

class LibraryService {
  final List<Book> _books = [];

  void addBook({
    required String title,
    String author = 'Unknown',
    int year = 0,
    String genre = 'Unknown',
  }) {

    Book book = Book(
      title: title,
      author: author,
      year: year,
      genre: genre,
    );

    _books.add(book);
    print('Book "${book.title}" added successfully.');
  }

  dynamic getBookInfo({required String title}) {
    //find the book with the matching title
    for (var book in _books) {
      if (book.title == title) {
        return {
          'author': book.author,
          'year': book.year,
          'genre': book.genre,
        };
      }
    }

    return "Book not found";
  }

  void listAllBooks({String? genre}) {
    if (_books.isEmpty) {
      print("Library is empty.");
      return;
    }

    bool found = false;

    for (var book in _books) {
      if (genre == null || book.genre == genre) {
        print(book.toString());
        found = true;
      }
    }

    if (!found && genre != null) {
      print("No books found for genre: $genre");
    }
  }

  dynamic listBooksByGenre({required String genre}) {
    List<Book> booksInGenre = [];

    for (var book in _books) {
      if (book.genre == genre) {
        booksInGenre.add(book);
      }
    }

    if (booksInGenre.isEmpty) {
      return "No books found for this genre";
    }

    return booksInGenre;
  }

  String removeBook({required String title}) {
    int initialLength = _books.length;

    _books.removeWhere((book) => book.title == title);

    if (_books.length < initialLength) {
      return "Book removed successfully";
    } else {
      return "Book not found";
    }
  }
}