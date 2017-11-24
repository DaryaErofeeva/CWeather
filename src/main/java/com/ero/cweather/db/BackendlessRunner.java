package com.ero.cweather.db;

import com.backendless.Backendless;
import com.ero.cweather.models.task;

import java.util.List;

public class BackendlessRunner {
    public static void main(String[] args) {
        Backendless.initApp("D8F33248-2281-9701-FF95-E6D737E37900", "AEAADC5C-EE33-858B-FF15-7EA0EA25C300");
//        task task = new task();
//        task.setIs_cloudly(true);
//        task.setIs_finished(false);
//        task.setIs_raining(true);
//        task.setMax_temp(30);
//        task.setMin_temp(21);
//        task.setMax_wind_speed(7);
//        task.setMin_wind_speed(0);
//        task.setTitle("Сидеть дома");
//        task.setDuration(1);
//
//        Backendless.Data.of(task.class).save(task, new AsyncCallback<task>() {
//            @Override
//            public void handleResponse(com.ero.cweather.models.task response) {
//                System.out.println("Success");
//            }
//
//            @Override
//            public void handleFault(BackendlessFault fault) {
//                System.out.println("Error: " + fault.toString());
//            }
//        });

        List<task> result = Backendless.Persistence.of(task.class).find();
        for (task t : result)
            System.out.println(t);
    }
}
