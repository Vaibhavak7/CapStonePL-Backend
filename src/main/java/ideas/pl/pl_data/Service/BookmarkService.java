package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.DTO.BookmarkDTO;
import ideas.pl.pl_data.Repository.BookmarkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookmarkService {

    @Autowired
    private BookmarkRepository bookmarkRepository;

    public List<BookmarkDTO> findByUser(int id) {
        return bookmarkRepository.findByUserId(id);
//        return bookmarkRepository.findById(id);

    }

}
