package classes;

import java.util.ArrayList;

public class IdGenerator {
    static long id = 0;
    static ArrayList<Long> ids = new ArrayList<>();
    static int i = 0;

    public static long getNextID() {
        long tmp;
        tmp = id;
        ids.add(i, id);
        id++;
        i++;
        return tmp;
    }
}
