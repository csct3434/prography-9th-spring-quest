package prography.pingpong.room.domain.userroom;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import prography.pingpong.common.entity.BaseTimeEntity;
import prography.pingpong.room.domain.room.Room;
import prography.pingpong.user.domain.User;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoom extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @JoinColumn(nullable = false)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Team team;

    private UserRoom(User user, Room room, Team team) {
        this.user = user;
        this.room = room;
        this.team = team;
    }

    public static UserRoom build(User user, Room room, Team team) {
        return new UserRoom(user, room, team);
    }

    public static UserRoom buildRed(User user, Room room) {
        return new UserRoom(user, room, Team.RED);
    }

    public static UserRoom buildBlue(User user, Room room) {
        return new UserRoom(user, room, Team.BLUE);
    }
}
