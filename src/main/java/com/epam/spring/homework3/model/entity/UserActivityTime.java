package com.epam.spring.homework3.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.Duration;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_activity_time")
public class UserActivityTime {

    @EmbeddedId
    UserActivityTimeKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("activityId")
    @JoinColumn(name = "activity_id")
    private Activity activity;

    private String duration;
}
