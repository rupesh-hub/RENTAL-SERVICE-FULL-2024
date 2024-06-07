package com.rentme.app.user.resource;

import com.rentme.app.user.model.RegistrationRequest;
import com.rentme.app.user.model.UserResponse;
import com.rentme.app.user.service.IUserService;
import com.rentme.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "users")
public class UserResource {

    private final IUserService userService;


    @GetMapping("/by.id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<UserResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/by.userId/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<UserResponse>> getByUserId(@PathVariable String userId) {
        return ResponseEntity.ok(userService.findByUserId(userId));
    }

    @GetMapping("/by.username/{username}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<UserResponse>> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.findByUsername(username));
    }

    @GetMapping("/by.email/{email}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<UserResponse>> getByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    @PutMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<Boolean>> update(
            @RequestBody final RegistrationRequest request,
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(userService.update(request, userId));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<Boolean>> delete(
            @PathVariable("userId") String userId
    ) {
        return ResponseEntity.ok(userService.delete(userId));
    }

    @PutMapping("/assign.roles")
    public ResponseEntity<GlobalResponse<Boolean>> assignRole(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "roleIds") String[] roleIds
    ) {
        return ResponseEntity.ok(userService.assignRole(userId, roleIds));
    }

    @PutMapping("/remove.roles")
    public ResponseEntity<GlobalResponse<Boolean>> unAssignRole(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "roleIds") String[] roleIds
    ) {
        return ResponseEntity.ok(userService.removeRole(userId, roleIds));
    }

}
