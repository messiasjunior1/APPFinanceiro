package com.utfpr.APPFinanceiro.model.contract;

import android.provider.BaseColumns;

public final class BalanceContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private BalanceContract() {}

    /* Inner class that defines the table contents */
    public static class BalanceEntry implements BaseColumns {
        public static final String TABLE_NAME = "balance";
        public static final String COLUMN_BALANCE_DESC = "desc";
        public static final String COLUMN_BALANCE_VALUE = "value";
        public static final String COLUMN_BALANCE_DATE = "date";
        public static final String COLUMN_BALANCE_TYPE = "type";
    }
}