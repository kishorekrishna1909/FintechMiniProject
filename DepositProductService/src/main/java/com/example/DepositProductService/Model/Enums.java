package com.example.DepositProductService.Model;

public class Enums {

    public enum DepositProductType{
        SAV, //SAVINGS ACCOUNT
        CUR,  //CURRENT ACCOUNT
        FIX   //FIXED ACCOUNT
    }

    public enum Currency{
        INR,
        JPN
    }
    
    public enum ProductCode{
        SAVINGS_BASIC,      //Basic savings with no/min balance
        SAVINGS_STUDENT,	//Student savings account
        CURRENT_STANDARD,   //Basic current account for businesses
        FIXED_DEPOSIT_STD,	//Standard fixed deposit
        FIXED_DEPOSIT_HIGH	//FD with high interest, longer term
    }

    public enum Status{
        ACTIVE,
        INACTIVE
    }
}
