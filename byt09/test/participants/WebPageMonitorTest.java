package participants;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WebPageMonitorTest {

    WebPageMonitor monitor;
    List<IObserver> observers;
    List<URL> urls;
    Long[] responses;

    @Before
    public void setUp() throws Exception {
        int urlCount = 25;
        int observersPerUrl = 2;
        int responseCount = 100;
        String urlFormatString = "http://google%d.com";

        responses = LongStream.range(System.currentTimeMillis(), System.currentTimeMillis() + responseCount)
                .boxed()
                .toArray(Long[]::new);

        observers = IntStream.range(0, urlCount * observersPerUrl)
                .mapToObj(i -> new WebPageObserver(String.valueOf(i)))
                .collect(Collectors.toList());

        urls = LongStream.range(1, 1 + urlCount)
                .mapToObj(i -> String.format(urlFormatString, i))
                .map(this::mockURL)
                .collect(Collectors.toList());

        monitor = new WebPageMonitor();
        urls.forEach(monitor::addMonitoredPage);
        for (int i = 0; i < urls.size(); i++) {
            monitor.addObserver(urls.get(i), observers.get(i * 2));
            monitor.addObserver(urls.get(i), observers.get(i * 2 + 1));
        }
    }

    @After
    public void tearDown() throws Exception {
        observers = null;
        responses = null;
        urls = null;
        monitor = null;
    }

    @Test
    public void updateLastModifiedTest() throws Exception {
        Method updateLastModified = WebPageMonitor.class.getDeclaredMethod("updateLastModified", URL.class);
        updateLastModified.setAccessible(true);
        /*
         * before updating the dates for the first time all of them should be null
         * */
        observers.forEach(o -> Assert.assertEquals((long) responses[0], o.getLastModified().getTime()));
        for (URL url : urls) {
            updateLastModified.invoke(monitor, url);
        }
        /*
         * updating change dates and check if they were set correctly
         * */
        for (int i = 3; i < responses.length; i++) { // starting with 3 because mockito starts returning responses from 3 index
            for (URL url : urls) {
                updateLastModified.invoke(monitor, url);
            }
            for (IObserver o : observers) {
                Assert.assertEquals((long) responses[i], o.getLastModified().getTime());
            }
        }
    }

    private URL mockURL(String urlString) {
        //mocking HttpURLConnection by URLStreamHandler since URL class is final and cannot be mocked
        HttpURLConnection mockConnection = mock(HttpURLConnection.class);
        when(mockConnection.getLastModified()).thenReturn(responses[0], responses);

        URLStreamHandler stubURLStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return mockConnection;
            }
        };

        try {
            return new URL(null, urlString, stubURLStreamHandler);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}