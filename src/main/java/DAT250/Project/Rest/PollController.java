package DAT250.Project.Rest;

import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.Poll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/polls")
@CrossOrigin(origins = "http://localhost:5173")
public class PollController {

    private final DomainManager domainManager;

    @Autowired
    public PollController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<Map.Entry<Integer, Poll>> createPoll(@RequestBody Poll poll) {
        Map.Entry<Integer, Poll> createdPoll = domainManager.addPoll(poll);
        return ResponseEntity.ok(createdPoll);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, Poll>> getAllPolls() {
        return ResponseEntity.ok(domainManager.getAllPolls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, Poll>> getPoll(@PathVariable Integer id) {
        Map.Entry<Integer, Poll> poll = domainManager.getPoll(id);
        return ResponseEntity.ok(poll);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, Poll>> updatePoll(@PathVariable Integer id, @RequestBody Poll poll) {
        Map.Entry<Integer, Poll> updatedPoll = domainManager.updatePoll(id, poll);
        return ResponseEntity.ok(updatedPoll);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, Poll>> deletePoll(@PathVariable Integer id) {
        Map.Entry<Integer, Poll> deletedPoll = domainManager.deletePoll(id);
        return ResponseEntity.ok(deletedPoll);
    }
}
