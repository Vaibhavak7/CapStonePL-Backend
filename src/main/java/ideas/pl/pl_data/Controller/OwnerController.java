package ideas.pl.pl_data.Controller;

import ideas.pl.pl_data.Entity.Owner;
import ideas.pl.pl_data.Service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public class OwnerController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/{ownerId}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable int ownerId) {
        Optional<Owner> owner = ownerService.findOwnerById(ownerId);
        return owner.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }
}
