package baseball;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Application {
    final static int LENGTH_OF_NUMBER = 3;
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        while (true) {
            System.out.println("숫자 야구 게임을 시작합니다.");

            List<Integer> computer = getRandomNumber();

            while (true) {
                // 사용자의 입력을 받는 부분

                Integer[] userNumber = setUserNumber();

                int numberOfStrike = calculatingStrikeValue(computer,userNumber);
                int numberOfBall = 0;



                if (numberOfStrike == 3) {
                    break;
                }

                for (int i = 0; i < LENGTH_OF_NUMBER; i++) {
                    if (computer.contains(userNumber[i]))
                        numberOfBall++;
                }

                numberOfBall = numberOfBall - numberOfStrike;

                if (numberOfStrike == 0 && numberOfBall != 0) {
                    System.out.println(numberOfBall + "볼");
                } else if (numberOfStrike == 0 && numberOfBall == 0) {
                    System.out.println("낫싱");
                } else if (numberOfBall == 0 && numberOfStrike != 0) {
                    System.out.println(numberOfStrike + "스트라이크");
                } else {
                    System.out.println(numberOfBall + "볼 " + numberOfStrike + "스트라이크");
                }
            }
            System.out.println("3스트라이크");
            System.out.println("3개의 숫자를 모두 맞히셨습니다! 게임 종료");
            System.out.println("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.");
            int input = Integer.parseInt(Console.readLine());
            if (input == 2) {
                break;
            }
        }
    }
    static List<Integer> getRandomNumber(){
        List<Integer> numbers = new ArrayList<>();
        while (numbers.size() < LENGTH_OF_NUMBER) {
            int randomNumber = Randoms.pickNumberInRange(1, 9);
            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }
        return numbers;
    }

    static Integer[] setUserNumber(){
        System.out.print("숫자를 입력해주세요 : ");
        String[] user = Console.readLine().split("");
        if (user.length != 3)
            throw new IllegalArgumentException("숫자의 길이가 큽니다");

        //TODO 무슨 말인지 모르겠다 공부하자 새로운 언어에서의 문법
        return Stream.of(user).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new);
    }

    static int calculatingStrikeValue(List<Integer> computer, Integer[] user){
        int numberOfStrike = 0;

        for (int i = 0; i < LENGTH_OF_NUMBER; i++) {
            if (computer.get(i).equals(user[i]))
                numberOfStrike++;
        }
        return numberOfStrike;
    }
}
