package org.bs.rental.exception;

public class DuplicateKeyException {

    // 중복 에러
    public static class duplicateKey extends RuntimeException{

        public duplicateKey(String msg) {
            super(msg); 
        }

    }
    
}
