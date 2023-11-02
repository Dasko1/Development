package com.browserstack.single.pages;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageListResult;
import com.mailosaur.models.SearchCriteria;

import java.io.IOException;

public class MailosaurEmailClient {

    private static final String API_KEY = "OicbHO31FASngd1";
    private static final String SERVER_ID = "qgowwpz9";
    private MailosaurClient mailosaurClient;

    public MailosaurEmailClient() {
        this.mailosaurClient = new MailosaurClient(API_KEY);
    }

    public Message getEmailBySubject(String subject) throws MailosaurException, IOException {
        var searchCriteria = new SearchCriteria();
        searchCriteria.withSubject(subject);

        return mailosaurClient.messages().get(SERVER_ID, searchCriteria);
    }

    public MessageListResult getEmailsBySubject(String subject) throws MailosaurException, IOException {
        var searchCriteria = new SearchCriteria();
        searchCriteria.withSubject(subject);

        return mailosaurClient.messages().search(SERVER_ID, searchCriteria);
    }
}
