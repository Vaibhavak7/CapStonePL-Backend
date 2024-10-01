package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.FeedbackDTO;
import ideas.pl.pl_data.Entity.Feedback;
import ideas.pl.pl_data.Projection.AppUserProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface FeedbackRepository extends CrudRepository<Feedback, Integer> {
    List<FeedbackDTO> findBy();

    Optional<FeedbackDTO> findByFeedbackId(int feedbackId);

    List<FeedbackDTO> findByProperty_PropertyId(int propertyId);

    List<FeedbackDTO> findByProperty_PropertyIdAndUser_UserId(int propertyId, int userId);

    @Query("SELECT AVG(r.rating)  from Feedback r where  r.property.propertyId = :propertyId")
    Double findAverageRatingByPropertyId(int propertyId);

}

