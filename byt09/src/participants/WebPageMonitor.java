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

    /**
     * regularly updates information about the modification dates
     */
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

    /**
     * updates last modified date for the passed url and notifies attached observers
     *
     * @param url url for which last modified date will be updated
     */
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

    /**
     * @param url url for which last modified date will be checked
     * @return date when the page under passed url was modified
     */
    private Date getServerLastModified(URL url) throws IOException {
        return new Date(url.openConnection().getLastModified());
    }

    /**
     * @param url             url observers of which will be notified
     * @param newLastModified new date of last modification that will be passed to observers
     */
    private void notifyObservers(URL url, Date newLastModified) {
        _observers.get(url).forEach(o -> o.update(newLastModified));
    }

    /**
     * adds the url to the set of monitored in case it isn't already
     *
     * @param url url of the page which will be added to the set of monitored
     */
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

    /**
     * @param url      url to which the observer will be attached
     * @param observer observer that will be attached to the url
     */
    public void addObserver(URL url, IObserver observer) {
        addMonitoredPage(url);
        _observers.putIfAbsent(url, new HashSet<IObserver>());
        _observers.get(url).add(observer);
    }

    /**
     * @param url      url from which the observer will be dettached
     * @param observer observer that will be detached from the url
     */
    public void removeObserver(URL url, IObserver observer) {
        if (_observers.containsKey(url)) {
            _observers.get(url).remove(observer);
        }
    }
}
