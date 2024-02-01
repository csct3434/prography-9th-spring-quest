package prography.pingpong.monitoring.health;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prography.pingpong.common.response.ApiResponse;

@RestController
@RequestMapping("/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<ApiResponse<Void>> check() {
        return ResponseEntity.ok(ApiResponse.success());
    }
}
