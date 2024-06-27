package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityDto {
    private String activity;
    private double availability;
    private String type;
    private int participants;
    private double price;
    private String accessibility;
    private String duration;
    private boolean kidFriendly;
    private String link;
    private String key;
}
