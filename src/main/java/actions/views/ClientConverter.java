package actions.views;

import java.util.ArrayList;
import java.util.List;

import constants.AttributeConst;
import constants.JpaConst;
import models.Client;

public class ClientConverter {

    public static Client toModel(ClientView cv) {
        return new Client(
                cv.getId(),
                cv.getCompany(),
                cv.getTitle(),
                cv.getProgress()== null
                        ? null
                        : cv.getProgress() == AttributeConst.PRO_NEGOTIATION.getIntegerValue()
                                ? JpaConst.PRO_NEGOTIATION
                                : cv.getProgress() == AttributeConst.PRO_WIP.getIntegerValue()
                                ? JpaConst.PRO_WIP
                                : JpaConst.PRO_END,
                cv.getContent(),
                cv.getCreatedAt(),
                cv.getUpdatedAt(),
                cv.getEmployee());

    }

    public static ClientView toView(Client c) {

        if(c == null) {
            return null;
        }

        return new ClientView(
                c.getId(),
                c.getCompany(),
                c.getTitle(),
                c.getProgress()== null
                        ? null
                        : c.getProgress() == JpaConst.PRO_NEGOTIATION
                                ? AttributeConst.PRO_NEGOTIATION.getIntegerValue()
                                : c.getProgress() == JpaConst.PRO_WIP
                                ? AttributeConst.PRO_WIP.getIntegerValue()
                                : AttributeConst.PRO_END.getIntegerValue(),
                c.getContent(),
                c.getCreatedAt(),
                c.getUpdatedAt(),
                c.getEmployee());

    }

    public static List<ClientView> toViewList(List<Client> list) {
        List<ClientView> evs = new ArrayList<>();

        for (Client c : list) {
            evs.add(toView(c));
        }

        return evs;
    }

    public static void copyViewToModel(Client c, ClientView cv) {
        c.setId(cv.getId());
        c.setCompany(cv.getCompany());
        c.setTitle(cv.getTitle());
        c.setProgress(cv.getProgress());
        c.setContent(cv.getContent());
        c.setCreatedAt(cv.getCreatedAt());
        c.setUpdatedAt(cv.getUpdatedAt());
        c.setEmployee(cv.getEmployee());
    }
}

