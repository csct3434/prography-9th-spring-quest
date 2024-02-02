package prography.pingpong.room.domain.room;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.pingpong.common.entity.BaseTimeEntity;
import prography.pingpong.room.domain.userroom.Team;
import prography.pingpong.room.dto.CreateRoomCommand;
import prography.pingpong.user.domain.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String title;

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private User host;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomStatus status;

    public Room(String title, RoomType roomType, User host, RoomStatus status) {
        this.title = title;
        this.roomType = roomType;
        this.host = host;
        this.status = status;
    }

    public static Room create(CreateRoomCommand command, User host) {
        return new Room(command.getTitle(), command.getRoomType(), host, RoomStatus.WAIT);
    }

    public int getCapacity() {
        return roomType.getCapacity();
    }

    public int getTeamCapacity() {
        return roomType.getCapacity() / Team.values().length;
    }

    public void setFinish() {
        this.status = RoomStatus.FINISH;
    }
}
