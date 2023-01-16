package jpabook.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {

    public NotEnoughStockException(){}

    public NotEnoughStockException(String message){
        //super(), 부모 클래스의 생성자를 호출할 때
        super(message);
    }

    public NotEnoughStockException(String message, Throwable cause){
        super(message, cause);
    }

    public NotEnoughStockException(Throwable cause){
        super(cause);
    }
}
