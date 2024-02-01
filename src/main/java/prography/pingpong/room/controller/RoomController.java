package prography.pingpong.room.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import prography.pingpong.common.response.ApiResponse;
import prography.pingpong.room.dto.CreateRoomRequest;
import prography.pingpong.room.service.CreateRoomService;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomService createRoomService;

    @PostMapping("/room")
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody CreateRoomRequest request) {
        createRoomService.doService(request.toCommand());
        return ResponseEntity.ok(ApiResponse.success());
    }

}
