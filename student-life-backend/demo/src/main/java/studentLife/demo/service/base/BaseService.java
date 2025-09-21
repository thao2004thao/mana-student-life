package studentLife.demo.service.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class BaseService {



    protected Instant getCurrentTimeInstant() {
        return Instant.now();
    }
}


