
import "library_ services.dart";

void main() {

  final libraryService = LibraryService();

  print('welcome to the library\n');

  libraryService.addBook(
      title: 'History of Palestine',
      author: 'Abdellatif Q. SH',
      year: 2022,
      genre: 'History'
  );

  libraryService.addBook(
      title: 'Love of the Land',
      author: 'Abdellatif Q. SH',
      year: 2025,
      genre: 'Fiction'
  );

  print('\nGetting book information:');
  var bookInfo = libraryService.getBookInfo(title: 'Love of the Land');
  print('Love of the Land $bookInfo');

  print('\nListing all books:');
  libraryService.listAllBooks();

  print('\nListing books by genre:');
  var fictionBooks = libraryService.listBooksByGenre(genre: 'Fiction');
  print('Fiction books: $fictionBooks');


}