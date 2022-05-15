package com.tcs.edu.decorator;

import com.tcs.edu.domain.Message;

public class SeverityMessageDecorator implements Decorator{


    @Override
    public Object addTimestamp(Message message) {
        return null;
    }

    /**
     * Перводит уровень сообщения в строку и добавляет соответствущее кол-во "!".
     * Считаем что:
     * MINOR - маловажные события
     * REGULAR - обычные
     * MAJOR - важные
     *
     * @author m.petrukhin
     */
    @Override
    public String severityDecorate(Message message) {
        String severityString = null;
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
}


