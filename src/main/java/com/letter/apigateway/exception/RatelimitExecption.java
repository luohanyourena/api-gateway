package com.letter.apigateway.exception;

/**
 * create:luohan
 */
public class RatelimitExecption extends RuntimeException{
   private int code;
  public RatelimitExecption(){
       super("未获取到令牌");
       this.code = 01;
   }
}
