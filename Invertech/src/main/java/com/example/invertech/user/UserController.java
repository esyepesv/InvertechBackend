package com.example.invertech.user;

import com.example.invertech.dto.UserLoggedDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
@CrossOrigin()
public class UserController {
    private final UserService userService;


    @PostMapping("/getUserLoggedData")
    public ResponseEntity<UserLoggedDTO> getUserLoggedData(@RequestBody UserLoggedRequest request) {
        return ResponseEntity.ok(userService.getUserData(request.getEmail()));
    }

    @GetMapping("/prueba")
    public ResponseEntity<String> getPrueba(){
        return ResponseEntity.ok("this endpoint works!");
    }
}
