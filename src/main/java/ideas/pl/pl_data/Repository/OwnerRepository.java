package ideas.pl.pl_data.Repository;

import ideas.pl.pl_data.DTO.PropertyDTO;
import ideas.pl.pl_data.Entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner,Integer> {

}
