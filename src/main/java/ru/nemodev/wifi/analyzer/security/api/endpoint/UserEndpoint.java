package ru.nemodev.wifi.analyzer.security.api.endpoint;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nemodev.wifi.analyzer.security.entity.user.User;

import java.util.List;

@RestController
@RequestMapping(value = "/v1/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api("Users information")
public class UserEndpoint {

    @GetMapping
    @ApiOperation(value = "Find by params")
    public ResponseEntity<List<User>> findBy(@RequestParam(value = "page", required = false) Integer page,
                                             @RequestParam(value = "size", required = false) Integer size) {
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Find user by id")
    public ResponseEntity<User> findById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    @ApiOperation(value = "Create user")
    public ResponseEntity create(@RequestBody User user) {
        return null;
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete by id")
    public ResponseEntity deleteById(@PathVariable("id") String id) {
        return null;
    }

}
