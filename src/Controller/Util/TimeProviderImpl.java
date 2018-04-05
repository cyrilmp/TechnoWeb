package Controller.Util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimeProviderImpl implements ITimeProvider {

    public Long getCurrentTime() {

        long result = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

        return result;
    }
}
