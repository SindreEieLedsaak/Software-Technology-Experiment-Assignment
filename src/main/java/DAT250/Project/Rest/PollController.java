package DAT250.Project.Rest;
import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final DomainManager domainManager;

    @Autowired
    public PollController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<HashMap<UUID,Poll>> createPoll(@RequestBody Poll poll) {
        HashMap<UUID,Poll> createdPoll = domainManager.addPoll(poll);
        return ResponseEntity.ok(createdPoll);
    }
    @GetMapping()
    public ResponseEntity<Collection<Poll>> getAllPolls() {
        return ResponseEntity.ok(domainManager.getAllPolls());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Poll> getPoll(@PathVariable UUID id) {
        Poll poll = domainManager.getPoll(id);
        return poll != null ? ResponseEntity.ok(poll) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poll> updatePoll(@PathVariable UUID id, @RequestBody Poll poll) {
        Poll updatedPoll = domainManager.updatePoll(id, poll);
        return ResponseEntity.ok(updatedPoll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePoll(@PathVariable UUID id) {
        Poll deletedPoll = domainManager.deletePoll(id);
        return deletedPoll != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
