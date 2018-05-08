package bean;

import java.util.List;

/**
 * @Description: TODO
 * @author: 01369674
 * @date: 2018/5/7
 */
public abstract class MyAPI {
    protected String name;
    
    protected List<MyAPI> correlations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MyAPI> getCorrelations(List<MyAPI> correlations) {
        return correlations;
    }

    public void setCorrelations(List<MyAPI> correlations) {
        this.correlations = correlations;
    }
}
