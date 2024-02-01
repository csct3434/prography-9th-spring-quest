package prography.pingpong.room.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prography.pingpong.common.response.ApiResponse;
import prography.pingpong.room.dto.CreateRoomRequest;
import prography.pingpong.room.dto.FindAllRoomsCommand;
import prography.pingpong.room.dto.FindAllRoomsResponse;
import prography.pingpong.room.service.CreateRoomService;
import prography.pingpong.room.service.FindAllRoomsService;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final CreateRoomService createRoomService;
    private final FindAllRoomsService findAllRoomsService;

    @PostMapping("/room")
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody CreateRoomRequest request) {
        createRoomService.doService(request.toCommand());
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping
    public ResponseEntity<ApiResponse<FindAllRoomsResponse>> findAll(
        @RequestParam("size") int pageSize,
        @RequestParam("page") int pageNumber
    ) {
        FindAllRoomsCommand command = new FindAllRoomsCommand(pageSize, pageNumber);
        FindAllRoomsResponse result = findAllRoomsService.doService(command);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

}
