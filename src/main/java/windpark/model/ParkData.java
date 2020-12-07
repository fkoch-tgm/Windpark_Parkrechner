package windpark.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO doc
 *
 * @author Felix Koch
 * @version 2020-12-07
 */
public class ParkData {
    private Map<String, List<WindengineData>> datastore;

    public ParkData() {
        datastore = new HashMap<>();
    }

    public List<WindengineData> getStore(String id) {
        if (!datastore.containsKey(id)) {
            datastore.put(id,new ArrayList<>());
        }
        return datastore.get(id);
    }
}
