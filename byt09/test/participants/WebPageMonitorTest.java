package participants;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.*;
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

        observers = IntStream.range(0, urlCount * observersPerUrl)
                .mapToObj(i -> new WebPageObserver(String.valueOf(i)))
                .collect(Collectors.toList());

        responses = LongStream.range(System.currentTimeMillis(), System.currentTimeMillis() + 100)
                .boxed()
                .toArray(Long[]::new);

        urls = LongStream.range(1, 1 + urlCount)
                .mapToObj(this::mockURL)
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
        observers.forEach(o -> Assert.assertNull(o.getLastModified()));
        for (URL url : urls) {
            updateLastModified.invoke(monitor, url);
        }
        /*
         * checking if all of the dates were set to some value after updating
         * */
        observers.forEach(o -> Assert.assertNotNull(o.getLastModified()));
        /*
         *
         * */
//        for (Long response : responses) {
            for (URL url : urls) {
                updateLastModified.invoke(monitor, url);
            }
            for (IObserver o : observers) {
                Assert.assertEquals((long)responses[0], o.getLastModified().getTime());
            }
//        }
    }

    private URL mockURL(long num) {
        String path = "http://google" + num + ".com";

        //mocking HttpURLConnection by URLStreamHandler since URL class is final and cannot be mocked
        HttpURLConnection mockCon = mock(HttpURLConnection.class);
        when(mockCon.getLastModified()).thenReturn(responses[0], responses);

        URLStreamHandler stubURLStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL u) throws IOException {
                return mockCon;
            }
        };

        try {
            return new URL(null, path, stubURLStreamHandler);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

}