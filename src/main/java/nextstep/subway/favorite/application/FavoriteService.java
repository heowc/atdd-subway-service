package nextstep.subway.favorite.application;

import nextstep.subway.auth.domain.LoginMember;
import nextstep.subway.favorite.domain.Favorite;
import nextstep.subway.favorite.domain.FavoriteRepository;
import nextstep.subway.favorite.dto.FavoriteRequest;
import nextstep.subway.favorite.dto.FavoriteResponse;
import nextstep.subway.member.application.MemberService;
import nextstep.subway.member.domain.Member;
import nextstep.subway.station.application.StationService;
import nextstep.subway.station.domain.Station;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoriteService {

    private final MemberService memberService;
    private final StationService stationService;
    private final FavoriteRepository favoriteRepository;

    public FavoriteService(MemberService memberService, StationService stationService,
                           FavoriteRepository favoriteRepository) {
        this.memberService = memberService;
        this.stationService = stationService;
        this.favoriteRepository = favoriteRepository;
    }

    public FavoriteResponse create(LoginMember loginMember, FavoriteRequest request) {
        Member member = memberService.findById(loginMember.getId());
        Station sourceStation = stationService.findStationById(request.getSource());
        Station targetStation = stationService.findStationById(request.getTarget());
        Favorite favorite = new Favorite(member, sourceStation, targetStation);
        return new FavoriteResponse(favoriteRepository.save(favorite));
    }

    public List<FavoriteResponse> findAll(LoginMember loginMember) {
        return null;
    }

    public void deleteById(LoginMember loginMember, Long id) {

    }
}
