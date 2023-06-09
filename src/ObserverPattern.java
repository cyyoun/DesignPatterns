import java.util.ArrayList;
import java.util.List;

interface Youtube {
    void subscribe(Observer obj); //팔로우

    void unsubscribe(Observer obj); //언팔

    void notifyObserve(); //구독자 확인

    String getUpdate(Observer obj);

}

class Creator implements Youtube {

    private List<Observer> observers;
    private String alarm;

    public Creator() {
        this.observers = new ArrayList<>();
        this.alarm = "";
    }

    @Override
    public void subscribe(Observer obj) {
        if (!observers.contains(obj)) observers.add(obj);
    }

    @Override
    public void unsubscribe(Observer obj) {
        if (observers.contains(obj)) observers.remove(obj);
    }

    @Override
    public void notifyObserve() {
        this.observers.forEach(Observer::update);
    }

    @Override
    public String getUpdate(Observer obj) {
        return alarm;
    }

    public void postMsg(String alarm) {
        System.out.println("Notifications 🔔 : " +alarm);
        this.alarm = alarm;
        notifyObserve();
    }
}

interface Observer {
    void update();
}

class Subscriber implements Observer {
    private String name;
    private Creator creator;

    public Subscriber(String name, Creator creator) {
        this.name = name;
        this.creator = creator;
    }

    @Override
    public void update() {
        String alarm = creator.getUpdate(this);
        System.out.println("name = " + name + ", alarm = " + alarm);
    }
}
public class ObserverPattern {
    public static void main(String[] args) {
        Creator creator = new Creator();
        Observer a = new Subscriber("a", creator);
        Observer b = new Subscriber("b", creator);
        creator.subscribe(a);
        creator.subscribe(b);
        creator.postMsg("The new video is fun! Watch it now!");
    }
}
