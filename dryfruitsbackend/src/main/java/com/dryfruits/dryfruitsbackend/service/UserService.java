package com.dryfruits.dryfruitsbackend.service;

import com.dryfruits.dryfruitsbackend.dto.SaveUserResponse;
import com.dryfruits.dryfruitsbackend.model.Type;
import com.dryfruits.dryfruitsbackend.model.User;
import com.dryfruits.dryfruitsbackend.repository.UserRepository;
import com.dryfruits.dryfruitsbackend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final Path uploadDir;

    public UserService(UserRepository userRepository,
                       @Value("${app.upload-dir:uploads}") String uploadDirStr) throws IOException {
        this.userRepository = userRepository;
        this.uploadDir = Paths.get(uploadDirStr).toAbsolutePath().normalize();
        Files.createDirectories(this.uploadDir); // ensure directory exists
    }

    /**
     * Save user profile with an uploaded image file (multipart). Returns DTO with id and profile path.
     */
    @Transactional
    public SaveUserResponse saveUserProfile(String name, String phoneNo, String typeStr, MultipartFile imageFile, String base64Image) {
        try {
            // Create and save user first (without profile path) to get id
            User user = new User();
            user.setName(name);
            user.setPhoneNo(phoneNo);
            user.setType(parseType(typeStr));
            user.setCreatedAt(new Date());
            user.setUpdatedAt(new Date());

            String token = JwtUtil.generateToken(name, phoneNo, typeStr);

            user.setToken(token);

            User saved = userRepository.save(user);

            // If an image file was uploaded, store it and update user's profile path
            String profilePath = null;
            if (imageFile != null && !imageFile.isEmpty()) {
                profilePath = storeFileForUser(saved.getId(), imageFile.getOriginalFilename(), imageFile.getBytes());
            } else if (base64Image != null && !base64Image.isBlank()) {
                // if frontend sends base64 string (data URL), save it too
                byte[] data = extractBase64Data(base64Image);
                String ext = detectImageExtensionFromBase64(base64Image);
                profilePath = storeFileForUser(saved.getId(), "profile." + ext, data);
            }

            if (profilePath != null) {
                saved.setProfile(profilePath);
                saved.setUpdatedAt(new Date());
                userRepository.save(saved);
            }

            return new SaveUserResponse(true, saved.getId(), profilePath, "User saved", token);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new SaveUserResponse(false, null, null, "Failed to save user: " + ex.getMessage(), "");
        }
    }

    private Type parseType(String t) {
        if (t == null) return null;
        try {
            return Type.valueOf(t.toUpperCase());
        } catch (Exception e) {
            return null;
        }
    }

    private String storeFileForUser(Integer userId, String originalFilename, byte[] bytes) throws IOException {
        // sanitize original filename
        String ext = "";
        int dot = originalFilename.lastIndexOf('.');
        if (dot >= 0) ext = originalFilename.substring(dot);
        String filename = "user-" + userId + ext;
        Path target = this.uploadDir.resolve(filename);
        try (FileOutputStream fos = new FileOutputStream(target.toFile())) {
            fos.write(bytes);
        }
        // return relative path (you can return a URL if you serve these files via HTTP)
        return "/uploads/" + filename;
    }

    private byte[] extractBase64Data(String base64DataUrl) {
        String base64 = base64DataUrl.contains(",")
                ? base64DataUrl.split(",", 2)[1]
                : base64DataUrl;
        return Base64.getDecoder().decode(base64);
    }


    private String detectImageExtensionFromBase64(String base64DataUrl) {
        if (base64DataUrl.startsWith("data:image/jpeg")) return "jpg";
        if (base64DataUrl.startsWith("data:image/png")) return "png";
        if (base64DataUrl.startsWith("data:image/webp")) return "webp";
        // default
        return "jpg";
    }
}
