package com.codekittens.thalidomide;

import com.codekittens.thalidomide.client.Client;
import com.codekittens.thalidomide.client.ClientImpl;
import com.codekittens.thalidomide.model.VoteResponse;
import com.codekittens.thalidomide.model.WrappedVoteResponse;
import com.codekittens.thalidomide.out.VotesPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        LOG.debug("Thalidomide injected!");

        Client client = new ClientImpl();
        client.init();

        /**
         *Karma list test
         */
//        KarmaResponse karmaState = client.listKarma(50, 0);
//        new KarmaPrinter<>().print(karmaState);

        /**
         *Comments ids list test
         */
//        List<String> commentsIds = client.listCommentsIds();
//        Set<String> commentsSet = new HashSet<>();
//        commentsSet.addAll(commentsIds);
//
//        commentsIds.forEach((String id) -> System.out.println());
//
//        commentsIds.forEach(System.out::println);

        /**
         *Comments votes list test
         */
        List<WrappedVoteResponse> votes = client.listCommentVotes(Arrays.asList(
                "39458333",
                "39457648",
                "39450607",
                "39380941",
                "39348520",
                "39323016",
                "39190537"
        ));
        VotesPrinter<WrappedVoteResponse> votesPrinter = new VotesPrinter<>();

        votesPrinter.print(votes);

        Thread.sleep(1000);
        System.exit(0);
    }

}
