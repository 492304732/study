package bean;

import java.util.List;

/**
 * @Description: TODO
 * @author: 01369674
 * @date: 2018/5/7
 */
public class ServiceAPI extends MyAPI{
    private List<MethodAPI> methods;

    public List<MethodAPI> getMethods() {
        return methods;
    }

    public void setMethods(List<MethodAPI> methods) {
        this.methods = methods;
    }
}
