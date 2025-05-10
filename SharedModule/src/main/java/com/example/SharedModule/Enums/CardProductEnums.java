package com.example.SharedModule.Enums;

public class CardProductEnums{

    public enum CardProductType {
        DEBIT,      // Debit card: Withdraws directly from a bank account
        CREDIT,     // Credit card: Allows purchases on credit with repayment later
        VIRTUAL,    // Virtual card: Digital-only, used for online transactions
    }

    public enum NetworkType {
        VISA,        // VISA: Widely accepted international card network
        MASTERCARD,  // MasterCard: Popular global network for debit and credit cards
        RUPAY,       // RuPay: Indian domestic card network
    }

    public enum CardProductCode {
        DEBIT_CLASSIC_VISA,          // Classic Visa debit card
        CREDIT_PLATINUM_VISA,        // Platinum Visa credit card
        DEBIT_SILVER_MASTERCARD,     // Silver MasterCard debit card
        PREPAID_VIRTUAL_RUPAY,       // Virtual RuPay prepaid card
        DEBIT_STUDENT_VISA,          // Student Visa debit card
        CREDIT_TRAVEL_USD_MASTERCARD,// Travel MasterCard with USD support
    }

    public enum Status{
        ACTIVE,
        INACTIVE
    }
}
