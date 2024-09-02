package DAT250.Project.Rest;
import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final DomainManager domainManager;

    @Autowired
    public UserController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<HashMap<UUID,User>> createUser(@RequestBody User user) {
        HashMap<UUID,User> userMap = domainManager.addUser(user);
        return ResponseEntity.ok(userMap);
    }
    @GetMapping
    public ResponseEntity<Collection<User>> getAllUsers() {

        return ResponseEntity.ok(domainManager.getAllUsers());
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable UUID id) {
        User user = domainManager.getUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable UUID id, @RequestBody User user) {
        User updatedUser = domainManager.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id) {
        User deletedUser = domainManager.deleteUser(id);
        return deletedUser != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}

