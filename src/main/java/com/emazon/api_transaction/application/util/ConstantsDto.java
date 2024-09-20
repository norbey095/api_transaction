package com.emazon.api_transaction.application.util;

import lombok.Getter;

@Getter
public class ConstantsDto {

   public static final String QUANTITY_NOT_NULL = "The quantity cannot be null";
   public static final String QUANTITY_NOT_NEGATIVE = "The quantity cannot be negative";
   public static final String ID_NOT_NULL = "The id cannot be null";
   public static final int NUMBER_0 = 0;
   public static final String UPDATE_CORRECT = "Se actualizo la cantidad correctamente";

    private ConstantsDto() {

    }
}
