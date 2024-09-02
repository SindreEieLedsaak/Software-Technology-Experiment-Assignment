package DAT250.Project.Rest;

import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/votes")
public class VoteController {

    private final DomainManager domainManager;

    @Autowired
    public VoteController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody Vote vote) {
        Vote createdVote = domainManager.addVote(vote);
        return ResponseEntity.ok(createdVote);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vote> getVote(@PathVariable UUID id) {
        Vote vote = domainManager.getVote(id);
        return vote != null ? ResponseEntity.ok(vote) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vote> updateVote(@PathVariable UUID id, @RequestBody Vote vote) {
        Vote updatedVote = domainManager.updateVote(id, vote);
        return ResponseEntity.ok(updatedVote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable UUID id) {
        Vote deletedVote = domainManager.deleteVote(id);
        return deletedVote != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

