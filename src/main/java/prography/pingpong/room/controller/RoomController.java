package prography.pingpong.room.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prography.pingpong.common.response.ApiResponse;
import prography.pingpong.room.dto.AttendRoomRequest;
import prography.pingpong.room.dto.CreateRoomRequest;
import prography.pingpong.room.dto.FindAllRoomsCommand;
import prography.pingpong.room.dto.FindAllRoomsResponse;
import prography.pingpong.room.dto.FindRoomDetailsCommand;
import prography.pingpong.room.dto.FindRoomDetailsResponse;
import prography.pingpong.room.dto.LeaveRoomRequest;
import prography.pingpong.room.service.AttendRoomService;
import prography.pingpong.room.service.CreateRoomService;
import prography.pingpong.room.service.FindAllRoomsService;
import prography.pingpong.room.service.FindRoomDetailsService;
import prography.pingpong.room.service.LeaveRoomService;

@RestController
@RequiredArgsConstructor
public class RoomController {

    private final LeaveRoomService leaveRoomService;
    private final AttendRoomService attendRoomService;
    private final CreateRoomService createRoomService;
    private final FindAllRoomsService findAllRoomsService;
    private final FindRoomDetailsService findRoomDetailsService;

    @PostMapping("/room")
    public ResponseEntity<ApiResponse<Void>> create(@Valid @RequestBody CreateRoomRequest request) {
        createRoomService.doService(request.buildCommand());
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/room")
    public ResponseEntity<ApiResponse<FindAllRoomsResponse>> findAll(
        @RequestParam("size") int pageSize,
        @RequestParam("page") int pageNumber
    ) {
        FindAllRoomsCommand command = new FindAllRoomsCommand(pageSize, pageNumber);
        FindAllRoomsResponse result = findAllRoomsService.doService(command);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<ApiResponse<FindRoomDetailsResponse>> findDetails(@PathVariable("roomId") int roomId) {
        FindRoomDetailsCommand command = new FindRoomDetailsCommand(roomId);
        FindRoomDetailsResponse result = findRoomDetailsService.doService(command);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    @PostMapping("/room/attention/{roomId}")
    public ResponseEntity<ApiResponse<Void>> attend(
        @PathVariable("roomId") int roomId,
        @RequestBody AttendRoomRequest request
    ) {
        attendRoomService.doService(request.buildCommand(roomId));
        return ResponseEntity.ok(ApiResponse.success());
    }

    @PostMapping("/room/out/{roomId}")
    public ResponseEntity<ApiResponse<Void>> leave(
        @PathVariable("roomId") int roomId,
        @RequestBody LeaveRoomRequest request
    ) {
        leaveRoomService.doService(request.buildCommand(roomId));
        return ResponseEntity.ok(ApiResponse.success());
    }

}
