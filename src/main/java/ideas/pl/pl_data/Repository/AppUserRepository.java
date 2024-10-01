package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.Entity.AppUser;
import ideas.pl.pl_data.Projection.AppUserProjection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends CrudRepository<AppUser,Integer> {
    List<AppUserProjection> findBy();

    Optional<AppUserProjection> findByUserId(int userId);

    Optional<AppUser> findByEmail(String email);
}
