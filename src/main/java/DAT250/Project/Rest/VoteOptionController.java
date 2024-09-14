package DAT250.Project.Rest;

import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.VoteOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vote-options")
@CrossOrigin(origins = "http://localhost:5173")
public class VoteOptionController {

    private final DomainManager domainManager;

    @Autowired
    public VoteOptionController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<Map.Entry<Integer, VoteOption>> createVoteOption(@RequestBody VoteOption voteOption) {
        Map.Entry<Integer, VoteOption> voteOptionHashMap = domainManager.addVoteOption(voteOption);
        return ResponseEntity.ok(voteOptionHashMap);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, VoteOption>> getAllVoteOptions() {
        return ResponseEntity.ok(domainManager.getAllVoteOptions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, VoteOption>> getVoteOption(@PathVariable Integer id) {
        Map.Entry<Integer, VoteOption> voteOption = domainManager.getVoteOption(id);
        return voteOption != null ? ResponseEntity.ok(voteOption) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, VoteOption>> updateVoteOption(@PathVariable Integer id,
            @RequestBody VoteOption voteOption) {
        Map.Entry<Integer, VoteOption> updatedVoteOption = domainManager.updateVoteOption(id, voteOption);
        return ResponseEntity.ok(updatedVoteOption);
    }

    @PutMapping("/{id}/upvote")
    public ResponseEntity<Map.Entry<Integer, VoteOption>> upVoteOption(@PathVariable Integer id) {
        Map.Entry<Integer, VoteOption> upVotedOption = domainManager.upVoteOption(id);
        return ResponseEntity.ok(upVotedOption);
    }

    @PutMapping("/{id}/downvote")
    public ResponseEntity<Map.Entry<Integer, VoteOption>> downVoteOption(@PathVariable Integer id) {
        Map.Entry<Integer, VoteOption> downVotedOption = domainManager.downVoteOption(id);
        return ResponseEntity.ok(downVotedOption);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, VoteOption>> deleteVoteOption(@PathVariable Integer id) {
        Map.Entry<Integer, VoteOption> deletedVoteOption = domainManager.deleteVoteOption(id);
        return deletedVoteOption != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
