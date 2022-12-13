package stream;

import org.junit.jupiter.api.Test;
import player.Player;
import player.PlayerTestHelper;

import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.*;
import static org.assertj.core.api.Assertions.*;

public class StreamShould {

    @Test
    void have_many_collectors() {
        List<Player> scorers = new PlayerTestHelper().getPlayers();

        String result = scorers.stream()
                .collect(teeing(
                        mapping(Player::getGoal, averagingDouble(goal -> goal)),
                        mapping(Player::toString, toSet()),
                        (average, playerInfo) -> playerInfo.stream().map(format(average)).collect(joining(" | "))
                ));

        assertThat(result).isEqualTo("name='Mokhtar Dahari', goal=89/101.2 | name='Ali Daei', goal=109/101.2 | name='Ferenc Puskás', goal=84/101.2 | name='Cristiano Ronaldo', goal=115/101.2");
    }

    private Function<String, String> format(Double average) {
        return pi -> pi.replace("Player{", "").replace("}", "") + "/" + average;
    }
}
