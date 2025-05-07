package org.example.controller;

/**
 * Интерфейс, через который View взаимодействует с контроллером игры.
 * Определяет события (ход по лунке, новая игра) и методы доступа к состоянию.
 */
public interface IGameController {

    /**
     * Обработать клик по лунке.
     *
     * @param holeIndex  номер лунки (1–9)
     * @param playerSide true для нижнего ряда (игрока), false для верхнего (оппонента)
     */
    void onHoleClicked(int holeIndex, boolean playerSide);

    /**
     * Запустить новую игру (сбросить состояние).
     */
    void onNewGame();

    /**
     * @return текущий игрок (0 — нижний/белый, 1 — верхний/чёрный)
     */
    int getCurrentPlayer();

    /**
     * @return результат игры (0 — победил игрок 0, 1 — победил игрок 1, -1 — ещё не завершена)
     */
    int getGameResult();

    /**
     * Получить количество семян в указанной лунке текущего игрока.
     *
     * @param holeIndex номер лунки (1–9)
     */
    int getHoleCount(int holeIndex);

    /**
     * Получить количество семян в указанной лунке оппонента.
     *
     * @param holeIndex номер лунки (1–9)
     */
    int getOpponentHoleCount(int holeIndex);

    /**
     * @param playerColor цвет игрока (0 или 1)
     * @return число семян в казане соответствующего игрока
     */
    int getKazan(int playerColor);

    /**
     * @param playerColor цвет игрока (0 или 1)
     * @return индекс его туздыка (1–9) или 0 если нет
     */
    int getTuzdyk(int playerColor);

    /**
     * @return true, если игра завершена
     */
    boolean isFinished();
}
