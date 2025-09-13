package studentLife.demo.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import java.time.Instant;

@Service
public class BaseService {


    protected Instant getCurrentTimeInstant() {
        return Instant.now();
    }
}
