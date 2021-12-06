package nextstep.subway.path.application;

import java.util.List;

import nextstep.subway.line.domain.Line;
import nextstep.subway.path.domain.Path;
import nextstep.subway.station.domain.Station;

public interface PathFinder {

	Path findShortestPath(List<Line> lines, Station departStation, Station arriveStation);
}