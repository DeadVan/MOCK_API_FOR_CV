package utils;

import dto.ActivityDto;

import java.util.Comparator;
import java.util.List;

public class Helper {

    public static void sortActivityList(List<ActivityDto> activityDtoList){
        LogUtils.info("sorting activity list");
        List<ActivityDto> filteredActivity = activityDtoList.stream()
                .filter(activity -> activity.getPrice() > 0)
                .sorted(Comparator.comparingDouble(ActivityDto::getAvailability))
                .toList();
        LogUtils.info("FILTERED ACTIVITIES ___________\n");
        for (ActivityDto activityDto : filteredActivity){
            LogUtils.info(String.valueOf(activityDto));
        }
    }
}
