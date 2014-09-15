package com.codekittens.thalidomide;

import com.codekittens.thalidomide.client.Client;
import com.codekittens.thalidomide.client.ClientImpl;
import com.codekittens.thalidomide.model.WrappedVoteResponse;
import com.codekittens.thalidomide.out.VotesAnalysePrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        LOG.debug("Thalidomide injected!");

//        Client client = new ClientImpl();
//        client.init();
//
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

//        commentsIds.forEach((String id) -> System.out.println());

//        commentsIds.forEach(System.out::println);



//        FileHelper fileHelper = new FileHelper("comments.lst");
//        commentsIds.forEach((String id) -> fileHelper.println(id));
//        fileHelper.close();

        /**
         *Comments votes list test
         * Write it to file
         */
//        List<String> ids = Arrays.asList(AllComments.ALL.split(","));
//
//        List<WrappedVoteResponse> votes = client.listCommentVotes(ids);
//
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//        String json = ow.writeValueAsString(votes);
//
//        FileHelper fileHelper = new FileHelper("list.json");
//        fileHelper.println(json);
//        fileHelper.close();

        /**
         * Read and analyse
         */



        Helper<WrappedVoteResponse> helper = new Helper<>();

        List<WrappedVoteResponse> decoded = helper.read("list.json");

        VotesAnalysePrinter printer = new VotesAnalysePrinter();

        printer.print(decoded);

        Thread.sleep(1000);
        System.exit(0);
    }

}
