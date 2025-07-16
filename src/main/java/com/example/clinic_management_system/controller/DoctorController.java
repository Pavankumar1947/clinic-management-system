import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/users/{userId}/doctors/{doctorId}/availability/{date}")
    public ResponseEntity<Map<String, Object>> checkDoctorAvailability(
            @PathVariable Long userId,
            @PathVariable Long doctorId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            @RequestParam("token") String token) {

        // Step 1: Validate the token (stubbed logic; customize with your tokenService)
        if (!tokenService.isValid(token, userId)) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("status", "error");
            errorResponse.put("message", "Unauthorized: Invalid token");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }

        // Step 2: Check availability using service
        boolean isAvailable = doctorService.isDoctorAvailableOnDate(doctorId, date);

        // Step 3: Build structured response
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("available", isAvailable);
        response.put("message", isAvailable
                ? "Doctor is available on " + date
                : "Doctor is not available on " + date);

        return ResponseEntity.ok(response);
    }
}
