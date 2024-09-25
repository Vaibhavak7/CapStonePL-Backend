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

    // Find feedback by ID
//    @Query("SELECT u FROM Feedback u WHERE u.feedbackId = :id")
//    Optional<FeedbackDTO> findFeedbackById(@Param("id") int id);

    Optional<FeedbackDTO> findByFeedbackId(int feedbackId);

//    @Query("SELECT u FROM Feedback u WHERE u.property.propertyId = :id")
//    List<FeedbackDTO> findFeedbackByPropertyId(@Param("id") int id);

    List<FeedbackDTO> findByProperty_PropertyId(int propertyId);

    // New query to check feedback by property ID and user ID
//    @Query("SELECT u FROM Feedback u WHERE u.property.propertyId = :propertyId AND u.user.userId = :userId")
//    List<FeedbackDTO> findFeedbackByPropertyIdAndUserId(@Param("propertyId") int propertyId, @Param("userId") int userId);

    List<FeedbackDTO> findByProperty_PropertyIdAndUser_UserId(int propertyId, int userId);

    @Query("SELECT AVG(r.rating)  from Feedback r where  r.property.propertyId = :propertyId")
    Double findAverageRatingByPropertyId(int propertyId);

}

