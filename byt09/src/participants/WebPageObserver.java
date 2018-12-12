package participants;

import java.util.Date;

public class WebPageObserver implements IObserver {

    private Date lastModified;

    @Override
    public void update(Date newLastModified) {
        System.out.println("the web page was modified, new modify date = " + lastModified);
    }
}
