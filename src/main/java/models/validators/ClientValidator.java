package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.ClientView;
import constants.MessageConst;

public class ClientValidator {

    public static List<String> validate(ClientView cv) {
        List<String> errors = new ArrayList<String>();

        //企業名（担当）のチェック
        String companyError = validateCompany(cv.getCompany());
        if (!companyError.equals("")) {
            errors.add(companyError);
        }

        //内容のチェック
        String titleError = validateTitle(cv.getTitle());
        if (!titleError.equals("")) {
            errors.add(titleError);
        }

        return errors;
    }

    private static String validateCompany(String company) {
        if (company == null || company.equals("")) {
            return MessageConst.E_COMPANY.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }

    private static String validateTitle(String title) {
        if (title == null || title.equals("")) {
            return MessageConst.E_TITLE.getMessage();
        }

        //入力値がある場合は空文字を返却
        return "";
    }
}