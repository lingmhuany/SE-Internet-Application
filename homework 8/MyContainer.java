import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;


public class MyContainer<T> {
    private ArrayList<Item<T>> list;

    private static final int EXPIRATIONTIME = 1000;  // 一秒过期
    private static final int NUMBER = 4;

    public MyContainer() {
        this.list = new ArrayList<Item<T>>();
        Timer timer = new Timer();
        timer.schedule(new RemindTask(), 2000);  // 两秒遍历查是否过期
    }

    public void put(T t) {
        list.add(new Item<T>(t));
    }

    public T get(){
        T t;
        if (list.size() <= NUMBER) {
            t = list.get(0).getContent();
            list.remove(0);
        } else {
            t = list.get(list.size() - 1).getContent();
            list.remove(list.size() - 1);
        }
        return t;
    }

    class RemindTask extends TimerTask {
        public void run() {
            long now = new Date().getTime();
            Iterator<Item<T>> it = list.iterator();
            while (it.hasNext()) {
                Item i = it.next();
                System.out.println(now);
                System.out.println(i.getTime());
                if (now - i.getTime() >= EXPIRATIONTIME) {
                    it.remove();
                }
            }
        }
    }
}
