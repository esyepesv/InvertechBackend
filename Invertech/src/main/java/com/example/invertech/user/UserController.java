package com.example.invertech.user;

import com.example.invertech.dto.IsFirstTimeResponse;
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
        System.out.println("/getUserLoggedData");
        return ResponseEntity.ok(userService.getUserData(request.getEmail()));
    }

    @PostMapping("/setUserPremium")
    public ResponseEntity<UserLoggedDTO> setUserPremium(@RequestBody UserLoggedRequest request) {
        System.out.println("/setUserPremium");
        return ResponseEntity.ok(userService.setUserPremium(request.getEmail()));
    }

    @GetMapping("/prueba")
    public ResponseEntity<String> getPrueba(){
        return ResponseEntity.ok("this endpoint works!");
    }

    @PostMapping("/isFirstTime")
    public ResponseEntity<IsFirstTimeResponse> isFirstTime(@RequestBody UserLoggedRequest request){
        System.out.println("/isFirstTime");
        return ResponseEntity.ok(userService.isFirstTime(request.getEmail()));
    }
    @PostMapping("/setFirstTimeFalse")
    public ResponseEntity<IsFirstTimeResponse> setFirstTimeFalse(@RequestBody UserLoggedRequest request){
        System.out.println("/setFirstTimeFalse");
        return ResponseEntity.ok(userService.setFirstTimeFalse(request.getEmail()));
    }
}
