package nextstep.subway.path.application;

import nextstep.subway.path.dto.PathRequest;
import nextstep.subway.path.dto.PathResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PathService {

    @Transactional(readOnly = true)
    public PathResponse findPathBetween(PathRequest pathRequest) {
        // TODO implement
        return null;
    }
}
