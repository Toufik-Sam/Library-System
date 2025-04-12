package BooksData;

import java.util.List;

public class FullBookDTO {
	private BookDTO _bookDTO;
	private List<BookPageDTO>_bookPages;
	public FullBookDTO(BookDTO bookDTO,List<BookPageDTO> bookPages) {
		this._bookDTO=bookDTO;
		this._bookPages=bookPages;
	}
	public void SetBook(BookDTO bookDTO) {
		this._bookDTO=bookDTO;
	}
	public BookDTO GetBook() {
		return this._bookDTO;
	}
	public void SetBookPages(List<BookPageDTO> bookPages) {
		this._bookPages=bookPages;
	}
	public List<BookPageDTO> GetBookPages(){
		return this._bookPages;
	}
}
