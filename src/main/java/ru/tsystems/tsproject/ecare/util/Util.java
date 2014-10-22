package ru.tsystems.tsproject.ecare.util;

import ru.tsystems.tsproject.ecare.ECareException;
import ru.tsystems.tsproject.ecare.service.ClientService;
import ru.tsystems.tsproject.ecare.service.IClientService;

import java.util.Date;

/**
 * Created by Selvin
 * on 21.10.2014.
 */
public class Util {
    private static IClientService clientService = ClientService.getInstance();

    public static Integer checkInt(String s) throws ECareException {
        try {
            int intDigit = Integer.valueOf(s);
            if (intDigit > 0) return intDigit;
            else {
                ECareException ecx = new ECareException("Entered the wrong data format.");
                throw ecx;
            }
        }catch (NumberFormatException nfx) {
            ECareException ecx = new ECareException("Entered the wrong data format.", nfx);
            throw ecx;
        }
    }

    public static Long checkLong(String s) throws ECareException {
        try {
            long longDigit = Long.valueOf(s);
            if (longDigit > 0) return longDigit;
            else {
                ECareException ecx = new ECareException("Entered the wrong data format.");
                throw ecx;
            }
        }catch (NumberFormatException nfx) {
            ECareException ecx = new ECareException("Entered the wrong data format.", nfx);
            throw ecx;
        }
    }

    public static Date checkDate(String s) throws ECareException {
        try {
            return java.sql.Date.valueOf(s);
        } catch (IllegalArgumentException iax) {
            ECareException ecx = new ECareException("Entered the wrong format of Date.", iax);
            throw ecx;
        }
    }

    public static String checkPassword(String password1, String password2) throws ECareException {
        if(password1.equals(password2)) return password1;
        else {
            ECareException ecx = new ECareException("Entered passwords do not match.");
            throw ecx;
        }
    }

    public static Long checkNumberOnExisting(String s) throws ECareException {
        try {
            long number = checkLong(s);
            try {
                clientService.findClientByNumber(number);
            } catch (ECareException ecx) {
                return number;
            }
            ECareException ecx = new ECareException("Entered telephone number already exist.");
            throw ecx;
        } catch (NumberFormatException nfx) {
            ECareException ecx = new ECareException("Wrong format of entered telephone number.", nfx);
            throw ecx;
        }
    }
}
