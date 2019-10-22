package ru.nemodev.wifi.analyzer.security.api.endpoint;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.wifi.analyzer.security.api.dto.user.UserDto;
import ru.nemodev.wifi.analyzer.security.api.dto.user.UserSaveDto;
import ru.nemodev.wifi.analyzer.security.api.processor.UserProcessor;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Users information")
public class UserEndpoint {

    private final UserProcessor userProcessor;

    public UserEndpoint(UserProcessor userProcessor) {
        this.userProcessor = userProcessor;
    }

    @GetMapping
    @ApiOperation(value = "Find by params")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDto>> findBy(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "size", required = false) Integer size) {
        return ResponseEntity.ok(userProcessor.findBy(page, size));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find user by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserDto> findById(@PathVariable("id") String id) {
        return ResponseEntity.of(userProcessor.findById(id));
    }

    @PostMapping
    @ApiOperation(value = "Create user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity create(@RequestBody UserSaveDto user) {
        return ResponseEntity.ok(userProcessor.create(user));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        userProcessor.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
