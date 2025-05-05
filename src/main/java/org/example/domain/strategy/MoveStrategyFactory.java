package org.example.domain.strategy;

import org.example.domain.rules.IRules;
import org.example.domain.rules.StandardRules;

public class MoveStrategyFactory {
    public static IMoveStrategy createStandardMoveStrategy() {
        IRules rules = new StandardRules();
        return new StandardMoveStrategy(rules);
    }
}