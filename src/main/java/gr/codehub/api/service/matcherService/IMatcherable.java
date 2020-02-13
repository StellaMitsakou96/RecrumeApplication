package gr.codehub.api.service.matcherService;

import gr.codehub.api.dto.MatcherDTO;
import gr.codehub.api.model.Matcher;

public interface IMatcherable {
    Matcher getManualMatch(MatcherDTO matcherDTO1);

    Matcher getAutomatedMatch(MatcherDTO matcherDTO1);

    Matcher updateOne(int id, MatcherDTO matcherDTO);

    String finalizedMatch(int id);

    String finalizedAutomatedMatch(int id);
}
