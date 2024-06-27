package utils;

import dto.ActivityDto;

import java.util.Comparator;
import java.util.List;

public class Helper {

    public static List<ActivityDto> sortActivityList(List<ActivityDto> activityDtoList){
        LogUtils.info("sorting activity list");
        return activityDtoList.stream()
                .filter(activity -> activity.getPrice() > 0)
                .sorted(Comparator.comparingDouble(ActivityDto::getAvailability))
                .toList();
    }
}
