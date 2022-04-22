package constant;

import java.util.HashMap;
import java.util.Map;

public class DayOfWeekConstant {
    public static final Map<String, Integer>  DAY_OF_WEEK_MAP = new HashMap<>();

    static {
        DAY_OF_WEEK_MAP.put("Thứ hai", 2);
        DAY_OF_WEEK_MAP.put("Thứ ba", 3);
        DAY_OF_WEEK_MAP.put("Thứ tư", 4);
        DAY_OF_WEEK_MAP.put("Thứ năm", 5);
        DAY_OF_WEEK_MAP.put("Thứ sáu", 6);
        DAY_OF_WEEK_MAP.put("Thứ bảy", 7);
    }
}
