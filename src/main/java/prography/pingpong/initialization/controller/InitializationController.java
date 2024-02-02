package prography.pingpong.initialization.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prography.pingpong.common.response.ApiResponse;
import prography.pingpong.initialization.dto.InitializeRequest;
import prography.pingpong.initialization.service.InitializeService;

@RestController
@RequiredArgsConstructor
public class InitializationController {

    private final InitializeService initializeService;

    @PostMapping("/init")
    public ResponseEntity<ApiResponse<Void>> init(@Valid @RequestBody InitializeRequest request) {
        initializeService.doService(request.buildCommand());
        return ResponseEntity.ok(ApiResponse.success());
    }
}
