package DAT250.Project.Rest;

import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.Vote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin(origins = "http://localhost:5173")
public class VoteController {

    private final DomainManager domainManager;

    @Autowired
    public VoteController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<Map.Entry<Integer, Vote>> createVote(@RequestBody Vote vote) {
        Map.Entry<Integer, Vote> createdVote = domainManager.addVote(vote);
        return ResponseEntity.ok(createdVote);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Vote>> getAllVotes() {
        return ResponseEntity.ok(domainManager.getAllVotes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, Vote>> getVote(@PathVariable Integer id) {
        Map.Entry<Integer, Vote> vote = domainManager.getVote(id);
        return vote != null ? ResponseEntity.ok(vote) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, Vote>> updateVote(@PathVariable Integer id, @RequestBody Vote vote) {
        Map.Entry<Integer, Vote> updatedVote = domainManager.updateVote(id, vote);
        return ResponseEntity.ok(updatedVote);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, Vote>> deleteVote(@PathVariable Integer id) {
        Map.Entry<Integer, Vote> deletedVote = domainManager.deleteVote(id);
        return deletedVote != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
