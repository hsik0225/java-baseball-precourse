package baseball;

import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import baseball.domain.FixedNumbersGenerator;
import baseball.domain.Judgment;

public class BaseballGameTest {

    private BaseballGame baseballGame;

    @BeforeEach
    public void initObjects() {
        final Scanner scanner = new Scanner(System.in);
        baseballGame = new BaseballGame(scanner, new FixedNumbersGenerator("123"));
    }

    @Test
    @DisplayName("사용자가 입력한 숫자가 정답일 경우의 테스트")
    public void answerTest() {
        baseballGame.setRound("123");
        baseballGame.startRound();

        assertThat(baseballGame.outputView.getResult(baseballGame.scoreBoard))
                .isEqualTo(BaseballGame.BALLS_LENGTH + Judgment.STRIKE.getMessage() +
                        OutputView.GAME_END);
    }

    @Test
    @DisplayName("사용자가 입력한 숫자가 낫싱일 경우의 테스트")
    public void nothingTest() {
        baseballGame.setRound("456");
        baseballGame.startRound();

        assertThat(baseballGame.outputView.getResult(baseballGame.scoreBoard))
                .isEqualTo(Judgment.NOTHING.getMessage());
    }

    @Test
    @DisplayName("사용자가 입력한 숫자가 볼만 존재하는 경우의 테스트")
    public void hasOnlyBallsTest() {
        baseballGame.setRound("531");
        baseballGame.startRound();

        assertThat(baseballGame.outputView.getResult(baseballGame.scoreBoard))
                .isEqualTo(2 + Judgment.BALL.getMessage());
    }

    @Test
    @DisplayName("사용자가 입력한 숫자가 스트라이크만 존재하는 경우의 테스트")
    public void hasOnlyStrikeTest() {
        baseballGame.setRound("124");
        baseballGame.startRound();

        assertThat(baseballGame.outputView.getResult(baseballGame.scoreBoard))
                .isEqualTo(2 + Judgment.STRIKE.getMessage());
    }

    @Test
    @DisplayName("사용자가 입력한 숫자가 스트라이크와 볼이 섞여있는 경우의 테스트")
    public void hasStrikeAndBallTest() {
        baseballGame.setRound("321");
        baseballGame.startRound();

        assertThat(baseballGame.outputView.getResult(baseballGame.scoreBoard))
                .isEqualTo(2 + Judgment.BALL.getMessage() + 1 + Judgment.STRIKE.getMessage());
    }
}
