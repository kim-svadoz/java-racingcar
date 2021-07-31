package step2.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import step2.enumeration.Operation;

class StringCalculatorTest {

    @ParameterizedTest
    @CsvSource(value = {"1,1,2", "2,-1,1", "-1,-3,-4"}, delimiter = ',')
    @DisplayName("(operate) 두 숫자의 합을 구하기")
    void add(int leftNum, int rightNum, int expectedResult) {

        String operatorSymbol = "+";

        int actualResult = Operation.of(operatorSymbol).operate(leftNum, rightNum);
        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @ParameterizedTest
    @CsvSource(value = {"4,1,3", "-1,3,-4", "-1,-3,2"}, delimiter = ',')
    @DisplayName("(operate) 두 숫자의 차를 구하기")
    void subtract(int leftNum, int rightNum, int expectedResult) {

        String operatorSymbol = "-";

        int actualResult = Operation.of(operatorSymbol).operate(leftNum, rightNum);

        assertThat(actualResult).isEqualTo(expectedResult);

    }


    @ParameterizedTest
    @CsvSource(value = {"2,2,4", "-1,3,-3", "-1,-3,3"}, delimiter = ',')
    @DisplayName("(operate) 두 숫자의 곱을 구하기")
    void multiply(int leftNum, int rightNum, int expectedResult) {

        String operatorSymbol = "*";

        int actualResult = Operation.of(operatorSymbol).operate(leftNum, rightNum);

        assertThat(actualResult).isEqualTo(expectedResult);

    }


    @ParameterizedTest
    @CsvSource(value = {"4,2,2", "5,2,2", "-3,-1, 3"}, delimiter = ',')
    @DisplayName("(opearte) 두 숫자를 나눈 몫을 구하기")
    void divide(int leftNum, int rightNum, int expectedResult) {

        String operatorSymbol = "/";

        int actualResult = Operation.of(operatorSymbol).operate(leftNum, rightNum);

        assertThat(actualResult).isEqualTo(expectedResult);

    }

    @ParameterizedTest
    @CsvSource(value = {"4,2", "5,2"}, delimiter = ',')
    @DisplayName("(operate) 알수없는 연산자(@)가 입력된 경우, 예외를 던진다.")
    void unknown_operatorSymbol(int leftNum, int rightNum) {

        String operatorSymbol = "@";

        assertThatThrownBy(() -> {
            Operation.of(operatorSymbol).operate(leftNum, rightNum);
        }).isInstanceOf(IllegalArgumentException.class).hasMessage("사칙연산 기호가 아닙니다.");
    }

    @ParameterizedTest
    @MethodSource("provideoperatorSymbolAndConstants")
    @DisplayName("(of) 사칙연산을 의미하는 문자열이 주어였을때, 올바른 Operation 상수가 반환되는지 확인한다.")
    void check_get_correct_operation_constant(String operatorSymbol, Operation expectedOperation) {

        assertThat(Operation.of(operatorSymbol)).isEqualTo(expectedOperation);
    }

    @ParameterizedTest
    @MethodSource("provideInputArrayAndResult")
    @DisplayName("(cacluate) 유저로부터 받은 문자열을 각종 사칙연산을 이용해 계산해 결과를 낸다.")
    void calculate(String[] userInputArray, int expectedResult) {

        int actualResult = StringCalculator.calculate(userInputArray);
        assertThat(actualResult).isEqualTo(expectedResult);
    }


    private static Stream<Arguments> provideoperatorSymbolAndConstants() {
        return Stream.of(
            Arguments.of("+", Operation.ADD),
            Arguments.of("-", Operation.SUBTRACT),
            Arguments.of("*", Operation.MULTIPLY),
            Arguments.of("/", Operation.DIVIDE)
        );
    }

    private static Stream<Arguments> provideInputArrayAndResult() {

        String[] arr1 = {"1", "+", "1"};
        String[] arr2 = {"4", "-", "2"};
        String[] arr3 = {"1", "*", "5"};
        String[] arr4 = {"6", "/", "2"};
        String[] arr5 = {"2", "+", "3", "*", "4", "/", "2"};

        return Stream.of(
            Arguments.of(arr1, 2),
            Arguments.of(arr2, 2),
            Arguments.of(arr3, 5),
            Arguments.of(arr4, 3),
            Arguments.of(arr5, 10)
        );
    }

}