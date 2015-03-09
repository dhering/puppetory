package org.puppetory.connector.query;

import java.util.LinkedList;
import java.util.Queue;

public class Condition {

    private final Value EMPTY_VALUE = new Value("");
    private Queue operation;

    public Condition(){
        this.operation = new LinkedList();
    }

    public Operation where(String value){
        add(new Value(value));
        return new Operation(this);
    }

    public Operation and(String value){
        add(new Value(value));
        return new Operation(this);
    }

    private void add(Value value){
        operation.add(value);
    }

    private void add(Operator operator){
        operation.add(operator);
    }

    public String toString(){
        StringBuffer out = new StringBuffer();

        while (operation.isEmpty() == false){
            out.append(operation.remove().toString());
        }

        return out.toString();
    }

    public class Operation {
        Condition condition;
        Value value;
        String operator;

        private Operation(Condition condition){
            this.condition = condition;
            this.value = condition.EMPTY_VALUE;
            operator = "";
        }

        public Condition equals(String value){
            condition.add(Operator.EQUALS);
            condition.add(new Value(value));
            return condition;
        }
    }

    public enum Operator{
        EQUALS("="),
        GRATER_THAN(">"),
        GRATER_THAN_EQUALS(""),
        LOWER_THAN("<"),
        LOWER_THAN_EQUALS("<=");

        private final String operator;

        Operator(String operator){
             this.operator = operator;
        }

        public String toString(){
            return operator;
        }
    }

    public class Value{

        private final String value;

        public Value(String value){
            this.value = value;
        }

        public String toString(){
            return value;
        }
    }
}
