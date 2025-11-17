package com.dryfruits.dryfruitsbackend.controller;

import com.dryfruits.dryfruitsbackend.dto.SaveUserJsonRequest;
import com.dryfruits.dryfruitsbackend.dto.SaveUserResponse;
import com.dryfruits.dryfruitsbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Accepts multipart/form-data:
     * - name (String)
     * - phoneNo (String)
     * - type (String) optional ("PERSONAL" or "BUSINESS")
     * - profileImage (file) optional
     *
     * Or you may POST JSON with `base64Image` field (see alternative endpoint below).
     */
    @PostMapping(value = "/saveProfile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<SaveUserResponse> saveProfile(
            @RequestParam String name,
            @RequestParam String phoneNo,
            @RequestParam(required = false) String type,
            @RequestPart(name = "profileImage", required = false) MultipartFile profileImage,
            @RequestParam(name = "base64Image", required = false) String base64Image
    ) {
        SaveUserResponse resp = userService.saveUserProfile(name, phoneNo, type, profileImage, base64Image);
        if (resp.isSuccess()) return ResponseEntity.ok(resp);
        return ResponseEntity.badRequest().body(resp);
    }

    /**
     * Alternative: Accept JSON (application/json) with base64 image string.
     * Example payload:
     * { "name":"John", "phoneNo":"123", "type":"personal", "base64Image":"data:image/png;base64,..." }
     */
    @PostMapping(value = "/saveProfileJson", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SaveUserResponse> saveProfileJson(@RequestBody SaveUserJsonRequest req) {
        SaveUserResponse resp = userService.saveUserProfile(req.getName(), req.getPhoneNo(), req.getType(), null, req.getBase64Image());
        if (resp.isSuccess()) return ResponseEntity.ok(resp);
        return ResponseEntity.badRequest().body(resp);
    }

}
