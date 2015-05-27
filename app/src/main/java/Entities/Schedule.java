package Entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grand on 27.05.2015.
 */
public class Schedule {
    private static List<Schedule_item> sheduleItems = null;
    public static List<Schedule_item> getInstance(){
        if(sheduleItems == null){
            sheduleItems = new ArrayList<Schedule_item>();
            return sheduleItems;
        }
        else return sheduleItems;
    }
}
