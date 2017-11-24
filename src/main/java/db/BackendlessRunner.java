package db;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.ero.cweather.models.task;

public class BackendlessRunner {
    public static void main(String[] args) {
//        HashMap task = new HashMap();
//        task.put("title", "Помыть машину");
//        task.put("min_wind_speed", "0.0");
//        task.put("max_wind_speed", "15.5");
//        task.put("min_temp", "21.0");
//        task.put("max_temp", "30.5");
//        task.put("is_raining", );
//        task.put("is_cloudy", );
//        task.put("is_finished", );
//
//        Backendless.initApp("D8F33248-2281-9701-FF95-E6D737E37900", "AEAADC5C-EE33-858B-FF15-7EA0EA25C300");
//
//        // save object synchronously
//        Map savedTask = Backendless.Persistence.of("Contact").save(task);
//
//        // save object asynchronously
//        Backendless.Persistence.of("task").save(task, new AsyncCallback<Map>() {
//            public void handleResponse(Map response) {
//                // new Contact instance has been saved
//                System.out.println("Success");
//            }
//
//            public void handleFault(BackendlessFault fault) {
//                // an error has occurred, the error code can be retrieved with fault.getCode()
//                System.out.println(fault.toString());
//            }
//        });

        Backendless.initApp("D8F33248-2281-9701-FF95-E6D737E37900", "AEAADC5C-EE33-858B-FF15-7EA0EA25C300");
        task task = new task();
        task.setIs_cloudly(true);
        task.setIs_finished(false);
        task.setIs_raining(false);
        task.setMax_temp(30);
        task.setMin_temp(21);
        task.setMax_wind_speed(7);
        task.setMin_wind_speed(0);
        task.setTitle("Погулять");
        task.setDuration(5);

        Backendless.Data.of(task.class).save(task, new AsyncCallback<task>() {
            @Override
            public void handleResponse(com.ero.cweather.models.task response) {
                System.out.println("Success");
            }

            @Override
            public void handleFault(BackendlessFault fault) {
                System.out.println("Error: " + fault.toString());
            }
        });
    }
}
