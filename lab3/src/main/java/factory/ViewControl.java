package factory;

public class ViewControl {
    static public void start(View view) {
        Thread thread = new Thread(() -> {
           while(true) {
               try {
                   Thread.sleep(250);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               view.fill();
           }
        });
        thread.start();
    }
}
