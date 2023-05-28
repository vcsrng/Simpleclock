import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Clock {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private SimpleDateFormat dateFormat;

    public Clock(String dateFormat) {
        this.dateFormat = new SimpleDateFormat(dateFormat);
    }

    public void start() {
        scheduler.scheduleAtFixedRate(new Runnable() {
            public void run() {
                Date date = new Date();
                System.out.println(dateFormat.format(date));
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    public void stop() {
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        Clock clock = new Clock("HH:mm:ss");
        System.out.println("Clock view in 10 secs");
        clock.start();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clock.stop();
    }
}
