package ideas.pl.pl_data.Service;

import ideas.pl.pl_data.Entity.Owner;
import ideas.pl.pl_data.Repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    public Optional<Owner> findOwnerById(int ownerId) {
        return ownerRepository.findById(ownerId);
    }
}
