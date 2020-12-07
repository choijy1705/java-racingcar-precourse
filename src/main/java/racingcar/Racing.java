package racingcar;

import View.OutputView;
import utils.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Racing {
    private static final String DELIMITER = ",";
    private static final int START_NUMBER = 0;
    private static final int END_NUMBER = 9;

    private List<Car> cars = new ArrayList<>();
    private int tryNumber;

    public void init(Scanner scanner) {
        saveCarName(scanner);
        saveTryNumber(scanner);
        raceStart();
        whoIsWinner();
    }

    private void whoIsWinner() {
        int max = cars.stream()
                .map(car -> car.getPosition())
                .max(Integer::compareTo)
                .get();
        List<String> winners = cars.stream()
                .filter(car -> car.isMax(max))
                .map(Car::getName)
                .collect(Collectors.toList());
        OutputView.theWinner(winners);
    }

    private void raceStart() {
        OutputView.gameResult();
        for(int i = 0;i<tryNumber;i++){
            racing();
        }
    }

    private void racing() {
        for(Car car : cars){
            car.goOrStop(RandomUtils.nextInt(START_NUMBER, END_NUMBER));
        }
        OutputView.gameStatus(cars);

    }

    private void saveTryNumber(Scanner scanner) {
        OutputView.writeTryNumber();
        Number number = new Number(scanner.next());
        tryNumber = number.getTryNumber();
    }

    public void saveCarName(Scanner scanner) {
        OutputView.writeCarName();
        String temp = scanner.next();
        String[] names = temp.split(DELIMITER);
        for (String name : names) {
            cars.add(new Car(name));
        }
    }

}
