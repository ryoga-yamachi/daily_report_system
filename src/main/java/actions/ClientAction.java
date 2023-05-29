package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.ClientView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import constants.MessageConst;
import services.ClientService;

public class ClientAction extends ActionBase {

    private ClientService service;

    @Override
    public void process() throws ServletException, IOException {

        service = new ClientService();

        invoke();
        service.close();
    }

    public void index() throws ServletException, IOException {

        int page = getPage();
        List<ClientView> clients = service.getAllPerPage(page);

        long clientsCount = service.countAll();

        putRequestScope(AttributeConst.CLIENTS, clients);
        putRequestScope(AttributeConst.CLI_COUNT, clientsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE);


        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }


        forward(ForwardConst.FW_CLI_INDEX);
    }

    public void entryNew() throws ServletException, IOException {

        putRequestScope(AttributeConst.TOKEN, getTokenId());


        forward(ForwardConst.FW_CLI_NEW);

    }

    public void create() throws ServletException, IOException {

        if (checkToken()) {


            ClientView cv = new ClientView(
                    null,
                    getRequestParam(AttributeConst.CLI_COMPANY),
                    getRequestParam(AttributeConst.CLI_TITLE),
                    toNumber(getRequestParam(AttributeConst.CLI_PROGRESS)),
                    getRequestParam(AttributeConst.CLI_CONTENT),
                    null,
                    null,
                    getRequestParam(AttributeConst.CLI_EMPLOYEE));

            List<String> errors = service.create(cv);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.REPORT, cv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_CLI_NEW);

            } else {

                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());

                redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void show() throws ServletException, IOException {

        ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

        if (cv == null) {
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.CLIENT, cv);

            forward(ForwardConst.FW_CLI_SHOW);
        }
    }

    public void edit() throws ServletException, IOException {

        ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

        if (cv == null) {
            forward(ForwardConst.FW_ERR_UNKNOWN);

        } else {

            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.CLIENT, cv);

            forward(ForwardConst.FW_CLI_EDIT);
        }

    }

    public void update() throws ServletException, IOException {

        if (checkToken()) {

            ClientView cv = service.findOne(toNumber(getRequestParam(AttributeConst.CLI_ID)));

            cv.setCompany(getRequestParam(AttributeConst.CLI_COMPANY));
            cv.setTitle(getRequestParam(AttributeConst.CLI_TITLE));
            cv.setProgress(toNumber(getRequestParam(AttributeConst.CLI_PROGRESS)));
            cv.setContent(getRequestParam(AttributeConst.CLI_CONTENT));
            cv.setEmployee(getRequestParam(AttributeConst.CLI_EMPLOYEE));

            List<String> errors = service.update(cv);

            if (errors.size() > 0) {

                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.CLIENT, cv);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_CLI_EDIT);
            } else {

                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                redirect(ForwardConst.ACT_CLI, ForwardConst.CMD_INDEX);

            }
        }
    }

}
