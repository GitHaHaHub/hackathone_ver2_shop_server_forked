// package com.kimh.spm;


// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.http.ResponseEntity;
// import java.util.List;
// import com.kimh.spm.model.User;
// import com.kimh.spm.service.UserHandler;
// import org.springframework.beans.factory.annotation.Autowired;

// @RestController
// public class RestTest {

//     @Autowired
//     private UserHandler userHandler;

//     @GetMapping("/RestTest")
//     public ResponseEntity<List<User>> viewAllUsers() {
//         return ResponseEntity.ok(userHandler.allUsers());
//     }
// }