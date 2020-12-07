package windpark.parkrechner;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import windpark.model.ParkTextData;

/**
 * TODO doc
 *
 * @author Felix Koch
 * @version 2020-12-07
 */
@RestController
public class Controller {
    @RequestMapping("/")
    public String getIndex() {
        return ParkrechnerApplication.data.data.toString();
    }
}
