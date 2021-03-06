package com.lisitao.broadcastbestpractice;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

//该类用于管理所有的活动
public class ActivityController {

    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activities.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activities.add(activity);
    }

    public static void finishAll() {
        for (Activity activity: activities) {
            if(!activity.isFinishing()) {
                activity.finish();
            }
        }
        activities.clear();
    }
}
