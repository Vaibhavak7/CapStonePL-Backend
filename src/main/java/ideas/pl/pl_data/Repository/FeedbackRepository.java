package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Projection.AppUserProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends CrudRepository<Feedback,Integer> {
    List<FeedbackDTO> findBy();

    // Find feedback by ID
    @Query("SELECT u FROM Feedback u WHERE u.feedbackId = :id")
    Optional<FeedbackDTO> findFeedbackById(@Param("id") int id);

    @Query("SELECT u FROM Feedback u WHERE u.property.propertyId = :id")
    List<FeedbackDTO> findFeedbackByPropertyId(@Param("id") int id);
}
