package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

public class SeverityMessageDecorator implements Decorator {


    /**
     * Перводит уровень сообщения в строку и добавляет соответствущее кол-во "!".
     * Считаем что:
     * MINOR - маловажные события
     * REGULAR - обычные
     * MAJOR - важные
     *
     * @author m.petrukhin
     */

    public String severityDecorate(Message message) {
        String severityString;
        switch (message.getLevel()) {
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

    @Override
    public Object addTimestamp(Message message) {
        return null;
    }
}

