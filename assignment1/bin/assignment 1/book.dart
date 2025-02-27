//book.dart - contains the Book class definition

class Book {
  final String title;
  final String author;
  final int year;
  final String genre;

  Book({
    required this.title,
    this.author = 'Unknown',
    this.year = 0,
    this.genre = 'Unknown',
  });

  Map<String, dynamic> toMap() {
    return {
      'title': title,
      'author': author,
      'year': year,
      'genre': genre,
    };
  }

  factory Book.fromMap(Map<String, dynamic> map) {
    return Book(
      title: map['title'],
      author: map['author'],
      year: map['year'],
      genre: map['genre'],
    );
  }

  @override
  String toString() {
    return 'Title: $title, Author: $author, Year: $year, Genre: $genre';
  }
}