package com.codekittens.thalidomide;

import com.codekittens.thalidomide.client.Client;
import com.codekittens.thalidomide.client.ClientImpl;
import com.codekittens.thalidomide.model.KarmaState;
import com.codekittens.thalidomide.out.KarmaPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        LOG.debug("Thalidomide injected!");

        Client client = new ClientImpl();
        client.init();
        KarmaState karmaState = client.listKarma(50, 0);
        new KarmaPrinter<>().print(karmaState);

//        List<String> commentsIds = client.listCommentsIds();
//        Set<String> commentsSet = new HashSet<>();
//        commentsSet.addAll(commentsIds);
//
//        commentsIds.forEach((String id) -> System.out.println());
//
//        commentsIds.forEach(System.out::println);

        Thread.sleep(1000);
        System.exit(0);
    }

}
