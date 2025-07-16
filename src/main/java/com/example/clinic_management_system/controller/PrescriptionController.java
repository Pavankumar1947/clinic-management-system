import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private TokenService tokenService;

    // POST method to save prescription
    @PostMapping("/users/{userId}/prescriptions")
    public ResponseEntity<Map<String, Object>> savePrescription(
            @PathVariable Long userId,
            @RequestBody @Validated Prescription prescription,
            @RequestParam("token") String token) {

        Map<String, Object> response = new HashMap<>();

        try {
            // Validate token
            if (!tokenService.isValid(token, userId)) {
                response.put("status", "error");
                response.put("message", "Unauthorized: Invalid token");
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
            }

            // Save prescription
            Prescription saved = prescriptionService.savePrescription(prescription);

            response.put("status", "success");
            response.put("message", "Prescription saved successfully");
            response.put("data", saved);

            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            response.put("status", "error");
            response.put("message", "Failed to save prescription: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
