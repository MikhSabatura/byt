package participants;

import exceptions.WebPageMonitorException;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WebPageMonitor {

    private static final int SLEEP_TIME = 60 * 1000;

    private Map<URL, Date> _lastModified;
    private Map<URL, Set<IObserver>> _observers;

    {
        _lastModified = new HashMap<>();
        _observers = new HashMap<>();
    }

    public void monitor() {
        while (true) {
            try {
                Thread.sleep(SLEEP_TIME);
                _lastModified.keySet().forEach(this::updateLastModified);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    private void updateLastModified(URL url) {
        Date currLastModified = null;
        try {
            currLastModified = getServerLastModified(url);
        } catch (IOException e) {
            throw new WebPageMonitorException("couldn't load last updated date", e);
        }
        if (!_lastModified.get(url).equals(currLastModified)) {
            _lastModified.put(url, currLastModified);
            notifyObservers(url, currLastModified);
        }
    }

    private Date getServerLastModified(URL url) throws IOException {
        return new Date(url.openConnection().getLastModified());
    }

    private void notifyObservers(URL url, Date newLastModified) {
        _observers.get(url).forEach(o -> o.update(newLastModified));
    }

    public void addMonitoredPage(URL url) {
        try {
            _lastModified.putIfAbsent(url, getServerLastModified(url));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeMonitoredPage(URL url) {
        _lastModified.remove(url);
        _observers.remove(url);
    }

    public void addObserver(URL url, IObserver observer) {
        addMonitoredPage(url);
        _observers.putIfAbsent(url, new HashSet<IObserver>());
        _observers.get(url).add(observer);
    }

    public void removeObserver(URL url, IObserver observer) {
        if (_observers.containsKey(url)) {
            _observers.get(url).remove(observer);
        }
    }
}
