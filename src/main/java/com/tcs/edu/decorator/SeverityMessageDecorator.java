package com.tcs.edu.decorator;

public class SeverityMessageDecorator {

    /**
     * Перводит уровень сообщения в строку и добавляет соответствущее кол-во "!".
     * Считаем что MINOR маловажные события
     * REGULAR - обычные
     * MAJOR - важные
     *
     * @param Severity Важность сообщения
     * @author m.petrukhin
     */
    public static String severityDecorate(Severity Severity) {
        String severityString = null;
        switch (Severity) {
            case MINOR:
                severityString = "()";
                break;
            case REGULAR:
                severityString = "(!)";
                break;
            case MAJOR:
                severityString = "(!!!)";
                break;
            default:
                severityString = "";
        }
        return severityString;
    }
}
