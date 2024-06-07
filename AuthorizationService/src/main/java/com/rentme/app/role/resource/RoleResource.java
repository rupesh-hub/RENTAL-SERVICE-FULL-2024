package com.rentme.app.role.resource;

import com.rentme.app.role.model.RoleRequest;
import com.rentme.app.role.model.RoleResponse;
import com.rentme.app.role.service.IRoleService;
import com.rentme.app.util.GlobalResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
@Tag(name = "user_roles")
public class RoleResource {

    private final IRoleService roleService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<GlobalResponse<Void>> save(@RequestBody @Valid RoleRequest request) {
        return ResponseEntity.ok(roleService.save(request));
    }

    @GetMapping("/by.role/{role}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<RoleResponse>> getByRole(@PathVariable String role) {
        return ResponseEntity.ok(roleService.getByRole(role));
    }

    @GetMapping("/by.roleId/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<RoleResponse>> getByRoleId(@PathVariable String roleId) {
        return ResponseEntity.ok(roleService.getByRoleId(roleId));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<List<RoleResponse>>> getAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(roleService.getAll(page, size));
    }

    @PutMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<Boolean>> update(
            @RequestBody final RoleRequest request,
            @PathVariable("roleId") String roleId
    ) {
        return ResponseEntity.ok(roleService.update(request, roleId));
    }

    @DeleteMapping("/{roleId}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<GlobalResponse<Boolean>> delete(
            @PathVariable("roleId") String roleId
    ) {
        return ResponseEntity.ok(roleService.delete(roleId));
    }

}
