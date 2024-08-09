package lk.ijse.pos_backend.bo;

import lk.ijse.pos_backend.bo.custom.impl.CustomerBoImpl;
import lk.ijse.pos_backend.bo.custom.impl.ItemBoImpl;
import lk.ijse.pos_backend.bo.custom.impl.OrderBoImpl;

public class BoFactory {
    private static BoFactory boFactory;

    private BoFactory() {
    }

    public static BoFactory getInstance() {
        return (boFactory == null) ? (boFactory = new BoFactory()) : (boFactory);
    }

    public enum BoType {
        CUSTOMER, ITEM, ORDER
    }

    public <T> T getBo(BoType boType) {
        switch (boType) {
            case CUSTOMER:
                return (T) new CustomerBoImpl();
            case ITEM:
                return (T) new ItemBoImpl();
            case ORDER:
                return (T) new OrderBoImpl();
            default:
                return null;
        }
    }
}
