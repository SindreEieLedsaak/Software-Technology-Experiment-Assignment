package DAT250.Project.Rest;

import DAT250.Project.Domainclasses.DomainManager;
import DAT250.Project.Domainclasses.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final DomainManager domainManager;

    @Autowired
    public UserController(DomainManager domainManager) {
        this.domainManager = domainManager;
    }

    @PostMapping
    public ResponseEntity<Map.Entry<Integer, User>> createUser(@RequestBody User user) {
        Map.Entry<Integer, User> userMap = domainManager.addUser(user);
        return ResponseEntity.ok(userMap);
    }

    @GetMapping
    public ResponseEntity<Map<Integer, User>> getAllUsers() {

        return ResponseEntity.ok(domainManager.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, User>> getUser(@PathVariable Integer id) {
        Map.Entry<Integer, User> user = domainManager.getUser(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map.Entry<Integer, User>> updateUser(@PathVariable Integer id, @RequestBody User user) {
        Map.Entry<Integer, User> updatedUser = domainManager.updateUser(id, user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        Map.Entry<Integer, User> deletedUser = domainManager.deleteUser(id);
        return deletedUser != null ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
