package com.unitTesting.streamunitTestdemo.model;

public class Validator {
    public boolean validateAccountType ( AccountType accountType){

        if( accountType == AccountType.CURRENT || accountType == AccountType.SAVINGS ){
            return true;
        }
            return false;
    }

}
