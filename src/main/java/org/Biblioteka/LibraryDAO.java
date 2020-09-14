package org.Biblioteka;

import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryDAO {
    private static LibraryDAO instance;
    private Connection connection;
    private PreparedStatement getWorkerByUsername,getIdOfReader,getAllReaders,addReader,updateReader,
            deleteReader,getIdOfWorker,getAllWorkers,addWorker,updateWorker,deleteWorker,
            addBook,updateBook,deleteBook,getIdOfBook,getAllBooks,addBookIssue,addBookRental,getInfoAboutIssuedBooks,
            getIdOfBookIssue,getIdOfBookRental,updateBookSamples,getBookSamples,updateBookRental,getBookIdFromBookIssue;
    public static LibraryDAO getInstance()
    {
        if(instance==null) instance=new LibraryDAO();
        return instance;
    }
    public static void removeInstance() {
        if (instance == null) return;
        instance.close();
        instance = null;
    }
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private LibraryDAO() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:libraryrpr - Copy.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try{
            getWorkerByUsername=connection.prepareStatement("SELECT * FROM worker WHERE username=? AND password=?");
        }catch (SQLException e)
        {
            regenerisiBazu();
            try{
                getWorkerByUsername=connection.prepareStatement("SELECT * FROM worker WHERE username=? AND password=?");
            }catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        }
        try {
            getIdOfReader=connection.prepareStatement("SELECT MAX(id)+1 FROM reader");
            getAllReaders=connection.prepareStatement("SELECT * FROM reader");
            addReader=connection.prepareStatement("INSERT INTO reader VALUES(?,?,?,?,?,?,?,?)");
            updateReader=connection.prepareStatement("UPDATE reader SET name=?, sur_name=?, jmbg=?, date_of_birth=?, no_of_id=?, adress=?, phone_number=? WHERE id=?");
            deleteReader=connection.prepareStatement("DELETE FROM reader WHERE id=?");
            getIdOfWorker=connection.prepareStatement("SELECT MAX(id)+1 FROM worker");
            getAllWorkers=connection.prepareStatement("SELECT * FROM worker");
            addWorker=connection.prepareStatement("INSERT INTO worker values (?,?,?,?,?,?,?,?,?,?)");
            updateWorker=connection.prepareStatement("UPDATE worker SET name=?, sur_name=?, jmbg=?, date_of_birth=?, no_of_id=?, adress=?, phone_number=?, username=?, password=? WHERE id=?");
            deleteWorker=connection.prepareStatement("DELETE FROM worker where id=?");
            addBook=connection.prepareStatement("INSERT INTO book VALUES(?,?,?,?,?,?,?,?,?)");
            updateBook=connection.prepareStatement("UPDATE book SET book_name=?, book_author=?, isbn=?, year_of_issue=?, number_of_pages=?, date_of_purchase=?, price=?, number_of_samples=? WHERE id=?");
            deleteBook=connection.prepareStatement("DELETE FROM book WHERE id=?");
            getIdOfBook=connection.prepareStatement("SELECT MAX(id)+1 FROM book");
            getAllBooks=connection.prepareStatement("SELECT * FROM book");
            getInfoAboutIssuedBooks=connection.prepareStatement("SELECT bi.id,b.book_name,b.book_author,b.isbn,(w.name || ' ' || w.sur_name) as 'izdao',bi.date_of_issuing,(SELECT (w1.name || ' ' || w1.sur_name) FROM worker w1 WHERE w1.id=br.worker_id) as 'primio',br.return_date FROM book_issuing bi, book_rental br,book b,worker w,reader r WHERE bi.id=br.book_issuing_id and  bi.book_id=b.id and bi.worker_id=w.id and bi.reader_id=r.id and r.id=?");
            addBookIssue=connection.prepareStatement("INSERT INTO book_issuing VALUES(?,?,?,?,?)");
            addBookRental=connection.prepareStatement("INSERT INTO book_rental VALUES(?,?,?,?)");
            getIdOfBookIssue=connection.prepareStatement("SELECT MAX(id)+1 FROM book_issuing");
            getIdOfBookRental=connection.prepareStatement("SELECT MAX(id)+1 FROM book_rental");
            updateBookSamples=connection.prepareStatement("UPDATE book SET number_of_samples=? WHERE id=?");
            getBookSamples=connection.prepareStatement("SELECT number_of_samples FROM book WHERE id=?");
            updateBookRental=connection.prepareStatement("UPDATE book_rental SET worker_id=?, return_date=? WHERE book_issuing_id=?");
            getBookIdFromBookIssue=connection.prepareStatement("SELECT book_id FROM book_issuing WHERE id=?");
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    private Worker getWorkerFromResultSet(ResultSet resultSet) throws SQLException {
        Worker worker=new Worker(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8),
                resultSet.getString(9),
                resultSet.getString(10)
        );
        return worker;
    }
    private Reader getReaderFromResultSet(ResultSet resultSet) throws SQLException {
        Reader reader=new Reader(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8)
        );
        return reader;
    }
    private Book getBookFromResultSet(ResultSet resultSet) throws SQLException
    {
        Book book=new Book(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getInt(5),
                resultSet.getInt(6),
                resultSet.getString(7),
                resultSet.getDouble(8),
                resultSet.getInt(9)
        );
        return book;
    }
    private BookIssue getInfoAboutIssuedBookFromResultSet(ResultSet resultSet) throws SQLException {
        BookIssue bookIssue=new BookIssue(
                resultSet.getInt(1),
                resultSet.getString(2),
                resultSet.getString(3),
                resultSet.getString(4),
                resultSet.getString(5),
                resultSet.getString(6),
                resultSet.getString(7),
                resultSet.getString(8)
        );
        return bookIssue;
    }
    public Worker LoggedInWorker(String username,String password)
    {
        try{
            getWorkerByUsername.setString(1,username);
            getWorkerByUsername.setString(2,password);
            ResultSet resultSet=getWorkerByUsername.executeQuery();
            if(!resultSet.next()) return null;
            return getWorkerFromResultSet(resultSet);
        }catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<Reader> readers()
    {
        ArrayList<Reader> result=new ArrayList<>();
        try {
            ResultSet resultSet=getAllReaders.executeQuery();
            while (resultSet.next())
            {
                Reader reader=getReaderFromResultSet(resultSet);
                result.add(reader);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void addReader(Reader reader)
    {
        try {
            ResultSet resultSet=getIdOfReader.executeQuery();

            if(resultSet.next())
            {
                reader.setId(resultSet.getInt(1));
            }
            else
            {
                reader.setId(1);
            }
            addReader.setInt(1,reader.getId());
            addReader.setString(2,reader.getName());
            addReader.setString(3,reader.getSurName());
            addReader.setString(4,reader.getJmbg());
            addReader.setString(5,reader.getDateOfBirth());
            addReader.setString(6,reader.getNoOfId());
            addReader.setString(7,reader.getAdress());
            addReader.setString(8,reader.getPhoneNumber());
            addReader.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void updateReader(Reader reader)
    {
        try {
            updateReader.setString(1,reader.getName());
            updateReader.setString(2,reader.getSurName());
            updateReader.setString(3,reader.getJmbg());
            updateReader.setString(4,reader.getDateOfBirth());
            updateReader.setString(5,reader.getNoOfId());
            updateReader.setString(6,reader.getAdress());
            updateReader.setString(7,reader.getPhoneNumber());
            updateReader.setInt(8,reader.getId());
            updateReader.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteReader(Reader reader)
    {
        try {
            deleteReader.setInt(1,reader.getId());
            deleteReader.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Worker> workers()
    {
        ArrayList<Worker> result=new ArrayList<>();
        try {
            ResultSet resultSet=getAllWorkers.executeQuery();
            while (resultSet.next())
            {
                Worker worker=getWorkerFromResultSet(resultSet);
                result.add(worker);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void addWorker(Worker worker)
    {
        try {
            ResultSet resultSet=getIdOfWorker.executeQuery();
            if(resultSet.next())
            {
                worker.setId(resultSet.getInt(1));
            }
            else {
                worker.setId(1);
            }
            addWorker.setInt(1,worker.getId());
            addWorker.setString(2,worker.getName());
            addWorker.setString(3,worker.getSurName());
            addWorker.setString(4,worker.getJmbg());
            addWorker.setString(5,worker.getDateOfBirth());
            addWorker.setString(6,worker.getNoOfId());
            addWorker.setString(7,worker.getAdress());
            addWorker.setString(8,worker.getPhoneNumber());
            addWorker.setString(9,worker.getUsername());
            addWorker.setString(10,worker.getPassword());
            addWorker.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void updateWorker(Worker worker)
    {
        try {
            updateWorker.setString(1,worker.getName());
            updateWorker.setString(2,worker.getSurName());
            updateWorker.setString(3,worker.getJmbg());
            updateWorker.setString(4,worker.getDateOfBirth());
            updateWorker.setString(5,worker.getNoOfId());
            updateWorker.setString(6,worker.getAdress());
            updateWorker.setString(7,worker.getPhoneNumber());
            updateWorker.setString(8,worker.getUsername());
            updateWorker.setString(9,worker.getPassword());
            updateWorker.setInt(10,worker.getId());
            updateWorker.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void deleteWorker(Worker worker)
    {
        try {
            deleteWorker.setInt(1,worker.getId());
            deleteWorker.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> books()
    {
        ArrayList<Book> result=new ArrayList<>();
        try
        {
            ResultSet resultSet=getAllBooks.executeQuery();
            while (resultSet.next())
            {
                Book book=getBookFromResultSet(resultSet);
                result.add(book);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
    public void addBook(Book book)
    {
        try {
            ResultSet resultSet=getIdOfBook.executeQuery();
            if(resultSet.next())
            {
                book.setId(resultSet.getInt(1));
            }
            else
            {
                book.setId(1);
            }
            addBook.setInt(1,book.getId());
            addBook.setString(2,book.getBookName());
            addBook.setString(3,book.getBookAuthor());
            addBook.setString(4,book.getIsbn());
            addBook.setInt(5,book.getYearOfIssue());
            addBook.setInt(6,book.getNumberOfPages());
            addBook.setString(7,book.getDateOfPurchase());
            addBook.setDouble(8,book.getPrice());
            addBook.setInt(9,book.getNumberOfSamples());
            addBook.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void updateBook(Book book) {
        try {
            updateBook.setString(1,book.getBookName());
            updateBook.setString(2,book.getBookAuthor());
            updateBook.setString(3,book.getIsbn());
            updateBook.setInt(4,book.getYearOfIssue());
            updateBook.setInt(5,book.getNumberOfPages());
            updateBook.setString(6,book.getDateOfPurchase());
            updateBook.setDouble(7,book.getPrice());
            updateBook.setInt(8,book.getNumberOfSamples());
            updateBook.setInt(9,book.getId());
            updateBook.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteBook(Book book) {
        try {
            deleteBook.setInt(1,book.getId());
            deleteBook.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<BookIssue> bookIssues(Reader reader)
    {
        ArrayList<BookIssue> result=new ArrayList<>();
        try {
           getInfoAboutIssuedBooks.setInt(1,reader.getId());
           ResultSet resultSet=getInfoAboutIssuedBooks.executeQuery();
           while(resultSet.next())
           {
              BookIssue bookIssue=getInfoAboutIssuedBookFromResultSet(resultSet);
              result.add(bookIssue);
           }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public void addBookIssue(Reader reader,Worker worker,Book book,String dateOfIssuing)
    {
        try
        {
            ResultSet resultSet=getIdOfBookIssue.executeQuery();
            int bookIssueId;
            if(resultSet.next())
            {
                bookIssueId=resultSet.getInt(1);
            }
            else
            {
                bookIssueId=1;
            }
            addBookIssue.setInt(1,bookIssueId);
            addBookIssue.setInt(2,reader.getId());
            addBookIssue.setInt(3,worker.getId());
            addBookIssue.setInt(4,book.getId());
            addBookIssue.setString(5,dateOfIssuing);
            addBookIssue.executeUpdate();
            ResultSet resultSet1=getIdOfBookRental.executeQuery();
            int bookRentalId;
            if(resultSet1.next())
            {
                bookRentalId=resultSet1.getInt(1);
            }
            else
            {
                bookRentalId=1;
            }
            addBookRental.setInt(1,bookRentalId);
            addBookRental.setInt(2,bookIssueId);
            addBookRental.setNull(3,Types.INTEGER);
            addBookRental.setString(4, null);
            addBookRental.executeUpdate();
            getBookSamples.setInt(1,book.getId());
            ResultSet resultSet2=getBookSamples.executeQuery();
            int numberOfBookSamples=resultSet2.getInt(1);
            numberOfBookSamples-=1;
            updateBookSamples.setInt(1,numberOfBookSamples);
            updateBookSamples.setInt(2,book.getId());
            updateBookSamples.executeUpdate();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    public void updateBookRental(BookIssue bookIssue,Worker worker,String returnDate)
    {
        try
        {
            updateBookRental.setInt(1,worker.getId());
            updateBookRental.setString(2,returnDate);
            updateBookRental.setInt(3,bookIssue.getId());
            updateBookRental.executeUpdate();
            getBookIdFromBookIssue.setInt(1,bookIssue.getId());
            ResultSet resultSet=getBookIdFromBookIssue.executeQuery();
            int bookId=resultSet.getInt(1);
            getBookSamples.setInt(1,bookId);
            ResultSet resultSet1=getBookSamples.executeQuery();
            int numberOfBookSamples=resultSet1.getInt(1);
            numberOfBookSamples+=1;
            updateBookSamples.setInt(1,numberOfBookSamples);
            updateBookSamples.setInt(2,bookId);
            updateBookSamples.executeUpdate();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    private void regenerisiBazu() {
        Scanner ulaz = null;
        try {
            ulaz = new Scanner(new FileInputStream("libraryrpr - Copy.sql"));
            String sqlUpit = "";
            while (ulaz.hasNext()) {
                sqlUpit += ulaz.nextLine();
                if (sqlUpit.charAt(sqlUpit.length() - 1) == ';') {
                    try {
                        Statement stmt = connection.createStatement();
                        stmt.execute(sqlUpit);
                        sqlUpit = "";
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public Connection getConnection()
    {
        return connection;
    }

}
